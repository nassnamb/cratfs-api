package com.nwn.crafts.repository;


import com.nwn.crafts.core.models.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserProfileRepository extends JpaRepository<UserProfile, String> {

    UserProfile getByUserLogin(String userLogin);

    List<UserProfile> findAllByProfile(String profile);

    @Modifying
    @Query("delete from UserProfile u where u.userLogin = :userLogin")
    void deleteUserProfileByUserLogin(String userLogin);
}
