package nl.chees.scorchedmmo.game;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import nl.chees.scorchedmmo.server.Handler;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.junit.Before;
import org.junit.Test;


public class HandlerTest {

	private Handler handler;
	private JSONParser parser;
	
	@Before
	public void setUp() {
		handler = new Handler(new Game());
		parser = new JSONParser();
	}

	@Test
	public void testShowField() throws Exception {
		String output = handler.showField();
		
		JSONObject parsed = (JSONObject) parser.parse(output);
		assertTrue(parsed.get("field") instanceof JSONArray);
	}

	@Test
	public void testShowEnterGame() throws Exception {
		Player player = new Player(null);
		
		String output = handler.showEnterGame(player);
		
		JSONObject parsed = (JSONObject) parser.parse(output);
		assertEquals(player.getName(), (String)parsed.get("enterGame"));
	}
	
	@Test
	public void testShowLeaveGame() throws Exception {
		Player player = new Player(null);
		
		String output = handler.showLeaveGame(player);
		
		JSONObject parsed = (JSONObject) parser.parse(output);
		assertEquals(player.getName(), (String)parsed.get("leaveGame"));
	}
	
	@Test
	public void testShowMessage() throws Exception {
		Player player = new Player(null);
		String text = "test message";
		
		String output = handler.showMessage(player, text);
		
		JSONObject parsed = (JSONObject) parser.parse(output);
		JSONObject message = (JSONObject) parsed.get("message");
		assertEquals(player.getName(), (String)message.get("from"));
		assertEquals(text, (String)message.get("text"));
		// {"message": {"from": "Player 1", "text": "test message"}}
	}
}
