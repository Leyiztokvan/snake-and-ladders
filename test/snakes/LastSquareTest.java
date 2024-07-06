package snakes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import snakes.Game;
import snakes.squares.LastSquare;
import snakes.Player;
import snakes.squares.Square;

import java.util.LinkedList;
import java.util.Queue;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class LastSquareTest {

    private Game mockGame;
    private LastSquare lastSquare;
    private int position = 50;
    Player mockPlayer = mock(Player.class);
    @BeforeEach

    public void start(){
        mockGame = mock(Game.class);
        Mockito.when(mockGame.isValidPosition(50)).thenReturn(true);
        lastSquare = new LastSquare(mockGame, position);
    }

    @Test
    public void isLastSquareTest(){
        assertTrue(lastSquare.isLastSquare(), "lastSquare is the last Square");
    }

    @Test
    public void landingEndsGameTest(){
        Queue<Player> players = new LinkedList<>();
        Player jack = new Player("jack");
        players.add(jack);
        Player jill = new Player("jill");
        players.add(jill);
        Game game = new Game(50, players, 6);
        LastSquare lastSquare = new LastSquare(game, 50);
        game.movePlayer(49);
        assertTrue(jack.wins(), "jack wins the game");
    }

}
