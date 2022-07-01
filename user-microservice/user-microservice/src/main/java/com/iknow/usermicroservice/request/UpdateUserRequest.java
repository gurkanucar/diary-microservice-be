package com.iknow.usermicroservice.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class UpdateUserRequest {

    @NotNull
    @Size(max = 50, min = 3)
    private String username;

    @NotNull
    @Email
    private String email;


    @NotNull
    @NotBlank
    private String name;

    @NotNull
    @NotBlank
    private String surname;

    @NotNull
    @Size(max = 50, min = 8)
    private String password;

    @Size(max = 250)
    private String biography;


    private String profileImageUrl;


}
