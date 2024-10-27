package com.esport.service.inter;

import com.esport.model.Tournament;

import java.util.List;
import java.util.Optional;

public interface TournamentServiceInter {
    Tournament addTournament(Tournament tournament);

    Optional<Tournament> findTournamentById(Long id);

    List<Tournament> getAllTournaments();

    Tournament modifyTournament(Tournament tournament);

    void removeTournament(Long id);

    void addTeamToTournament(Long tournamentId, Long teamId);

    void removeTeamFromTournament(Long tournamentId, Long teamId);
    long obtainEstimatedDurationOfTournament(Long tournamentId);
}
