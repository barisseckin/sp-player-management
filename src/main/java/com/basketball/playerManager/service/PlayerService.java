package com.basketball.playerManager.service;

import com.basketball.playerManager.dtos.PlayerDto;
import com.basketball.playerManager.dtos.converter.PlayerDtoConverter;
import com.basketball.playerManager.dtos.request.CreatePlayerRequest;
import com.basketball.playerManager.dtos.request.UpdatePlayerRequest;
import com.basketball.playerManager.model.Player;
import com.basketball.playerManager.model.PlayerPositionType;
import com.basketball.playerManager.repository.PlayerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PlayerService {

    private final PlayerRepository playerRepository;

    private final PlayerDtoConverter playerDtoConverter;

    public PlayerDto save(CreatePlayerRequest request) {
        Player player = new Player(request.getName(),
                request.getSurname(),
                request.getPositionType(),
                request.getTeam());

        if (playerRepository.findAll().size() < 12) {
            return playerDtoConverter.convert(playerRepository.save(player));
        }

        return null;
    }

    public PlayerDto deleteByName(String name) {
       Optional<Player> player = playerRepository.findPlayerByName(name);
       playerRepository.deleteById(player.get().getId());

       return playerDtoConverter.convert(player.get());
    }

    public List<PlayerDto> getAll() {
        return playerDtoConverter.convert(playerRepository.findAll());
    }

   public List<PlayerDto> getByPosition(PlayerPositionType type) {
        return playerDtoConverter.convert(playerRepository.findPlayerByPositionType(type));
   }

   public PlayerDto update(String name, UpdatePlayerRequest request) {
        Optional<Player> player = playerRepository.findPlayerByName(name);

        Player updatedPlayer = new Player(player.get().getId(),
                player.get().getName(),
                player.get().getSurname(),
                request.getPositionType(),
                request.getTeam());

        return playerDtoConverter.convert(playerRepository.save(updatedPlayer));
   }

}
