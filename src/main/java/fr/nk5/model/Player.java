package fr.nk5.model;

import java.util.Objects;

public class Player {
    protected String name;
    protected String team;

    public Player(String name, String team) {
        this.name = name;
        this.team = team;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return Objects.equals(name, player.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
