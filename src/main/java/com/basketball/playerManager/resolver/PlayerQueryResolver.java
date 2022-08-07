package com.basketball.playerManager.resolver;

import com.basketball.playerManager.dtos.PlayerDto;
import com.basketball.playerManager.dtos.converter.PlayerDtoConverter;
import com.basketball.playerManager.repository.PlayerRepository;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class PlayerQueryResolver implements GraphQLQueryResolver {

    private final PlayerRepository playerRepository;

    private final PlayerDtoConverter playerDtoConverter;

    public List<PlayerDto> getAll() {
        return playerDtoConverter.convert(playerRepository.findAll());
    }

    public PlayerDto getByName(String name) {
        return playerDtoConverter.convert(playerRepository.findPlayerByName(name).get());
    }

}
