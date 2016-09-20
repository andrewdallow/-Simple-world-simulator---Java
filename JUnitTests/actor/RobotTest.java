package actor;

import static org.junit.Assert.*;

import java.util.GregorianCalendar;

import org.junit.Before;
import org.junit.Test;

import place.Room;
import thing.Computer;
import thing.Thing;

/**
 * JUnit test to test the methods in class Computer.
 * @author Andrew Dallow
 *
 */
public class RobotTest {
	/**
	 * Test computer.
	 */
	private Computer testComputer;
	/**
	 * Test Room 1.
	 */
	private Room testRoom1;
	/**
	 * Test Room 2.
	 */
	private Room testRoom2;
	/**
	 * Test Person.
	 */
	private Person testPerson;
	/**
	 * Test Robot.
	 */
	private Robot testRobot;
	/**
	 * Test Thing.
	 */
	private Thing testThing;

	/**
	 * Setup a test computer, person, Rooms, Robot, and Thing for testing
	 * the Robot class.
	 */
	@Before
	public void setUp() {
		
		testComputer = new Computer(123456789, "standard workstation");
		testPerson = new Person("John Doe", "male",
				 new GregorianCalendar(1950, 02, 03));
		
		testRoom1 = new Room("112", 1, "Lab supervisor");
		testRoom2 = new Room("247", 2, "Computer Lab");
		
		testRobot = new Robot(2468, "Move heavy items", testRoom2);
		
		testThing = new Thing("fax", "A fax Machine");
		
		testPerson.moveTo(testRoom1);
		testRoom1.addThing(testComputer);
		
		
	}
	
	/**
	 * Confirm that setController changes the computer controlling the robot.
	 */
	@Test
	public final void testSetRobotController() {
		assertEquals("Computer Controller Null", testRobot.controller(), null);
		testRobot.setController(testComputer);
		assertEquals("Computer Controller Set", testRobot.controller(),
				testComputer);
	}
	
	/**
	 * Confirm that PowerOn() changes isPowerOn to true only if a computer 
	 * controller is specified for robot and a Person is logged on to that
	 * computer.
	 */
	@Test
	public final void testPowerOn() {
		
		testRobot.powerOn();
		assertFalse("No Controller or Person logged on", 
				testRobot.isPoweredOn());
		
		testComputer.setRobotControl(testRobot);
		testRobot.powerOn();
		
		assertFalse("Controller, No Person logged on", 
				testRobot.isPoweredOn());
		
		testComputer.logon(testPerson);
			
		testRobot.powerOn();
		assertTrue("Has Controller and Person logged on", 
				testRobot.isPoweredOn());
	}

	/**
	 * Confirm that PowerOff() changes isPowerOn to false only if a computer 
	 * controller is specified for robot and a Person is logged on to that
	 * computer.
	 */
	@Test
	public final void testPowerOff() {
		testComputer.setRobotControl(testRobot);
		testComputer.logon(testPerson);
		testRobot.powerOn();
		
		assertTrue("Is powered on", testRobot.isPoweredOn());
		
		testComputer.logOff();
		assertEquals("Person not Logged On", testRobot.isPoweredOn(), true);
		
		testComputer.logon(testPerson);
		testRobot.powerOff();
		assertEquals("Is powered on", testRobot.isPoweredOn(), false);		
		
	}
	
