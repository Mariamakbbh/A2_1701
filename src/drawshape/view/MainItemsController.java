package drawshape.view;

import java.io.IOException;

import drawshape.ShapeMain;
import javafx.fxml.FXML;
import javafx.fxml.FXML;

public class MainItemsController {
	
	private ShapeMain shapeMain;
	
	@FXML
	private void drawRectNewStage() throws IOException {
		shapeMain.showDrawRectStage();
	}
	
	@FXML
	private void drawTriangleNewStage() throws IOException {
		shapeMain.showDrawTriangleStage();
	}
	
	@FXML
	private void exit() throws IOException {
		shapeMain.doubleBuzz();
//		main.buildStringShapesDrawn();
//		StatisticsController.handleCreateListAction();
		if(ShapeMain.rectangleDrawnCounter > 0 || ShapeMain.triangleDrawnCounter > 0){
			shapeMain.showStatisticsAndExitScene();
		}
		else{
			System.exit(0);
		}
	}
	
	public void setShapeMain(ShapeMain shapeMain) {
		this.shapeMain = shapeMain;
	}
}
