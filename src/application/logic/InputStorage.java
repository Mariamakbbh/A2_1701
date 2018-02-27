package application.logic;

public class InputStorage {
	public char direction;
	public int speed;
	public int duration;
	
	//Storage object for each set of Finch instructions
	public InputStorage(char direction, int speed, int duration) {
	
		this.direction = direction;
		this.speed = speed;
		this.duration = duration;
	}
}
