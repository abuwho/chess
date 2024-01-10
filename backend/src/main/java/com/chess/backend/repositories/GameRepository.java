package com.chess.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.chess.backend.models.Game;

public interface GameRepository extends JpaRepository <Game, Long> {
    
}
