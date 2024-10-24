package com.esport.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@Entity
@Table(name = "tournaments")
public class Tournament {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "game", nullable = false)
    private String game;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;

    @Column(name = "spectator_count")
    private int spectatorCount;

    @ManyToMany(mappedBy = "tournaments")
    private List<Team> teams = new ArrayList<>();

    @Column(name = "estimated_duration")
    private int estimatedDuration;

    @Column(name = "break_time")
    private int breakTime;

    @Column(name = "ceremony_time")
    private int ceremonyTime;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private TournamentStatus status;

    public Tournament() {}

    public Tournament(String title, String game, LocalDate startDate, LocalDate endDate,
                      int spectatorCount, List<Team> teams, int estimatedDuration,
                      int breakTimeBetweenMatches, int ceremonyTime, TournamentStatus status) {
        this.title = title;
        this.game = game;
        this.startDate = startDate;
        this.endDate = endDate;
        this.spectatorCount = spectatorCount;
        this.teams = teams;
        this.estimatedDuration = estimatedDuration;
        this.breakTime = breakTimeBetweenMatches;
        this.ceremonyTime = ceremonyTime;
        this.status = status;
    }
    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGame() {
        return game;
    }

    public void setGame(String game) {
        this.game = game;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public int getSpectatorCount() {
        return spectatorCount;
    }

    public void setSpectatorCount(int spectatorCount) {
        this.spectatorCount = spectatorCount;
    }

    public List<Team> getTeams() {
        return teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }

    public int getEstimatedDuration() {
        return estimatedDuration;
    }

    public void setEstimatedDuration(int estimatedDuration) {
        this.estimatedDuration = estimatedDuration;
    }

    public int getBreakTime() {
        return breakTime;
    }

    public void setBreakTime(int breakTime) {
        this.breakTime = breakTime;
    }

    public int getCeremonyTime() {

        return ceremonyTime;
    }

    public void setCeremonyTime(int ceremonyTime) {

        this.ceremonyTime = ceremonyTime;
    }

    public TournamentStatus  getStatus() {
        return status;
    }

    public void setStatus(TournamentStatus  status) {
        this.status = status;
    }
}
