package com.gmail.eksuzyan.pavel.game.view;

import com.gmail.eksuzyan.pavel.game.controller.XsAndOs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@SuppressWarnings("Duplicates")
public class XsAndOsConsole {

    private static final char boundary = '*';
    private static final char separator = ' ';

    private static final char[] edge = new char[]{
            boundary, separator, boundary, separator, boundary, separator,
            boundary, separator, boundary, separator, boundary
    };

    public static void main(String[] args) throws IOException {

        BufferedReader inStream = new BufferedReader(new InputStreamReader(System.in));

        XsAndOs game = new XsAndOs();

        System.out.println();
        System.out.println("<<< ***       Welcome!      *** >>>");
        System.out.println("<<< *** Noughts And Crosses *** >>>");
        System.out.println();
        System.out.println("Players will make a move one by one.");

        System.out.println();
        printGrid(game.getGrid());
        System.out.println();

        String inputX, inputY;
        int x = 0, y = 0;
        int player = 1;
        while (!game.isFinished()) {

            System.out.println("Player #" + player + " is going to make a move...");
            System.out.println();

            System.out.print("Type row number: ");
            if ((inputX = inStream.readLine()) != null)
                try {
                    x = Integer.parseInt(inputX);
                } catch (NumberFormatException e) {
                    System.out.println("You can type only digits instead of '" + inputX + "'.");
                    System.out.println();
                    continue;
                }

            System.out.print("Type column number: ");
            if ((inputY = inStream.readLine()) != null)
                try {
                    y = Integer.parseInt(inputY);
                } catch (NumberFormatException e) {
                    System.out.println("You can type only digits instead of '" + inputY + "'.");
                    System.out.println();
                    continue;
                }

            try {
                if (!game.move(x, y, player)) {
                    System.out.println("Please, choose any other cell. It has been already busy.");
                    System.out.println();
                    continue;
                }
            } catch (IllegalArgumentException | IllegalStateException e) {
                System.out.println(e.getMessage());
                System.out.println();
                continue;
            }

            System.out.println();
            printGrid(game.getGrid());
            System.out.println();

            if (game.getWinner() != 0) {
                System.out.println("We have a winner! It's player #" + player + "!");
                System.out.printf("");
                System.out.println("<<< *** Congratulations! *** >>>");
                System.out.println();
                break;
            }

            if (player == 1)
                player++;
            else
                player--;
        }

        System.out.println("Your game is over ;-)");
    }

    private static void printGrid(String[][] grid) {
        System.out.println(edge);
        for (String[] row : grid) {
            System.out.print(boundary);
            for (String column : row) {
                System.out.print(separator);
                System.out.print(column);
                System.out.print(separator);
            }
            System.out.println(boundary);
        }
        System.out.println(edge);
    }

}
