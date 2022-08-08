package com.basketball.playerManager.resolver;

import com.basketball.playerManager.TestUtils;
import com.basketball.playerManager.dtos.PlayerDto;
import com.basketball.playerManager.dtos.converter.PlayerDtoConverter;
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
import static org.mockito.Mockito.verify;

class PlayerQueryResolverTest extends TestUtils {

    private PlayerRepository playerRepository;
    private PlayerDtoConverter playerDtoConverter;

    private PlayerQueryResolver playerQueryResolver;

    @BeforeEach
    public void setUp() {
        playerDtoConverter = mock(PlayerDtoConverter.class);
        playerRepository = mock(PlayerRepository.class);

        playerQueryResolver = new PlayerQueryResolver(playerRepository, playerDtoConverter);
    }

    @Test
    public void testGetAllPlayers_itShouldReturnPlayerDtoList() {
        List<Player> playerList = generatePlayer();
        List<PlayerDto> playerDtoList = generatePlayerDtoList(playerList);

        when(playerRepository.findAll()).thenReturn(playerList);
        when(playerDtoConverter.convert(playerList)).thenReturn(playerDtoList);

        List<PlayerDto> result = playerQueryResolver.getAll();

        assertEquals(result, playerDtoList);
        verify(playerRepository).findAll();
        verify(playerDtoConverter).convert(playerList);
    }

    @Test
    public void testGetByPlayerName_itShouldReturnPlayerDto() {
        String testName = "Test-Name";

        Player player = generatePlayer(testName, "Test-Password", PlayerPositionType.PG, new Team(1, "Test-Team"));
        PlayerDto playerDto = generatePlayerDto(testName, "Test-Password", PlayerPositionType.PG, new Team(1, "Test-Team"));

        when(playerRepository.findPlayerByName(testName)).thenReturn(Optional.of(player));
        when(playerDtoConverter.convert(player)).thenReturn(playerDto);

        PlayerDto result = playerQueryResolver.getByName(testName);

        assertEquals(result, playerDto);
        verify(playerRepository).findPlayerByName(testName);
    }
}