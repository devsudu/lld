import org.controllers.GameController;
import org.exceptions.DuplicateSymbolException;
import org.exceptions.InvalidBotCountException;
import org.exceptions.InvalidPlayerCountException;
import org.models.Game;
import org.models.Player;
import org.models.enums.GameState;
import org.models.enums.PlayerType;
import org.strategies.winningStrategies.ColumnWinningStrategy;
import org.strategies.winningStrategies.DiagonalWinningStrategy;
import org.strategies.winningStrategies.RowWinningStrategy;
import org.strategies.winningStrategies.WinningStrategy;

import java.util.List;

public class Client {
    public static void main(String[] args) throws InvalidBotCountException, DuplicateSymbolException, InvalidPlayerCountException {
        int dimension = 3;
        Player p1 = new Player("Sudu", "", 'S', PlayerType.HUMAN);
        Player p2 = new Player("Abi", "", 'A', PlayerType.HUMAN);
        Player p3 = new Player("Sudu", "", 'S', PlayerType.HUMAN);
        List<Player> players = List.of(p1, p2);

        WinningStrategy w1 = new RowWinningStrategy();
        WinningStrategy w2 = new ColumnWinningStrategy();
        WinningStrategy w3 = new DiagonalWinningStrategy();
        List<WinningStrategy> winningStrategies = List.of(w1, w2, w3);

        GameController gameController = new GameController();
        Game game = gameController.startGame(dimension, players, winningStrategies);

        while (game.getGameState().equals(GameState.IN_PROGRESS)){
            game.printBoard();
            game.makeMove();
            game.printBoard();
            game.unDoMove();
        }

        if(game.getGameState().equals(GameState.ENDED)){
            System.out.println("Game has a winner and winner is "+game.getWinner().getName());
        }
    }
}
