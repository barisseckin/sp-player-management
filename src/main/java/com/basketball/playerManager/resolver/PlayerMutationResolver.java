package com.basketball.playerManager.resolver;

import com.basketball.playerManager.dtos.PlayerDto;
import com.basketball.playerManager.dtos.converter.PlayerDtoConverter;
import com.basketball.playerManager.dtos.request.CreatePlayerRequest;
import com.basketball.playerManager.exception.NotFoundException;
import com.basketball.playerManager.model.Player;
import com.basketball.playerManager.repository.PlayerRepository;
import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class PlayerMutationResolver implements GraphQLMutationResolver {

    private final PlayerRepository playerRepository;

    private final PlayerDtoConverter playerDtoConverter;

    public PlayerDto save(CreatePlayerRequest request) {
        if (playerRepository.findAll().size() < 12) {
            Player player = new Player();
            player.setName(request.getName());
            player.setSurname(request.getSurname());
            player.setPositionType(request.getPositionType());
            player.setTeam(request.getTeam());

            return playerDtoConverter.convert(playerRepository.save(player));
        }

        return null;
    }

    public PlayerDto deleteByName(String name) {
        Optional<Player> player =  playerRepository.findPlayerByName(name);

        if (player.isPresent()) {
            playerRepository.deleteById(player.get().getId());
            return playerDtoConverter.convert(player.get());
        }
        else {
            throw new NotFoundException("Player not found, name: " + name);
        }
    }

}
