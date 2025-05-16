package org.strategies.winningStrategies;

import org.models.Cell;
import org.models.Move;

import java.util.HashMap;
import java.util.Map;

public class DiagonalWinningStrategy implements WinningStrategy {
    @Override
    public Boolean checkWinner(Map<String, Map<Integer, Map<Character, Integer>>> gameStore, Move move, int N) {
        Map<Integer, Map<Character, Integer>> leftDiagonal = gameStore.get("leftDiagonal");
        Map<Integer, Map<Character, Integer>> rightDiagonal = gameStore.get("rightDiagonal");

        Cell fromCell = move.getFromCell();
        Cell toCell = move.getToCell();
        Character symbol = move.getPlayer().getSymbol();

        Map<Character, Integer> lmap = null;
        // left diagonal
        if(toCell.getRow() == toCell.getCol()){
            if(fromCell != null){
                if(!leftDiagonal.containsKey(0)){
                    leftDiagonal.put(0, new HashMap<>());
                }

                lmap = leftDiagonal.get(0);
                lmap.put(symbol, lmap.getOrDefault(symbol, 0) - 1);
            }

            if(!leftDiagonal.containsKey(0)){
                leftDiagonal.put(0, new HashMap<>());
            }

            lmap = leftDiagonal.get(0);
            lmap.put(symbol, lmap.getOrDefault(symbol, 0) + 1);
        }

        Map<Character, Integer> rmap = null;
        // right diagonal
        if(toCell.getRow() + toCell.getCol() == N - 1){
            if(fromCell != null){
                if(!rightDiagonal.containsKey(0)){
                    rightDiagonal.put(0, new HashMap<>());
                }

                rmap = rightDiagonal.get(0);
                rmap.put(symbol, rmap.getOrDefault(symbol, 0) - 1);
            }

            if(!rightDiagonal.containsKey(0)){
                rightDiagonal.put(0, new HashMap<>());
            }

            rmap = rightDiagonal.get(0);
            rmap.put(symbol, rmap.getOrDefault(symbol, 0) + 1);
        }

        if(toCell.getRow() == toCell.getCol() && lmap.getOrDefault(symbol, 0) == N){
            return true;
        }else if(toCell.getRow() + toCell.getCol() == N - 1 && rmap.getOrDefault(symbol, 0) == N){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public Boolean handleUndo(Map<String, Map<Integer, Map<Character, Integer>>> gameStore, Move move, int N) {
        Map<Integer, Map<Character, Integer>> leftDiagonal = gameStore.get("leftDiagonal");
        Map<Integer, Map<Character, Integer>> rightDiagonal = gameStore.get("rightDiagonal");

        Cell fromCell = move.getFromCell();
        Cell toCell = move.getToCell();
        Character symbol = move.getPlayer().getSymbol();

        Map<Character, Integer> map = null;
        // left diagonal
        if(toCell.getRow() == toCell.getCol()){
            if(fromCell != null){
                if(!leftDiagonal.containsKey(fromCell.getRow())){
                    leftDiagonal.put(fromCell.getRow(), new HashMap<>());
                }

                map = leftDiagonal.get(fromCell.getRow());
                map.put(symbol, map.getOrDefault(symbol, 0) + 1);
            }

            if(!leftDiagonal.containsKey(toCell.getRow())){
                leftDiagonal.put(toCell.getRow(), new HashMap<>());
            }

            map = leftDiagonal.get(toCell.getRow());
            map.put(symbol, map.getOrDefault(symbol, 0) - 1);
        }

        // right diagonal
        if(toCell.getRow() + toCell.getCol() == N - 1){
            if(fromCell != null){
                if(!rightDiagonal.containsKey(fromCell.getRow())){
                    rightDiagonal.put(fromCell.getRow(), new HashMap<>());
                }

                map = rightDiagonal.get(fromCell.getRow());
                map.put(symbol, map.getOrDefault(symbol, 0) + 1);
            }

            if(!rightDiagonal.containsKey(toCell.getRow())){
                rightDiagonal.put(toCell.getRow(), new HashMap<>());
            }

            map = rightDiagonal.get(toCell.getRow());
            map.put(symbol, map.getOrDefault(symbol, 0) - 1);
        }

        return true;
    }
}
