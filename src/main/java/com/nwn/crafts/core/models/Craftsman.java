package com.nwn.crafts.core.models;


import javax.persistence.*;

@Entity(name = "craftsman")
public class Craftsman {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //sequence auto-generated numbers
    @Column(name = "craftsman_id")
    private Long craftsmanId;

    @Column(name = "craftsman_name")
    private String craftsmanName;

    public Craftsman() {
    }

    public Long getCraftsmanId() {
        return craftsmanId;
    }

    public String getCraftsmanName() {
        return craftsmanName;
    }

    public void setCraftsmanId(Long craftsmanId) {
        this.craftsmanId = craftsmanId;
    }

    public void setCraftsmanName(String craftsmanName) {
        this.craftsmanName = craftsmanName;
    }
}
