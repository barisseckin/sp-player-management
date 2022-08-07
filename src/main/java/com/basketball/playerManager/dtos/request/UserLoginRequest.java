package com.basketball.playerManager.dtos.request;

import lombok.Data;

@Data
public class UserLoginRequest {

    private String userName;
    private String password;
}
