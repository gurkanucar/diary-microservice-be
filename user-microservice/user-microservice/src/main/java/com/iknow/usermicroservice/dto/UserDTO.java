package com.iknow.usermicroservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class UserDTO {

    private Long id;
    private String username;
    private String email;
    private String name;
    private String surname;
    private String biography;
    private LocalDate createdDate;
    private String profileImageUrl;
    private boolean isActive;
    private List<RoleDTO> roles;

}