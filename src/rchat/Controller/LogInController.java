package rchat.Controller;

import java.io.IOException;

import rchat.Model.Client;
import rchat.View.MessageScreen;

public class LogInController {
	
	// This class needs to be refactored...
	// also acts as the message screen controller
	
	private static String username;
	private static int port;
	private static String ip;
	private ClientController clientController;
	private Client client;
	
	public LogInController(String username, int port, String ip) throws IOException {
		this.username = username;
		this.port = port;
		this.ip = ip;
		MessageScreen msgScreen = new MessageScreen();
		msgScreen.setVisible(true);
		msgScreen.setUsername(username);
	}
	
	public LogInController(String username, int port) throws IOException {
		// TODO
		// where IP is local host
		this.username = username;
		this.port = port;
		MessageScreen msgScreen = new MessageScreen();
		msgScreen.setVisible(true);
		msgScreen.setUsername(username);
		String host = "localhost";
	}
	
	// TODO log in validation should be in this class
	
	public static String getIp() {
		return ip;
	}
	
	public static String getUsername() {
		return username;
	}
	
	public static int getPort() {
		return port;
	}
	
}
