package io.hexlet.xo.model;

public class Game {

    private final Player[] players;

    private final Field field;

    private final String name;

    public Game(final Field field, final String name, final Player[] players) {
        this.players = players;
        this.field = field;
        this.name = name;
    }

    public Player[] getPlayers() {
        return players;
    }

    public Field getField() {
        return field;
    }

    public String getName() {
        return name;
    }

}
