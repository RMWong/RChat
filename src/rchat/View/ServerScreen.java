package rchat.View;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class ServerScreen extends JFrame {
	
	private JPanel contentPane;
	private JTextField textField;
	
	public ServerScreen() {
		setTitle("R Chat Server");
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
		
		textField = new JTextField();
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblServerRunning = new JLabel("The server Is running.");
		contentPane.add(lblServerRunning);
		setLocationRelativeTo(null);
	}
	
}
