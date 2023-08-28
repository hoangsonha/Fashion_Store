package com.hoangsonha.fashion_store.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "email", nullable = false, unique = true, length = 100)
    private String email;
    @Column(name = "password", nullable = false, length = 50)
    private String password;
    @Column(name = "first_name", nullable = true, length = 6)
    private String firstName;
    @Column(name = "last_name", nullable = true, length = 6)
    private String lastName;
    @Column(name = "phone_number", nullable = false, length = 20)
    private String phoneNumber;
    @Column(name = "role", nullable = false,  length = 1)
    private int role;
}
