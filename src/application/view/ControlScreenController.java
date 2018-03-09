package application.view;

import application.Main;
import application.logic.InputStorage;
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
	@SuppressWarnings("unused")
	private Main main;
	private ControlScreenController Controller;
	
	public ControlScreenController() {
	}
	public void initialize() {
		
		//create instances of bot and retrace class when layout is loaded
		startServices();
		//hold copy of this class and pass it to Bot
		 Controller = this;
		
		/*
		 * attach event listeners for text inputs which minimize user input error
		 * uses regEx to help validate input
		 */
		
		//make sure input is an integer
			numToRetrace.textProperty().addListener(new ChangeListener<String>() {
				public void changed(ObservableValue<? extends String> observable, String oldValue, 
						String newValue) {
					if (!newValue.matches("\\d{0,2}")) {
						numToRetrace.setText(newValue.replaceAll("[^\\d]", ""));
					} if(newValue.isEmpty()) {
						
					} else if( Integer.parseInt(newValue) > (bot.inputList.size())) {
						numToRetrace.setText(String.valueOf(bot.inputList.size()));
					}
				}
			});

			//make sure value is between 1-6 and only one digit
			duration.textProperty().addListener(new ChangeListener<String>() {
				public void changed(ObservableValue<? extends String> observable, String oldValue, 
						String newValue) {
					if (newValue.matches("[^1-6]")) {
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
					if (!newValue.matches("[0-9]*")) {
						speed.setText(newValue.replace("[^0-9]", ""));
//						speed.setText((newValue.length() > 1) ? newValue.substring(0, newValue.length()-1) :  "");
					} else if(!newValue.matches("[1|2][0-9]{0,2}")) {
						speed.setText((newValue.length() > 1) ? newValue.substring(0, newValue.length()-1) :  "");
					}
					if(newValue.length() > 3) {
						speed.setText(oldValue.substring(0, 3));
					}
					if(newValue.length() == 3) {
						int value = Integer.parseInt(newValue);
						if(value > 200) {
							speed.setText("200");
						} else if(value < 100) {
							speed.setText("100");
						}
					}

				}});

			//make sure direction is valid F, B, L, R no more then 1 character
			direction.textProperty().addListener(new ChangeListener<String>() {
				public void changed(ObservableValue<? extends String> observable, String oldValue, 
						String newValue) {
					if (!newValue.matches("[f|F|b|B|r|R|l|L|q|Q]")) {
						direction.setText(newValue.replaceAll("[^f|F|b|B|r|R|l|L|q|Q]", ""));
					}
					if(newValue.length() > 1) {
						direction.setText(oldValue.substring(0, 1));
					}	        
				}});

			///scroll to bottom of textarea after update
			outputArea.textProperty().addListener(new ChangeListener<Object>() {
				@Override
				public void changed(ObservableValue<?> observable, Object oldValue,
						Object newValue) {
					outputArea.setScrollTop(Double.MAX_VALUE);
				}
			});
		
		//set initial action window message
		String welcome = "> Prepare to assume control of a very powerful Robot";
		outputArea.setText(welcome);
	}
	/*
	 * getters and clearers that assist with retrieving values from the UI and clearing TextFields 
	 * error handling was added in case of a NULLPOINTEEXCEPTION
	 */
	public int getNum() {
		try {
			
		if(numToRetrace == null || numToRetrace.getText().trim() == ""){
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
	
	public int getSpeed() {
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
	
	public int getDuration() {
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
	
	public char getDirection() {
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
	
	private void clearData() { 
		clearNum();
		clearSpeed();
		clearDirection();
		clearDuration();
	}
	
	//background thread for running bot commands so UI doesn't freeze
	@FXML
	public void startAction() {
		Runnable task = new Runnable() {
			public void run() {
				acceptInput();
			}
		};
		Thread finchAction = new Thread(task);
		finchAction.setDaemon(true);
		finchAction.start();
	}
	
	@FXML
	public void startRetrace() {
		System.out.println("Retracing in thread");
		Runnable task = new Runnable() {
			public void run() {
				retrace();
			}
		};
		Thread retraceThread = new Thread(task);
		retraceThread.setDaemon(true);
		retraceThread.start();
	}
	public void startServices() {
		System.out.println("Starting serivces");
		Runnable task = new Runnable() {
			public void run() {
				bot = new RobotController();
				bot.startFinch();
				retracer = new Retracer(bot, Controller);

			}
		};
		Thread services = new Thread(task);
		services.setDaemon(true);
		services.start();
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
			//Clear the text boxes for next action
			clearData();
		} catch(Exception e) {
			actionUpdate("Invalid inputs!");
			clearData();
			return;
		}
		//make sure text is lower case
		direction = Character.toLowerCase(direction);
		
		//validate speed and duration are within bounds
	if(direction == 'q') {
		//implements Q command, incase anyone wants to use it
		bot.endFinch();
		System.exit(0);
	}
		if(validate(speed, duration)){
			//error message to user
			actionUpdate("Values didnt pass validation");
			return;
		}
		
		/*
		 * call bot action based off user input values. Each case adds  a new InputStorage object to the retrace list
		 * then takes the requested action and finally sends an update to the UI. 
		 */
		switch(direction) {
			case 'f': 
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
				
			default:
					actionUpdate("Invalid input! Please double check the intstructions and try again");
		}
		
	}
	//Check for invalid values and notify user
	private boolean validate(int speed, int duration) {
		
		//if flag is true there was an invalid value
		boolean flag = false;
		
		//check speed
		if(speed < 100 || speed > 200) {
			actionUpdate("Speed must be between 100 and 200 Finch units");
			flag = true;
		}
		
		//check duration
		if(duration <= 0 || duration > 6) {
			actionUpdate("Duration must be between 1 and 6 seconds, dummie!");
			flag = true;
		}
		//true if invalid values present
		return flag ? true : false;
	}
	
	
	//retrace call from the UI
	private void retrace() {
		//handle nulls
		try {
			int num = getNum();
			//call the call to retrace and clear data
			retracer.callRetrace(num, bot.inputList);
			clearData();
			
		} catch (Exception e) {
			
			//clear data either way
			clearData();
		}
	}
	
	//sends new update message to UI
	public void actionUpdate(String message) {
		outputArea.appendText("\r\n>" + message);
		outputArea.appendText("");
	}
	
	//give main access to bot instance. Getting pretty dependency heavy now!
	public void setMain(Main main) {
		this.main = main;
		main.bot = bot;
	}
}
