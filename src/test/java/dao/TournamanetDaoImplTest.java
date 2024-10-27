package dao;

import com.esport.dao.impl.TournamentDaoImpl;
import com.esport.model.Team;
import com.esport.model.Tournament;
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
public class TournamanetDaoImplTest {
    @Mock
    private EntityManager entityManager;

    @Mock
    private EntityTransaction transaction;

    @InjectMocks
    private TournamentDaoImpl tournamentDao;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        when(entityManager.getTransaction()).thenReturn(transaction);
    }

    @Test
    void saveTournamentTest() {
        Tournament tournament = new Tournament();
        tournament.setTitle("Tournament 1");

        doNothing().when(entityManager).persist(tournament);

        Tournament savedTournament = tournamentDao.save(tournament);

        verify(transaction, times(1)).begin();
        verify(entityManager, times(1)).persist(tournament);
        verify(transaction, times(1)).commit();
        assertNotNull(savedTournament);
    }

    @Test
    void updateTournamentTest() {
        Tournament tournament = new Tournament();
        tournament.setId(1L);
        tournament.setTitle("Updated Tournament");

        when(entityManager.merge(tournament)).thenReturn(tournament);

        Tournament updatedTournament = tournamentDao.update(tournament);

        verify(transaction, times(1)).begin();
        verify(entityManager, times(1)).merge(tournament);
        verify(transaction, times(1)).commit();
        assertEquals("Updated Tournament", updatedTournament.getTitle());
    }

    @Test
    void findByIdTest() {
        Tournament tournament = new Tournament();
        tournament.setId(1L);
        when(entityManager.find(Tournament.class, 1L)).thenReturn(tournament);

        Tournament result = tournamentDao.findById(1L);

        assertEquals(1L, result.getId());
        verify(entityManager, times(1)).find(Tournament.class, 1L);
    }

    @Test
    void findAllTest() {
        List<Tournament> tournaments = Arrays.asList(new Tournament(), new Tournament());
        when(entityManager.createQuery("SELECT t FROM Tournament t", Tournament.class).getResultList()).thenReturn(tournaments);

        List<Tournament> result = tournamentDao.findAll();

        assertEquals(2, result.size());
        verify(entityManager, times(1)).createQuery("SELECT t FROM Tournament t", Tournament.class);
    }

    @Test
    void deleteTournamentTest() {
        Tournament tournament = new Tournament();
        tournament.setId(1L);

        when(entityManager.find(Tournament.class, 1L)).thenReturn(tournament);

        tournamentDao.delete(1L);

        verify(transaction, times(1)).begin();
        verify(entityManager, times(1)).remove(tournament);
        verify(transaction, times(1)).commit();
    }

    @Test
    void calculateEstimatedDurationOfTournamentTest() {

        Team teamA = new Team();
        teamA.setName("Team A");

        Team teamB = new Team();
        teamB.setName("Team B");

        Tournament tournament = new Tournament();
        tournament.setTeams(Arrays.asList(teamA, teamB)); // Use Team instances
        tournament.setEstimatedDuration(30); // 30 minutes per match
        tournament.setBreakTime(15); // 15 minutes break time

        when(entityManager.find(Tournament.class, 1L)).thenReturn(tournament);

        long estimatedDuration = tournamentDao.calculateEstimatedDurationOfTournament(1L);

        assertEquals(75, estimatedDuration); // (2 teams * 30) + 15 break
    }
}
