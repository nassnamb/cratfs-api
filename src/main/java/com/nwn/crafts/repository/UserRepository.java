package com.nwn.crafts.repository;

import com.nwn.crafts.core.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User getByLogin(String login);

    @Modifying
    @Query("delete from User u where u.login = :login")
    void deleteUserByLogin(String login);
}
