package rchat.Controller;

import rchat.Model.Client;
import rchat.View.MessageScreen;

public class ClientController {
	
	private Client client;
	private MessageScreen messageScreen;
	
	public ClientController(String username, String host, int port, MessageScreen messageScreen) {
		this.messageScreen = messageScreen;
		System.out.println("Client Controller is on!");
		client = new Client(username, host, port, this);
	}
	
	public void sendMessage(String message) {
		client.processMessage(message);
	}
	
	public void receiveMessages(String message) {
		//receiveMessageOnScreen(message);
		messageScreen.receiveMessageOnScreen(message);
	}
	
}
