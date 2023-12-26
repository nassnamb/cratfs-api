package com.nwn.crafts.core.models.ihm;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "user_profile")
//@IdClass(UserProfilePK.class)
public class UserProfile {

    @Id
    @Column(name = "user_login", nullable = false)
    private Long userLogin;

    //@Id
    @Column(name = "profile", nullable = false)
    private String profile;

    @Column(name = "grant_date")
    private String  grantDate;

}
