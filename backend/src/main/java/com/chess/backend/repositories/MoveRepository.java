package com.chess.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.chess.backend.models.Move;

public interface MoveRepository extends JpaRepository <Move, Long> {
    
}
