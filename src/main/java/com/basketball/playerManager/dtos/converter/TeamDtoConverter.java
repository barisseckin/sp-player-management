package com.basketball.playerManager.dtos.converter;

import com.basketball.playerManager.dtos.TeamDto;
import com.basketball.playerManager.model.Team;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TeamDtoConverter {

    public TeamDto convert(Team team) {
        return new TeamDto(team.getName());
    }

    public List<TeamDto> convert(List<Team> teams) {
        return teams.stream().map(this::convert).collect(Collectors.toList());
    }
}
