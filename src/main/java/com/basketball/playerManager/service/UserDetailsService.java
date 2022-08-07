package com.basketball.playerManager.service;

import com.basketball.playerManager.model.User;
import com.basketball.playerManager.repository.UserRepository;
import com.basketball.playerManager.security.JwtUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return JwtUserDetails.create(userRepository.findUserByUserName(username));
    }

    public UserDetails loadUserById(int id) {
        User user = userRepository.findById(id).get();
        return JwtUserDetails.create(user);
    }
}
