package com.esport.dao.impl;

import com.esport.dao.inter.GameDao;
import com.esport.model.Game;
import com.esport.util.JPAUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class GameDaoImpl implements GameDao {

    private EntityManager entityManager;
    private static final Logger logger = LoggerFactory.getLogger(GameDaoImpl.class);

    public GameDaoImpl() {
        this.entityManager = JPAUtil.getEntityManager();
    }

    @Override
    public Game createGame(Game game) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(game);
            entityManager.getTransaction().commit();
            return game;
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            logger.error("Error creating game: " + e.getMessage(), e);
            return null;
        }
    }

    @Override
    public Game getGameById(Long id) {
        try {
            return entityManager.find(Game.class, id);
        } catch (Exception e) {
            logger.error("Error fetching game by ID: " + e.getMessage(), e);
            return null;
        }
    }

    @Override
    public List<Game> getAllGames() {
        try {
            return entityManager.createQuery("SELECT g FROM Game g", Game.class).getResultList();
        } catch (Exception e) {
            logger.error("Error fetching all games: " + e.getMessage(), e);
            return null;
        }
    }

    @Override
    public Game updateGame(Game game) {
        try {
            entityManager.getTransaction().begin();
            Game updatedGame = entityManager.merge(game);
            entityManager.getTransaction().commit();
            return updatedGame;
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            logger.error("Error updating game: " + e.getMessage(), e);
            return null;
        }
    }

    @Override
    public void deleteGame(Long id) {
        try {
            entityManager.getTransaction().begin();
            Game game = entityManager.find(Game.class, id);
            if (game != null) {
                entityManager.remove(game);
            }
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            logger.error("Error deleting game: " + e.getMessage(), e);
        }
    }
}
