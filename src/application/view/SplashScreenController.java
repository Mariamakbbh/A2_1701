package application.view;

import application.Main;
import javafx.fxml.FXML;


public class SplashScreenController {

	private Main main; 
	
	public SplashScreenController() {
	}
	
	//open control window by calling it from main.
	@FXML
	private void controlScreen() {
		main.showControlScreen();
	}
	
	//give access to main
	public void setMain(Main main) {
		this.main = main;
	}
	
	
}
