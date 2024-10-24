package com.esport.dao.impl;

import com.esport.dao.inter.PlayerDao;
import com.esport.model.Player;
import com.esport.util.JPAUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.persistence.EntityManager;
import java.util.List;

public class PlayerDaoImpl implements PlayerDao {

    private EntityManager entityManager;
    private static final Logger logger = LoggerFactory.getLogger(PlayerDaoImpl.class);

    public PlayerDaoImpl() {
        this.entityManager = JPAUtil.getEntityManager();
    }

    @Override
    public Player findById(Long id) {
        try {
            return entityManager.find(Player.class, id);
        } catch (Exception e) {
            logger.error("Error fetching player by ID: " + e.getMessage(), e);
            return null;
        }
    }

    @Override
    public List<Player> findAll() {
        try {
            return entityManager.createQuery("SELECT p FROM Player p", Player.class).getResultList();
        } catch (Exception e) {
            logger.error("Error fetching all players: " + e.getMessage(), e);
            return null;
        }
    }

    @Override
    public Player save(Player player) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(player);
            entityManager.getTransaction().commit();
            return player;
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            logger.error("Error saving player: " + e.getMessage(), e);
            return null;
        }
    }

    @Override
    public Player update(Player player) {
        try {
            entityManager.getTransaction().begin();
            Player updatedPlayer = entityManager.merge(player);
            entityManager.getTransaction().commit();
            return updatedPlayer;
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            logger.error("Error updating player: " + e.getMessage(), e);
            return null;
        }
    }


    @Override
    public void delete(Long id) {
        try {
            entityManager.getTransaction().begin();
            Player player = entityManager.find(Player.class, id);
            if (player != null) {
                entityManager.remove(player);
            }
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            logger.error("Error deleting player: " + e.getMessage(), e);
        }
    }
}
