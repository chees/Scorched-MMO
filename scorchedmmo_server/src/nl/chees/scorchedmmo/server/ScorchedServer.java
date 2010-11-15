package nl.chees.scorchedmmo.server;

import java.io.IOException;

import net.tootallnate.websocket.WebSocket;
import net.tootallnate.websocket.WebSocketServer;
import nl.chees.scorchedmmo.game.Game;
import nl.chees.scorchedmmo.game.Player;

/**
 * A WebSocketServer implementation.
 */
public class ScorchedServer extends WebSocketServer {

	private Game game;
	private Handler handler;
	
    public ScorchedServer(int port) {
        super(port);
        game = new Game();
        handler = new Handler(game);
    }

    
    public void onClientOpen(WebSocket socket) {
    	Player player = new Player(socket);
    	game.addPlayer(player);
    	send(socket, handler.showField());
    	sendToAllExcept(socket, handler.showEnterGame(player));
    }

    public void onClientClose(WebSocket socket) {
    	Player player = game.removePlayer(socket);
        sendToAll(handler.showLeaveGame(player));
    }

    public void onClientMessage(WebSocket socket, String message) {
    	Player player = game.getPlayer(socket);
        sendToAll(handler.showMessage(player, message));
    }

    
    @Override
    public void sendToAll(String text) {
    	System.out.println(text);
    	try {
    		super.sendToAll(text);
    	} catch(IOException e) {
    		e.printStackTrace();
    	}
    }
    
    @Override
    public void sendToAllExcept(WebSocket socket, String text) {
    	System.out.println(text);
    	try {
			super.sendToAllExcept(socket, text);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    private void send(WebSocket socket, String text) {
    	System.out.println(text);
    	try {
			socket.send(text);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    
    public static void main(String[] args) {
        int port = 4242;
        if(args.length > 0)
        	port = Integer.parseInt(args[0]);
        ScorchedServer s = new ScorchedServer(port);
        s.start();
        System.out.println("ScorchedServer started on port: " + s.getPort());
    }
}
