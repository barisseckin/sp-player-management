package com.basketball.playerManager.dtos.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CreateTeamRequest {

    @NotNull
    private String name;
}
