package org.controllers;

import org.exceptions.DuplicateSymbolException;
import org.exceptions.InvalidBotCountException;
import org.exceptions.InvalidPlayerCountException;
import org.models.Game;
import org.models.Player;
import org.strategies.winningStrategies.WinningStrategy;

import java.util.List;

public class GameController {
    public Game startGame(int dimension, List<Player> players, List<WinningStrategy> winningStrategies) throws InvalidBotCountException, DuplicateSymbolException, InvalidPlayerCountException {
        return Game.getBuilder().setDimension(dimension).setPlayers(players).setWinningStrategies(winningStrategies).build();
    }

    public Boolean printBoard(Game game){
        return game.printBoard();
    }

    public Boolean makeMove(Game game){
        return game.makeMove();
    }

    public Boolean undo(Game game){
        return game.unDoMove();
    }
}
