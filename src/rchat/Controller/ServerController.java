package rchat.Controller;

import rchat.Model.Server;
import rchat.View.ServerScreen;

public class ServerController {

	public ServerController() {
		try {
			ServerScreen serverScreen = new ServerScreen();
			serverScreen.setVisible(true);
			
			// TODO Uncomment and use #### as port
			//new Server(####);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
