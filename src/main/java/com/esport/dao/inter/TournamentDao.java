package com.esport.dao.inter;

import com.esport.model.Tournament;

import java.util.List;

public interface TournamentDao {
    Tournament findById(Long id);
    List<Tournament> findAll();
    Tournament save(Tournament tournament);
    Tournament update(Tournament tournament);
    void delete(Long id);

    Long calculateEstimatedDurationOfTournament(Long tournamentId);
}
