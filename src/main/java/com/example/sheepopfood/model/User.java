package com.example.sheepopfood.model;

import com.example.sheepopfood.model.enums.EUserRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fullName;
    private String email;
    private String phone;
    private String password;
//    @Column(nullable = false, columnDefinition = "boolean default true")

    private EUserRole role;
    private LocalDate openTime;

    public User(Long id) {
        this.id = id;
    }
}
