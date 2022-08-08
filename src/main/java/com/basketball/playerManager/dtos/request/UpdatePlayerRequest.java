package com.basketball.playerManager.dtos.request;

import com.basketball.playerManager.model.PlayerPositionType;
import com.basketball.playerManager.model.Team;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UpdatePlayerRequest {

    private PlayerPositionType positionType;
    private Team team;
}
