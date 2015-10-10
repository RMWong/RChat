package rchat.Model;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Enumeration;
import java.util.Hashtable;


public class Server {
	// Server side uses ServerSocket class
	private ServerSocket serverSocket;
	private Socket socketConnection;
	
	// TODO refactor to use a thread safe and up to date Hashing structure
	private Hashtable<Socket, DataOutputStream> outputStreams = new Hashtable<Socket, DataOutputStream>();
	
	private OutputStream output;
	private InputStream input;
	
	public Server(int port) throws IOException {
		// Listen for port
		listen(port);
	}

	// Listen on port
	public void listen(int port) throws IOException {
		serverSocket = new ServerSocket(port);
		System.out.println("Server is listening on port: " + port);		
		// listen on port
		while (true) { 
			socketConnection = serverSocket.accept();
			
			
			System.out.println("Connection from " + serverSocket + " was a success!");
			DataOutputStream out = new DataOutputStream(socketConnection.getOutputStream());
		  
			// Save the data in hashtable 
			outputStreams.put(socketConnection, out);
		  
			// Create a new thread for this connection then let it run 
			new ServerThread(this, socketConnection); 
		}	
	}
	
	// Get an enumeration of all the OutputStreams, one for each client connected to us
	public Enumeration getOutputStreams() {
		return outputStreams.elements();
	}
	
	// Send messages to all clients
	public void sendToAll(String message) {
		synchronized(outputStreams) {
			for (Enumeration e = getOutputStreams(); e.hasMoreElements();) {
				DataOutputStream dout = (DataOutputStream) e.nextElement();
				
				try {
					dout.writeUTF(message);;
				} catch(IOException ioe) {
					ioe.printStackTrace();
				}
			}
		}
	}
	
	public void removeConnection(Socket s) {
		synchronized(outputStreams) {
			System.out.println("Removing connection to " + s);
			outputStreams.remove(s);
			try {
				s.close();
			} catch(IOException ioe) {
				System.out.println("Error closing " + s);
				ioe.printStackTrace();
			}
		}
	}
	
}
