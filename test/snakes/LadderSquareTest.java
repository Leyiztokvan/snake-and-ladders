package snakes;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import snakes.Game;
import snakes.squares.LadderSquare;
import snakes.squares.Square;

public class LadderSquareTest {

    private Game mockGame;
    private LadderSquare ladderSquare;
    private int ladderPosition = 3;
    private int ladderTransport = 4;

    @BeforeEach
    public void start(){
        mockGame = mock(Game.class);
        Mockito.when(mockGame.isValidPosition(3)).thenReturn(true);
        Mockito.when(mockGame.isValidPosition(7)).thenReturn(true);
        ladderSquare = new LadderSquare(ladderTransport, mockGame, ladderPosition);


    }

    @Test
    public void validTransportTest(){
        assertTrue(ladderSquare.isValidTransport(ladderTransport), "Transport should be valid because >0");

    }

    @Test
    public void destinationTest(){
        Square destinationSquare = mock(Square.class);
        when(mockGame.getSquare(ladderPosition + ladderTransport)).thenReturn(destinationSquare);
        //We mocked the Square class, which is way faster
        assertEquals(destinationSquare, ladderSquare.destination());
        //In LadderSquare, I had to make the method public, there is probably a better way to do it, but I couln't find it.

    }
    @Test

    public void landHereOrGoHomeTest(){
        Square destinationSquare = mock(Square.class);
        when(mockGame.getSquare(ladderPosition + ladderTransport)).thenReturn(destinationSquare);
        when(destinationSquare.landHereOrGoHome()).thenReturn(destinationSquare);
        assertEquals(destinationSquare, ladderSquare.landHereOrGoHome());
    }
}
