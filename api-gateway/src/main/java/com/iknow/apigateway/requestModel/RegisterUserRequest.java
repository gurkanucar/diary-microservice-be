package com.iknow.apigateway.requestModel;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterUserRequest {


    @Column(unique = true)
    @Size(max = 50, min = 3)
    private String username;

    @NotEmpty
    @Size(max = 255)
    @Email
    private String email;

    @NotEmpty
    @NotNull
    @Size(max = 50, min = 3)
    private String name;

    @NotEmpty
    @NotNull
    @Size(max = 50, min = 3)
    private String surname;

    @NotEmpty
    @NotNull
    @Size(max = 50, min = 8)
    private String password;

    @Size(max = 250)
    private String biography;

}
