package com.nwn.crafts.core.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "client")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //sequence auto-generated numbers
    @Column(name = "client_id")
    private Long clientId;

    @Column(name = "client_name")
    private String clientName;
}
