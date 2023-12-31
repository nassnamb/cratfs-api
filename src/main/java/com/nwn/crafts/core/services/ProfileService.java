package com.nwn.crafts.core.services;

import com.nwn.crafts.core.domain.CraftsException;
import com.nwn.crafts.core.models.ProfileNotFoundException;
import com.nwn.crafts.core.models.Profile;
import com.nwn.crafts.core.models.UserNotFoundException;
import com.nwn.crafts.repository.ProfileRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

import static java.lang.String.format;

@Service
public class ProfileService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ProfileRepository profileRepository;


    public List<Profile> findAll() {
        return profileRepository.findAll();
    }

    public Profile findById(String profileId) {
        return profileRepository.findById(profileId).orElseThrow(() -> new ProfileNotFoundException(format("No profile found for this profile id: %s", profileId)));
    }

    public Profile addProfile(Profile profile) {
        return profileRepository.saveAndFlush(profile);
    }

    public void deleteById(String profileId) {
        Objects.requireNonNull(profileId, "profile Id can not be null");
        if (findById(profileId) != null) {
            profileRepository.deleteById(profileId);
        } else {
            throw new UserNotFoundException("le profil " + profileId + " est introuvable");
        }
    }

    public Profile updateProfile(String profileId, Profile profile) throws CraftsException {
        Objects.requireNonNull(profile);
        logger.debug("Updating profile with id : {}", profileId);
        Profile current = findById(profileId);
        if (current != null) {
            BeanUtils.copyProperties(profile, current, "profile");
        } else {
            throw new CraftsException(format("No profile found with id: %s", profileId));
        }
        return profileRepository.saveAndFlush(current);
    }
}
