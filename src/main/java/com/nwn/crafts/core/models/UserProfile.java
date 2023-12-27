package com.nwn.crafts.core.models;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "user_profile")
//@IdClass(UserProfilePK.class)
public class UserProfile {

    @Id
    @Column(name = "user_login", nullable = false)
    private String userLogin;

    //@Id
    @Column(name = "profile", nullable = false)
    private String profile;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "grant_date")
    private Date grantDate;

}
