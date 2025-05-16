package org.strategies.winningStrategies;

import org.models.Cell;
import org.models.Move;

import java.util.HashMap;
import java.util.Map;

public class RowWinningStrategy implements WinningStrategy {
    @Override
    public Boolean checkWinner(Map<String, Map<Integer, Map<Character, Integer>>> gameStore, Move move, int N) {
        Map<Integer, Map<Character, Integer>> rowHashMap = gameStore.get("row");

        Cell fromCell = move.getFromCell();
        Cell toCell = move.getToCell();
        Character symbol = move.getPlayer().getSymbol();

        if(fromCell != null){
            if(!rowHashMap.containsKey(fromCell.getRow())){
                rowHashMap.put(fromCell.getRow(), new HashMap<>());
            }

            Map<Character, Integer> map = rowHashMap.get(fromCell.getRow());
            map.put(symbol, map.getOrDefault(symbol, 0) - 1);
        }

        if(!rowHashMap.containsKey(toCell.getRow())){
            rowHashMap.put(toCell.getRow(), new HashMap<>());
        }

        Map<Character, Integer> map = rowHashMap.get(toCell.getRow());
        map.put(symbol, map.getOrDefault(symbol, 0) + 1);

        if(map.get(symbol) == N){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public Boolean handleUndo(Map<String, Map<Integer, Map<Character, Integer>>> gameStore, Move move, int N) {
        Map<Integer, Map<Character, Integer>> rowHashMap = gameStore.get("row");

        Cell fromCell = move.getFromCell();
        Cell toCell = move.getToCell();
        Character symbol = move.getPlayer().getSymbol();

        Map<Character, Integer> map = rowHashMap.get(toCell.getRow());
        map.put(symbol, map.get(symbol) - 1);

        if(fromCell != null){
            Map<Character, Integer> rmap = rowHashMap.get(fromCell.getRow());
            rmap.put(symbol, map.get(symbol) + 1);
        }

        return true;
    }
}
