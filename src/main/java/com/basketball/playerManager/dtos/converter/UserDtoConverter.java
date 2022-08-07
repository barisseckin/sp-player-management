package com.basketball.playerManager.dtos.converter;

import com.basketball.playerManager.dtos.UserDto;
import com.basketball.playerManager.model.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserDtoConverter {

    public UserDto convert(User user) {
        return new UserDto(user.getUserName(),
                user.getMail());
    }

    public List<UserDto> convert(List<User> users) {
        return users.stream().map(this::convert).collect(Collectors.toList());
    }
}
