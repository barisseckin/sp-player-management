package com.basketball.playerManager.repository;

import com.basketball.playerManager.model.Player;
import com.basketball.playerManager.model.PlayerPositionType;
import com.basketball.playerManager.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Integer> {
    Optional<Player> findPlayerByName(String name);
    List<Player> findPlayerByPositionType(PlayerPositionType type);
}
