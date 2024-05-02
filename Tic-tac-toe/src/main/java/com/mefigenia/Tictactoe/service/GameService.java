package com.mefigenia.Tictactoe.service;

import com.mefigenia.Tictactoe.model.Game;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
public class GameService {
    Game game = Game.getInstance();


    public ResponseEntity<String> newGame() {
        char[][] board = game.getBoard();

        for(int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                board[i][j] = ' ';
            }
        }
        game.setBoard(board);
        game.setActualPlayer(1);


        return new ResponseEntity<>("New Game started", HttpStatus.OK);
    }

    public ResponseEntity<String> play(int positionX, int positionY) {
        char[][] board = game.getBoard();
        int player = game.getActualPlayer();

        if(board[positionX][positionY] == ' ')
        {
            if (player == game.getPlayer1()) {
                board[positionX][positionY] = game.getX();
                game.setActualPlayer(game.getPlayer2());
            }
            else {
                board[positionX][positionY] = game.getY();
                game.setActualPlayer(game.getPlayer1());
            }

            String gameSatus = checkWinner(board);

            if(gameSatus.equals("Good Move"))
                printGame(board);

            return new ResponseEntity<>(gameSatus, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("Try another move", HttpStatus.OK);
        }

    }

    private String checkWinner(char[][] board) {
        char value;
        if (board[0][0] == game.getX() || board[0][0] == game.getY()) {
            value = board[0][0];
            int cont = 0;

            for (int i = 1; i < board.length; i++) {

                if(board[0][i] == value)
                    ++cont;
                if (cont == 2)
                    return "Player number " + game.getActualPlayer() + " is the winner";
            }

            for (int i = 1; i < board.length; i++) {
                if(board[i][0] == value)
                    ++cont;
                if (cont == 2)
                    return "Player number " + game.getActualPlayer() + " is the winner";
            }

            if( board[1][1] == value && board[2][2] == value) {
                return "Player number " + game.getActualPlayer() + " is the winner";
            }
        }

        if (board[1][1] == game.getX() || board[1][1] == game.getY()) {
            value = board[1][1];
            if( board[0][1] == value && board[2][1] == value) {
                return "Player number " + game.getActualPlayer() + " is the winner";
            }
            if( board[1][0] == value && board[1][2] == value) {
                return "Player number " + game.getActualPlayer() + " is the winner";
            }
        }

        if (board[2][2] == game.getX() || board[2][2] == game.getY()) {
            value = board[2][2];
            if( board[2][0] == value && board[2][1] == value) {
                return "Player number " + game.getActualPlayer() + " is the winner";
            }
            if( board[0][2] == value && board[1][2] == value) {
                return "Player number " + game.getActualPlayer() + " is the winner";
            }
        }
        return "Good Move";
    }

    public void printGame(char[][] board) {
        for(int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                System.out.print(board[i][j]);
                System.out.print("  ");
            }
            System.out.println();
        }
    }
}
