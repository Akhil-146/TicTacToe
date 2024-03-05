package model;

import java.util.Scanner;

public class HumanPlayer extends Player{

    public HumanPlayer(String name, Symbol symbol) {
        super(name, symbol);
    }

    @Override
    public Pair makeMove() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please make your Move, Enter Cell Row and Column: ");
        int row = sc.nextInt();
        int col = sc.nextInt();
        return new Pair(row, col);
    }
}
