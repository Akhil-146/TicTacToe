package model;

public class BotPlayer extends Player{
    BotLevel botLevel;

    public BotPlayer(String name, Symbol symbol, BotLevel botLevel) {
        super(name, symbol);
        this.botLevel = botLevel;
    }

    @Override
    public Pair makeMove() {
        return null;
    }
}
