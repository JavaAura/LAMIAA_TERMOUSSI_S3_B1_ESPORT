package com.esport.service;

import com.esport.model.Game;
import com.esport.repository.impl.GameRepositoryImpl;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class GameService {
    private final GameRepositoryImpl gameRepository;

    public GameService() {
        this.gameRepository = new GameRepositoryImpl();

    }

    public Game addGame(Game game) {
        return gameRepository.addGame(game);
    }
    public Optional<Game> findGameById(Long id) {
        return Optional.ofNullable(gameRepository.findGameById(id));
    }

    public List<Game> getAllGames() {
        List<Game> games = gameRepository.getAllGames();
        return games != null ? games : Collections.emptyList();
    }

    public Game modifyGame(Game game) {
        return gameRepository.modifyGame(game);
    }

    public void removeGame(Long id) {
        gameRepository.removeGame(id);
    }

}
