package com.basketball.playerManager.dtos.request;

import com.basketball.playerManager.model.PlayerPositionType;
import com.basketball.playerManager.model.Team;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CreatePlayerRequest {

    @NotNull
    private String name;
    @NotNull
    private String surname;
    @NotNull
    private PlayerPositionType positionType;
    @NotNull
    private Team team;
}
