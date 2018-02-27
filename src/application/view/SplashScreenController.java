package application.view;



import application.Main;
import javafx.fxml.FXML;


public class SplashScreenController {

	private Main main; 
	
	public SplashScreenController() {
	}
	
	@FXML
	private void controlScreen() {
		main.showControlScreen();
	}
	
	
	public void setMain(Main main) {
		this.main = main;
	}
	
	
}
