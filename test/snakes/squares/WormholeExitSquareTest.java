package snakes.squares;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import snakes.Game;
import snakes.Player;

import java.util.LinkedList;
import java.util.Queue;

import static org.junit.jupiter.api.Assertions.*;

class WormholeExitSquareTest {

    private Game mockGame;
    private Queue<Player> players;
    private int wormholeEntranceSquarePos;
    private int wormholeExitSquarePos;
    private Player jack;
    private Player jill;

    @BeforeEach
    public void beforeEach() {
        //we set up the game with a mocked die to control the rolls.
        players = new LinkedList<>();
        jack = new Player("jack");
        players.add(jack);
        jill = new Player("jill");
        players.add(jill);
        mockGame = new Game(10, players, 6);
        wormholeEntranceSquarePos = 3; // set EntranceSquarePos to 5
        // set square 3 to a WormholeEntranceSquare
        mockGame.setSquare(wormholeEntranceSquarePos, new WormholeEntranceSquare(mockGame, wormholeEntranceSquarePos));
        wormholeExitSquarePos = 5; // set ExitSquarePos to 5
        // set square at position 5 to a WormholeExitSquare
        mockGame.setSquare(wormholeExitSquarePos, new WormholeExitSquare(mockGame, wormholeExitSquarePos));

    }

    @Test
    public void wormholeExitSquareWithOneExitTest(){
        // We only need one player to test the square
        // If I didn't add jill in @BeforeEach there would be an invariant error occurring here, because there must be at leas 2 players in a game
        players.remove(jill);
        assertEquals(jack, mockGame.currentPlayer());

        // test the wormholeEntranceSquare with a WormholeExitSquare at position 5
        // the wormholeExits square's lists is not empty
        assertFalse(mockGame.wormholeExits().isEmpty());
        Square exitSquare = mockGame.getSquare(wormholeExitSquarePos);
        // square at position 5 is a wormholeExitSquare
        assertTrue(exitSquare.isWormholeExit());

        mockGame.movePlayer(2); // jack lands on WormholeEntranceSquare
        // jack position should be = 5 = wormholeExitSquarePos
        assertEquals(wormholeExitSquarePos, jack.position(), "jack has entered the wormholeEntranceSquare and moves to the wormholeExitSquare");
    }

    @Test
    public void wormholeExitSquareWithOneOccupiedExitTest(){
        // test wormholeEntranceSquare with a WormholeExitSquare at position 5 when it's already occupied
        // the wormholeExits square's lists is not empty
        assertFalse(mockGame.wormholeExits().isEmpty());

        Square exitSquare = mockGame.getSquare(wormholeExitSquarePos);
        // square at position 5 is a wormholeExitSquare
        assertTrue(exitSquare.isWormholeExit());
        assertEquals(jack, mockGame.currentPlayer());

        mockGame.movePlayer(2); // jack lands on WormholeEntranceSquare
        // jack position should be = 5 = wormholeExitSquarePos
        assertEquals(wormholeExitSquarePos, jack.position(), "jack has entered the wormholeEntranceSquare and moves to the wormholeExitSquare");

        assertEquals(jill, mockGame.currentPlayer());
        mockGame.movePlayer(4); // jill will land on WormholeEntranceSquare that is already occupied by jack
        // jill position shouldn't change = 1 = firstSquare
        assertEquals(1, jill.position());
    }


    @Test
    void isWormholeExitTests() {
        Square exitSquare = mockGame.getSquare(wormholeExitSquarePos);
        // square at position 5 is a wormholeExitSquare
        assertTrue(exitSquare.isWormholeExit());
    }
}