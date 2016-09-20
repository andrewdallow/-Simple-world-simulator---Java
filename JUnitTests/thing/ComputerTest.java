package thing;

import static org.junit.Assert.*;

import java.util.GregorianCalendar;

import org.junit.Before;
import org.junit.Test;

import place.Place;
import place.Room;
import actor.Person;
import actor.Robot;
/**
 * Confirm that the methods of Computer work as expected.
 * @author Andrew Dallow
 *
 */
public class ComputerTest {
	/**
	 * Arbitrary computer ID number.
	 */
	private int computerID = 123456789;
	/**
	 * Arbitrary computer description.
	 */
	private String computerDescription = "standard workstation";
	/**
	 * Arbitrary test Room 1.
	 */
	private Place testLocation1;
	/**
	 * Arbitrary test Room 2.
	 */
	private Place testLocation2;
	/**
	 * Arbitrary test Person.
	 */
	private Person testPerson;
	/**
	 * Arbitrary test robot ID number.
	 */
	private int robotID = 2468;
	/**
	 * purpose of test robot.
	 */
	private String robotPurpose = "Move heavy items";
	/**
	 * Arbitrary test cpmputer1.
	 */
	private Computer computer1;
	
	/**
	 * Setup test computer, person, and locations before each test.
	 */
	@Before
	public final void setUp() {
		computer1 = new Computer(computerID, computerDescription);
		testLocation1 = new Room("testRoom1", 1);
		testLocation2 = new Room("testRoom2", 2);
		testPerson = new Person("John Doe", "male",
				 new GregorianCalendar(1950, 01, 01));
	}
	
	/**
	 * Confirm that setRobotControl sets the computer controller for the robot. 
	 */
	@Test
	public final void testSetRobotControl() {
		testLocation1.addThing(computer1);
		Robot robot = new Robot(robotID, robotPurpose, testLocation1);
		
		computer1.setRobotControl(robot);
		assertEquals("Connect computer to robot", 
				computer1.robotControlled(), robot);
		assertEquals("Connect robot to computer", 
				robot.robotController(), computer1);		
		
	}

	
	/**
	 * Confirm that logon() logs a person onto the computer in the same room
	 * or does nothing if someone is already logged on or computer is in a 
	 * different room. 
	 */
	@Test
	public final void testLogon() {
		testPerson.moveTo(testLocation1);
		testLocation2.addThing(computer1);
		
		computer1.logon(testPerson);
		assertEquals("Cannot logon to computer from different location",
				computer1.loggedOn(), null);
				
		testPerson.moveTo(testLocation2);	
		computer1.logon(testPerson);
		assertEquals("Logon from same location", 
				computer1.loggedOn(), testPerson);
		
		Person testPerson2 = new Person("Jane Doe", "female",
				 new GregorianCalendar(1950, 01, 01));
		
		computer1.logon(testPerson2);
		assertEquals("Logon while someone is already logged on", 
				computer1.loggedOn(), testPerson);
	}

	
	/**
	 * Confirm that logOff() logs a person off from the computer.
	 */
	@Test
	public final void testLogOff() {
		testPerson.moveTo(testLocation2);	
		testLocation2.addThing(computer1);
		computer1.logon(testPerson);
		assertEquals(computer1.loggedOn(), testPerson);
		computer1.logOff();
		assertEquals(computer1.loggedOn(), null);
	}
	
	
	/**
	 * Confirm toString produces the correct string.
	 */
	@Test
	public final void testToString() {
		assertEquals(computer1.toString(), "[CompID: " + computerID + ", " 
												+ computerDescription + "]");
	}

}
