package com.gmail.eksuzyan.pavel.game.controller;

import java.util.stream.Stream;

enum Player {

    NOBODY(0),
    FIRST(1),
    SECOND(2);

    private final int mark;

    Player(int mark) {
        this.mark = mark;
    }

    public int mark() {
        return mark;
    }

    public static Player of(int mark) throws IllegalArgumentException {
        return Stream.of(Player.values())
                .filter(foreigner -> foreigner != Player.NOBODY)
                .filter(player -> player.mark == mark)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Player '" + mark + "' doesn't exist!"));
    }
}
