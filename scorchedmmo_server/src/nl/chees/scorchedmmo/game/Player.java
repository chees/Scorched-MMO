package nl.chees.scorchedmmo.game;

import net.tootallnate.websocket.WebSocket;

public class Player {

	private static int playerCount = 0;

	private WebSocket socket;
	private String name;

	public Player(WebSocket ws) {
		playerCount++;
		socket = ws;
		name = "Player " + playerCount;
	}

	public WebSocket getSocket() {
		return socket;
	}

	public String getName() {
		return name;
	}
}
