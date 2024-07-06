package snakes.squares;

import snakes.Game;
import snakes.Player;
/**
 * Represents a square where the player gets to roll the die again after entering.
 * If a player lands on this square, they will get to roll the die again immediately.
 */
public class RollAgainSquare extends StandardSquare {
    /**
     * Constructs a new RollAgainSquare with the specified game and position.
     *
     * @param game     The game instance associated with this square.
     * @param position The position of this square on the board.
     */
    public RollAgainSquare(Game game, int position) {
        super(game, position);
    }

    /**
     * Handles the player entering the square.
     * Calls the superclass method to handle the player's entry and then repeats the current turn in the game.
     *
     * @param player The player entering the square.
     */

    public void enter(Player player){
        super.enter(player);
        this.game.repeatCurrentTurn();
    }

    /**
     * Returns the label of the square, including the position and indicating it's a RollAgainSquare.
     *
     * @return The label of the square.
     */

    @Override
    public String squareLabel() {
        return String.format("%d (RollAgain)", position);
    }
}