package com.nwn.crafts.core.models.ihm;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
public class UserWithProfile {

    private String login;

    private String lastName;

    private String firstName;

    private String status;

    private Timestamp creationDate;

    private String profile;
}
