package org.strategies.botPlayingStrategies;

import org.models.Board;
import org.models.Cell;
import org.models.Player;

public interface BotPlayingStrategy {
    Cell chooseCellToMakeMove(Board board);
    Cell selectSymbolToMakeMove(Board board, Player player);
}
