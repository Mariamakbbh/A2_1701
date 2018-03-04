package drawshape.view;

import java.io.IOException;

import drawshape.ShapeMain;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class DrawTriangleController {

	private ShapeMain shapeMain;
	
	@FXML
	private Label errMessageSideOne;
	@FXML
	private Label errMessageSideTwo;
	@FXML
	private Label errMessageSideThree;
	@FXML
	private Label errMessageGeneral;
	@FXML
	private Label executeMessage;
	@FXML
	private TextField sideOneField;
	@FXML
	private TextField sideTwoField;
	@FXML
	private TextField sideThreeField;
	@FXML
	private Button drawButton;	
	@FXML
	private Button closeButton;

	@FXML
	public void handledrawButtonAction(ActionEvent event) throws IOException {
		if(isInputValid() && (sidesValidForTriangle())){
			resetErrorMessages();
			executeMessage.setText("Drawing...");
				shapeMain.drawTriangleProcess(Integer.parseInt(sideOneField.getText()),Integer.parseInt(sideTwoField.getText()), Integer.parseInt(sideThreeField.getText()));
				//Closing input window
//				Stage stage = (Stage) drawButton.getScene().getWindow();
//				stage.close();
				//Alert drawing done
				showAlertDoneNewStage();
//			resetErrorMessages();
//			Main.moveFinch();
//			//Closing input window
//			Stage stage = (Stage) drawButton.getScene().getWindow();
//			stage.close();
//			//Alert drawing done
//			showAlertDoneNewStage();
//			//Show list!!!!!
//			showListDrawn();
		}
		else System.out.println("Invalid input!!Nooouah!");
	}
	
//	private void showListDrawn(){
//		Main.buildStringShapesDrawn();
//	}
	@FXML
	private void showAlertDoneNewStage() throws IOException {
		shapeMain.showAlertDone();
	}

	private boolean isInputValid() {
		if(isAnyInputNull())return false;
		resetErrorMessages();
		int x=0;
		int sideOne = Integer.parseInt(sideOneField.getText());
		int sideTwo = Integer.parseInt(sideTwoField.getText());
		int sideThree = Integer.parseInt(sideThreeField.getText());

		if(!(sideOne >= 30 && sideOne <=90)){
			errMessageSideOne.setText("*Invalid side one value. Please try again");
			return false;
		}
		if(!(sideTwo >= 30 && sideTwo <=90)){
			errMessageSideTwo.setText("*Invalid side two value. Please try again");
			return false;
		}
		if(!(sideThree >= 30 && sideThree <=90)){
			errMessageSideThree.setText("*Invalid side three value. Please try again");
			return false;
		}
		return true;
	}
	
	private boolean isAnyInputNull(){
		resetErrorMessages();
		int x=0;
		if(!(sideOneField.getLength()>0)){
			errMessageGeneral.setText("*All fields MUST BE completed. Please try again");			
			x+=1;
		}
		if(!(sideTwoField.getLength()>0)){
			errMessageGeneral.setText("*All fields MUST BE completed. Please try again");			
			x+=1;
		}
		if(!(sideThreeField.getLength()>0)){
			errMessageGeneral.setText("*All fields MUST BE completed. Please try again");			
			x+=1;
		}
		if(!(x==0)) return true;
		return false;
	}
	
	private boolean sidesValidForTriangle() {
		resetErrorMessages();
		int s1 = Integer.parseInt(sideOneField.getText());
		int s2 = Integer.parseInt(sideTwoField.getText());
		int s3 = Integer.parseInt(sideThreeField.getText());
		if(!(((s1+s2)>s3) && ((s2+s3)>s1) && ((s1+s3)>s2))){
			errMessageGeneral.setText("These values can NOT form a triangle. Please try again");
			return false;
		}
		return true;
	}

	private void resetErrorMessages() {
		errMessageSideOne.setText("");
		errMessageSideTwo.setText("");
		errMessageSideThree.setText("");
		errMessageGeneral.setText("");
	}

	@FXML
	public void handleCloseButtonAction(ActionEvent event) {
		Stage stage = (Stage) closeButton.getScene().getWindow();
		stage.close();
	}
	
	public void setShapeMain(ShapeMain shapeMain) {
		this.shapeMain = shapeMain;
	}

}
