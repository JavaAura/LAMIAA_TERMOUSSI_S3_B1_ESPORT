package com.esport.repository.inter;

import com.esport.model.Player;

import java.util.List;

public interface PlayerRepository {
    Player addPlayer(Player player);

    Player findPlayerById(Long id);

    List<Player> getAllPlayers();

    Player modifyPlayer(Player player);

    void removePlayer(Long id);

}
