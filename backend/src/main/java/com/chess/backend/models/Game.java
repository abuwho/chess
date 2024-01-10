package com.chess.backend.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;

import java.util.List;


@Entity
public class Game {
    private @Id @GeneratedValue Long id;

    private Player whitePlayer;

    private Player blackPlayer;

    private List<Move> moves;

    private StateType[][] state = new StateType[8][8];

}
