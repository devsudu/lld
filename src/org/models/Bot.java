package org.models;

import org.factories.BotPlayingStrategyFactory;
import org.models.enums.BotDifficultyLevel;
import org.models.enums.PlayerType;
import org.strategies.botPlayingStrategies.BotPlayingStrategy;

public class Bot extends Player {
    private BotDifficultyLevel botDifficultyLevel;
    private BotPlayingStrategy botPlayingStrategy;

    public Bot(String name, String avatar, Character symbol, PlayerType playerType, BotDifficultyLevel botDifficultyLevel) {
        super(name, avatar, symbol, playerType);
        this.botDifficultyLevel = botDifficultyLevel;
        this.botPlayingStrategy = BotPlayingStrategyFactory.getBotPlayingStrategy(botDifficultyLevel);
    }

    public Move makeMove(Board board){
        Cell prevCell = null;
        Cell newCell = null;
        if(this.getMovesCount() > board.getDimension()){
            prevCell = selectSymbolToMakeMove(board);
        }
        newCell = chooseCellToMakeMove(board);
        return new Move(prevCell, newCell, this);
    }

    public BotDifficultyLevel getBotDifficultyLevel() {
        return botDifficultyLevel;
    }

    public void setBotDifficultyLevel(BotDifficultyLevel botDifficultyLevel) {
        this.botDifficultyLevel = botDifficultyLevel;
    }

    public BotPlayingStrategy getBotPlayingStrategy() {
        return botPlayingStrategy;
    }

    public void setBotPlayingStrategy(BotPlayingStrategy botPlayingStrategy) {
        this.botPlayingStrategy = botPlayingStrategy;
    }
}
