package com.esport.dao.impl;

import com.esport.dao.inter.TournamentDao;
import com.esport.dao.inter.TournamentMetier;

public class TournamentMetierImpl implements TournamentMetier {
    private final TournamentDao tournamentDao;

    public TournamentMetierImpl(TournamentDao tournamentDao) {
        this.tournamentDao = tournamentDao;
    }

    @Override
    public Long obtainEstimatedDurationOfTournament(Long tournamentId) {
        return tournamentDao.calculateEstimatedDurationOfTournament(tournamentId);
    }

}
