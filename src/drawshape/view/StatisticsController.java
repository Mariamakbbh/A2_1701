package drawshape.view;

import drawshape.ShapeMain;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class StatisticsController {
	
	private ShapeMain shapeMain;
	
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
		shapeMain.buildStringShapesDrawn();
		shapeMain.calculateMostDrawn();
		taListOfShapesDrawn.setText(ShapeMain.listShapesDrawn.toString());
		txtMostDrawnShape.setText(ShapeMain.mostDrawn);
	}

	@FXML
	public void handleCloseButtonAction(ActionEvent event) {
		System.exit(0);
	}
}
