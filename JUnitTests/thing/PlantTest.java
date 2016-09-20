package thing;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
/**
 * Confirm that the methods of Plant work as expected.
 * @author Andrew Dallow
 *
 */
public class PlantTest {
	/**
	 * Test Plant.
	 */
	private Plant testPlant;

	/**
	 * Setup a new plant object before each test.
	 */
	@Before
	public void setUp() {
		testPlant = new Plant("Pine Tree", "long needle-shaped leaves", 
				"Tree", 5000);
	}

	/**
	 * Confirm that toString produces the correct String.
	 */
	@Test
	public final void testToString() {
		int quantity = 5000;
		assertEquals(testPlant.toString(), "[ Plant: " 
				+ "Pine Tree" + ", Tree: " + "long needle-shaped leaves" 
				+ ", Quantity: " + quantity + "]");
	}


}
