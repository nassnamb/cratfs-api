package com.nwn.crafts.dto;

import lombok.Data;

import java.util.Date;

@Data
public class UserDto {

    private Long userId;

    private String login;

    private String lastName;

    private String firstName;

    private String status;

    private Date creationDate;

}
