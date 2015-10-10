package rchat.Model;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import rchat.Controller.ClientController;
import rchat.View.MessageScreen;

public class Client implements Runnable {
	// Client side uses Socket class
	private Socket socket;
	
	private DataOutputStream dout;
	private DataInputStream din;
	ClientController clientController;
	
	public Client(String username, String host, int port, ClientController clientController) {
		try {
			this.clientController = clientController;
			socket = new Socket(host, port);
			System.out.println("connected to " + socket);
			din = new DataInputStream(socket.getInputStream());
			dout = new DataOutputStream(socket.getOutputStream());
			new Thread(this).start();
		} catch (IOException ioe) {
			System.out.println(ioe);
		}
	}
	 
	// Call when Client Controller has a message passed through
	public void processMessage(String message) {
		try {
			// Sends message to server
			dout.writeUTF(message);
			System.out.println("Message sent to server.");
		} catch (IOException ioe) {
			System.out.println(ioe);
		}
	}

	// Receive messages from Server, write it out to the text area
	@Override
	public void run() {
		try {
			while(true) {
				String message = din.readUTF();
				clientController.receiveMessages(message);
			}
		} catch (IOException ioe) {
			System.out.println(ioe);;
		}
	}
}
