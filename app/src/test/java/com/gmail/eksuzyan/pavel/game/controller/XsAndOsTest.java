package com.gmail.eksuzyan.pavel.game.controller;

import org.junit.Test;

import static org.junit.Assert.*;

@SuppressWarnings("Duplicates")
public class XsAndOsTest {

    @Test
    public void moveToNoBusyCellTest() {
        XsAndOs game = new XsAndOs();

        boolean result = game.move(1, 1, 1);

        assertTrue(result);
    }

    @Test
    public void moveToBusyCellTest() {
        XsAndOs game = new XsAndOs();

        game.move(1, 1, 1);
        boolean result = game.move(1, 1, 2);

        assertFalse(result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void moveToOutOfGridCell1Test() {
        XsAndOs game = new XsAndOs();

        game.move(-1, 1, 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void moveToOutOfGridCell2Test() {
        XsAndOs game = new XsAndOs();

        game.move(3, 1, 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void moveToOutOfGridCell3Test() {
        XsAndOs game = new XsAndOs();

        game.move(1, -1, 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void moveToOutOfGridCell4Test() {
        XsAndOs game = new XsAndOs();

        game.move(1, 3, 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void makeMoveWithIncorrectMark1Test() {
        XsAndOs game = new XsAndOs();

        game.move(1, 1, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void makeMoveWithIncorrectMark2Test() {
        XsAndOs game = new XsAndOs();

        game.move(1, 1, 3);
    }

    @Test
    public void gameFinished1Test() {
        XsAndOs game = new XsAndOs();

        game.move(0, 0, 1);
        game.move(1, 0, 2);
        game.move(2, 0, 1);
        game.move(0, 1, 2);
        game.move(1, 2, 1);
        game.move(2, 2, 2);
        game.move(0, 2, 1);
        game.move(1, 1, 2);
        game.move(2, 1, 1);

        boolean result = game.isFinished();

        assertTrue(result);
    }

    @Test
    public void gameNotFinishedTest() {
        XsAndOs game = new XsAndOs();

        game.move(1, 0, 2);
        game.move(2, 0, 1);
        game.move(0, 1, 2);
        game.move(1, 1, 1);
        game.move(2, 1, 2);
        game.move(1, 2, 1);
        game.move(0, 2, 2);
        game.move(2, 2, 1);

        boolean result = game.isFinished();

        assertFalse(result);
    }

    @Test(expected = IllegalStateException.class)
    public void moveIsOutOfOrderTest() {
        XsAndOs game = new XsAndOs();

        game.move(1, 0, 1);
        game.move(2, 0, 1);
    }

    @Test
    public void winnerExists1Test() {
        XsAndOs game = new XsAndOs();

        game.move(0, 0, 1);
        game.move(1, 0, 2);
        game.move(1, 1, 1);
        game.move(2, 0, 2);
        game.move(2, 2, 1);

        int winner = game.getWinner();

        assertEquals(1, winner);
    }

    @Test
    public void winnerExists2Test() {
        XsAndOs game = new XsAndOs();

        game.move(1, 1, 1);
        game.move(1, 0, 2);
        game.move(2, 2, 1);
        game.move(2, 0, 2);
        game.move(0, 0, 1);

        int winner = game.getWinner();

        assertEquals(1, winner);
    }

    @Test
    public void winnerExists3Test() {
        XsAndOs game = new XsAndOs();

        game.move(1, 1, 1);
        game.move(0, 1, 2);
        game.move(2, 1, 1);
        game.move(0, 2, 2);
        game.move(2, 0, 1);
        game.move(0, 0, 2);

        int winner = game.getWinner();

        assertEquals(2, winner);
    }

    @Test(expected = IllegalStateException.class)
    public void winnerAlreadyFoundTest() {
        XsAndOs game = new XsAndOs();

        game.move(1, 1, 1);
        game.move(0, 1, 2);
        game.move(2, 1, 1);
        game.move(0, 2, 2);
        game.move(2, 0, 1);
        game.move(0, 0, 2);

        game.move(1, 2, 1);
    }

    @Test
    public void gameFinishedAfterWinnerFoundTest() {
        XsAndOs game = new XsAndOs();

        game.move(1, 1, 1);
        game.move(0, 1, 2);
        game.move(2, 1, 1);
        game.move(0, 2, 2);
        game.move(2, 0, 1);
        game.move(0, 0, 2);

        assertTrue(game.isFinished());
    }

    @Test(expected = IllegalStateException.class)
    public void gameFinished2Test() {
        XsAndOs game = new XsAndOs();

        game.move(0, 0, 1);
        game.move(1, 0, 2);
        game.move(2, 0, 1);
        game.move(0, 1, 2);
        game.move(1, 2, 1);
        game.move(2, 2, 2);
        game.move(0, 2, 1);
        game.move(1, 1, 2);
        game.move(2, 1, 1);

        game.move(1, 1, 2);
    }

    @Test
    public void gameGridShownTest() {
        XsAndOs game = new XsAndOs();

        game.move(1, 1, 2);
        game.move(1, 2, 1);
        game.move(2, 2, 2);

        String[][] result = game.getGrid();

        String[][] expected = {
                new String[]{"0", "0", "0"},
                new String[]{"0", "2", "1"},
                new String[]{"0", "0", "2"}};

        assertArrayEquals(expected, result);
    }
}
