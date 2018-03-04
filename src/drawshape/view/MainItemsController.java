package drawshape.view;

import java.io.IOException;

import drawshape.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXML;

public class MainItemsController {
	
	private Main main;
	
	@FXML
	private void drawRectNewStage() throws IOException {
		Main.showDrawRectStage();
	}
	
	@FXML
	private void drawTriangleNewStage() throws IOException {
		Main.showDrawTriangleStage();
	}
	
	@FXML
	private void exit() throws IOException {
		main.doubleBuzz();
//		main.buildStringShapesDrawn();
//		StatisticsController.handleCreateListAction();
		if(Main.rectangleDrawnCounter > 0 || Main.triangleDrawnCounter > 0){
			main.showStatisticsAndExitScene();
		}
		else{
			System.exit(0);
		}
	}
}
