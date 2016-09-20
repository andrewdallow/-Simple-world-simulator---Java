package actor;

import static org.junit.Assert.*;

import java.util.GregorianCalendar;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import place.Room;
import thing.Food;
import thing.Thing;
/**
 * JUnit test to test the methods in class Person.
 * @author Andrew Dallow
 * @version 1.0
 *
 */
public class PersonTest {
	/**
	 * Test object of type Person.
	 */
	private Person testPerson;
	/**
	 * Arbitrary Thing object.
	 */
	private Thing testItem1;
	/**
	 * Another arbitrary Thing object.
	 */
	private Thing testItem2;
	/**
	 * Arbitrary Room object. 
	 */
	private Room testRoom1;
	/**
	 * Another arbitrary Room object.
	 */
	private Room testRoom2;
	/**
	 * Test first name to used in Person.
	 */
	private String testName = "John Doe";
	/**
	 * Test last name to used in Person.
	 */
	private String testLastName = "Doe";
	

	/**
	 * Create a test Person to be used in following tests along with 2 test 
	 * Thing's and 2 test Room's.
	 */
	@Before
	public final void setUp() {
		testPerson = new Person(testName, "male",
				 new GregorianCalendar(1950, 02, 03));
		
		testItem1 = new Thing("fax", "A fax Machine");
		testItem2 = new Thing("torch", "An LED torch");
		
		testRoom1 = new Room("247", 2, "SVEG lab");
		testRoom2 = new Room("112", 1, "Lab supervisor");
	}

	/**
	 * Confirm that name() returns the name of Person.
	 */
	@Test
	public final void testName() {
		assertEquals(testName, testPerson.name());
	}
    /**
     * Confirm that location() returns the location of testRoom1, once Person
     * has been set to this location.
     */
	@Test
	public final void testLocation() {
		testPerson.moveTo(testRoom1);
		assertEquals(testRoom1, testPerson.location());
	}
	/**
	 * Confirm that inventory returns empty when Person is not carrying
	 * anything or returns the collection of Things Person is carrying. 
	 */
	@Test
	public final void testInventory() {
		assertTrue(testPerson.inventory().isEmpty());
		testRoom1.addThing(testItem1);
		testRoom1.addThing(testItem2);
		testPerson.moveTo(testRoom1);
		testPerson.take(testItem1);
		testPerson.take(testItem2);
		
		assertEquals(2, testPerson.inventory().size());
		assertTrue(testPerson.inventory().contains(testItem1));
		assertTrue(testPerson.inventory().contains(testItem2));
	}
    /**
     * Confirm that moveTo() changes the location of the Person.
     */
	@Test
	public final void testMoveTo() {
		testPerson.moveTo(testRoom1);
		assertEquals(testRoom1, testPerson.location());
		testPerson.moveTo(testRoom2);
		assertEquals(testRoom2, testPerson.location());
	}
	/**
	 * Confirm that take adds Thing's to inventory and removes it from contents
	 * of the current room.
	 */
	@Test
	public final void testTake() {
		assertTrue(testPerson.inventory().isEmpty());
		testRoom1.addThing(testItem1);
		testRoom2.addThing(testItem2);
		testPerson.moveTo(testRoom1);
		testPerson.take(testItem1);
		testPerson.take(testItem2);
		
		assertTrue(testPerson.inventory().contains(testItem1));
		assertFalse(testRoom1.contents().contains(testItem1));
		
		testPerson.moveTo(testRoom2);
		testPerson.take(testItem2);
		
		assertTrue(testPerson.inventory().contains(testItem1));
		assertTrue(testPerson.inventory().contains(testItem2));
		assertFalse(testRoom2.contents().contains(testItem2));				
	}
	/**
	 * Confirm that drop removes Thing's from inventory and adds them to 
	 * the contents of the current location. 
	 */
	@Test
	public final void testDrop() {
		assertTrue(testPerson.inventory().isEmpty());
		testRoom1.addThing(testItem1);
		testRoom2.addThing(testItem2);
		testPerson.moveTo(testRoom1);
		testPerson.take(testItem1);		
		testPerson.moveTo(testRoom2);
		testPerson.take(testItem2);
		
		testPerson.drop(testItem1);
		assertFalse(testPerson.inventory().contains(testItem1));
		assertTrue(testRoom2.contents().contains(testItem1));
		
		testPerson.drop(testItem2);
		assertTrue(testRoom2.contents().contains(testItem1));
		assertTrue(testRoom2.contents().contains(testItem2));
		
		testPerson.take(testItem2);
		testPerson.moveTo(testRoom1);
		testPerson.drop(testItem2);
		
		assertTrue(testRoom1.contents().contains(testItem2));
		assertTrue(testPerson.inventory().isEmpty());
	}
	
	
	/**
	 * Conform that getAge works for each scenario.
	 */
	@Test
	public final void testAge() {
		int ageBefore = 63;
		int ageAfter = 64; 
		
		assertEquals("Month less.", 
				testPerson.getAge(new GregorianCalendar(2014, 01, 01)),	
				ageBefore);
		
		assertEquals("Same Month, Less Day", 
				testPerson.getAge(new GregorianCalendar(2014, 02, 02)),	
				ageBefore);
		
		assertEquals("Same Month, Less Day", 
				testPerson.getAge(new GregorianCalendar(2014, 02, 04)),	
				ageAfter);
		
		assertEquals("Month Greater.", 
				testPerson.getAge(new GregorianCalendar(2014, 03, 01)),	
				ageAfter);
		
	}
	/**
	 * Confirm that eat() removes food from world. 
	 */
	@Test
	public void testEat() {
		Food testFood = new Food("White Bread", "Yeast Bread", 
				"Pams Toast sliced", false);
		
		testRoom1.addThing(testFood);
		testPerson.moveTo(testRoom1);
		
		testPerson.eat(testFood);
		assertTrue("Food in Room", 
				testRoom1.contents().contains(testFood));
		
		testPerson.take(testFood);
		
		assertTrue("Has food in inventory", 
				testPerson.inventory().contains(testFood));
		
		testPerson.eat(testFood);
		assertFalse("Food consumed", 
				testRoom1.contents().contains(testFood));
		
		
	}
	
	/**
	 * Confirm that toString returns either just the Persons name or the name 
	 * and location.
	 */
	@Test
	public final void testToString() {
		int age = testPerson.getAge(new GregorianCalendar());
		assertEquals("[Person: " + testPerson.name() + ", Age: " + age 
				+ ", Gender: " + testPerson.getPersonGender() + "]", 
				testPerson.toString());
		
		testPerson.moveTo(testRoom1);
		testRoom1.addThing(testItem1);
		testRoom1.addThing(testItem2);
		testPerson.take(testItem1);
		testPerson.take(testItem2);
		
		assertEquals("[Person: " + testPerson.name() + ", Age: " + age + ", in "
		+ testRoom1.toString() + ", Gender: " + testPerson.getPersonGender() 
		+ "]", testPerson.toString());
		

	}

}
