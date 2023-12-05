package com.example.usersys.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull(message = "Cannot be name null ")
    @Column(columnDefinition = "varchar(20) not null unique")
    @Size(min = 4, message = "Length more than 4 ")
    private String name;

    @NotNull(message = "Cannot be password null ")
    @Column(columnDefinition = "varchar(20) not null ")
    private String password;

    @Email(message = "must be valid email ")
    @NotNull(message = "Cannot be email null ")
    @Column(columnDefinition = "varchar(20) unique not null ")
    private String email;


    @NotNull(message = "Cannot be role null ")
    @Column(columnDefinition = "varchar(20)  not null check(role='user' or role = 'admin') ")
    private String role;


    @NotNull(message = "Cannot be age null ")
    @Column(columnDefinition = "int  not null ")
    private Integer age;



}
