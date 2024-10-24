package com.esport.service;

import com.esport.model.Tournament;
import com.esport.repository.impl.TournamentRepositoryImpl;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class TournamentService {
    private final TournamentRepositoryImpl tournamentRepository;

    public TournamentService() {
        this.tournamentRepository = new TournamentRepositoryImpl();
    }

    public Tournament addTournament(Tournament tournament) {
        return tournamentRepository.addTournament(tournament);
    }

    public Optional<Tournament> findTournamentById(Long id) {
        return Optional.ofNullable(tournamentRepository.findTournamentById(id));
    }

    public List<Tournament> getAllTournaments() {
        List<Tournament> tournaments = tournamentRepository.getAllTournaments();
        return tournaments != null ? tournaments : Collections.emptyList();
    }

    public Tournament modifyTournament(Tournament tournament) {
        return tournamentRepository.modifyTournament(tournament);
    }

    public void removeTournament(Long id) {
        tournamentRepository.removeTournament(id);
    }
}
