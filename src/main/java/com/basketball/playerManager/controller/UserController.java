package com.basketball.playerManager.controller;

import com.basketball.playerManager.dtos.UserDto;
import com.basketball.playerManager.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<UserDto>> getAll() {
        return new ResponseEntity<>(userService.getAll(), HttpStatus.OK);
    }

    @DeleteMapping("/{userName}")
    public ResponseEntity<UserDto> deleteByUserName(@PathVariable("userName") String userName) {
        return new ResponseEntity<>(userService.deleteByUserName(userName), HttpStatus.OK);
    }

    @GetMapping("/get-by-userName/{userName}")
    public ResponseEntity<UserDto> getByUserName(@PathVariable("userName") String userName) {
        return new ResponseEntity<>(userService.getByUserName(userName), HttpStatus.OK);
    }

    @GetMapping("/get-by-mail/{mail}")
    public ResponseEntity<UserDto> getByMail(@PathVariable("mail") String mail) {
        return new ResponseEntity<>(userService.getByMail(mail), HttpStatus.OK);
    }
}
