package com.esport.repository.inter;

import com.esport.model.Team;

import java.util.List;

public interface TeamRepository {
    Team addTeam(Team team);

    Team findTeamById(Long id);

    List<Team> getAllTeams();

    Team modifyTeam(Team team);

    void removeTeam(Long id);

}
