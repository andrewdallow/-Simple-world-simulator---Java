package world;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


/**
 * JUnit test to test the methods of class world.
 * @author Andrew Dallow
 *
 */
public class WorldTest {
	/**
	 * Arbitrary empty world.
	 */
	private World emptyWorld;
	/**
	 * Test demo world.
	 */
	private World testDemoWorld;
	
	/**
	 * Create both an empty and demo world before each test.
	 */
	@Before
	public final void setUp() {
		emptyWorld = new World();
		testDemoWorld = new World();
		testDemoWorld.demoWorld();

	}
	/**
	 * Confirm that actors() returns the collection of people.
	 */
	@Test
	public final void testActors() {
		assertTrue(emptyWorld.actors().isEmpty());		
		assertFalse(testDemoWorld.actors().isEmpty());

	}
	/**
	 * Confirm that items() returns the collection if Thing's.
	 */
	@Test
	public final void testItems() {
		assertTrue(emptyWorld.items().isEmpty());		
		assertFalse(testDemoWorld.items().isEmpty());
	}
	/**
	 * Confirm that places() returns the collection of Room's. 
	 */
	@Test
	public final void testPlaces() {
		assertTrue(emptyWorld.places().isEmpty());		
		assertFalse(testDemoWorld.places().isEmpty());
	}

}
