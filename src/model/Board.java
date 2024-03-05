package model;

import java.util.ArrayList;
import java.util.List;

public class Board {
    List<List<Cell>> cells;

    public List<List<Cell>> getCells() {
        return cells;
    }

    public void setCells(List<List<Cell>> cells) {
        this.cells = cells;
    }

    public Board(int n) {
        cells = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            List<Cell> row = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                row.add(new Cell(CellStatus.UN_OCCUPIED));
            }
            cells.add(row);
        }
    }

    public void printBoard() {
        for (List<Cell> rowCells : cells) {
            for (Cell cell : rowCells) {
                cell.printCell();
            }
            System.out.println();
        }
    }

    public boolean checkIfCellIsUnOccupied(int row, int col) {
        return cells.get(row).get(col).isCellUnOccupied();
    }

    public void setPlayer(int row, int col, Player player) {
        cells.get(row).get(col).setPlayer(player);
    }

    public Cell getCell(int row, int col) {
        return cells.get(row).get(col);
    }

    public int getBoardSize() {
        return cells.size();
    }
}
