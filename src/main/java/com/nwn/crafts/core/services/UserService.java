package com.nwn.crafts.core.services;

import com.nwn.crafts.core.domain.CraftsException;
import com.nwn.crafts.core.models.User;
import com.nwn.crafts.dto.UserDto;
import com.nwn.crafts.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.lang.String.format;

@Service
public class UserService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserRepository userRepository;

    public List<UserDto> findAll() {
        return userRepository.findAll()
                .stream()
                .map(this::mapToUserDto)
                .collect(Collectors.toList());
    }

    public UserDto findById(Long userId) {
        return userRepository.findById(userId).map(this::mapToUserDto).orElse(null);
    }

    public UserDto findByLogin(String login) {
        return mapToUserDto(userRepository.getByLogin(login));
    }

    public Long getUserIdFromLogin(String login) {
        return findByLogin(login).getUserId();
    }

    public String getUserLoginFromUserId(Long userId) {
        return findById(userId).getLogin();
    }

    public UserDto addUser(User user) {
        var userAdded = userRepository.saveAndFlush(user);
        return mapToUserDto(userAdded);
    }

    public UserDto updateUser(Long userId, User user) throws CraftsException {
        Objects.requireNonNull(user);
        logger.debug("Updating user with id : {}", userId);
        User current = userRepository.findById(userId).orElse(null);
        if (current != null) {
            BeanUtils.copyProperties(user, current, "userId");
        } else {
            throw new CraftsException(format("No user found with id: %s", userId));
        }
        var updatedUser = userRepository.saveAndFlush(current);
        return mapToUserDto(updatedUser);
    }

    public void deleteUserById(Long userId) {
        userRepository.deleteById(userId);
    }

    public void deleteByUserLogin(String login) {
        userRepository.deleteUserByLogin(login);
    }

    private UserDto mapToUserDto(User user) {
        var userDto = new UserDto();
        userDto.setUserId(user.getUserId());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setLogin(user.getLogin());
        userDto.setStatus(user.getStatus());
        userDto.setCreationDate(user.getCreationDate());
        return userDto;
    }
}
