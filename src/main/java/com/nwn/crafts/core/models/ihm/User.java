package com.nwn.crafts.core.models.ihm;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "users")
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    private String login;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "first_name")
    private String firstName;

    private String password;

    private String status;//ACTIF ou INACTIF

    @Column(name = "creation_date")
    private String  creationDate;

}
