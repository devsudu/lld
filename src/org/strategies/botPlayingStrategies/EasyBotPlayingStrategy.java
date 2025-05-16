package org.strategies.botPlayingStrategies;

import org.models.Board;
import org.models.Cell;
import org.models.Player;
import org.models.enums.CellState;

import java.util.List;

public class EasyBotPlayingStrategy implements BotPlayingStrategy {
    @Override
    public Cell chooseCellToMakeMove(Board board) {
        for (List<Cell> cells: board.getBoard()){
            for(Cell cell: cells){
                if(cell.getCellState().equals(CellState.EMPTY)){
                    return cell;
                }
            }
        }
        return null;
    }

    @Override
    public Cell selectSymbolToMakeMove(Board board, Player player) {
        for (List<Cell> cells: board.getBoard()){
            for(Cell cell: cells){
                if(cell.getCellState().equals(CellState.FILLED) && cell.getPlayer().getSymbol().equals(player.getSymbol())){
                    return cell;
                }
            }
        }
        return null;
    }
}
