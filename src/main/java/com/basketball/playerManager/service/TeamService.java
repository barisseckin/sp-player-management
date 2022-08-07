package com.basketball.playerManager.service;

import com.basketball.playerManager.dtos.TeamDto;
import com.basketball.playerManager.dtos.converter.TeamDtoConverter;
import com.basketball.playerManager.dtos.request.CreateTeamRequest;
import com.basketball.playerManager.dtos.request.UpdateTeamRequest;
import com.basketball.playerManager.exception.NotFoundException;
import com.basketball.playerManager.model.Team;
import com.basketball.playerManager.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TeamService {

    private final TeamRepository teamRepository;

    private final TeamDtoConverter teamDtoConverter;

    public TeamDto save(CreateTeamRequest request) {
        Team team = new Team();
        team.setName(request.getName());

        return teamDtoConverter.convert(teamRepository.save(team));
    }

    public TeamDto deleteByName(String name) {
        Optional<Team> team = teamRepository.findTeamByName(name);

        if (team.isPresent()) {
            teamRepository.deleteById(team.get().getId());
            return teamDtoConverter.convert(team.get());
        }
        else {
            throw  new NotFoundException("Team not found, name: " + name);
        }
    }

    public List<TeamDto> getAll() {
        return teamDtoConverter.convert(teamRepository.findAll());
    }

    public TeamDto getByName(String name) {
        return teamDtoConverter.convert(teamRepository.findTeamByName(name).get());
    }

    public TeamDto update(String name, UpdateTeamRequest request) {
        Optional<Team> team = teamRepository.findTeamByName(name);

        Team updatedTeam = new Team(team.get().getId(),
                request.getName());

        return teamDtoConverter.convert(teamRepository.save(updatedTeam));
    }

}
