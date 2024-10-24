package com.esport.dao.inter;

import com.esport.model.Team;

import java.util.List;

public interface TeamDao {
    Team findById(Long id);
    List<Team> findAll();
    Team save(Team team);
    Team update(Team team);
    void delete(Long id);
}
