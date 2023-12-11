package com.nwn.crafts.core.domain;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "craftsman")
public class Craftsman {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //sequence auto-generated numbers
    @Column(name = "craftsman_id")
    private Long craftsmanId;

    @Column(name = "craftsman_name")
    private String craftsmanName;
}
