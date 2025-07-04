package snakes.squares;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import snakes.Game;
import snakes.Player;

import java.util.LinkedList;
import java.util.Queue;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class WormholeEntranceSquareTest {

    private Game mockGame;
    private Queue<Player> players;
    private int wormholeEntranceSquarePos;
    private int wormholeExitSquarePos;
    private Player jack;
    private Player jill;

    @BeforeEach
    public void beforeEach() {
        //set up the game with a mocked die to control the rolls.
        players = new LinkedList<>();
        jack = new Player("jack");
        players.add(jack);
        jill = new Player("jill");
        players.add(jill);
        mockGame = new Game(10, players, 6);
        wormholeEntranceSquarePos = 3;
        //set square 3 to a WormholeEntranceSquare
        mockGame.setSquare(wormholeEntranceSquarePos, new WormholeEntranceSquare(mockGame, wormholeEntranceSquarePos));
    }

    @Test
    public void wormholeEntranceSquareWithNoExitsTest() {
        //only one player is needed to test the square
        //If jill in @BeforeEach is not added, there would be an invariant error occurring here,
        //because there must be at least 2 players in a game
        players.remove(jill);
        assertEquals(jack, mockGame.currentPlayer());

        //first, test the wormholeEntranceSquare without having a WormholeExitSquare
        //the player should stay on the square
        mockGame.movePlayer(2); //jack lands on WormholeEntranceSquare
        assertEquals(wormholeEntranceSquarePos, jack.position(), "jack has entered the wormholeEntranceSquare");
        //jack position should still be the wormholeEntranceSquarePos
        assertEquals(jack.position(), wormholeEntranceSquarePos);
    }

    @Test
    public void wormholeEntranceSquareWithOneExitTest() {
        //only one player is needed to test the square
        //If jill in @BeforeEach is not added, an invariant error occurs here,
        // because there must be at least 2 players in a game
        players.remove(jill);
        assertEquals(jack, mockGame.currentPlayer());

        //test wormholeEntranceSquare with a WormholeExitSquare at position 5
        wormholeExitSquarePos = 5; // set ExitSquarePos to 5
        //change square at position 5 to a WormholeExitSquare
        mockGame.setSquare(wormholeExitSquarePos, new WormholeExitSquare(mockGame, wormholeExitSquarePos));

        //check if wormholeExits square's lists is empty
        assertFalse(mockGame.wormholeExits().isEmpty());
        mockGame.movePlayer(2); //jack lands on WormholeEntranceSquare
        //jack position should be = 5 = wormholeExitSquarePos
        assertEquals(wormholeExitSquarePos, jack.position(), "jack has entered the wormholeEntranceSquare and moves to the wormholeExitSquare");
    }

    @Test
    public void wormholeEntranceSquareWithOneOccupiedExitTest() {
        //test wormholeEntranceSquare with a WormholeExitSquare at position 5 when it's already occupied
        wormholeExitSquarePos = 5; // set ExitSquarePos to 5
        //change square at position 5 to a WormholeExitSquare
        mockGame.setSquare(wormholeExitSquarePos, new WormholeExitSquare(mockGame, wormholeExitSquarePos));
        // the wormholeExits square's lists is not empty
        assertFalse(mockGame.wormholeExits().isEmpty());

        assertEquals(jack, mockGame.currentPlayer());
        mockGame.movePlayer(2); // jack lands on WormholeEntranceSquare
        //jack position should be 5 = wormholeExitSquarePos
        assertEquals(wormholeExitSquarePos, jack.position(), "jack has entered the wormholeEntranceSquare and moves to the wormholeExitSquare");

        assertEquals(jill, mockGame.currentPlayer());
        //jill will land on WormholeEntranceSquare that is already occupied by jack
        mockGame.movePlayer(4);
        //jill position shouldn't change = 1 = firstSquare
        assertEquals(1, jill.position());
    }
}