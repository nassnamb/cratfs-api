package com.nwn.crafts.core.services;

import com.nwn.crafts.core.domain.CraftsException;
import com.nwn.crafts.core.models.User;
import com.nwn.crafts.core.models.UserProfile;
import com.nwn.crafts.dto.UserDto;
import com.nwn.crafts.repository.UserProfileRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

import static java.lang.String.format;

@Service
public class UserProfileService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserProfileRepository userProfileRepository;


    public List<UserProfile> findAll() {
        return userProfileRepository.findAll();
    }

    public List<UserProfile> findUsersByProfile(String profile) {
        return userProfileRepository.findAllByProfile(profile);
    }

    public UserProfile findByLogin(String userLogin) {
        return userProfileRepository.getByUserLogin(userLogin);
    }

    public UserProfile addUserProfile(UserProfile userProfile) {
        return userProfileRepository.saveAndFlush(userProfile);
    }

    public UserProfile updateUserProfile(String userLogin, UserProfile userProfile) throws CraftsException {
        Objects.requireNonNull(userProfile);
        logger.debug("Updating user with Login : {}", userLogin);
        UserProfile current = userProfileRepository.findById(userLogin).orElse(null);
        if (current != null) {
            BeanUtils.copyProperties(userProfile, current, "userLogin");
        } else {
            throw new CraftsException(format("No UserProfile found with login: %s", userLogin));
        }
        return userProfileRepository.saveAndFlush(current);
    }

    public void deleteByLogin(String userLogin) {
        userProfileRepository.deleteUserProfileByUserLogin(userLogin);
    }
}
