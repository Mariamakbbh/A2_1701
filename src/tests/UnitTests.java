package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import application.logic.InputStorage;
import application.logic.Retracer;
import application.logic.RobotController;
import application.view.ControlScreenController;

public class UnitTests {
	ControlScreenController scn = new ControlScreenController();
	RobotController bot = new RobotController();
	Retracer retracer = new Retracer(bot, scn);
	
	@Test
	public void addTest() {
		
		bot.addAction(new InputStorage('f', 100, 3));
		int size = bot.inputList.size();
		assertTrue(size == 1);
	}

	@Test
	public void getTests() {
		char dir = scn.getDirection();
		int spd = scn.getSpeed();
		int dur = scn.getDuration();
		int num = scn.getNum();
		
		assertTrue(dir == 0);
		assertTrue(spd == 0);
		assertTrue(dur == 0);
		assertTrue(num == 0);
	}
	
}
