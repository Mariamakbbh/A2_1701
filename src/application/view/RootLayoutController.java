package application.view;

import application.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;


public class RootLayoutController {
	Main main;
	public RootLayoutController() {
		
	}
	@FXML 
	private void exit() {
		main.bot.endFinch();
		System.exit(0);
	}
	
	@FXML
	private void about() {
		System.out.print("About called");
		String details = "Assignment 2, Task 3: Navigate designed and implemented by Hamilton Manalo with heavy assistance from Stackoverflow.";
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("About");
		alert.setHeaderText("About A2: Navigate");
		alert.setContentText(details);
		alert.showAndWait();
	}
	public void setMain(Main main) {
		this.main = main;
	}
}
