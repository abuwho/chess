package com.chess.backend.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;


@Entity
public class Player {
    private @Id @GeneratedValue Long id;

    private String name;

    private PlayerRole role;

}
