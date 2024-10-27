package dao;

import com.esport.dao.impl.GameDaoImpl;
import com.esport.dao.inter.GameDao;
import com.esport.model.Game;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class GameDaoImplTest {
    @Mock
    private EntityManager entityManager;

    @Mock
    private EntityTransaction transaction;

    @InjectMocks
    private GameDaoImpl gameDao;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        when(entityManager.getTransaction()).thenReturn(transaction);
    }

    @Test
    void createGameTest() {
        Game game = new Game();
        game.setName("Game 1");

        doNothing().when(entityManager).persist(game); // Simulate persist operation

        Game createdGame = gameDao.createGame(game);

        verify(transaction, times(1)).begin();
        verify(entityManager, times(1)).persist(game);
        verify(transaction, times(1)).commit();
        assertNotNull(createdGame);
    }

    @Test
    void getGameByIdTest() {
        Game game = new Game();
        game.setId(1L);
        when(entityManager.find(Game.class, 1L)).thenReturn(game);

        Game result = gameDao.getGameById(1L);

        assertEquals(1L, result.getId());
        verify(entityManager, times(1)).find(Game.class, 1L);
    }

    @Test
    void getAllGamesTest() {
        List<Game> games = Arrays.asList(new Game(), new Game());
        when(entityManager.createQuery("SELECT g FROM Game g", Game.class).getResultList()).thenReturn(games);

        List<Game> result = gameDao.getAllGames();

        assertEquals(2, result.size());
        verify(entityManager, times(1)).createQuery("SELECT g FROM Game g", Game.class);
    }

    @Test
    void updateGameTest() {
        Game game = new Game();
        game.setId(1L);
        game.setName("Updated Game");

        when(entityManager.merge(game)).thenReturn(game);
        when(entityManager.getTransaction()).thenReturn(transaction);

        Game updatedGame = gameDao.updateGame(game);

        verify(transaction, times(1)).begin();
        verify(entityManager, times(1)).merge(game);
        verify(transaction, times(1)).commit();
        assertEquals("Updated Game", updatedGame.getName());
    }

    @Test
    void deleteGameByIdTest() {
        Game game = new Game();
        game.setId(1L);

        when(entityManager.find(Game.class, 1L)).thenReturn(game);
        when(entityManager.getTransaction()).thenReturn(transaction);

        gameDao.deleteGame(1L);

        verify(transaction, times(1)).begin();
        verify(entityManager, times(1)).remove(game);
        verify(transaction, times(1)).commit();
    }

}
