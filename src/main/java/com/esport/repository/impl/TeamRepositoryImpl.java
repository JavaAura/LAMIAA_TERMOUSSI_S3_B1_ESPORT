package com.esport.repository.impl;

import com.esport.dao.impl.TeamDaoImpl;
import com.esport.dao.inter.TeamDao;
import com.esport.model.Team;
import com.esport.repository.inter.TeamRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import java.util.List;

public class TeamRepositoryImpl implements TeamRepository {

    private static final Logger logger = LoggerFactory.getLogger(TeamRepositoryImpl.class);
    private static final String PERSISTENCE_UNIT_NAME = "cycloneJPA";
    private final TeamDao teamDAO;
    @PersistenceContext
    private EntityManager entityManager;
    private EntityManagerFactory emf;

    public TeamRepositoryImpl() {
        this.teamDAO = new TeamDaoImpl();
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
    public Team addTeam(Team team) {
        return teamDAO.save(team);
    }

    @Override
    public Team findTeamById(Long id) {
        return teamDAO.findById(id);
    }

    @Override
    public List<Team> getAllTeams() {
        return teamDAO.findAll();
    }

    @Override
    public Team modifyTeam(Team team) {
        return teamDAO.update(team);
    }

    @Override
    public void removeTeam(Long id) {
        teamDAO.delete(id);
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
