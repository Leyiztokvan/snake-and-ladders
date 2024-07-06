package snakes.squares;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import snakes.Game;
import snakes.Player;

import java.util.LinkedList;
import java.util.Queue;

import static org.junit.jupiter.api.Assertions.*;

class SkipSquareTest {

    private Game mockGame;
    private int skipSquarePosition;

    private Queue<Player> players;
    private Player jack;
    private Player jill;
    private Player bob;

    @BeforeEach
    public void beforeEach() {
        //we set up the game with a mocked die to control the rolls.
        players = new LinkedList<>();
        jack = new Player("jack");
        jill = new Player("jill");
        players.add(jack);
        players.add(jill);
        mockGame = new Game(10, players, 6);
        skipSquarePosition = 5;
        mockGame.setSquare(skipSquarePosition, new SkipSquare(mockGame, skipSquarePosition)); //square 5 is now a SkipSquare
    }


    @Test
    // check if the game works correctly with `SkipSquare` when there are 2 players
    void twoPlayerSkipSquareTest(){
        assertEquals(jack, mockGame.currentPlayer());
        // The first roll moves jack on the SkipSquare,
        mockGame.movePlayer(4);
        assertEquals(skipSquarePosition, jack.position()); // check if jack is on SkipSquare
        //jack should be able to roll again because he landed on the SkipSquare
        // the second roll moves jack on the square 8 since he moved again after jill's turn is skipped
        assertEquals(jill, mockGame.currentPlayer()); // jill is the current player but is skipped
        mockGame.movePlayer(3);
        assertEquals(8, jack.position());
        assertEquals(jill, mockGame.currentPlayer());
        assertEquals(jack, mockGame.nextPlayer());
    }

    @Test
    // check if the game work correctly when there are `SkipSquare` with 3 players
    void threePlayerSkipSquareTest(){
        bob = new Player("Bob");
        players.add(bob);
        mockGame = new Game(10, players, 6);
        skipSquarePosition = 5;
        mockGame.setSquare(skipSquarePosition, new SkipSquare(mockGame, skipSquarePosition)); //square 5 is now a

        assertEquals(jack, mockGame.currentPlayer());
        // The first roll moves jack on square 3,
        mockGame.movePlayer(2);
        // The second roll moves jill on the SkipSquare,
        mockGame.movePlayer(4);
        assertEquals(skipSquarePosition, jill.position()); // check if jill is on SkipSquare
        // jill should be able to roll again because she landed on the SkipSquare
        // the second roll moves jill on the square 8 since she moved again after jack's turn is skipped
        assertEquals(bob, mockGame.currentPlayer()); // now is bob the current player but is skipped
        // jack moves to square 6
        mockGame.movePlayer(3);
        assertEquals(1, bob.position());
        assertEquals(6, jack.position());
        // jill moves to square 8
        mockGame.movePlayer(3);
        assertEquals(8, jill.position());
        assertEquals(jack, mockGame.currentPlayer());
        mockGame.movePlayer(2);
    }

}