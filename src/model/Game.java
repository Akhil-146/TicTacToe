package model;

import exception.BotCountExceededException;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private Board board;
    private List<Player> players;
    private GameStatus gameStatus;
    private int currentPlayerIdx;
    private List<Move> moves;

    private Game(Board board, List<Player> players, GameStatus gameStatus, int currentPlayerIdx, List<Move> moves) {
        this.board = board;
        this.players = players;
        this.gameStatus = gameStatus;
        this.currentPlayerIdx = currentPlayerIdx;
        this.moves = moves;
    }

    public static GameBuilder getBuilder() {
        return new GameBuilder();
    }

    public Board getBoard() {
        return board;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public int getCurrentPlayerIdx() {
        return currentPlayerIdx;
    }

    public List<Move> getMoves() {
        return moves;
    }

    public void printBoard() {
        board.printBoard();
    }

    public void makeMove() {
        Player currentPlayer = players.get(currentPlayerIdx);
        Pair rowColCoordinates = currentPlayer.makeMove();

        while (!board.checkIfCellIsUnOccupied(rowColCoordinates.getRow(), rowColCoordinates.getCol())) {
            if (currentPlayer instanceof HumanPlayer) {
                System.out.println("This cell is already occupied, please make a different move");
            }
            rowColCoordinates = currentPlayer.makeMove();
        }

        board.setPlayer(rowColCoordinates.getRow(), rowColCoordinates.getCol(), currentPlayer);
        moves.add(new Move(currentPlayer, board.getCell(rowColCoordinates.getRow(), rowColCoordinates.getCol())));

        if (checkForGameWin()) {
            gameStatus = GameStatus.WON;
            return;
        } else if (checkForGameDraw()) {
            gameStatus = GameStatus.DRAWN;
            return;
        }

        currentPlayerIdx = (currentPlayerIdx + 1) % (board.getBoardSize() - 1);
    }

    private boolean checkForGameWin() {
        return false;
    }

    private boolean checkForGameDraw() {
        int n = board.getBoardSize();
        return moves.size() == n * n;
    }

    public static class GameBuilder {
        private Board board;
        private List<Player> players;
        private GameStatus gameStatus;
        private int currentPlayerIdx;
        private List<Move> moves;

        public GameBuilder setPlayer(List<Player> players) {
            this.players = players;
            board = new Board(players.size() + 1);
            return this;
        }

        public Game build() throws BotCountExceededException {
            int botCount = 0;
            for (Player player : players) {
                if (player instanceof BotPlayer)
                    botCount++;
                if (botCount > 1)
                    throw new BotCountExceededException("Found more than 1 bots, maximum only 1 bot is allowed");
            }

            return new Game(board, players, GameStatus.IN_PROGRESS, 0, new ArrayList<>());
        }
    }
}
