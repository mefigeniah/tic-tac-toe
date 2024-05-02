package com.mefigenia.Tictactoe.model;

import lombok.Data;
import org.springframework.stereotype.Component;


@Data
public class Game {
    private final int  player1 = 1;
    private final int  player2 = 2;
    private final char x = 'x';
    private final char y = 'y';
    private char[][] board = new char[3][3];
    private int actualPlayer = player1;
    private static Game single_instance = null;

    private Game() {

    }

    public static synchronized Game getInstance()
    {
        if (single_instance == null)
            single_instance = new Game();

        return single_instance;
    }
}
