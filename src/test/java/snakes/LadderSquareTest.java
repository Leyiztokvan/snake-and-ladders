package snakes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import snakes.squares.LadderSquare;
import snakes.squares.Square;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class LadderSquareTest {

    private Game mockGame;
    private LadderSquare ladderSquare;
    private final int ladderPosition = 3;
    private final int ladderTransport = 4;

    @BeforeEach
    public void start() {
        mockGame = mock(Game.class);
        Mockito.when(mockGame.isValidPosition(3)).thenReturn(true);
        Mockito.when(mockGame.isValidPosition(7)).thenReturn(true);
        ladderSquare = new LadderSquare(ladderTransport, mockGame, ladderPosition);


    }

    @Test
    public void validTransportTest() {
        assertTrue(ladderSquare.isValidTransport(ladderTransport), "Transport should be valid because >0");

    }

    @Test
    public void destinationTest() {
        Square destinationSquare = mock(Square.class);
        when(mockGame.getSquare(ladderPosition + ladderTransport)).thenReturn(destinationSquare);
        //mocked the Square class
        assertEquals(destinationSquare, ladderSquare.destination());
    }

    @Test

    public void landHereOrGoHomeTest() {
        Square destinationSquare = mock(Square.class);
        when(mockGame.getSquare(ladderPosition + ladderTransport)).thenReturn(destinationSquare);
        when(destinationSquare.landHereOrGoHome()).thenReturn(destinationSquare);
        assertEquals(destinationSquare, ladderSquare.landHereOrGoHome());
    }
}
