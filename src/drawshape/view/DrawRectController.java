package drawshape.view;

import java.io.IOException;

import drawshape.Main;
import edu.cmu.ri.createlab.terk.robot.finch.Finch;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class DrawRectController {

	private Main main;

	//	@FXML
	//	private void drawRectNewStage() throws IOException {
	//		main.showDrawRectStage();
	//	}

	@FXML
	private Label errMessageWidth;
	@FXML
	private Label errMessageHeight;
	@FXML
	private Label errMessageGeneral;
	@FXML
	private Label executeMessage;
	@FXML
	private TextField widthField;
	@FXML
	private TextField heightField;
	@FXML
	private Button drawButton;
	@FXML
	private Button closeButton;

	@FXML
	public void handledrawButtonAction(ActionEvent event) throws IOException {
		if(isInputValid()){
			resetErrorMessages();
			executeMessage.setText("Drawing...");
				main.drawRectangleProcess(Integer.parseInt(widthField.getText()),Integer.parseInt(heightField.getText()));
				//Closing input window
				Stage stage = (Stage) drawButton.getScene().getWindow();
				stage.close();
				//Alert drawing done
				showAlertDoneNewStage();
		}
		else System.out.println("Invalid input!!Nooouah!");
	}
	private boolean isInputValid() {
		if(isAnyInputNull())return false;
		resetErrorMessages();
		int x=0;
		int width = Integer.parseInt(widthField.getText());
		int height = Integer.parseInt(heightField.getText());
		if(!(width >= 30 && width <=90)){
			errMessageWidth.setText("*Invalid width. Please try again");
			return false;
		}
		if(!(height >= 30 && height <=90)){
			errMessageHeight.setText("*Invalid height. Please try again");
			return false;
		}
		return true;
	}

	private boolean isAnyInputNull(){
		resetErrorMessages();
		int x=0;
		if(!(widthField.getLength()>0)){
			errMessageGeneral.setText("*All fields MUST BE completed. Please try again");			
			x+=1;
		}
		if(!(heightField.getLength()>0)){
			errMessageGeneral.setText("*All fields MUST BE completed. Please try again");			
			x+=1;
		}
		if(!(x==0)) return true;
		return false;
	}

	private void resetErrorMessages() {
		errMessageWidth.setText("");
		errMessageHeight.setText("");
		errMessageGeneral.setText("");
	}

	@FXML
	public void handleCloseButtonAction(ActionEvent event) {
		Stage stage = (Stage) closeButton.getScene().getWindow();
		stage.close();
	}
	
	@FXML
	private void showAlertDoneNewStage() throws IOException {
		Main.showAlertDone();
	}
}
