package controller;

import exception.BotCountExceededException;
import model.Game;
import model.GameStatus;
import model.Player;

import java.util.List;

public class GameController {
    public Game createGame(List<Player> players) throws BotCountExceededException {
        return Game.getBuilder()
                .setPlayer(players)
                .build();
    }

    public GameStatus getGameStatus(Game game) {
        return game.getGameStatus();
    }

    public void printBoard(Game game) {
        game.printBoard();
    }

    public void makeMove(Game game) {
        game.makeMove();
    }
}
