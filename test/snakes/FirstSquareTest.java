package snakes;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;
import snakes.Game;
import snakes.Player;
import snakes.squares.FirstSquare;
import snakes.squares.Square;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class FirstSquareTest {
    private Game mockGame;
    private FirstSquare firstSquare;
    private Player jack;
    private Player jill;

    @BeforeEach
    public void start(){
        mockGame = Mockito.mock(Game.class);
        jack = Mockito.mock(Player.class);
        jill = Mockito.mock(Player.class);
        Mockito.when(mockGame.isValidPosition(1)).thenReturn(true);
        firstSquare = new FirstSquare(mockGame, 1);
    }

    @Test
    public void enterAndIsOccupiedTest(){
        assertFalse(firstSquare.isOccupied(), "firstSquare is not occupied yet");
        firstSquare.enter(jack);
        assertTrue(firstSquare.isOccupied(), "firstSquare is now occupied by jack");
        firstSquare.enter(jill);
        assertTrue(firstSquare.isOccupied(), "firstSquare is now occupied by jack and jill");
    }

    @Test
    public void leaveTest(){
        firstSquare.enter(jack);
        assertTrue(firstSquare.isOccupied(), "firstSquare is now occupied by jack");
        //We need to make sure that isOccupied() is still working
        firstSquare.leave(jack);
        assertFalse(firstSquare.isOccupied(), "firstSquare is not occupied anymore");

    }

    @Test
    public void landHereOrGoHome(){
        Square resultSquare = firstSquare.landHereOrGoHome();
        assertSame(resultSquare, firstSquare,"The player should land on the first Square");

    }

    @Test
    public void isFirstSquareTest(){
        assertTrue(firstSquare.isFirstSquare());
    }






}
