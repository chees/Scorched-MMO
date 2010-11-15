package info.chees.scorchedmmo.game;

import java.util.ArrayList;
import java.util.List;

import net.tootallnate.websocket.WebSocket;

public class Game {

	private List<Player> players;
	private Field field;
	
	public Game() {
		players = new ArrayList<Player>();
		field = new Field();
	}
	
	public void addPlayer(Player player) {
		players.add(player);
	}

	/**
	 * Removes the player with the given WebSocket from the game.
	 * @param ws The WebSocket of the Player.
	 * @return The Player or null if it wasn't found.
	 */
	public Player removePlayer(WebSocket ws) {
		for(Player p : players) {
			if(p.getSocket() == ws) {
				players.remove(p);
				return p;
			}
		}
		return null;
	}
	
	public List<Player> getPlayers() {
		return players;
	}

	public Player getPlayer(WebSocket ws) {
		for(Player p : players) {
			if(p.getSocket() == ws) {
				return p;
			}
		}
		return null;
	}

	public Field getField() {
		return field;
	}

}
