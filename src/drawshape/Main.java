package drawshape;

import java.io.IOException;
import java.util.ArrayList;

import drawshape.view.StatisticsController;
import edu.cmu.ri.createlab.terk.robot.finch.Finch;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
	
public class Main extends Application {
	private static Stage primaryStage;
	private static BorderPane mainLayout;
	static Finch myFinch = new Finch();
	public static ArrayList<Shape> drawnList = new ArrayList<Shape>();
	public static StringBuilder listShapesDrawn = new StringBuilder("");
	public static int rectangleDrawnCounter = 0;
	public static int triangleDrawnCounter = 0;
	public static String mostDrawn;
    
	public static void main(String[] args) {
		launch(args);
	}
	
	public static void moveFinch(){

    myFinch.setLED(0, 255, 0);
    myFinch.setWheelVelocities(100,-100,5000);
}
	
	@Override
	public void start(Stage primaryStage) throws IOException {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Draw Shape App");
//		scene.getIcons().add(new Image("/img/AlertIconGreen.png"));
		showMainView();
		showMainItems();
	}
	
	public static void drawRectangleProcess(int width, int height){
		rectangleDrawnCounter += 1;
		Rectangle r = new Rectangle(width, height);
		drawnList.add(r);
		executeDrawRectangle(width, height);
		//Single buzz
		singleBuzz();
	}
	
//	double a = Math.round(Math.toDegrees(acos((Math.pow(C,2)+Math.pow(B,2))-Math.pow(A,2))));
//	double b = Math.round(Math.toDegrees(acos((Math.pow(A,2)+Math.pow(C,2))-Math.pow(B,2))));
//	double c = Math.round(Math.toDegrees(acos((Math.pow(A,2)+Math.pow(B,2))-Math.pow(C,2))));
	
	public static void drawTriangleProcess(int sideOne, int sideTwo, int sideThree){
		triangleDrawnCounter += 1;
		float a1 = (float) Math.toDegrees(Math.acos((sideOne*sideOne + sideTwo*sideTwo - sideThree*sideThree)/(2*sideOne*sideTwo)));
		float a2 = (float) Math.toDegrees(Math.acos((sideThree*sideThree + sideTwo*sideTwo - sideOne*sideOne)/(2*sideThree*sideTwo)));
		float a3 = (float) Math.toDegrees(Math.acos((sideThree*sideThree + sideOne*sideOne - sideTwo*sideTwo)/(2*sideOne*sideThree)));
		int angleA = Math.round(a1);
		int angleB = Math.round(a2);
		int angleC = Math.round(a3);
		Triangle t = new Triangle(sideOne, sideTwo, sideThree, angleA, angleB, angleC);
		drawnList.add(t);
		executeDrawTriangle(sideOne, sideTwo, sideThree, angleA, angleB, angleC);
		//Single buzz
		singleBuzz();
	}
	
	public static void executeDrawTriangle(int s1, int s2, int s3, int a1, int a2, int a3){
			myFinch.setWheelVelocities(150, 150,67*s1);
			myFinch.setWheelVelocities(-97, 97,11*a3);
			myFinch.setWheelVelocities(150, 150,67*s2);
			myFinch.setWheelVelocities(-97, 97,11*a2);
			myFinch.setWheelVelocities(150, 150,67*s3);
			myFinch.setWheelVelocities(-97, 97,11*a1);
		}
	
	public static void executeDrawRectangle(int w, int h){
		for(int i=0; i<2; i++){
			myFinch.setWheelVelocities(150, 150,67*w);
			myFinch.setWheelVelocities(-97, 97,11*90);
			myFinch.setWheelVelocities(150, 150,67*h);
			myFinch.setWheelVelocities(-97, 97,11*90);
		}
	}
	
	public static void buildStringShapesDrawn(){
		for(int i=0; i<drawnList.size(); i++){
			listShapesDrawn.append(drawnList.get(i).getInfo() + "\n");
		}
 
//		for (int i=0; i<drawnList.size(); i++)
//		 {
//		   //Concatenate each loop 
//			listShapesDrawn.append("    Field "+i+"\n");
//		 }
//		textArea.setText(listShapesDrawn.toString());
	}
	
	public static void calculateMostDrawn(){
		if(rectangleDrawnCounter > triangleDrawnCounter){
			mostDrawn = "Rectangle";
		}
		else mostDrawn = "Triangle";
	}
	
	private void showMainView() throws IOException {
	FXMLLoader loader = new FXMLLoader();
	loader.setLocation(Main.class.getResource("view/Mainview.fxml"));
	mainLayout = loader.load();
	Scene scene = new Scene(mainLayout);
	primaryStage.setScene(scene);
	primaryStage.show();
	primaryStage.setResizable(false);
}
	
	private void showMainItems() throws IOException  {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("view/MainItems.fxml"));
		BorderPane mainItems = loader.load();
		mainLayout.setCenter(mainItems);
		
	}
	
	public static void showDrawRectStage() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("view/DrawRect.fxml"));
		BorderPane DrawRect = loader.load();
		
		Stage addDialogStage = new Stage();
		addDialogStage.setTitle("");
		addDialogStage.initModality(Modality.WINDOW_MODAL);
		addDialogStage.initOwner(primaryStage);
		Scene scene = new Scene(DrawRect);
		addDialogStage.setScene(scene);
		addDialogStage.showAndWait();
		
	}
	
	public static void singleBuzz(){
		//Single buzz on finch
//		myFinch.buzz(10000, 1000);
//		myFinch.sleep(2000);	
	}
	
	public static void doubleBuzz(){
		//Double buzz on finch
//		singleBuzz();
//		singleBuzz();
	}
	
	
	public static void showDrawTriangleStage() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("view/DrawTriangle.fxml"));
		BorderPane DrawTriangle = loader.load();
		
		Stage addDialogStage = new Stage();
		addDialogStage.setTitle("");
		addDialogStage.initModality(Modality.WINDOW_MODAL);
		addDialogStage.initOwner(primaryStage);
		Scene scene = new Scene(DrawTriangle);
		addDialogStage.setScene(scene);
		addDialogStage.showAndWait();
		
	}
	
	public static void showAboutStage() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("view/About.fxml"));
		BorderPane about = loader.load();
		
		Stage addDialogStage = new Stage();
		addDialogStage.setTitle("");
		addDialogStage.initModality(Modality.WINDOW_MODAL);
		addDialogStage.initOwner(primaryStage);
		Scene scene = new Scene(about);
		addDialogStage.setScene(scene);
		addDialogStage.showAndWait();
	}
	
	public static void showStatisticsAndExitScene() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("view/Statistics.fxml"));
		BorderPane ShowStatistics = loader.load();
		
		Stage addDialogStage = new Stage();
		addDialogStage.setTitle("");
		addDialogStage.initModality(Modality.WINDOW_MODAL);
		addDialogStage.initOwner(primaryStage);
		Scene scene = new Scene(ShowStatistics);
		addDialogStage.setScene(scene);
		addDialogStage.showAndWait();
	}
	
	public static void showAlertDone() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("view/AlertMessageDone.fxml"));
		GridPane ShowAlert = loader.load();
		
		Stage addDialogStage = new Stage();
		addDialogStage.setTitle("");
		addDialogStage.initModality(Modality.WINDOW_MODAL);
		addDialogStage.initOwner(primaryStage);
		Scene scene = new Scene(ShowAlert);
		addDialogStage.setScene(scene);
		addDialogStage.showAndWait();
	}
}
