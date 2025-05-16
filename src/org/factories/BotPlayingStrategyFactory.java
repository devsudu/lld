package org.factories;

import org.models.enums.BotDifficultyLevel;
import org.strategies.botPlayingStrategies.BotPlayingStrategy;
import org.strategies.botPlayingStrategies.EasyBotPlayingStrategy;
import org.strategies.botPlayingStrategies.HardBotPlayingStrategy;
import org.strategies.botPlayingStrategies.MediumBotPlayingStrategy;

public class BotPlayingStrategyFactory {
    public static BotPlayingStrategy getBotPlayingStrategy(BotDifficultyLevel botDifficultyLevel){
        switch (botDifficultyLevel){
            case EASY -> new EasyBotPlayingStrategy();
            case MEDIUM -> new MediumBotPlayingStrategy();
            case HARD -> new HardBotPlayingStrategy();
        }
        return new HardBotPlayingStrategy();
    }
}
