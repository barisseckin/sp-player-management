package com.basketball.playerManager.service;

import com.basketball.playerManager.TestUtils;
import com.basketball.playerManager.dtos.TeamDto;
import com.basketball.playerManager.dtos.converter.TeamDtoConverter;
import com.basketball.playerManager.dtos.request.CreateTeamRequest;
import com.basketball.playerManager.dtos.request.UpdateTeamRequest;
import com.basketball.playerManager.model.Team;
import com.basketball.playerManager.repository.TeamRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TeamServiceTest extends TestUtils{

    private TeamDtoConverter teamDtoConverter;
    private TeamRepository teamRepository;

    private TeamService teamService;

    @BeforeEach
    public void setUp() {
        teamDtoConverter = mock(TeamDtoConverter.class);
        teamRepository = mock(TeamRepository.class);

        teamService = new TeamService(teamRepository, teamDtoConverter);
    }

    @Test
    public void testGetAllTeams_itShouldReturnTeamDtoList() {
        List<Team> teamList = generateTeam();
        List<TeamDto> teamDtoList = generateTeamDtoList(teamList);

        when(teamRepository.findAll()).thenReturn(teamList);
        when(teamDtoConverter.convert(teamList)).thenReturn(teamDtoList);

        List<TeamDto> result = teamService.getAll();

        assertEquals(result, teamDtoList);
        verify(teamRepository).findAll();
        verify(teamDtoConverter).convert(teamList);
    }

    @Test
    public void testGetByTeamName_itShouldReturnTeamDto() {
        String teamName = "Test-Name";

        Team team = generateTeam(teamName);
        TeamDto teamDto = generateTeamDto(teamName);

        when(teamRepository.findTeamByName("Test-Name")).thenReturn(Optional.of(team));
        when(teamDtoConverter.convert(team)).thenReturn(teamDto);

        TeamDto result = teamService.getByName(teamName);

        assertEquals(result, teamDto);
        verify(teamRepository).findTeamByName(teamName);
        verify(teamDtoConverter).convert(team);
    }

    @Test
    public void testCreateTeam_itShouldReturnTeamDto() {
        String teamName = "Test-Name";

        CreateTeamRequest request = new CreateTeamRequest(teamName);
        Team team = new Team(teamName);
        Team savedTeam = new Team(1, teamName);
        TeamDto teamDto = new TeamDto(teamName);

        when(teamRepository.save(team)).thenReturn(savedTeam);
        when(teamDtoConverter.convert(savedTeam)).thenReturn(teamDto);

        TeamDto result = teamService.save(request);

        assertEquals(result, teamDto);
        verify(teamRepository).save(team);
    }

    @Test
    public void testUpdateTeam_itShouldReturnTeamDto() {
        String teamName = "Test-Name";

        UpdateTeamRequest request = new UpdateTeamRequest(teamName);
        Team team = new Team(teamName);
        Team savedTeam = new Team(1, teamName);
        TeamDto teamDto = new TeamDto(teamName);

        when(teamRepository.findTeamByName(teamName)).thenReturn(Optional.of(team));
        when(teamRepository.save(team)).thenReturn(savedTeam);
        when(teamDtoConverter.convert(savedTeam)).thenReturn(teamDto);

        TeamDto result = teamService.update(teamName, request);

        assertEquals(teamDto, result);
        verify(teamRepository).findTeamByName(teamName);
    }



}