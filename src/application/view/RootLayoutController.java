package application.view;

import application.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;


public class RootLayoutController {
	Main main;
	
	public RootLayoutController() {
	}
	
	//exit command ends Finch to prevent USB issues and then exits program
	@FXML 
	private void exit() {
		main.bot.endFinch();
		System.exit(0);
	}
	
	//lil pop up about message
	@FXML
	private void about() {
		
		//out going message
		String details = "Assignment 2, Task 3: Navigate designed and implemented by Hamilton Manalo with heavy assistance from Stackoverflow. \r\n UI: JavaFX";
		//create alert, set title and head then display
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
