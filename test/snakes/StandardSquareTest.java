package snakes;

import snakes.squares.Square;
import snakes.squares.StandardSquare;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class StandardSquareTest {
	@Test
	public void testMoveAndLandOnly() {
		snakes.Game game = mock(Game.class);
		snakes.squares.Square testSquare;
		snakes.squares.Square start, stop;

		when(game.isValidPosition(anyInt())).thenReturn(true); //tell the mocked game class what to do if isValidPosition() is called
		testSquare = new StandardSquare(game, 1); //create square on which we want to test moveAndLand()
		start = mock(snakes.squares.Square.class); //mock for findSquare()
		stop = mock(snakes.squares.Square.class); //mock for landHereOrGoHome()

		when(game.findSquare(1, 2)).thenReturn(start);
		when(start.landHereOrGoHome()).thenReturn(stop);

		Square destination = testSquare.moveAndLand(2);
		assertEquals(stop, destination); //actual test for testSquare
	}
}
