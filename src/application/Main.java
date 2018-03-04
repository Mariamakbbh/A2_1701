package application;


import java.io.IOException;

import application.logic.Retracer;
import application.logic.RobotController;
import application.view.ControlScreenController;
import application.view.RootLayoutController;
import application.view.SplashScreenController;
import drawshape.ShapeMain;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	
	private ShapeMain ShapeMain;
	public Stage primaryStage;
	private BorderPane rootLayout;
	public RobotController bot;
	
	@Override
	public void start(Stage primaryStage) {
		//handle exception
		try {
			
			this.primaryStage = primaryStage;
			//Set scene window title
			this.primaryStage.setTitle("Assignment 2: Task 3 \"Navigate\"");
			//Open window
			loadScene();
			//Open splash screen
			showSplashScreen();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		startBot();
		
	}
	public void startBot() {
		System.out.println("Starting serivces");
		Runnable task = new Runnable() {
			public void run() {
				bot = new RobotController();
				bot.startFinch();
			}
		};
		Thread services = new Thread(task);
		services.setDaemon(true);
		services.start();
	}
	public static void main(String[] args) {
		launch(args);
	}
	
	
	public void loadScene() {
		//handle exceptions
		try { 
			
			FXMLLoader loader = new FXMLLoader();
			//get layout from file system
			loader.setLocation(Main.class.getResource("view/RootLayout.fxml"));
			
			//load layout
			rootLayout = (BorderPane) loader.load();
			
			//give main access to controller
			RootLayoutController controller = loader.getController();
			
			//give controller access to main
			controller.setMain(this);
			
			//create scene
			Scene scene = new Scene(rootLayout);
			
			//set and show scene
			primaryStage.setScene(scene);
			primaryStage.show();
			
		} catch(IOException e ) {
			e.printStackTrace();
		}
	}
	
		public void showSplashScreen() {
			//handle exceptions
			try{
				
				FXMLLoader loader = new FXMLLoader();
				
				//load splash screen layout 
				loader.setLocation(Main.class.getResource("view/SplashScreen.fxml"));
				AnchorPane SplashScreen = (AnchorPane) loader.load();
				
				//set to center of Rootlayout
				rootLayout.setCenter(SplashScreen);
				
				//give main access to controller
				SplashScreenController controller = loader.getController();
				
				//give controller access to main
				controller.setMain(this);
				
			} catch( IOException e) {
				
				e.printStackTrace();
			}
		}
		
	public void showControlScreen() {
		
		//handle exception
		try {
			FXMLLoader loader = new FXMLLoader();
			
			//load control screen layouts
			loader.setLocation(Main.class.getResource("view/ControlScreen.fxml"));
			AnchorPane ControlScreen = (AnchorPane) loader.load();
			
			//center control screen in main window
			rootLayout.setCenter(ControlScreen);
			
			//give main access to controller
			ControlScreenController controller = loader.getController();
			
			//give controller access to main
			controller.setMain(this);
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	
	public void showDrawShape() {
		ShapeMain = new ShapeMain(this, primaryStage);
		try {
			ShapeMain.showMainView();
			ShapeMain.showMainItems();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	//give main access to bot instance so Finch can be properly ended prior to program closing
//	public void setBot(RobotController bot) {
//		this.bot = bot;
//	}
}
