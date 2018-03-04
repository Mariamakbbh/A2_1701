package drawshape.view;

import drawshape.Main;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class StatisticsController {
	
	private Main main;
	
	@FXML
	private Button closeButton;
	
	@FXML
	private Label txtMostDrawnShape;
	
	@FXML
	public TextArea taListOfShapesDrawn;
	
	
	
	public void initialize() {
		this.handleShowStatistics();
	}
	
	@FXML
	public void handleShowStatistics() {
		main.buildStringShapesDrawn();
		main.calculateMostDrawn();
		taListOfShapesDrawn.setText(Main.listShapesDrawn.toString());
		txtMostDrawnShape.setText(Main.mostDrawn);
	}

	@FXML
	public void handleCloseButtonAction(ActionEvent event) {
		System.exit(0);
	}
}
