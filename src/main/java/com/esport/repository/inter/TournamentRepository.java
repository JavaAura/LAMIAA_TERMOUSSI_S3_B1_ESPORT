package com.esport.repository.inter;

import com.esport.model.Tournament;

import java.util.List;

public interface TournamentRepository {
    Tournament addTournament(Tournament tournament);

    Tournament findTournamentById(Long id);

    List<Tournament> getAllTournaments();

    Tournament modifyTournament(Tournament tournament);

    void removeTournament(Long id);
    long calculateEstimatedDurationOfTournament(Long tournamentId);

}
