package com.esport.repository.impl;

import com.esport.dao.impl.PlayerDaoImpl;
import com.esport.dao.inter.PlayerDao;
import com.esport.model.Player;
import com.esport.repository.inter.PlayerRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class PlayerRepositoryImpl implements PlayerRepository {
    private static final Logger logger = LoggerFactory.getLogger(PlayerRepositoryImpl.class);
    private static final String PERSISTENCE_UNIT_NAME = "cycloneJPA";
    private final PlayerDao playerDAO;

    private EntityManager entityManager;
    private EntityManagerFactory emf;

    public PlayerRepositoryImpl() {
        this.playerDAO = new PlayerDaoImpl();
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
    public Player addPlayer(Player player) {
        return playerDAO.save(player);
    }

    @Override
    public Player findPlayerById(Long id) {
        return playerDAO.findById(id);
    }

    @Override
    public List<Player> getAllPlayers() {
        return playerDAO.findAll();
    }

    @Override
    public Player modifyPlayer(Player player) {
        return playerDAO.update(player);
    }

    @Override
    public void removePlayer(Long id) {
        playerDAO.delete(id);
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
