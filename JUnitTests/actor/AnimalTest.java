package actor;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import place.Forest;
import place.Room;
import thing.Food;

public class AnimalTest {

	/**
	 * Test animal.
	 */
	private Animal animal;
	/**
	 * Test Forest.
	 */
	private Forest testForest;
	
	/**
	 * Setup Test animal and forest before each test.
	 */
	@Before
	public final void setUp() {
		int numLegs = 4;
		animal = new Animal("Possum", "Mammal", "male", numLegs);
		testForest = new Forest("Hamner Forest Park", "Alpine Forest", 
				"Warm, Mountain-like");
	}

	/**
	 * Confirm that MoveTo for Animal only moves animals to Forests.
	 */
	@Test
	public final void testMoveTo() {
		Room testRoom = new Room("247", 2, "Computer Lab");
		
		animal.moveTo(testForest);
		assertEquals("Move To Forest", animal.location(), testForest);
		
		animal.moveTo(testRoom);
		assertEquals("Move To Room", animal.location(), testForest);	
		
	}
	
	/**
	 * Test that eat for animals in forests consumes the specified food item
	 * in the forest.
	 */
	@Test
	public final void testEat() {
		Food testFood = new Food("White Bread", "Yeast Bread", 
				"Pams Toast sliced", false);
		
		testForest.addThing(testFood);
		assertTrue("Food in Forest", 
				testForest.contents().contains(testFood));
		
		animal.moveTo(testForest);
		
		animal.eat(testFood);
		assertFalse("Food consumed", 
				testForest.contents().contains(testFood));
	}

	/**
	 * Confirm that toString produces the correct string details. 
	 */
	@Test
	public final void testToString() {
		animal.moveTo(testForest);
		
		assertEquals(animal.toString(), 
				"[Animal: Possum, Type: Mammal, Gender: male, Legs: 4"
				+ " is in " + testForest.toString() + "]");
	}
}
