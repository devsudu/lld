package org.models;

import org.models.enums.CellState;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private int dimension;
    private List<List<Cell>> board;

    Board(int dimension){
        this.dimension = dimension;
        this.board = new ArrayList<>();

        for(int i=0; i<dimension; i++){
            this.board.add(new ArrayList<>());
            for(int j=0; j<dimension; j++){
                this.board.get(i).add(new Cell(i, j));
            }
        }
    }

    public Boolean printBoard(){
        for (List<Cell> row: board){
            for(Cell cel: row){
                if(cel.getCellState().equals(CellState.EMPTY)){
                    System.out.print("|   |");
                }else{
                    System.out.print("| "+cel.getPlayer().getSymbol()+" |");
                }
            }
            System.out.println();
        }
        return true;
    }

    public Boolean updateNewMoveOnBoard(Move move){
        Cell fromCell = move.getFromCell();
        Cell toCell = move.getToCell();
        Player player = move.getPlayer();

        if(fromCell != null){
            Cell prevCell = board.get(fromCell.getRow()).get(fromCell.getCol());
            prevCell.setCellState(CellState.EMPTY);
            prevCell.setPlayer(null);
        }

        Cell newCell = board.get(toCell.getRow()).get(toCell.getCol());
        newCell.setCellState(CellState.FILLED);
        newCell.setPlayer(player);
        return true;
    }

    public Boolean updateUndoMoveOnBoard(Move move){
        Cell fromCell = move.getFromCell();
        Cell toCell = move.getToCell();
        Player player = move.getPlayer();

        if(fromCell != null){
            Cell prevCell = board.get(fromCell.getRow()).get(fromCell.getCol());
            prevCell.setCellState(CellState.FILLED);
            prevCell.setPlayer(player);
        }

        Cell newCell = board.get(toCell.getRow()).get(toCell.getCol());
        newCell.setCellState(CellState.EMPTY);
        newCell.setPlayer(null);
        return true;
    }

    public Boolean validateNewCell(int row, int col){
        if(row < 0 || col < 0 || row >= dimension || col >= dimension){
            return false;
        }
        return board.get(row).get(col).getCellState().equals(CellState.EMPTY);
    }

    public Boolean validateSelectedSymbol(int row, int col, Player player){
        if(row < 0 || col < 0 || row >= dimension || col >= dimension){
            return false;
        }
        return board.get(row).get(col).getCellState().equals(CellState.FILLED) && board.get(row).get(col).getPlayer().getName().equals(player.getName());
    }

    public int getDimension() {
        return dimension;
    }

    public void setDimension(int dimension) {
        this.dimension = dimension;
    }

    public List<List<Cell>> getBoard() {
        return board;
    }

    public void setBoard(List<List<Cell>> board) {
        this.board = board;
    }
}
