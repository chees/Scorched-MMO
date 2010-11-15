package nl.chees.scorchedmmo.game;

import net.tootallnate.websocket.WebSocket;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class GameTest {
	
	private Game game;
	
	@Before
	public void setUp() {
		game = new Game();
	}
	
	@Test
	public void testAddPlayer() throws Exception {
		Player player = new Player(null);
		game.addPlayer(player);
		assertTrue(game.getPlayers().size() == 1);
	}
	
	@Test
	public void testRemovePlayer() throws Exception {
		WebSocket ws = mock(WebSocket.class);
		Player player = new Player(ws);
		game.addPlayer(player);
		
		Player removed = game.removePlayer(ws);
		
		assertEquals(player, removed);
		assertTrue(game.getPlayers().size() == 0);
	}
	
	@Test
	public void testGetPlayerBySocket() throws Exception {
		WebSocket ws = mock(WebSocket.class);
		Player player = new Player(ws);
		game.addPlayer(player);
		
		assertEquals(player, game.getPlayer(ws));
	}
	
	@Test
	public void testGameHasField() throws Exception {
		assertNotNull(game.getField());
	}
}
