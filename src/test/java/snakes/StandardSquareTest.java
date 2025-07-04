package snakes;

import org.junit.jupiter.api.Test;
import snakes.squares.Square;
import snakes.squares.StandardSquare;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class StandardSquareTest {
    @Test
    public void testMoveAndLandOnly() {
        snakes.Game game = mock(Game.class);
        snakes.squares.Square testSquare;
        snakes.squares.Square start, stop;

        when(game.isValidPosition(anyInt())).thenReturn(true); //if isValidPosition() then the mocked class will do:
        testSquare = new StandardSquare(game, 1); //create square on which moveAndLand() is tested
        start = mock(snakes.squares.Square.class); //mock for findSquare()
        stop = mock(snakes.squares.Square.class); //mock for landHereOrGoHome()

        when(game.findSquare(1, 2)).thenReturn(start);
        when(start.landHereOrGoHome()).thenReturn(stop);

        Square destination = testSquare.moveAndLand(2);
        assertEquals(stop, destination);
    }
}
