package com.iknow.apigateway.config;


import com.iknow.apigateway.config.filter.CustomAuthenticationFilter;
import com.iknow.apigateway.config.filter.CustomAuthorizationFilter;
import com.iknow.apigateway.exception.CustomAccessDeniedHandler;
import com.iknow.apigateway.exception.CustomAuthenticationExceptionHandler;
import com.iknow.apigateway.service.AuthService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.http.HttpMethod.POST;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;
    private final AuthService authService;


    public SecurityConfig(UserDetailsService userDetailsService,
                          PasswordEncoder passwordEncoder,
                          AuthService authService) {
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
        this.authService = authService;
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        CustomAuthenticationFilter customAuthFilter =
                new CustomAuthenticationFilter(authService);
        customAuthFilter.setAuthenticationManager(authenticationManagerBean());

        http.cors().and()
                .authorizeRequests()
                .antMatchers("/loginListener/**").permitAll()
                .antMatchers(POST, "/auth/login","/user", "/auth/register", "/auth/refresh").permitAll()
                .antMatchers("/h2-console/**", "/chat", "/loginListener", "/topic").permitAll()
                .antMatchers("/role/**").hasAnyAuthority("ADMIN", "SUPERADMIN")
                .anyRequest().authenticated()
                .and()
                .exceptionHandling().accessDeniedHandler(accessDeniedHandler())
                .and()
                .addFilterAt(customAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling().authenticationEntryPoint(authenticationExceptionHandler())
                .and()
                .addFilterBefore(new CustomAuthorizationFilter(authService),
                        UsernamePasswordAuthenticationFilter.class)
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().csrf().disable();

    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public CustomAccessDeniedHandler accessDeniedHandler() {
        return new CustomAccessDeniedHandler();

    }

    @Bean
    public CustomAuthenticationExceptionHandler authenticationExceptionHandler() {
        return new CustomAuthenticationExceptionHandler();
    }


}
