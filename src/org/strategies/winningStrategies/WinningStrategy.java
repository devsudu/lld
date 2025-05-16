package org.strategies.winningStrategies;

import org.models.Move;

import java.util.Map;

public interface WinningStrategy {
    Boolean checkWinner(Map<String, Map<Integer, Map<Character, Integer>>> gameStore, Move move, int N);
    Boolean handleUndo(Map<String, Map<Integer, Map<Character, Integer>>> gameStore, Move move, int N);
}
