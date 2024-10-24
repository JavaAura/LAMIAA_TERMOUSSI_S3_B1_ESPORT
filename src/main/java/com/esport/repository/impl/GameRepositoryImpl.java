package com.esport.repository.impl;

import com.esport.dao.impl.GameDaoImpl;
import com.esport.dao.inter.GameDao;
import com.esport.model.Game;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.esport.repository.inter.GameRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class GameRepositoryImpl implements GameRepository {
    private static final Logger logger = LoggerFactory.getLogger(GameRepositoryImpl.class);
    private static final String PERSISTENCE_UNIT_NAME = "cycloneJPA";
    private final GameDao gameDAO;

    private EntityManager entityManager;
    private EntityManagerFactory emf;

    public GameRepositoryImpl() {
        this.gameDAO = new GameDaoImpl();
        initEntityManager();
    }

    private void initEntityManager() {
        try {
            emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
            entityManager = emf.createEntityManager();
            logger.info("EntityManager initialized successfully");
        } catch (Exception e) {
            logger.error("Failed to initialize EntityManager", e);
        }
    }

    @Override
    public Game addGame(Game game) {
        return gameDAO.createGame(game);
    }

    @Override
    public Game findGameById(Long id) {
        return gameDAO.getGameById(id);
    }

    @Override
    public List<Game> getAllGames() {
        return gameDAO.getAllGames();
    }

    @Override
    public Game modifyGame(Game game) {
        return gameDAO.updateGame(game);
    }

    @Override
    public void removeGame(Long id) {
        gameDAO.deleteGame(id);
    }


    public void close() {
        if (entityManager != null && entityManager.isOpen()) {
            entityManager.close();
        }
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
        logger.info("EntityManager and EntityManagerFactory closed");
    }
}
