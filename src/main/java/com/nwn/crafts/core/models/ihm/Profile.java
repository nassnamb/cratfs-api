package com.nwn.crafts.core.models.ihm;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "profile")
public class Profile {

    @Id
    @Column(name = "profile")
    private String profile;

    private String description;

}
