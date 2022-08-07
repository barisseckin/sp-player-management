package com.basketball.playerManager.controller;

import com.basketball.playerManager.dtos.request.CreateUserRequest;
import com.basketball.playerManager.dtos.request.UserLoginRequest;
import com.basketball.playerManager.security.JwtTokenProvider;
import com.basketball.playerManager.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;

    private final JwtTokenProvider jwtTokenProvider;

    private final UserService userService;

    private final PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserLoginRequest request) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(request.getUserName(), request.getPassword());
        Authentication authentication = authenticationManager.authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwtToken = "Bearer " + jwtTokenProvider.generateJwtToken(authentication);
        return new ResponseEntity<>(jwtToken, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@Valid @RequestBody CreateUserRequest request) {
        userService.save(new CreateUserRequest(request.getUserName(), passwordEncoder.encode(request.getPassword()), request.getMail()));
        return new ResponseEntity<>(request.getMail(), HttpStatus.CREATED);
    }
}
