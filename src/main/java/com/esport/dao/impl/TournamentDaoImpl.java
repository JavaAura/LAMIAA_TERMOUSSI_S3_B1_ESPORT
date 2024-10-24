package com.esport.dao.impl;

import com.esport.model.Tournament;
import com.esport.util.JPAUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.esport.dao.inter.TournamentDao;

import javax.persistence.EntityManager;
import java.util.List;

public class TournamentDaoImpl implements TournamentDao {

    private EntityManager entityManager;
    private static final Logger logger = LoggerFactory.getLogger(TournamentDaoImpl.class);

    public TournamentDaoImpl() {
        this.entityManager = JPAUtil.getEntityManager();
    }

    @Override
    public Tournament save(Tournament tournament) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(tournament);
            entityManager.getTransaction().commit();
            return tournament;
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            logger.error("Error saving tournament: " + e.getMessage(), e);
            return null;
        }
    }

    @Override
    public Tournament update(Tournament tournament) {
        try {
            entityManager.getTransaction().begin();
            Tournament updatedTournament = entityManager.merge(tournament);
            entityManager.getTransaction().commit();
            return updatedTournament;
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            logger.error("Error updating tournament: " + e.getMessage(), e);
            return null;
        }
    }

    @Override
    public Tournament findById(Long id) {
        try {
            return entityManager.find(Tournament.class, id);
        } catch (Exception e) {
            logger.error("Error fetching tournament by ID: " + e.getMessage(), e);
            return null;
        }
    }

    @Override
    public List<Tournament> findAll() {
        try {
            return entityManager.createQuery("SELECT t FROM Tournament t", Tournament.class).getResultList();
        } catch (Exception e) {
            logger.error("Error fetching all tournaments: " + e.getMessage(), e);
            return null;
        }
    }

    @Override
    public void delete(Long id) {
        try {
            entityManager.getTransaction().begin();
            Tournament tournament = entityManager.find(Tournament.class, id);
            if (tournament != null) {
                entityManager.remove(tournament);
            }
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            logger.error("Error deleting tournament: " + e.getMessage(), e);
        }
    }
}
