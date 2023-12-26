package com.nwn.crafts.controllers;

import com.nwn.crafts.core.domain.CraftsException;
import com.nwn.crafts.core.domain.ProfileNotFoundException;
import com.nwn.crafts.core.models.ihm.Profile;
import com.nwn.crafts.core.services.ProfileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/profiles")
public class ProfileController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ProfileService profileService;


    @Operation(summary = "Get all profiles", description = "Returns the list of all profiles in Database ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
            @ApiResponse(responseCode = "404", description = "Not found - No profile found")
    })
    @GetMapping("/")
    public List<Profile> list() {
        return profileService.findAll();
    }


    @Operation(summary = "Get a profile Object by profile", description = "Returns a profile Object from his profile")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
            @ApiResponse(responseCode = "404", description = "Not found - No profile Object was found for this profile")
    })
    @GetMapping("{profile}")
    public Profile get(@PathVariable String profile) {
        return profileService.findById(profile);
    }


    @Operation(summary = "Create new Profile Object", description = "Create and save new Profile Object")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully created"),
            @ApiResponse(responseCode = "404", description = "No Profile is created")
    })
    @PostMapping("/create")
    public Profile create(@RequestBody final Profile profile) {
        return profileService.addProfile(profile);
    }


    @Operation(summary = "Update Profile", description = "Update an existing Profile's information from his profile id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully Updated"),
            @ApiResponse(responseCode = "404", description = "Not found - No Profile Object was found for this Profile's id")
    })
    @PutMapping("/update/{profileId}")
    public Profile update(@PathVariable String profileId, @RequestBody final Profile profile) {
        try {
            return profileService.updateProfile(profileId, profile);
        } catch (CraftsException e) {
            logger.error("Error updating profile Object with id: {}", profileId);
            throw new ProfileNotFoundException(e.getMessage());
        }
    }

    @Operation(summary = "Delete Profile", description = "Delete an existing Profile's Object from his profile's id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully Deleted"),
            @ApiResponse(responseCode = "404", description = "Not found - No Profile's Object was found for this profile's id")
    })
    @DeleteMapping("/delete/{profileId}")
    public void delete(@PathVariable String profileId) {
        profileService.deleteById(profileId);
    }
}
