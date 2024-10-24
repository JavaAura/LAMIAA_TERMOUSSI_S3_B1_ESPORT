package com.esport.dao.inter;

import com.esport.model.Game;

import java.util.List;

public interface GameDao {
    Game createGame(Game game);
    Game getGameById(Long id);
    List<Game> getAllGames();
    Game updateGame(Game game);
    void deleteGame(Long id);
}
