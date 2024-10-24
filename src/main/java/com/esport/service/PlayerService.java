package com.esport.service;

import com.esport.model.Player;
import com.esport.repository.impl.PlayerRepositoryImpl;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class PlayerService {
    private final PlayerRepositoryImpl playerRepository;

    public PlayerService() {
        this.playerRepository = new PlayerRepositoryImpl();
    }

    public Player addPlayer(Player player) {
        return playerRepository.addPlayer(player);
    }

    public Optional<Player> findPlayerById(Long id) {
        return Optional.ofNullable(playerRepository.findPlayerById(id));
    }

    public List<Player> getAllPlayers() {
        List<Player> players = playerRepository.getAllPlayers();
        return players != null ? players : Collections.emptyList();
    }

    public Player modifyPlayer(Player player) {
        return playerRepository.modifyPlayer(player);
    }

    public void removePlayer(Long id) {
        playerRepository.removePlayer(id);
    }
}
