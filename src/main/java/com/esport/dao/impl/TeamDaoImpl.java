package com.esport.dao.impl;

import com.esport.dao.inter.TeamDao;
import com.esport.model.Team;
import com.esport.util.JPAUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.persistence.EntityManager;
import java.util.List;

public class TeamDaoImpl implements TeamDao {
    private EntityManager entityManager;
    private static final Logger logger = LoggerFactory.getLogger(TeamDaoImpl.class);

    public TeamDaoImpl() {
        this.entityManager = JPAUtil.getEntityManager();
    }

    @Override
    public Team save(Team team) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(team);
            entityManager.getTransaction().commit();
            return team;
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            logger.error("Error saving team: " + e.getMessage(), e);
            return null;
        }
    }

    @Override
    public Team update(Team team) {
        try {
            entityManager.getTransaction().begin();
            Team updatedTeam = entityManager.merge(team);
            entityManager.getTransaction().commit();
            return updatedTeam;
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            logger.error("Error updating team: " + e.getMessage(), e);
            return null;
        }
    }

    @Override
    public Team findById(Long id) {
        try {
            return entityManager.find(Team.class, id);
        } catch (Exception e) {
            logger.error("Error fetching team by ID: " + e.getMessage(), e);
            return null;
        }
    }

    @Override
    public List<Team> findAll() {
        try {
            return entityManager.createQuery("SELECT t FROM Team t", Team.class).getResultList();
        } catch (Exception e) {
            logger.error("Error fetching all teams: " + e.getMessage(), e);
            return null;
        }
    }

    @Override
    public void delete(Long id) {
        try {
            entityManager.getTransaction().begin();
            Team team = entityManager.find(Team.class, id);
            if (team != null) {
                entityManager.remove(team);
            }
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            logger.error("Error deleting team: " + e.getMessage(), e);
        }
    }
}
