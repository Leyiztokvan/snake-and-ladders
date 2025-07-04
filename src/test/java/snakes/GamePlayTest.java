package snakes;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.LinkedList;
import java.util.Queue;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

public class GamePlayTest {

    private Game newGame;
    private Player jack;

    @BeforeEach
    //first initialize a game with two player, which will be used in the tests below
    public void start() {
        Queue<Player> players = new LinkedList<>();
        jack = new Player("Jack");
        Player jill = new Player("Jill");
        players.add(jack);
        players.add(jill);
        newGame = new Game(9, players, 6);

    }

    @Test
    public void playWithMockito() {
        //mock the IDie
        IDie mockDie = Mockito.mock(IDie.class);
        //configure the mocked die to return 4 everytime it is rolled
        when(mockDie.roll()).thenReturn(4);
        //activate the play method
        newGame.play(mockDie);
        //Jack will now move to Square 5, jill will do too (since it is occupied, jill will go back to FirstSquare),
        //Jack will roll 4 again and move to Square 9, winning at the same time the game
        assertTrue(jack.wins());

    }

    @Test
    public void playWithoutMockito() {
        //basically this tests the same thing as with the mocked Die using mockito, but
        //we have to create an entire class in order to make the mocked die return 4
        IDie mockDie = new MockDie(4);
        newGame.play(mockDie);
        assertTrue(jack.wins());

    }
}
