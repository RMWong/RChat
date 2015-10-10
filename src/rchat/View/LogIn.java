package rchat.View;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import javax.swing.*;

import rchat.Controller.LogInController;

public class LogIn extends JFrame {

	private JTextField username;
	private JTextField ip;
	private JTextField port;

	public LogIn() throws Exception {
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

		setTitle("R Chat");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400, 400);
		setLocationRelativeTo(null);

		JPanel panel = new JPanel();

		getContentPane().setLayout(null);
		getContentPane().add(panel);

		username = new JTextField();
		username.setBounds(179, 80, 86, 20);
		getContentPane().add(username);
		username.setColumns(10);		

		ip = new JTextField();
		ip.setBounds(179, 130, 86, 20);
		getContentPane().add(ip);
		ip.setColumns(10);
		// TODO REMOVE DEFAULT
		ip.setText("localhost");

		port = new JTextField();
		port.setBounds(179, 180, 86, 20);
		getContentPane().add(port);
		port.setColumns(10);
		// TODO UNCOMMENT #### TO SET PORT REMOVE DEFAULT
		//port.setText("####");

		JButton btnNewButton = new JButton("Log In");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (verifyLogIn(username.getText(), ip.getText(), port.getText())) {
					dispose();
					try {
						new LogInController(username.getText() ,Integer.parseInt(port.getText()));
					} catch (Exception e) {
						e.printStackTrace();
					} 
				} else
					System.out.println("Invalid log in information, please try again.");
			}
		});
		btnNewButton.setBounds(179, 221, 89, 23);
		getContentPane().add(btnNewButton);

		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setBounds(82, 83, 63, 14);
		getContentPane().add(lblUsername);

		JLabel lblIpAddress = new JLabel("IP Address:");
		lblIpAddress.setBounds(82, 133, 78, 14);
		getContentPane().add(lblIpAddress);

		JLabel lblPortNumber = new JLabel("Port Number:");
		lblPortNumber.setBounds(82, 183, 78, 14);
		getContentPane().add(lblPortNumber);
	}

	private boolean verifyLogIn(String username, String ip, String port) {
		return (!username.equals("") && !ip.equals("") && !port.equals(""));
	}

}
