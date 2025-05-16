package org.strategies.winningStrategies;

import org.models.Cell;
import org.models.Move;

import java.util.HashMap;
import java.util.Map;

public class ColumnWinningStrategy implements WinningStrategy {
    @Override
    public Boolean checkWinner(Map<String, Map<Integer, Map<Character, Integer>>> gameStore, Move move, int N) {
        Map<Integer, Map<Character, Integer>> columnHashMap = gameStore.get("column");

        Cell fromCell = move.getFromCell();
        Cell toCell = move.getToCell();
        Character symbol = move.getPlayer().getSymbol();

        if(fromCell != null){
            if(!columnHashMap.containsKey(fromCell.getRow())){
                columnHashMap.put(fromCell.getRow(), new HashMap<>());
            }

            Map<Character, Integer> map = columnHashMap.get(fromCell.getRow());
            map.put(symbol, map.getOrDefault(symbol, 0) - 1);
        }

        if(!columnHashMap.containsKey(toCell.getCol())){
            columnHashMap.put(toCell.getCol(), new HashMap<>());
        }

        Map<Character, Integer> map = columnHashMap.get(toCell.getCol());
        map.put(symbol, map.getOrDefault(symbol, 0) + 1);

        if(map.get(symbol) == N){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public Boolean handleUndo(Map<String, Map<Integer, Map<Character, Integer>>> gameStore, Move move, int N) {
        Map<Integer, Map<Character, Integer>> columnHashMap = gameStore.get("column");

        Cell fromCell = move.getFromCell();
        Cell toCell = move.getToCell();
        Character symbol = move.getPlayer().getSymbol();

        Map<Character, Integer> map = columnHashMap.get(toCell.getCol());
        map.put(symbol, map.get(symbol) - 1);

        if(fromCell != null){
            Map<Character, Integer> cmap = columnHashMap.get(fromCell.getRow());
            cmap.put(symbol, map.get(symbol) + 1);
        }

        return true;
    }
}
