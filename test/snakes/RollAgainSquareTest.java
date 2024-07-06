package snakes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import snakes.squares.RollAgainSquare;

import java.util.Queue;
import java.util.LinkedList;
import static org.junit.jupiter.api.Assertions.*;

public class RollAgainSquareTest {

    public Game game;
    public Player jack;
    public Player jill;
    public IDie mockDie;

    @BeforeEach
    public void start(){
        //we set up the game with a mocked die to control the rolls.
        Queue<Player> players = new LinkedList<>();
        jack = new Player("jack");
        players.add(jack);
        jill = new Player("jill");
        players.add(jill);
        game = new Game(5, players, 6);
        mockDie = mock(IDie.class);

        game.setSquare(2, new RollAgainSquare(game, 2)); //square 2 is now a RollAgainSquare


    }

    @Test
    public void rollAgainTest(){
        //Even tho we have no method in the RollAgainSquare, we can still test it with the play method in game
        when(mockDie.roll()).thenReturn(1,3); //The first roll moves on the RollAgainSquare, the second one moves forward
        game.play(mockDie);
        verify(mockDie, times(2)).roll(); //We verfiy that roll() was called twice.
        assertEquals(5, jack.position()); //Jack should now be on square 5.
    }
}
