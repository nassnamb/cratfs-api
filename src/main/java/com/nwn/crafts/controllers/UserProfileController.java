package com.nwn.crafts.controllers;

import com.nwn.crafts.core.domain.CraftsException;
import com.nwn.crafts.core.domain.UserProfileNotFoundException;
import com.nwn.crafts.core.models.ihm.UserProfile;
import com.nwn.crafts.core.services.ProfileService;
import com.nwn.crafts.core.services.UserProfileService;
import com.nwn.crafts.core.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/userProfile")
public class UserProfileController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserProfileService userProfileService;

    @Autowired
    private UserService userService;

    @Autowired
    private ProfileService profileService;

    @Operation(summary = "Get all UserProfiles", description = "Returns the list of all UserProfiles in Database ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
            @ApiResponse(responseCode = "404", description = "Not found - No UserProfile found")
    })
    @GetMapping("/")
    public List<UserProfile> list() {
        return userProfileService.findAll();
    }

    @Operation(summary = "Get a UserProfile by login", description = "Returns a UserProfile from user's login")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
            @ApiResponse(responseCode = "404", description = "Not found - No UserProfile was found for this login")
    })
    @GetMapping("{login}")
    public UserProfile get(@PathVariable String userLogin) {
        return userProfileService.findByLogin(userLogin);
    }


    @Operation(summary = "Create new UserProfile", description = "Create and save new UserProfile")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully created"),
            @ApiResponse(responseCode = "404", description = "No Craftsman is created")
    })
    @PostMapping("/create")
    public UserProfile create(@RequestBody final UserProfile userProfile) {
        return userProfileService.addUserProfile(userProfile);
    }


    @Operation(summary = "Update userProfile", description = "Update an existing userProfile's information from his login")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully Updated"),
            @ApiResponse(responseCode = "404", description = "Not found - No craftsman was found for this id")
    })
    @PutMapping("/update/{login}")
    public UserProfile update(@PathVariable String login, @RequestBody final UserProfile userProfile) {
        try {
            return userProfileService.updateUserProfile(login, userProfile);
        } catch (CraftsException e) {
            logger.error("Error updating userProfile with login: {}", login);
            throw new UserProfileNotFoundException(e.getMessage());
        }
    }

    @Operation(summary = "Delete userProfile", description = "Delete an existing userProfile from his login")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully Deleted"),
            @ApiResponse(responseCode = "404", description = "Not found - No userProfile was found for this login")
    })
    @DeleteMapping("/delete/{login}")
    public void delete(@PathVariable String login) {
        userProfileService.deleteByLogin(login);
    }



}
