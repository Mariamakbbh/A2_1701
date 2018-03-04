package application.logic;

import java.util.ArrayList;
import java.util.List;
import edu.cmu.ri.createlab.terk.robot.finch.Finch;

public class RobotController {
	 
	//list of actions for retracing 
	public List<InputStorage> inputList = new ArrayList<InputStorage>();
	public Finch bot;
	
	public RobotController() {
	}
	
	public void startFinch() {
	//make sure Finch instance hasn't been started
		if(bot == null)
			bot = new Finch();
	}
	
	public void endFinch() {
	//make sure there is a Finch instance to quit
		if(bot != null)
			bot.quit();
	}
	
	//add action to retrace list
	public void addAction(InputStorage value) {
		inputList.add(0, value);
	}
	
	//helper method to turn left
	private void left() {
		bot.setWheelVelocities(-140, 105, 850);
	}
	
	//helper method to turn right
	private void right() {
		bot.setWheelVelocities(105, -140, 850);
	}
	
	//movement commands
	public void moveForward(int speed, int duration) {
		bot.setWheelVelocities(speed, speed, 1000*duration);
	}
	
	public void moveBack(int speed, int duration) {
		bot.setWheelVelocities(-speed, -speed, 1000*duration);
	}
	
	public void moveLeft(int speed, int duration) {
		left();
		bot.setWheelVelocities(speed, speed, 1000*duration);
	}
		
	public void moveRight(int speed, int duration) {
		right();
		bot.setWheelVelocities(speed, speed, 1000*duration);
	}
}
