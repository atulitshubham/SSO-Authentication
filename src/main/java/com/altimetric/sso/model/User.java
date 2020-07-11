package com.altimetric.sso.model;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@ToString
@Entity
@Data
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private Integer userId;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
}
