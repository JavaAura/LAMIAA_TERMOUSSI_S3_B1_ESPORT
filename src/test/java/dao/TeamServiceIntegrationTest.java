package dao;

import com.esport.model.Player;
import com.esport.model.Team;
import com.esport.repository.impl.TeamRepositoryImpl;
import com.esport.service.TeamService;
import com.esport.util.JPAUtil;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
public class TeamServiceIntegrationTest {
    private TeamService teamService;
    private EntityManager entityManager;

    @BeforeEach
    void setUp() {

        entityManager = JPAUtil.getEntityManager();
        teamService = new TeamService();
    }

    @AfterEach
    void tearDown() {

        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.createQuery("DELETE FROM Team").executeUpdate();
        transaction.commit();
        entityManager.close();
    }

    @Test
    void testAddAndFindTeam() {
        Player player = new Player("Player 1", 25, null);
        Team team = new Team("Team A", Arrays.asList(player), Collections.emptyList(), 1);

        Team addedTeam = teamService.addTeam(team);
        assertNotNull(addedTeam);
        assertNotNull(addedTeam.getId());

        Optional<Team> foundTeam = teamService.findTeamById(addedTeam.getId());
        assertTrue(foundTeam.isPresent());
        assertEquals("Team A", foundTeam.get().getName());
    }

    @Test
    void testGetAllTeams() {
        Player player1 = new Player("Player 1", 25, null);
        Team team1 = new Team("Team A", Arrays.asList(player1), Collections.emptyList(), 1);

        teamService.addTeam(team1);

        Player player2 = new Player("Player 2", 30, null);
        Team team2 = new Team("Team B", Arrays.asList(player2), Collections.emptyList(), 2);

        teamService.addTeam(team2);

        List<Team> teams = teamService.getAllTeams();
        assertEquals(2, teams.size());
    }


    @Test
    void testModifyTeam() {
        Player player = new Player("Player 1", 25, null);
        Team team = new Team("Team A", Arrays.asList(player), Collections.emptyList(), 1);

        Team addedTeam = teamService.addTeam(team);

        addedTeam.setName("Updated Team A");
        Team modifiedTeam = teamService.modifyTeam(addedTeam);

        assertNotNull(modifiedTeam);
        assertEquals("Updated Team A", modifiedTeam.getName());
    }

    @Test
    void testRemoveTeam() {
        Player player = new Player("Player 1", 25, null);
        Team team = new Team("Team A", Arrays.asList(player), Collections.emptyList(), 1); 

        Team addedTeam = teamService.addTeam(team);

        teamService.removeTeam(addedTeam.getId());

        Optional<Team> foundTeam = teamService.findTeamById(addedTeam.getId());
        assertFalse(foundTeam.isPresent());
    }


}
