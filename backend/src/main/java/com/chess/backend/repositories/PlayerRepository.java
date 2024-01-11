package com.chess.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.chess.backend.models.Player;

public interface PlayerRepository extends JpaRepository<Player, Long> {
    
}
