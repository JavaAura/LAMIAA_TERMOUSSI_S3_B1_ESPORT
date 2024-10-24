package com.esport.service;

import com.esport.model.Team;
import com.esport.repository.impl.TeamRepositoryImpl;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class TeamService {
    private final TeamRepositoryImpl teamRepository;

    public TeamService() {
        this.teamRepository = new TeamRepositoryImpl();
    }

    public Team addTeam(Team team) {
        return teamRepository.addTeam(team);
    }

    public Optional<Team> findTeamById(Long id) {
        return Optional.ofNullable(teamRepository.findTeamById(id));
    }

    public List<Team> getAllTeams() {
        List<Team> teams = teamRepository.getAllTeams();
        return teams != null ? teams : Collections.emptyList();
    }

    public Team modifyTeam(Team team) {
        return teamRepository.modifyTeam(team);
    }

    public void removeTeam(Long id) {
        teamRepository.removeTeam(id);
    }
}
