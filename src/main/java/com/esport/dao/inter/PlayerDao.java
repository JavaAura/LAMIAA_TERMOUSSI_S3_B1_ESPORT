package com.esport.dao.inter;

import com.esport.model.Player;

import java.util.List;

    public interface PlayerDao {
    Player findById(Long id);
    List<Player> findAll();
    Player save(Player player);
    Player update(Player player);
    void delete(Long id);
}