	/**
	 * Confirm that take in robot only works if a computer 
	 * controller is specified for robot and a Person is logged on to that
	 * computer.
	 */
	@Test
	public final void testTake() {
		testRoom2.addThing(testThing);		
		testRobot.take(testThing);
		
		assertTrue("No controller or logged On, Room Contents", 
				testRoom2.contents().contains(testThing));
		assertFalse("No controller or logged On, inventory", 
				testRobot.inventory().contains(testThing));
		
		testComputer.setRobotControl(testRobot);
		testRobot.take(testThing);
		
		assertTrue("Controller, Not logged On, Room Contents", 
				testRoom2.contents().contains(testThing));
		assertFalse("Controller, Not logged On, inventory", 
				testRobot.inventory().contains(testThing));
		
		testComputer.logon(testPerson);
		testRobot.take(testThing);
		
		assertTrue("Not Powered on, Room Contents", 
				testRoom2.contents().contains(testThing));
		assertFalse("Not Powered on, inventory", 
				testRobot.inventory().contains(testThing));
		
		testRobot.powerOn();
		testRobot.take(testThing);		
		
		assertFalse("Powered on, Room contents", 
				testRoom2.contents().contains(testThing));
		assertTrue("Powered on, inventory", 
				testRobot.inventory().contains(testThing));		
		
	}

	/**
	 * Confirm that drop in robot only works if a computer 
	 * controller is specified for robot and a Person is logged on to that
	 * computer.
	 */
	@Test
	public final void testDrop() {
		testRoom2.addThing(testThing);	
		testComputer.setRobotControl(testRobot);
		testComputer.logon(testPerson);
		testRobot.powerOn();		
		testRobot.take(testThing);
		
		assertFalse("Initial, Room contents", 
				testRoom2.contents().contains(testThing));
		assertTrue("Initial, inventory", 
				testRobot.inventory().contains(testThing));	
		
		testRobot.powerOff();
		testRobot.drop(testThing);
		
		assertFalse("No controller or logged On, Room Contents", 
				testRoom2.contents().contains(testThing));
		assertTrue("No controller or logged On, inventory", 
				testRobot.inventory().contains(testThing));
		
		testComputer.setRobotControl(testRobot);
		testRobot.drop(testThing);
		
		assertFalse("Controller, Not logged On, Room Contents", 
				testRoom2.contents().contains(testThing));
		assertTrue("Controller, Not logged On, inventory", 
				testRobot.inventory().contains(testThing));
		
		testComputer.logon(testPerson);
		testRobot.drop(testThing);
		
		assertFalse("Not Powered on, Room Contents", 
				testRoom2.contents().contains(testThing));
		assertTrue("Not Powered on, inventory", 
				testRobot.inventory().contains(testThing));
		
		testRobot.powerOn();
		testRobot.drop(testThing);
		
		assertTrue("Powered on, Room contents", 
				testRoom2.contents().contains(testThing));
		assertFalse("Powered on, inventory", 
				testRobot.inventory().contains(testThing));			
		
	}

	/**
	 * Confirm that moveTo in robot only works if a computer 
	 * controller is specified for robot and a Person is logged on to that
	 * computer.
	 */
	@Test
	public final void testMoveTo() {
		testRobot.moveTo(testRoom1);

		assertEquals("No controller or logged On", 
				testRobot.location(), testRoom2);
		
		testComputer.setRobotControl(testRobot);
		testRobot.moveTo(testRoom1);
		
		assertEquals("Controller, Not logged On",
				testRobot.location(), testRoom2);
		
		testComputer.logon(testPerson);
		testRobot.moveTo(testRoom1);
		
		assertEquals("Not Powered on", testRobot.location(), testRoom2);
		
		testRobot.powerOn();
		testRobot.moveTo(testRoom1);
		
		assertEquals("Powered on", testRobot.location(), testRoom1);		
		
	}

	/**
	 * Confirm that toString() produces the correct string details.
	 */
	@Test
	public final void testToString() {
		assertEquals(testRobot.toString(), "[Robot: ID" + 2468 
				+ ", is in " + testRobot.location().toString() 
				+ ", Power: OFF]");
		
		testComputer.logon(testPerson);
		testComputer.setRobotControl(testRobot);
		testRobot.powerOn();
		
		assertEquals(testRobot.toString(), "[Robot: ID" + 2468 
				+ ", is in " + testRobot.location().toString() 
				+ ", Power: ON]");
	}





}
