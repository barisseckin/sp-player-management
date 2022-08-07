package com.basketball.playerManager.dtos;

import com.basketball.playerManager.model.PlayerPositionType;
import com.basketball.playerManager.model.Team;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PlayerDto {

    private String name;
    private String surname;
    private PlayerPositionType positionType;
    private Team team;
}
