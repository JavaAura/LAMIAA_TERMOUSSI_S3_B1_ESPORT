package com.esport.repository.inter;

import com.esport.model.Game;

import java.util.List;

public interface GameRepository {

    Game addGame(Game game);

    Game findGameById(Long id);

    List<Game> getAllGames();

    Game modifyGame(Game game);

    void removeGame(Long id);

}
