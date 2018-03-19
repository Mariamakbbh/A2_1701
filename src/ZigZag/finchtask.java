package ZigZag;

import java.util.Scanner;
import edu.cmu.ri.createlab.terk.robot.finch.Finch;

public class finchtask {

	Finch myf;
	
	public void setFinch(Finch myf) {
		this.myf = myf;
	}
//			new Finch(); // initialise finch object

	int inputLength; // initialise variables to store values
	int inputSections;
	double totalStraight;
	int totalDistance;

	public void main(String[] args) { // main where all methods are called in proper sequence

		inputValues(); // calling input values method
		movement(); // calling movement method
		calculateDistance(); // calling calculate distance method
		calculateLine(); // calling calculate line method
	}

	public void inputValues() {

		boolean valid = false; // boolean to check if values meet condition
		Scanner inputL = new Scanner(System.in); // intialise scanner for section length
		Scanner inputS = new Scanner(System.in); // intialise scanner for section number

		do { // do while loop in order to go through scanner again until boolean is true
			System.out.println("Enter length of sections(cm):");
			int length = inputL.nextInt();

			inputLength = length; // return value to int input length

			if (inputLength >= 30 && inputLength <= 80) { // conditional statement to meet requirement
				valid = true;						// value must be greater than or equal to 30
			}										// and less than or equal to 80

			else {
				System.out.println("Error: Value entered is out of range. Please enter a value between 30-80.");
			}	// else statement to show error message to user when if statement is not met
		}
		while (!valid); // 

		valid = false;

		do { // another do while loop for section number same thing but with different conditional statement
			System.out.println("Enter number of sections:");
			int sections = inputS.nextInt();

			inputSections = sections;

			if (inputSections % 2 == 0 && inputSections <= 10) {
				valid = true;
			}

			else {
				System.out.println("Error: Value entered is out of range. Please enter an even number less than 10.");
			}

		}		
		while (!valid);

		valid = false;
	}

	public void tune1() { // tune1 & tune2 required by spec, includes LED and buzzer

		myf.setLED(0, 255, 0); // green light
		myf.buzz(5000, inputLength * 100);
	}

	public void tune2() {

		myf.setLED(255, 0, 0); // red light
		myf.buzz(7000, inputLength * 100);
	}

	public void forward() { // constant 100 speed movement changes with input length

		myf.setWheelVelocities(100,100, inputLength * 100); // *100 because every 10cm = 1000 milliseconds
	}

	public void rightAngle() { // clockwise 90 degree turn

		myf.setWheelVelocities(100,0,1500);
	}

	public void leftAngle() { // anti-clockwise 90 degree turn 

		myf.setWheelVelocities(0,100,1500);
	}

	public void turnAround() { // 180 degree turn

		myf.setWheelVelocities(100,0,3000);
	}

	public void sectionOne() { // first zigzag section method calls other methods

		tune1();
		forward();

	}

	public void sectionTwo() { // second zigzag section method calls other methods

		tune2();
		forward();

	}

	public void movement() { // movement method to alternate sections as required in spec

		finchtask test = new finchtask();

		for (int i=0; i<=inputSections; i++) { // for loop that increments i until the number of sections

			if (i==inputSections){ // when the finch is at the last section it will turn around 180 
				test.turnAround();					// and retrace its path with the same corresponding lights/buzz
			}
			else if (i==inputSections-1) { // when the finch is on the last section it will not execute 
				test.sectionTwo();// since req is even number it will always end the same // extra 90 degree turn
			}																				// only move
			else if (i%2 == 0) { // modulus remainder 0 means its an even number and should execute section1 method
				test.sectionOne();
				rightAngle(); // added to turn the finch 90 degrees
			}
			else  {
				test.sectionTwo();
				leftAngle();	// added to turn the finch -90 degrees
			}	
		}

		for (int i=0; i<=inputSections; i++) { // same for loop seperated to reset input sections

			if (i==inputSections){ // means completed all sections and will turn off mentioned in spec
				myf.quit();
			}
			else if(i==inputSections-1){ // means just move and wont execute extra turn
				test.sectionOne();
			}
			else if(i%2 == 0){ // modulus have been reversed as we are retracing the finches previous movement
				test.sectionTwo();														// section methods
				leftAngle();
			}
			else  {
				test.sectionOne(); // same with this
				rightAngle();
			}	
			
		}	
	}// used sine formula to calculate the missing length in isosceles triangle to give line displacement A -> B
	public void calculateLine() { // calculates movement of finch in straight line
// maths sine operater and to radians operators converts number to angles so java can read and perform calculations
		double straight = inputLength *Math.sin(Math.toRadians(45)) / (Math.sin(Math.toRadians(90)));
		double roundedStraight = Math.round(straight * 100D) / 100D; // rounds off to 2 decimals
		totalStraight = roundedStraight * inputSections / 2; // finally multiply it by input sections for total
		System.out.println("Total straight line distance travelled: " + totalStraight + " cm"); // output
	}

	public void calculateDistance() {

		totalDistance = inputLength * inputSections; // straight forward, total distance length x sections
		
		System.out.println("Total distance travelled: " + totalDistance + " cm"); // output

	}
}