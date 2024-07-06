package snakes;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import snakes.Game;
import snakes.Player;
import snakes.squares.LadderSquare;
import snakes.squares.LastSquare;
import snakes.squares.Square;
import org.junit.jupiter.api.BeforeEach;
import java.util.LinkedList;
import java.util.Queue;

public class GamePlayTest {

    private Game newGame;
    private IDie mockDie;
    private Player jack;
    private Player jill;

    @BeforeEach
    //We first initialize a game with two player that will be used in both tests:
    public void start(){
        Queue<Player> players = new LinkedList<>();
        jack = new Player("Jack");
        jill = new Player("Jill");
        players.add(jack);
        players.add(jill);
        newGame = new Game(9, players, 6);

        //We now mock the IDie
}
    @Test
    public void playWithMockito(){
        //We now mock the IDie
        mockDie = Mockito.mock(IDie.class);
        //We configure the mocked die to return 4 everytime it is rolled:
        when(mockDie.roll()).thenReturn(4);
        //We now activate the play method
        newGame.play(mockDie);
        /**
         * Jack will now move to Square 5, jill will too (since it is occupied, she will go back to FirstSquare),
         * Jack will roll 4 again and move to Square 9, winning at the same time the game.
         **/
        assertTrue(jack.wins());

    }

    @Test
    public void playWithoutMockito(){
        IDie mockDie = new MockDie(4);
        newGame.play(mockDie);
        assertTrue(jack.wins());

        //We basically did the same thing as with the mocked Die with mockito, but
        //we had to create an entire class in order to make the mocked die return 4.


    }
}
