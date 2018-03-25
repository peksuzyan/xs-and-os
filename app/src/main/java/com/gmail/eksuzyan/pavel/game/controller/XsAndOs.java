package com.gmail.eksuzyan.pavel.game.controller;

import java.util.stream.IntStream;
import java.util.stream.Stream;

@SuppressWarnings("WeakerAccess")
public class XsAndOs {

    private static final Cell[][] VICTORIES = {
            {new Cell(0, 0), new Cell(1, 0), new Cell(2, 0)},
            {new Cell(0, 1), new Cell(1, 1), new Cell(2, 1)},
            {new Cell(0, 2), new Cell(1, 2), new Cell(2, 2)},
            {new Cell(0, 0), new Cell(0, 1), new Cell(0, 2)},
            {new Cell(1, 0), new Cell(1, 1), new Cell(1, 2)},
            {new Cell(2, 0), new Cell(2, 1), new Cell(2, 2)},
            {new Cell(0, 0), new Cell(1, 1), new Cell(2, 2)},
            {new Cell(2, 0), new Cell(1, 1), new Cell(0, 2)}};

    private final int[][] grid = new int[3][3];

    private Player last = Player.NOBODY;
    private Player winner = Player.NOBODY;

    private boolean finished = false;

    public boolean move(int x, int y, int mark) throws IllegalArgumentException, IllegalStateException {
        return move(x, y, Player.of(mark));
    }

    private boolean move(int x, int y, Player player) throws IllegalArgumentException, IllegalStateException {

        if (x < 0 || x > 2 || y < 0 || y > 2)
            throw new IllegalArgumentException("Move can't be made to out of grid.");

        if (last == player)
            throw new IllegalStateException("Move is out of order.");

        if (winner != Player.NOBODY)
            throw new IllegalStateException("Game is over! Winner's player '" + winner + "'!");

        if (finished)
            throw new IllegalStateException("Game is over! Nobody won.");

        if (grid[x][y] != Player.NOBODY.mark()) return false;

        grid[x][y] = player.mark();
        last = player;
        winner = searchWinner();
        finished = isFinished();

        return true;
    }

    @SuppressWarnings("ForLoopReplaceableByForEach")
    public boolean isFinished() {

        if (winner != Player.NOBODY || finished) return true;

        for (int x = 0; x < grid.length; x++) {
            for (int y = 0; y < grid[x].length; y++) {
                if (grid[x][y] == Player.NOBODY.mark()) return false;
            }
        }

        return true;
    }

    public int getWinner() {
        return winner.mark();
    }

    private Player searchWinner() {

        for (Cell[] victory : VICTORIES) {
            Player winner = searchWinner(victory[0], victory[1], victory[2]);

            if (winner != Player.NOBODY) return winner;
        }

        return Player.NOBODY;
    }

    private Player searchWinner(Cell first, Cell middle, Cell last) {
        if (isWinner(Player.FIRST, first, middle, last))
            return Player.FIRST;

        if (isWinner(Player.SECOND, first, middle, last))
            return Player.SECOND;

        return Player.NOBODY;
    }

    private boolean isWinner(Player player, Cell first, Cell middle, Cell last) {
        return grid[first.x][first.y] == player.mark()
                && grid[middle.x][middle.y] == player.mark()
                && grid[last.x][last.y] == player.mark();
    }

    public String[][] getGrid() {
        return Stream.of(grid)
                .map(array ->
                        IntStream.of(array)
                                .boxed()
                                .map(Object::toString)
                                .toArray(String[]::new))
                .toArray(String[][]::new);
    }

}
