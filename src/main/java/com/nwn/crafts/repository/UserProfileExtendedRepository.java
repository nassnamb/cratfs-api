package com.nwn.crafts.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserProfileExtendedRepository {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

}
