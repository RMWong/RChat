package rchat;

import rchat.View.LogIn;

public class ClientApplication {
	public static void main(String[] args) {
		try {
			LogIn log = new LogIn();
			log.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
