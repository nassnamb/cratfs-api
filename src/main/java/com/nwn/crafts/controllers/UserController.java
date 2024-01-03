package com.nwn.crafts.controllers;

import com.nwn.crafts.core.domain.*;
import com.nwn.crafts.core.models.User;
import com.nwn.crafts.core.models.UserNotFoundException;
import com.nwn.crafts.core.services.UserService;
import com.nwn.crafts.dto.UserDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/users")
public class UserController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserService userService;

    @Operation(summary = "Get all users", description = "Returns the list of all users in Database ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
            @ApiResponse(responseCode = "404", description = "Not found - No user found")
    })
    @GetMapping("/")
    public List<UserDto> list() {
        return userService.findAll();
    }

    @Operation(summary = "Get a user by id", description = "Returns a user from his id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
            @ApiResponse(responseCode = "404", description = "Not found - No user was found for this id")
    })
    @GetMapping("/byId/{id}")
    public UserDto get(@PathVariable Long id) {
        return userService.findById(id);
    }


    @Operation(summary = "Get a user by Login", description = "Returns a user from his login")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
            @ApiResponse(responseCode = "404", description = "Not found - No user was found for this login")
    })
    @GetMapping("/byLogin/{login}")
    public UserDto getByLogin(@PathVariable String login) {
        return userService.findByLogin(login);
    }


    @Operation(summary = "Create new User", description = "Create and save new User")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully created"),
            @ApiResponse(responseCode = "404", description = "No User is created")
    })
    @PostMapping("/create")
    public UserDto create(@RequestBody final User user) {
        return userService.addUser(user);
    }


    @Operation(summary = "Update User", description = "Update an existing User's information from his id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully Updated"),
            @ApiResponse(responseCode = "404", description = "Not found - No User was found for this id")
    })
    @PutMapping("/update/{id}")
    public UserDto update(@PathVariable Long id, @RequestBody final User user) {
        try {
            return userService.updateUser(id, user);
        } catch (CraftsException e) {
            logger.error("Error updating user with id: {}", id);
            throw new UserNotFoundException(e.getMessage());
        }
    }

    @Operation(summary = "Delete user by id", description = "Delete an existing user from his id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully Deleted"),
            @ApiResponse(responseCode = "404", description = "Not found - No craftsman was found for this id")
    })
    @DeleteMapping("/delete/byId/{id}")
    public void delete(@PathVariable Long id) {
        userService.deleteUserById(id);
    }

    @Operation(summary = "Delete user by login", description = "Delete an existing user from his login")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully Deleted"),
            @ApiResponse(responseCode = "404", description = "Not found - No User was found for this login")
    })
    @DeleteMapping("/delete/byLogin/{login}")
    public void deleteByLogin(@PathVariable String login) {
        userService.deleteByUserLogin(login);
    }


}
