import controller.GameController;
import model.*;

import java.util.*;

public class TicTacToe {
    public static void main(String[] args) {
        GameController gameController = new GameController();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Hello World. Let's Start the Game.....");
        System.out.println("Choose No of Players: ");
        int noOfPlayers = scanner.nextInt();
        System.out.println("Great thanks, Let's Create each Player Profile.....");

        List<Player> players = new ArrayList<>();
        Set<Character> chosenSymbols = new HashSet<>();

        for (int i = 0; i < noOfPlayers; i++) {
            System.out.println("Enter Player " + (i + 1) + " Name: ");
            String name = scanner.next();
            System.out.println("Great, now choose Player " + (i + 1) + " Symbol: ");
            System.out.println("Allowed Symbols are: a - z and 0 - 9 ");
            String symbolStr = scanner.next();
            char ch = symbolStr.charAt(0);
            while (chosenSymbols.contains(ch)) {
                System.out.println("OOPS, Entered Symbol is already taken, try choosing other symbols");
                symbolStr = scanner.next();
                ch = symbolStr.charAt(0);
            }
            chosenSymbols.add(ch);
            players.add(new HumanPlayer(name, new Symbol(ch)));
        }

        System.out.println("Great. So far so good, do you want to have any Bots to play with you");
        System.out.println("At max, you can add 1 Bot");
        int noOfBots = scanner.nextInt();
        String allowedSymbols = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();

        for (int i = 0; i < noOfBots; i++) {
            System.out.println("Choose the bot difficulty level: ");
            System.out.println("Allowed Values are Easy (E), Medium (M), Hard (H)");
            String difficultyLevel = scanner.next();
            BotLevel botLevel = switch (difficultyLevel.charAt(0)) {
                case 'E' -> BotLevel.EASY;
                case 'M' -> BotLevel.MEDIUM;
                case 'H' -> BotLevel.HARD;
                default -> {
                    System.out.println("Invalid BotLevel Chosen, Setting it to Easy Level...");
                    yield BotLevel.EASY;
                }
            };

            int idx = random.nextInt(0, allowedSymbols.length());
            char ch = allowedSymbols.charAt(idx);
            while (!chosenSymbols.contains(ch)) {
                idx = random.nextInt(0, allowedSymbols.length());
                ch = allowedSymbols.charAt(idx);
            }
            chosenSymbols.add(ch);
            players.add(new BotPlayer("Bot " + (i+1), new Symbol(ch), botLevel));
        }

        System.out.println("Hurray!!!. We are all done, customize the game exclusively for you.....");
        Game game;
        try {
            game = gameController.createGame(players);
        } catch (Exception ex) {
            System.out.println("OOPS... Something went wrong. Please try again. Error : "+ex.getMessage());
            return;
        }

        System.out.println("Let's Start the Game........");
        while (gameController.getGameStatus(game) == GameStatus.IN_PROGRESS) {
            gameController.printBoard(game);
            gameController.makeMove(game);
        }

        GameStatus gameStatus = gameController.getGameStatus(game);
        if (gameStatus.equals(GameStatus.WON)) {
            System.out.println("Congratulations, Player has won the game");
        } else {
            System.out.println("OOPS, The game has Drawn");
        }
        gameController.printBoard(game);
    }
}
