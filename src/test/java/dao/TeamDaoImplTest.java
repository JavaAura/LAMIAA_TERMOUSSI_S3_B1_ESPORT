package dao;

import com.esport.dao.impl.TeamDaoImpl;
import com.esport.dao.inter.TeamDao;
import com.esport.model.Player;
import com.esport.model.Team;
import com.esport.model.Tournament;
import com.esport.util.JPAUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
public class TeamDaoImplTest {
    @Mock
    private EntityManager entityManager;

    @Mock
    private EntityTransaction transaction;

    @InjectMocks
    private TeamDaoImpl teamDao;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        when(entityManager.getTransaction()).thenReturn(transaction);
    }

    @Test
    void saveTeamTest() {
        Player player = new Player("Player 1", 25, null); // Create a Player instance
        Team team = new Team("Team A", Arrays.asList(player), null, 1); // Create a Team instance

        doNothing().when(entityManager).persist(team); // Simulate persist operation

        Team savedTeam = teamDao.save(team);

        verify(transaction, times(1)).begin();
        verify(entityManager, times(1)).persist(team);
        verify(transaction, times(1)).commit();
        assertNotNull(savedTeam);
    }

    @Test
    void findTeamByIdTest() {
        Player player = new Player("Player 1", 25, null); // Create a Player instance
        Team team = new Team("Team A", Arrays.asList(player), null, 1);
        team.setId(1L); // Set ID for the team

        when(entityManager.find(Team.class, 1L)).thenReturn(team);

        Team result = teamDao.findById(1L);

        assertEquals(1L, result.getId());
        assertEquals("Team A", result.getName());
        verify(entityManager, times(1)).find(Team.class, 1L);
    }

    @Test
    void findAllTeamsTest() {
        Player player1 = new Player("Player 1", 25, null); // Create a Player instance
        Player player2 = new Player("Player 2", 30, null); // Create another Player instance
        List<Team> teams = Arrays.asList(
                new Team("Team A", Arrays.asList(player1), null, 1),
                new Team("Team B", Arrays.asList(player2), null, 2)
        );

        when(entityManager.createQuery("SELECT t FROM Team t", Team.class).getResultList()).thenReturn(teams);

        List<Team> result = teamDao.findAll();

        assertEquals(2, result.size());
        verify(entityManager, times(1)).createQuery("SELECT t FROM Team t", Team.class);
    }

    @Test
    void updateTeamTest() {
        Player player = new Player("Player 1", 25, null); // Create a Player instance
        Team team = new Team("Team A", Arrays.asList(player), null, 1);
        team.setId(1L); // Set ID for the team
        team.setName("Updated Team");

        when(entityManager.merge(team)).thenReturn(team);
        when(entityManager.getTransaction()).thenReturn(transaction);

        Team updatedTeam = teamDao.update(team);

        verify(transaction, times(1)).begin();
        verify(entityManager, times(1)).merge(team);
        verify(transaction, times(1)).commit();
        assertEquals("Updated Team", updatedTeam.getName());
    }

    @Test
    void deleteTeamByIdTest() {
        Player player = new Player("Player 1", 25, null); // Create a Player instance
        Team team = new Team("Team A", Arrays.asList(player), null, 1);
        team.setId(1L); // Set ID for the team

        when(entityManager.find(Team.class, 1L)).thenReturn(team);
        when(entityManager.getTransaction()).thenReturn(transaction);

        teamDao.delete(1L);

        verify(transaction, times(1)).begin();
        verify(entityManager, times(1)).remove(team);
        verify(transaction, times(1)).commit();
    }
}
