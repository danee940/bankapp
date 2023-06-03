package com.danee.bankapp.model;

import lombok.Data;

import javax.persistence.*;


@Entity
@Table(name = "users")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    private String username;

    private String password;

    private boolean enabled;
}


