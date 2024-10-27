package com.esport.util;

import java.time.LocalDate;

public class InputValidator {

    public boolean validateUsername(String username) {
        return username != null && !username.trim().isEmpty() ;
    }

    public boolean validateAge(int age) {
        return age > 0 && age < 120;
    }

    public boolean validateTeamId(Long teamId) {
        return teamId != null && teamId > 0;
    }
    public boolean validateTitle(String title) {
        return title != null && !title.trim().isEmpty();
    }

    public boolean validateGameId(Long gameId) {
        return gameId != null && gameId > 0;
    }

    public boolean validateDate(LocalDate date) {
        return date != null && !date.isBefore(LocalDate.now());
    }

    public boolean validateSpectatorCount(int spectatorCount) {
        return spectatorCount >= 0;
    }

    public boolean validateDuration(int duration) {
        return duration > 0;
    }

    public boolean validateBreakTime(int breakTime) {
        return breakTime >= 0;
    }

    public boolean validateCeremonyTime(int ceremonyTime) {
        return ceremonyTime >= 0;
    }
}
