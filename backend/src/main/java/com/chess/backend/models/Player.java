package com.chess.backend.models;


import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;


@Entity
@Table(name = "players")
public class Player {
    private @Id @GeneratedValue Long id;

    private String userName;

    private String email;

    public Player () {};

    public Player (String userName, String email) {
        this.userName = userName;
        this.email = email;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long newId) {
        this.id = newId;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String newUserName) {
        this.userName = newUserName;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String newEmail) {
        this.email = newEmail;
    }

    @Override
    public boolean equals(Object o) {
        
        if (this == o) return true;

        if (!(o instanceof Player)) return false;

        Player player = (Player) o;
        return Objects.equals(this.id, player.id) 
            && Objects.equals(this.userName, player.userName) 
            && Objects.equals(this.email, player.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.userName, this.email);
    }

    @Override
    public String toString() {
        return "Player{" + "id=" + this.id + ", userName='" + this.userName + "'";
    }

}
