package com.basketball.playerManager.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
public class CreateTeamRequest {

    @NotNull
    private String name;
}
