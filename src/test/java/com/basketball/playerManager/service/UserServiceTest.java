package com.basketball.playerManager.service;

import com.basketball.playerManager.TestUtils;
import com.basketball.playerManager.dtos.UserDto;
import com.basketball.playerManager.dtos.converter.UserDtoConverter;
import com.basketball.playerManager.dtos.request.CreateUserRequest;
import com.basketball.playerManager.model.User;
import com.basketball.playerManager.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest extends TestUtils {

    private UserRepository userRepository;
    private UserDtoConverter userDtoConverter;

    private UserService userService;

    @BeforeEach
    public void setUp() {
        userRepository = mock(UserRepository.class);
        userDtoConverter = mock(UserDtoConverter.class);

        userService = new UserService(userRepository, userDtoConverter);
    }

    @Test
    public void testGetAllUser_itShouldReturnUserDtoList() {
        List<User> userList = generateUser();
        List<UserDto> userDtoList = generateUserDtoList(userList);

        when(userRepository.findAll()).thenReturn(userList);
        when(userDtoConverter.convert(userList)).thenReturn(userDtoList);

        List<UserDto> result = userService.getAll();

        assertEquals(result, userDtoList);
        verify(userRepository).findAll();
    }

    @Test
    public void testGetByUserName_itShouldReturnUserDto() {
        String userName = "Test-UserName";

        User user = generateUser(userName, "Test-Password", "test@gmail.com");
        UserDto userDto = generateUserDto(userName, "test@gmail.com");

        when(userRepository.findUserByUserName(userName)).thenReturn(user);
        when(userDtoConverter.convert(user)).thenReturn(userDto);

        UserDto result = userService.getByUserName(userName);

        assertEquals(result, userDto);
        verify(userRepository).findUserByUserName(userName);
    }

    @Test
    public void testGetByMail_itShouldReturnUserDto() {
        String mail = "test@gmail.com";

        User user = generateUser("Test-UserName", "Test-Password", mail);
        UserDto userDto = generateUserDto("Test-UserName", mail);

        when(userRepository.findUserByMail(mail)).thenReturn(user);
        when(userDtoConverter.convert(user)).thenReturn(userDto);

        UserDto result = userService.getByMail(mail);

        assertEquals(result, userDto);
        verify(userRepository).findUserByMail(mail);
    }
}