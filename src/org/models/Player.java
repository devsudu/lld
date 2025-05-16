package org.models;

import org.models.enums.PlayerType;

import java.util.Scanner;

public class Player {
    private String name;
    private String avatar;
    private Character symbol;
    private PlayerType playerType;
    private int movesCount;
    private Scanner scanner = new Scanner(System.in);

    public Move makeMove(Board board){
        Cell prevCell = null;
        Cell newCell = null;
//        System.out.println("===>"+movesCount +" "+ board.getDimension());
        if(movesCount >= board.getDimension()){
            prevCell = selectSymbolToMakeMove(board);
        }
        newCell = chooseCellToMakeMove(board);
        return new Move(prevCell, newCell, this);
    }

    public Boolean askForUndoMove(){
        System.out.println("Do u want to undo Y/N?");
        String undoAnswer = scanner.next();
        if(undoAnswer.equalsIgnoreCase("Y")){
            return true;
        }
        return false;
    }

    public Cell chooseCellToMakeMove(Board board){
        Boolean isValidCell = false;
        Cell newCell = null;
        while (!isValidCell){
            System.out.println("Enter row no to make move");
            int row = scanner.nextInt();

            System.out.println("Enter column no to make move");
            int col = scanner.nextInt();

            if(!board.validateNewCell(row, col)){
                System.out.println("Invalid move, pls retry");
            }else{
                isValidCell = true;
                newCell = new Cell(row, col);
            }
        }
        return newCell;
    }

    public Cell selectSymbolToMakeMove(Board board){
        Boolean isValidSymbol = false;
        Cell newCell = null;
        while (!isValidSymbol){
            System.out.println("Enter row no to select symbol");
            int row = scanner.nextInt();

            System.out.println("Enter column no to select symbol");
            int col = scanner.nextInt();

            if(!board.validateSelectedSymbol(row, col, this)){
                System.out.println("Invalid symbol, pls reselect");
            }else{
                isValidSymbol = true;
                newCell = new Cell(row, col);
            }
        }
        return newCell;
    }

    public Player(String name, String avatar, Character symbol, PlayerType playerType) {
        this.name = name;
        this.avatar = avatar;
        this.symbol = symbol;
        this.playerType = playerType;
        this.movesCount = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Character getSymbol() {
        return symbol;
    }

    public void setSymbol(Character symbol) {
        this.symbol = symbol;
    }

    public PlayerType getPlayerType() {
        return playerType;
    }

    public void setPlayerType(PlayerType playerType) {
        this.playerType = playerType;
    }

    public int getMovesCount() {
        return movesCount;
    }

    public void setMovesCount(int movesCount) {
        this.movesCount = movesCount;
    }
}
