package com.basketball.playerManager.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Email;

@Data
@AllArgsConstructor
public class CreateUserRequest {

    private String userName;
    private String password;
    @Email
    private String mail;
}
