package org.models;

import org.exceptions.DuplicateSymbolException;
import org.exceptions.InvalidBotCountException;
import org.exceptions.InvalidPlayerCountException;
import org.models.enums.GameState;
import org.models.enums.PlayerType;
import org.strategies.winningStrategies.WinningStrategy;

import java.util.*;

public class Game {
    Map<String, Map<Integer, Map<Character, Integer>>> gameStore;
    private Board board;
    private GameState gameState;
    private List<Player> players;
    private List<WinningStrategy> winningStrategies;
    private Player winner;
    private int currentPlayerTurnIndex;
    private List<Move> moves;

    public Boolean printBoard(){
        return board.printBoard();
    }

    public Boolean makeMove(){
        Player currentPlayer = players.get(currentPlayerTurnIndex);
        System.out.println("Its "+currentPlayer.getName()+"'s turn");

        Move newMove = currentPlayer.makeMove(board);
        Boolean isUpdated =board.updateNewMoveOnBoard(newMove);

        if(isUpdated){
            moves.add(newMove);
        }
        return true;
    }

    public Boolean unDoMove(){
        Player currentPlayer = players.get(currentPlayerTurnIndex);
        if(currentPlayer.getPlayerType().equals(PlayerType.BOT)){
            currentPlayer.setMovesCount(currentPlayer.getMovesCount() + 1);
            currentPlayerTurnIndex = (currentPlayerTurnIndex + 1) % players.size();
            return false;
        }
        if(moves.size() == 0){
            System.out.println("Undo not possible");
            return false;
        }

        Move lastMove = moves.get(moves.size() - 1);
        if(currentPlayer.askForUndoMove()){
            moves.remove(lastMove);
            if(board.updateUndoMoveOnBoard(lastMove)){
                printHashMap();
                return true;
            }else {
                return false;
            }
        }else {
            currentPlayer.setMovesCount(currentPlayer.getMovesCount() + 1);
            currentPlayerTurnIndex = (currentPlayerTurnIndex + 1) % players.size();
            if(checkWinner(lastMove)){
                gameState = GameState.ENDED;
                winner = currentPlayer;
            }
            printHashMap();
            return false;
        }
    }

    public Boolean checkWinner(Move move){
        for (WinningStrategy winningStrategy: winningStrategies){
            if(winningStrategy.checkWinner(gameStore, move, board.getDimension())){
                return true;
            }
        }
        return false;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public List<WinningStrategy> getWinningStrategies() {
        return winningStrategies;
    }

    public void setWinningStrategies(List<WinningStrategy> winningStrategies) {
        this.winningStrategies = winningStrategies;
    }

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public int getCurrentPlayerTurnIndex() {
        return currentPlayerTurnIndex;
    }

    public void setCurrentPlayerTurnIndex(int currentPlayerTurnIndex) {
        this.currentPlayerTurnIndex = currentPlayerTurnIndex;
    }

    public List<Move> getMoves() {
        return moves;
    }

    public void setMoves(List<Move> moves) {
        this.moves = moves;
    }

    private Game(int dimension, List<Player> players, List<WinningStrategy> winningStrategies){
        this.board = new Board(dimension);
        this.players = players;
        this.winningStrategies = winningStrategies;
        this.winner = null;
        this.moves = new ArrayList<>();
        this.gameState = GameState.IN_PROGRESS;
        this.currentPlayerTurnIndex = 0;

        this.gameStore = new HashMap<>();
        this.gameStore.put("row", new HashMap<>());
        this.gameStore.put("column", new HashMap<>());
        this.gameStore.put("leftDiagonal", new HashMap<>());
        this.gameStore.put("rightDiagonal", new HashMap<>());
    }

    public void printHashMap(){
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        for (Iterator<Map.Entry<String, Map<Integer, Map<Character, Integer>>>> entries = gameStore.entrySet().iterator(); entries.hasNext(); ) {
            Map.Entry<String, Map<Integer, Map<Character, Integer>>> entry = entries.next();
            System.out.println();
            System.out.println(entry.getKey());
            System.out.println("=========================================================");
            for (Iterator<Map.Entry<Integer, Map<Character, Integer>>> rows = entry.getValue().entrySet().iterator(); rows.hasNext(); ) {
                Map.Entry<Integer, Map<Character, Integer>> entry1 = rows.next();
                System.out.println(entry.getKey()+entry1.getKey());
                System.out.println(".............");
                for (Iterator<Map.Entry<Character, Integer>> symbols = entry1.getValue().entrySet().iterator(); symbols.hasNext(); ) {
                    Map.Entry<Character, Integer> symbol = symbols.next();
                    System.out.println(symbol.getKey() +" "+symbol.getValue());
                }
            }
        }
    }

    public static Builder getBuilder(){
        return new Builder();
    }

    public static class Builder{
        private int dimension;
        private List<Player> players;
        private List<WinningStrategy> winningStrategies;

        public int getDimension() {
            return dimension;
        }

        public Builder setDimension(int dimension) {
            this.dimension = dimension;
            return this;
        }

        public List<Player> getPlayers() {
            return players;
        }

        public Builder setPlayers(List<Player> players) {
            this.players = players;
            return this;
        }

        public List<WinningStrategy> getWinningStrategies() {
            return winningStrategies;
        }

        public Builder setWinningStrategies(List<WinningStrategy> winningStrategies) {
            this.winningStrategies = winningStrategies;
            return this;
        }

        public Game build() throws InvalidBotCountException, DuplicateSymbolException, InvalidPlayerCountException {
            validate();
            return new Game(dimension, players, winningStrategies);
        }

        public void validate() throws InvalidPlayerCountException, InvalidBotCountException, DuplicateSymbolException {
            validatePlayerCount();
            validateBotCount();
            validateUniqueSymbols();
        }

        public void validatePlayerCount() throws InvalidPlayerCountException {
            if(players.size() != dimension - 1) throw new InvalidPlayerCountException("Invalid player count");
        }

        public void validateBotCount() throws InvalidBotCountException {
            int botCount = (int) players.stream().filter(player -> player.getPlayerType().equals(PlayerType.BOT)).count();
            if(botCount > dimension - 2) throw new InvalidBotCountException("Invalid bot count");
        }

        public void validateUniqueSymbols() throws DuplicateSymbolException {
            Set<Character> uniqueSymbols = new HashSet<>();
            for(Player player: players){
                if(!uniqueSymbols.contains(player.getSymbol())){
                    uniqueSymbols.add(player.getSymbol());
                }else {
                    throw new DuplicateSymbolException("Duplicate symbols");
                }
            }

        }
    }
}
