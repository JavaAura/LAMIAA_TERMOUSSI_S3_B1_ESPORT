package com.esport.repository.impl;

import com.esport.dao.impl.TournamentDaoImpl;
import com.esport.dao.inter.TournamentDao;
import com.esport.model.Tournament;
import com.esport.repository.inter.TournamentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import java.util.List;

public class TournamentRepositoryImpl implements TournamentRepository {
    private static final Logger logger = LoggerFactory.getLogger(TournamentRepositoryImpl.class);
    private static final String PERSISTENCE_UNIT_NAME = "cycloneJPA";
    private final TournamentDao tournamentDAO;

    @PersistenceContext
    private EntityManager entityManager;
    private EntityManagerFactory emf;

    public TournamentRepositoryImpl() {
        this.tournamentDAO = new TournamentDaoImpl();
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
    public Tournament addTournament(Tournament tournament) {
        return tournamentDAO.save(tournament);
    }

    @Override
    public Tournament findTournamentById(Long id) {
        return tournamentDAO.findById(id);
    }

    @Override
    public List<Tournament> getAllTournaments() {
        return tournamentDAO.findAll();
    }

    @Override
    public Tournament modifyTournament(Tournament tournament) {
        return tournamentDAO.update(tournament);
    }

    @Override
    public void removeTournament(Long id) {
        tournamentDAO.delete(id);
    }
    @Override
    public long calculateEstimatedDurationOfTournament(Long tournamentId) {
        return tournamentDAO.calculateEstimatedDurationOfTournament(tournamentId);
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

