package com.nwn.crafts.core.domain;

public class ProfileNotFoundException extends RuntimeException{

    public ProfileNotFoundException(String message) {
        super(message);
    }
}
