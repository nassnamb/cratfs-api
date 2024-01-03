package com.nwn.crafts.core.services;

import com.nwn.crafts.core.domain.CraftsException;
import com.nwn.crafts.core.models.UserProfileNotFoundException;
import com.nwn.crafts.core.models.UserProfile;
import com.nwn.crafts.core.models.ihm.UserWithProfile;
import com.nwn.crafts.repository.UserProfileExtendedRepository;
import com.nwn.crafts.repository.UserProfileRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

import static java.lang.String.format;

@Service
public class UserProfileService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserProfileRepository userProfileRepository;

    @Autowired
    private UserProfileExtendedRepository userProfileExtendedRepository;


    public List<UserProfile> findAll() {
        return userProfileRepository.findAll();
    }

    public List<UserProfile> findUsersByProfile(String profile) {
        return userProfileRepository.findAllByProfile(profile);
    }

    public UserProfile findByLogin(String userLogin) {
        return userProfileRepository.findById(userLogin).orElseThrow(() -> new UserProfileNotFoundException(format("No UserProfile found for this login : %s", userLogin)));
    }

    public List<UserWithProfile> getUsersWithProfiles () {
        return userProfileExtendedRepository.getUsersWithProfiles();
    }

    public UserProfile addUserProfile(UserProfile userProfile) {
        Objects.requireNonNull(userProfile, "userProfile can not be null");
        return userProfileRepository.saveAndFlush(userProfile);
    }

    public UserProfile updateUserProfile(String userLogin, UserProfile userProfile) throws CraftsException {
        Objects.requireNonNull(userProfile, "userProfile can not be null");
        logger.debug("Updating user with Login : {}", userLogin);
        UserProfile current = userProfileRepository.findById(userLogin).orElse(null);
        if (current != null) {
            BeanUtils.copyProperties(userProfile, current, "userLogin", "grantDate");
        } else {
            throw new CraftsException(format("No UserProfile found with login: %s", userLogin));
        }
        return userProfileRepository.saveAndFlush(current);
    }

    @Transactional
    public void deleteByLogin(String userLogin) {
        userProfileRepository.deleteUserProfileByUserLogin(userLogin);
    }
}
