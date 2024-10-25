package com.esport.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.NotNull;
@Entity
@Table(name = "tournaments")
public class Tournament {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "title", nullable = false)
    private String title;

    @ManyToOne
    @JoinColumn(name = "game_id", nullable = false)
    private Game game;

    @NotNull
    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @NotNull
    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;

    @NotNull
    @Column(name = "spectator_count")
    private int spectatorCount;


    @ManyToMany(mappedBy = "tournaments")
    private List<Team> teams = new ArrayList<>();

    @NotNull
    @Column(name = "estimated_duration")
    private int estimatedDuration;

    @NotNull
    @Column(name = "break_time")
    private int breakTime;

    @NotNull
    @Column(name = "ceremony_time")
    private int ceremonyTime;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private TournamentStatus status;

    public Tournament() {}

    public Tournament(String title, Game game, LocalDate startDate, LocalDate endDate,
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

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
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
    @Override
    public String toString() {
        StringBuilder teamNames = new StringBuilder();
        for (Team team : teams) {
            teamNames.append(team.getName()).append(", ");
        }

        return "Tournament{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", game='" + game + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", spectatorCount=" + spectatorCount +
                ", teams=[" + teamNames + "]" +
                ", estimatedDuration=" + estimatedDuration +
                ", breakTime=" + breakTime +
                ", ceremonyTime=" + ceremonyTime +
                ", status=" + status +
                '}';
    }

}
