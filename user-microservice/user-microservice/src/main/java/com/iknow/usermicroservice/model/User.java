package com.iknow.usermicroservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.MERGE;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
public class User implements Serializable {

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
    @Size(max = 50, min = 3)
    private String name;
    @Size(max = 50, min = 3)
    private String surname;
    @NotEmpty
    @Column(length = 60)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    @Size(max = 250)
    private String biography;
    @CreatedDate
    @CreationTimestamp
    private LocalDate createdDate;
    @UpdateTimestamp
    private LocalDate updatedDate;
    @ManyToMany(fetch = FetchType.EAGER, cascade = MERGE)
    private List<Role> roles = new ArrayList<>();
    private boolean isActive;
    private String profileImageUrl;


}
