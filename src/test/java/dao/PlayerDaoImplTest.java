package dao;

import com.esport.dao.impl.PlayerDaoImpl;
import com.esport.dao.inter.PlayerDao;
import com.esport.model.Player;
import com.esport.model.Team;
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

public class PlayerDaoImplTest {
    @Mock
    private EntityManager entityManager;

    @Mock
    private EntityTransaction transaction;

    @InjectMocks
    private PlayerDaoImpl playerDao;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        when(entityManager.getTransaction()).thenReturn(transaction);
    }

    @Test
    void savePlayerTest() {
        Team team = new Team();
        Player player = new Player("Player 1", 25, team);

        doNothing().when(entityManager).persist(player);

        Player savedPlayer = playerDao.save(player);

        verify(transaction, times(1)).begin();
        verify(entityManager, times(1)).persist(player);
        verify(transaction, times(1)).commit();
        assertNotNull(savedPlayer);
    }

    @Test
    void findPlayerByIdTest() {
        Team team = new Team();
        Player player = new Player("Player 1", 25, team);
        player.setId(1L);

        when(entityManager.find(Player.class, 1L)).thenReturn(player);

        Player result = playerDao.findById(1L);

        assertEquals(1L, result.getId());
        assertEquals("Player 1", result.getUsername());
        verify(entityManager, times(1)).find(Player.class, 1L);
    }

    @Test
    void findAllPlayersTest() {
        Team team = new Team();
        List<Player> players = Arrays.asList(
                new Player("Player 1", 25, team),
                new Player("Player 2", 30, team)
        );

        when(entityManager.createQuery("SELECT p FROM Player p", Player.class).getResultList()).thenReturn(players);

        List<Player> result = playerDao.findAll();

        assertEquals(2, result.size());
        verify(entityManager, times(1)).createQuery("SELECT p FROM Player p", Player.class);
    }

    @Test
    void updatePlayerTest() {
        Team team = new Team();
        Player player = new Player("Player 1", 25, team);
        player.setId(1L);
        player.setUsername("Updated Player");

        when(entityManager.merge(player)).thenReturn(player);
        when(entityManager.getTransaction()).thenReturn(transaction);

        Player updatedPlayer = playerDao.update(player);

        verify(transaction, times(1)).begin();
        verify(entityManager, times(1)).merge(player);
        verify(transaction, times(1)).commit();
        assertEquals("Updated Player", updatedPlayer.getUsername());
    }

    @Test
    void deletePlayerByIdTest() {
        Team team = new Team();
        Player player = new Player("Player 1", 25, team);
        player.setId(1L);

        when(entityManager.find(Player.class, 1L)).thenReturn(player);
        when(entityManager.getTransaction()).thenReturn(transaction);

        playerDao.delete(1L);

        verify(transaction, times(1)).begin();
        verify(entityManager, times(1)).remove(player);
        verify(transaction, times(1)).commit();
    }
}
