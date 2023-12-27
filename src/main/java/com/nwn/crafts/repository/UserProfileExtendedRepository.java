package com.nwn.crafts.repository;

import com.nwn.crafts.core.models.ihm.UserWithProfile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserProfileExtendedRepository {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public List<UserWithProfile> getUsersWithProfiles() {
        String sqlQuery = "select u.login, u.last_name, u.first_name, u.status, u.creation_date, up.profile\n" +
                "from user_profile up\n" +
                "inner join users u on u.login = up.user_login\n" +
                "order by u.login";

        logger.debug("Query to execute: \n {}", sqlQuery);

        return namedParameterJdbcTemplate.query(
                sqlQuery,
                (rs, rowNum) -> new UserWithProfile(
                        rs.getString("LOGIN"),
                        rs.getString("LAST_NAME"),
                        rs.getString("FIRST_NAME"),
                        rs.getString("STATUS"),
                        rs.getTimestamp("CREATION_DATE"),
                        rs.getString("PROFILE"))
        );
    }


}
