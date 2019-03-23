package shop;

import shop.beam.User;
import shop.view.Login;

public class Main {

	private static Main instance = new Main();
		
	public static Main getInst() {
		return instance;
	}
	
	private User user;
	
	private void init() {
		Login login = new Login();
		login.init();
	}
	
	public User getUser() {
		return user;
	}
	
	public static void main(String[] args) {
		Main.getInst().init();
	}
}
