package info.chees.scorchedmmo.game;

import info.chees.scorchedmmo.game.Player;
import junit.framework.Assert;

import org.junit.Test;

public class PlayerTest {
	@Test
	public void testPlayerHasName() throws Exception {
		Player player = new Player(null);
		Assert.assertTrue(player.getName().length() > 1);
	}
}
