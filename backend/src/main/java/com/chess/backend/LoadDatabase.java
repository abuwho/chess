package com.chess.backend;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.chess.backend.models.Game;
import com.chess.backend.models.Player;
import com.chess.backend.repositories.GameRepository;
import com.chess.backend.repositories.PlayerRepository;

@Configuration()
public class LoadDatabase {
    
    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(PlayerRepository playerRepo, GameRepository gameRepo) {
        return args -> {
            log.info("Preloading " + playerRepo.save(new Player("alice", "alice@alice.com")));
            log.info("Preloading " + playerRepo.save(new Player("bob", "bob@bob.com")));
            log.info("Preloading game " + gameRepo.save(new Game()));
            log.info("Preloading game " + gameRepo.save(new Game()));
        };
    }

    // @Bean
    // CommandLineRunner initDatabase(GameRepository gameRepo) {
    //     return args -> {
    //         log.info("Preloading " + gameRepo.save(new Game()));
    //         log.info("Preloading " + gameRepo.save(new Game()));
    //     };
    // }
}
