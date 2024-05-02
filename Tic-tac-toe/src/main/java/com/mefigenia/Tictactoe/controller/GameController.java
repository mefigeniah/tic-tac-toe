package com.mefigenia.Tictactoe.controller;


import com.mefigenia.Tictactoe.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("game")
public class GameController {

    @Autowired
    GameService gameService;

    @PostMapping("new")
    public ResponseEntity<String> newGame() {
        return gameService.newGame();
    }

    @PostMapping("play")
    public ResponseEntity<String> play(@RequestParam int positionX, @RequestParam int positionY) {
        return gameService.play(positionX,positionY);
    }
}
