package com.basketball.playerManager.dtos.request;

import com.basketball.playerManager.model.PlayerPositionType;
import com.basketball.playerManager.model.Team;
import lombok.Data;

@Data
public class UpdatePlayerRequest {

    private PlayerPositionType positionType;
    private Team team;
}
