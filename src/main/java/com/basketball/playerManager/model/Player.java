package com.basketball.playerManager.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String surname;
    private PlayerPositionType positionType;
    @ManyToOne(fetch = FetchType.EAGER)
    private Team team;

    public Player(String name, String surname, PlayerPositionType positionType, Team team) {
        this.name = name;
        this.surname = surname;
        this.positionType = positionType;
        this.team = team;
    }
}
