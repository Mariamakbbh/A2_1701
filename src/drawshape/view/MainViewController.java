package drawshape.view;

import java.io.IOException;

import drawshape.ShapeMain;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;

public class MainViewController {

	private ShapeMain shapeMain;

	@FXML
	private MenuItem navbarAbout;

	@FXML
	private MenuItem navbarExit;

	@FXML
	private void aboutNewStage() throws IOException {
		shapeMain.showAboutStage();
	}
	@FXML
	private void killApplication() throws IOException {
		System.exit(0);
	}
}
