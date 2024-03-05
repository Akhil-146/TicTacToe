package model;

public class Cell {
    private CellStatus cellStatus;
    private Player player;

    public Cell(CellStatus cellStatus) {
        this.cellStatus = cellStatus;
    }

    public CellStatus getCellStatus() {
        return cellStatus;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
        cellStatus = CellStatus.OCCUPIED;
    }

    public boolean isCellUnOccupied() {
        return player == null && cellStatus == CellStatus.UN_OCCUPIED;
    }

    public void printCell() {
        if (cellStatus == CellStatus.UN_OCCUPIED)
            System.out.print(" _ ");
        else
            System.out.print(" " + player.getSymbol().getCharacterSymbol() + " ");
    }
}
