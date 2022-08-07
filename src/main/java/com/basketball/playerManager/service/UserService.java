package com.basketball.playerManager.service;

import com.basketball.playerManager.dtos.UserDto;
import com.basketball.playerManager.dtos.converter.UserDtoConverter;
import com.basketball.playerManager.dtos.request.CreateUserRequest;
import com.basketball.playerManager.model.User;
import com.basketball.playerManager.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final UserDtoConverter userDtoConverter;

    public UserDto save(CreateUserRequest request) {
        User user = new User();
        user.setUserName(request.getUserName());
        user.setPassword(request.getPassword());
        user.setMail(request.getMail());

        return userDtoConverter.convert(userRepository.save(user));
    }

    public List<UserDto> getAll() {
        return userDtoConverter.convert(userRepository.findAll());
    }

    public UserDto getByUserName(String userName) {
        return userDtoConverter.convert(userRepository.findUserByUserName(userName));
    }

    public UserDto getByMail(String mail) {
        return userDtoConverter.convert(userRepository.findUserByMail(mail));
    }

    public UserDto deleteByUserName(String userName) {
        User user = userRepository.findUserByUserName(userName);
        userRepository.deleteById(user.getId());

        return userDtoConverter.convert(user);
    }

}
