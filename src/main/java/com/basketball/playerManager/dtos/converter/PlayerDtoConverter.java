package com.basketball.playerManager.dtos.converter;

import com.basketball.playerManager.dtos.PlayerDto;
import com.basketball.playerManager.model.Player;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PlayerDtoConverter {

    public PlayerDto convert(Player player) {
        return new PlayerDto(player.getName(),
                player.getSurname(),
                player.getPositionType(),
                player.getTeam());
    }

    public List<PlayerDto> convert(List<Player> players) {
        return players.stream().map(this::convert).collect(Collectors.toList());
    }
}
