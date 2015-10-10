package rchat.View;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.text.DefaultCaret;

import rchat.Controller.ClientController;
import rchat.Controller.LogInController;
import rchat.Model.Client;

public class MessageScreen extends JFrame {
	
	private JPanel contentPane;
	private JTextField messageField;
	private JTextArea messageArea;
	private JMenuBar menuBar;
	private DefaultCaret caret;
	private JScrollPane scrollPane;
	private JScrollBar scrollBar;
	private JTextArea sentMessages;
	private JTextField textField;
	private JButton btnSend;
	private String username;
	private ClientController clientController;
	
	public MessageScreen() {
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800,600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5,5,5,5));
		setContentPane(contentPane);
		setLocationRelativeTo(null);

		GridBagLayout gbl_contentPane = new GridBagLayout();
		// Edit the GridBagLayout's individual cells in the array
		gbl_contentPane.columnWidths = new int[]{790, 10}; // Sum = 800
		gbl_contentPane.rowHeights = new int[]{590, 10}; // Sum = 600
		gbl_contentPane.columnWeights = new double[]{1.0, 0.0};
		gbl_contentPane.rowWeights = new double[]{1.0, 0.0};
		contentPane.setLayout(gbl_contentPane);
		
		sentMessages = new JTextArea();
		sentMessages.setEditable(false);
		GridBagConstraints gbc_sentMessages = new GridBagConstraints();
		gbc_sentMessages.insets = new Insets(0, 0, 5, 5);
		gbc_sentMessages.fill = GridBagConstraints.BOTH;
		gbc_sentMessages.gridx = 0;
		gbc_sentMessages.gridy = 0;
		contentPane.add(sentMessages, gbc_sentMessages);
		
		textField = new JTextField();
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					// Send message
					sendMessageOnScreen();
				}
			}
		});
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.anchor = GridBagConstraints.NORTH;
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 0;
		gbc_textField.gridy = 1;
		contentPane.add(textField, gbc_textField);
		textField.setColumns(10);
		
		btnSend = new JButton("Send");
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				sendMessageOnScreen();
			}
		});
		
		GridBagConstraints gbc_btnSend = new GridBagConstraints();
		gbc_btnSend.anchor = GridBagConstraints.NORTH;
		gbc_btnSend.insets = new Insets(0, 0, 5, 0);
		gbc_btnSend.gridx = 1;
		gbc_btnSend.gridy = 1;
		contentPane.add(btnSend, gbc_btnSend);
		
		// Add button + Enter functionality
		
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenuItem exitMenuItem = new JMenuItem("Exit");
		menuBar.add(exitMenuItem);	
		
		String host = LogInController.getIp();
		int port = LogInController.getPort();
		clientController = new ClientController(username, host, port, this);
	}
	
	// Sends message from Client Controller to Client
	public void sendMessageOnScreen() {
		if (!textField.getText().equals("")) {
			String message = username + ": "; 
			message += textField.getText();
			textField.setText("");
			clientController.sendMessage(message);
		}
	}
	
	public void receiveMessageOnScreen(String message) {
		sentMessages.append(message + "\n");
	}

	public void setUsername(String username) {
		this.username = username;
		setTitle("R Chat - " + username);
	}
	
}
