package application.logic;

import java.util.List;

import application.view.ControlScreenController;

public class Retracer {
	
	RobotController bot;
	ControlScreenController out;
	
	
	public Retracer(RobotController bot, ControlScreenController out) {
		//give access to Finch instance for control/access to list and to UI
		this.bot = bot;
		this.out = out;
		
	}
	
	//validate comment prior to executing 
	public void callRetrace(int numToRetrace, List<InputStorage> listOfMovements) {
		//validate request 
		if(listOfMovements.size() < numToRetrace){
			out.actionUpdate("You haven't completed " + numToRetrace + " actions yet! Try " + listOfMovements.size() + " or less");
			return;
		}
		//if valid, call retrace
		retrace(numToRetrace, listOfMovements);
	}
	
	/*
	 * retrace previous movements. each movement is saved to front of list so normal iteration will work
	 * counter and label break loop when desired number is reached
	 */
	private void retrace(int numToRetrace, List<InputStorage> list) {
		
		int counter = 0;
		
		loop:
		for(InputStorage dataSet : list) {
			
			switch(dataSet.direction) {
			
				case 'f':
					bot.moveForward(dataSet.speed, dataSet.duration);
					counter++;
					if(counter == numToRetrace)
						break loop;
					break;
					
				case 'b':
					bot.moveBack(dataSet.speed, dataSet.duration);
					counter++;
					if(counter == numToRetrace)
						break loop;
					break;
					
				case 'l':
					bot.moveLeft(dataSet.speed, dataSet.duration);
					counter++;
					if(counter == numToRetrace)
						break loop;
					break;
					
				case 'r':
					bot.moveRight(dataSet.speed, dataSet.duration);
					counter++;
					if(counter == numToRetrace)
						break loop;
					break;
				default:
					out.actionUpdate("WARNING INVALID COMMAND IN RETRACE LIST");
					break;
			}
		}
	}
}
