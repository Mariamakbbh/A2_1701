package drawshape.view;

import java.io.IOException;

import drawshape.Main;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;

public class MainViewController {

	private Main main;

	@FXML
	private MenuItem navbarAbout;

	@FXML
	private MenuItem navbarExit;

	@FXML
	private void aboutNewStage() throws IOException {
		main.showAboutStage();
	}
	@FXML
	private void killApplication() throws IOException {
		System.exit(0);
	}
}
