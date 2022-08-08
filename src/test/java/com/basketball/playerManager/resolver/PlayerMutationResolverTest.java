package com.basketball.playerManager.resolver;

import com.basketball.playerManager.TestUtils;
import com.basketball.playerManager.dtos.PlayerDto;
import com.basketball.playerManager.dtos.converter.PlayerDtoConverter;
import com.basketball.playerManager.dtos.request.CreatePlayerRequest;
import com.basketball.playerManager.model.Player;
import com.basketball.playerManager.model.PlayerPositionType;
import com.basketball.playerManager.model.Team;
import com.basketball.playerManager.repository.PlayerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PlayerMutationResolverTest extends TestUtils {

    private PlayerRepository playerRepository;
    private PlayerDtoConverter playerDtoConverter;

    private PlayerMutationResolver playerMutationResolver;

    @BeforeEach
    public void setUp() {
        playerRepository = mock(PlayerRepository.class);
        playerDtoConverter = mock(PlayerDtoConverter.class);

        playerMutationResolver = new PlayerMutationResolver(playerRepository, playerDtoConverter);
    }

    @Test
    public void testCreatePlayer_itShouldReturnPlayerDto() {
        CreatePlayerRequest request = new CreatePlayerRequest("Test-Name", "Test-Surname", PlayerPositionType.PG, new Team(1, "Test-Team"));
        Player player = generatePlayer("Test-Name", "Test-Surname", PlayerPositionType.PG, new Team(1, "Test-Team"));
        Player savedPlayer = new Player(1, "Test-Name", "Test-Surname", PlayerPositionType.PG, new Team(1, "Test-Team"));
        PlayerDto playerDto = generatePlayerDto("Test-Name", "Test-Surname", PlayerPositionType.PG, new Team(1, "Test-Team"));

        when(playerRepository.save(player)).thenReturn(savedPlayer);
        when(playerDtoConverter.convert(savedPlayer)).thenReturn(playerDto);

        PlayerDto result = playerMutationResolver.save(request);

        assertEquals(result, playerDto);
        verify(playerRepository).save(player);
    }
}