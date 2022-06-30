package com.iknow.usermicroservice.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


public class RegisterUserRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @Size(max = 250)
    private String biography;

}
