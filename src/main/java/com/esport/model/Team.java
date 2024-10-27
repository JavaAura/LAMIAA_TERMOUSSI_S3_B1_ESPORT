package com.esport.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.NotNull;
@Entity
@Table(name = "teams")
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Player> players = new ArrayList<>();

    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinTable(
            name = "team_tournament",
            joinColumns = @JoinColumn(name = "team_id"),
            inverseJoinColumns = @JoinColumn(name = "tournament_id")
    )
    private List<Tournament> tournaments = new ArrayList<>();

    @NotNull
    @Column(name = "ranking")
    private int ranking;

    public Team() {}

    public Team(String name, List<Player> players, List<Tournament> tournaments, int ranking) {
        this.name = name;
        this.players = players;
        this.tournaments = tournaments;
        this.ranking = ranking;
    }
    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public List<Tournament> getTournaments() {
        return tournaments;
    }

    public void setTournaments(List<Tournament> tournaments) {
        this.tournaments = tournaments;
    }

    public int getRanking() {
        return ranking;
    }

    public void setRanking(int ranking) {
        this.ranking = ranking;
    }
    @Override
    public String toString() {
        StringBuilder playerNames = new StringBuilder();
        for (Player player : players) {
            playerNames.append(player.getUsername()).append(", ");
        }

        StringBuilder tournamentTitles = new StringBuilder();
        for (Tournament tournament : tournaments) {
            tournamentTitles.append(tournament.getTitle()).append(", ");
        }

        return "Team{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", ranking=" + ranking +
                ", players=[" + playerNames + "]" +
                ", tournaments=[" + tournamentTitles + "]" +
                '}';
    }

}

