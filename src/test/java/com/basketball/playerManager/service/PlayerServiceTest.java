package com.basketball.playerManager.service;

import com.basketball.playerManager.TestUtils;
import com.basketball.playerManager.dtos.PlayerDto;
import com.basketball.playerManager.dtos.converter.PlayerDtoConverter;
import com.basketball.playerManager.dtos.request.CreatePlayerRequest;
import com.basketball.playerManager.dtos.request.UpdatePlayerRequest;
import com.basketball.playerManager.model.Player;
import com.basketball.playerManager.model.PlayerPositionType;
import com.basketball.playerManager.model.Team;
import com.basketball.playerManager.repository.PlayerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PlayerServiceTest extends TestUtils {

    private PlayerDtoConverter playerDtoConverter;
    private PlayerRepository playerRepository;

    private PlayerService playerService;

    @BeforeEach
    public void setUp() {
        playerDtoConverter = mock(PlayerDtoConverter.class);
        playerRepository = mock(PlayerRepository.class);

        playerService = new PlayerService(playerRepository, playerDtoConverter);
    }

    @Test
    public void testGetAllPlayers_itShouldReturnPlayerDtoList() {
        List<Player> playerList = generatePlayer();
        List<PlayerDto> playerDtoList = generatePlayerDtoList(playerList);

        when(playerRepository.findAll()).thenReturn(playerList);
        when(playerDtoConverter.convert(playerList)).thenReturn(playerDtoList);

        List<PlayerDto> result = playerService.getAll();

        assertEquals(result, playerDtoList);
        verify(playerRepository).findAll();
        verify(playerDtoConverter).convert(playerList);
    }

    @Test
    public void testGetByPlayerPosition_itShouldReturnPlayerDtoList() {
        PlayerPositionType positionType = PlayerPositionType.PG;

        List<Player> playerList = generatePlayer();
        List<PlayerDto> playerDtoList = generatePlayerDtoList(playerList);

        when(playerRepository.findPlayerByPositionType(positionType)).thenReturn(playerList);
        when(playerDtoConverter.convert(playerList)).thenReturn(playerDtoList);

        List<PlayerDto> result = playerService.getByPosition(positionType);

        assertEquals(result, playerDtoList);
        verify(playerRepository).findPlayerByPositionType(positionType);
    }

    @Test
    public void testCreatePlayer_itShouldReturnPlayerDto() {
        CreatePlayerRequest request = new CreatePlayerRequest("Test-Name", "Test-Surname", PlayerPositionType.PG, new Team(1, "Test-Team"));
        Player player = generatePlayer("Test-Name", "Test-Surname", PlayerPositionType.PG, new Team(1, "Test-Team"));
        Player savedPlayer = new Player(1, "Test-Name", "Test-Surname", PlayerPositionType.PG, new Team(1, "Test-Team"));
        PlayerDto playerDto = generatePlayerDto("Test-Name", "Test-Surname", PlayerPositionType.PG, new Team(1, "Test-Team"));

        when(playerRepository.save(player)).thenReturn(savedPlayer);
        when(playerDtoConverter.convert(savedPlayer)).thenReturn(playerDto);

        PlayerDto result = playerService.save(request);

        assertEquals(result, playerDto);
        verify(playerRepository).save(player);
    }

    @Test
    public void testUpdatePlayer_itShouldReturnPlayerDto() {
        UpdatePlayerRequest request = new UpdatePlayerRequest(PlayerPositionType.PG, new Team(1, "Test-Team"));
        Player player = generatePlayer("Test-Name", "Test-Surname", PlayerPositionType.PG, new Team(1, "Test-Team"));
        Player savedPlayer = new Player(1, "Test-Name", "Test-Surname", PlayerPositionType.PG, new Team(1, "Test-Team"));
        PlayerDto playerDto = generatePlayerDto("Test-Name", "Test-Surname", PlayerPositionType.PG, new Team(1, "Test-Team"));

        when(playerRepository.findPlayerByName("Test-Name")).thenReturn(Optional.of(player));
        when(playerRepository.save(player)).thenReturn(savedPlayer);
        when(playerDtoConverter.convert(savedPlayer)).thenReturn(playerDto);

        PlayerDto result = playerService.update("Test-Name", request);

        assertEquals(result, playerDto);
        verify(playerRepository).findPlayerByName("Test-Name");
    }

}