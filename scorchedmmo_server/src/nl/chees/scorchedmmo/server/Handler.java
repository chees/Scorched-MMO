package nl.chees.scorchedmmo.server;

import nl.chees.scorchedmmo.game.Game;
import nl.chees.scorchedmmo.game.Player;

import org.json.simple.JSONObject;

public class Handler {

	Game game;
	
	public Handler(Game game) {
		this.game = game;
	}

	public String showField() {
		String output = "{\"field\":[";
		boolean first = true;
		for(int h : game.getField().getHeights()) {
			if(first) {
				output += h;
				first = false;
			} else {
				output += "," + h;
			}
		}
		output += "]}";
		return output;
	}

	@SuppressWarnings("unchecked")
	public String showEnterGame(Player player) {
		JSONObject json = new JSONObject();
		json.put("enterGame", player.getName());
		return json.toString();
	}

	@SuppressWarnings("unchecked")
	public String showLeaveGame(Player player) {
		JSONObject json = new JSONObject();
		json.put("leaveGame", player.getName());
		return json.toString();
	}

	@SuppressWarnings("unchecked")
	public String showMessage(Player player, String text) {
		JSONObject message = new JSONObject();
		message.put("from", player.getName());
		message.put("text", text);
		
		JSONObject json = new JSONObject();
		json.put("message", message);
		return json.toString();
	}

}
