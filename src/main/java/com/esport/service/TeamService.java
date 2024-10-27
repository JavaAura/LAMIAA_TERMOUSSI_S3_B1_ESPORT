package com.esport.service;

import com.esport.model.Team;
import com.esport.repository.impl.TeamRepositoryImpl;

import java.util.*;
import java.util.stream.Collectors;

public class TeamService {
    private final TeamRepositoryImpl teamRepository;
    private Map<Long, Team> teamMap;

    public TeamService() {
        this.teamMap = new HashMap<>();
        this.teamRepository = new TeamRepositoryImpl();
    }

    private void loadTeamsMap() {
        teamMap = teamRepository.getAllTeams()
                .stream()
                .collect(Collectors.toMap(Team::getId, team -> team));
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
