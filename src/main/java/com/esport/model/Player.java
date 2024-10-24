package com.esport.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
@Entity
@Table(name = "players")
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @NotNull
    @Column(name = "age", nullable = false)
    private int age;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "team_id", nullable = true)
    private Team team;


    public Player(String username, int age, Team team) {
        this.username = username;
        this.age = age;
        this.team = team;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}
