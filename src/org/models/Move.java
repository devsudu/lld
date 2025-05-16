package org.models;

public class Move {
    private Cell fromCell;
    private Cell toCell;
    private Player player;

    public Move(Cell fromCell, Cell toCell, Player player) {
        this.fromCell = fromCell;
        this.toCell = toCell;
        this.player = player;
    }

    public Cell getFromCell() {
        return fromCell;
    }

    public void setFromCell(Cell fromCell) {
        this.fromCell = fromCell;
    }

    public Cell getToCell() {
        return toCell;
    }

    public void setToCell(Cell toCell) {
        this.toCell = toCell;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
