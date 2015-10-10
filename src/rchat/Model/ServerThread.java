package rchat.Model;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;

public class ServerThread extends Thread {
	private Server server;
	private Socket socket;
	
	public ServerThread(Server server, Socket socket) {
		this.server = server;
		this.socket = socket;
		start();
	}
	
	public void run() {
		try {
			// Wait for the data output stream from the client
			// Receive with the data input stream
			DataInputStream din = new DataInputStream(socket.getInputStream());
			
			while (true) {
				// Read the incoming message
				String message = din.readUTF();
				
				// Server Thread console testing
				/*if (!message.equals("")) {
					System.out.println("Server received the message");
				}*/
				
				// Server will send the messsage back to all clients
				server.sendToAll(message);
			}
		} catch (EOFException eofe) {
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			server.removeConnection(socket);
		}
	}	
}
