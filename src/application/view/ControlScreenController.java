package application.view;


import application.Main;
import application.logic.InputStorage;
//import application.Main;
import application.logic.Retracer;
import application.logic.RobotController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ControlScreenController {

	//javaFX fields 
	@FXML
	TextField numToRetrace;
	
	@FXML 
	TextField duration;
	
	@FXML
	TextField speed;
	
	@FXML
	TextField direction;
	
	@FXML
	private TextArea outputArea;

	private Retracer retracer;
	private RobotController bot;
	private Main main;
	
	
	public ControlScreenController() {
	}
	
	public void initialize() {
		
		//create instances of bot and retrace class when layout is loaded
		bot = new RobotController();
		retracer = new Retracer(bot, this);
		bot.startFinch();
		
		/*
		 * attach event listeners for text inputs which minimize user input error
		 * uses regEx to help validate input
		 */
		
		//make sure input is an integer
		numToRetrace.textProperty().addListener(new ChangeListener<String>() {
			 public void changed(ObservableValue<? extends String> observable, String oldValue, 
				        String newValue) {
				        if (!newValue.matches("\\d*")) {
				            numToRetrace.setText(newValue.replaceAll("[^\\d]", ""));
				        }}});
		
		//make sure value is between 1-6 and only one digit
		duration.textProperty().addListener(new ChangeListener<String>() {
			 public void changed(ObservableValue<? extends String> observable, String oldValue, 
				        String newValue) {
				        if (newValue.matches("[^1-6]*")) {
				        	duration.setText(newValue.replaceAll("[^1-6]", ""));
				        }
				        if(newValue.length() > 1) {
				        	duration.setText(oldValue.substring(0, 1));
				        }
			 }});
		
		//make sure value is no more then a 3 digit integer
		speed.textProperty().addListener(new ChangeListener<String>() {
			 public void changed(ObservableValue<? extends String> observable, String oldValue, 
				        String newValue) {
				        if (!newValue.matches("[0-9]{1,3}")) {
				    		speed.setText(newValue.replaceAll("[^0-9]{0,3}", ""));
				        }
				        if(newValue.length() > 3) {
				        	speed.setText(oldValue.substring(0, 3));
				        }}});
		
		//make sure direction is valid F, B, L, R no more then 1 character
		direction.textProperty().addListener(new ChangeListener<String>() {
			 public void changed(ObservableValue<? extends String> observable, String oldValue, 
				        String newValue) {
				        if (!newValue.matches("[f|F|b|B|r|R|l|L|q|Q]")) {
				        	direction.setText(newValue.replaceAll("[^f|F|b|B|r|R|l|L|q|Q]", ""));
				        }
				        if(newValue.length() > 3) {
				        	direction.setText(oldValue.substring(0, 1));
				        }	        
			 }});
		
		//set initial action window message
		String welcome = "> Prepare to assume control of a very powerful Robot";
		outputArea.setText(welcome);
	}
	/*
	 * getters and clearers that assist with retrieving values from the UI and clearing TextFields 
	 * error handling was added in case of a NULLPOINTEEXCEPTION
	 */
	private int getNum() {
		try {
			
		if(numToRetrace.getText().trim().equals("")){
			
			System.out.println("Called");
			System.out.println(numToRetrace.getText().toString());
			return 0;
		}
		int value = Integer.parseInt(numToRetrace.getText());
		return value;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	private void clearNum() {
		numToRetrace.clear();
	}
	
	private int getSpeed() {
		try {
			if(speed.getText().trim().equals(""))
				return 0;
			int value  = Integer.parseInt(speed.getText());
			return value;
			
		} catch (Exception e) {
			return 0;
		}
	}
	
	private void clearSpeed() {
		try {
			
		speed.clear();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private int getDuration() {
		try{
		if(duration.getText().trim().equals(""))
			return 0;
		int value = Integer.parseInt(duration.getText());
		return value;
		} catch (Exception e) {
			return 0;
		}
	}
	
	private void clearDuration() {
		duration.clear();
	}
	
	private char getDirection() {
	    try {
	    if(direction.getText().trim().equals(""))
	    	return 0;
		char value = direction.getText().charAt(0);
		return value;
	    } catch (Exception e) {
	    	return 0;
	    }
	}
	
	private void clearDirection() {
		direction.clear();
	}
	
	//background thread for running bot commands so UI doesn't freeze
	@FXML
	public void startAction() {
		System.out.println("startAction called");
		Runnable task = new Runnable() {
			public void run() {
				acceptInput();
			}
		};
		//May work
		Thread finchAction = new Thread(task);
		finchAction.setDaemon(true);
		finchAction.start();
	}
	
	//call actions on Finch when commands accurately entered
	@FXML
	private void acceptInput() {
		
		int speed;
		int duration;
		char direction;
		
		//handling exceptions for null's just in case
		try {
			speed = getSpeed();
			duration = getDuration();
			direction = getDirection();
			actionUpdate("Got values");
		} catch(Exception e) {
			actionUpdate("Invalid inputs!");
			clearData();
			return;
		}
		//make sure text is lower case
		direction = Character.toLowerCase(direction);
		
		//validate speed and duration are within bounds
		if(validate(speed, duration)){
			//error message to user
			actionUpdate("Values didnt pass validation");
			return;
		}
		
		switch(direction) {
			case 'f': 
				System.out.println("Switch reached");
				bot.moveForward(speed, duration);
				bot.addAction(new InputStorage(direction, speed, duration));
				actionUpdate("Finch is moving forward at " + speed + " for " + duration);
				break;
				
			case 'b':
				bot.moveBack(speed, duration);
				bot.addAction(new InputStorage(direction, speed, duration));
				actionUpdate("Finch is moving back at " + speed + " for " + duration);
				break;
				
			case 'r':
				bot.moveRight(speed, duration);
				bot.addAction(new InputStorage(direction, speed, duration));
				actionUpdate("Finch is moving right at " + speed + " for " + duration);
				break;
				
			case 'l':
				bot.moveLeft(speed, duration);
				bot.addAction(new InputStorage(direction, speed, duration)) 	;
				actionUpdate("Finch is moving left at " + speed + " for " + duration);
				break;
				
			case 'q': //implements Q command, incase anyone wants to use it
				bot.endFinch();
				System.exit(0);
				break;
			default:
					actionUpdate("Invalid input! Please double check the intstructions and try again");
		}
		
		clearData();
	}
	
	private boolean validate(int speed, int duration) {
		if(speed < 100 && speed > 200) {
			actionUpdate("Speed must be between 100 and 200 Finch units");
			if(duration <= 0 && duration > 6)
				actionUpdate("Duration must be between 1 and 6 seconds, dummie!");
				return true;
		}
		return	 false;
	}
	
	private void clearData() { 
		clearNum();
		clearSpeed();
		clearDirection();
		clearDuration();
	}
	
	@FXML
	private void retrace() {
		
		try {
			int num = getNum();
			
			retracer.callRetrace(num, bot.inputList);
			clearData();
			
		} catch (Exception e) {
			
			clearData();
			actionUpdate("Invalid retrace input, please try again!");
		}
	}
	
	public void actionUpdate(String message) {
		String update = outputArea.getText() + "\r\n" + "> " + message;
		outputArea.setText(update);
	}
	
	/*
	 * 
	 * TEST Methods
	 * 
	 */
	
	@FXML
	private void testSetter() {
		clearNum();
		actionUpdate("YOO");
	}
	public void setMain(Main main) {
		this.main = main;
		main.bot = bot;
	}
}
