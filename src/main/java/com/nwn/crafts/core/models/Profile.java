package com.nwn.crafts.core.models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "profile")
public class Profile {

    @Id
    @Column(name = "profile")
    private String profileId;

    private String description;

}
