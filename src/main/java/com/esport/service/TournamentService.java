package com.esport.service;

import com.esport.model.Team;
import com.esport.model.Tournament;
import com.esport.repository.impl.TeamRepositoryImpl;
import com.esport.repository.impl.TournamentRepositoryImpl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class TournamentService {
    private final TournamentRepositoryImpl tournamentRepository;
    @PersistenceContext
    private EntityManager entityManager;
    private final TeamRepositoryImpl teamRepository;
    public TournamentService() {
        this.teamRepository = new TeamRepositoryImpl();
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
    @Transactional
    public void addTeamToTournament(Long tournamentId, Long teamId) {
        Tournament tournament = tournamentRepository.findTournamentById(tournamentId);
        Team team = teamRepository.findTeamById(teamId);

        if (tournament != null && team != null) {
            tournament.getTeams().add(team);
            team.getTournaments().add(tournament);
            tournamentRepository.modifyTournament(tournament);
            teamRepository.modifyTeam(team);

            System.out.println("Team added to the tournament successfully.");
        } else {
            System.out.println("Either the tournament or team does not exist.");
        }
    }

}
