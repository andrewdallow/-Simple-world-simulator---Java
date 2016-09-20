package thing;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
/**
 * Confirm that Food's methods workd as expected.
 * @author Andrew Dallow
 *
 */
public class FoodTest {
	/**
	 * Test Food.
	 */
	private Food testFood;
	

	/**
	 * Create a Food object before each test.
	 */
	@Before
	public void setUp() {
		testFood = new Food("White Bread", "Yeast Bread", 
				"Pams Toast sliced", false);
	}	

	
	/**
	 * Confirm that cook() changes isCooked from True to false if Food is 
	 * un-cooked, or does nothing if already cooked.
	 */
	@Test
	public final void testCook() {
		assertEquals("Not Cooked", testFood.isCooked(), false);
		testFood.cook();
		assertEquals("Cooked", testFood.isCooked(), true);
		testFood.cook();
		assertEquals("Already Cooked", testFood.isCooked(), true);
	}
	/**
	 * Confirm that toString produces the correct string for cooked and
	 * un-cooked food.
	 */
	@Test
	public final void testToString() {
		assertEquals(testFood.toString(), 
				"[Food: White Bread, Yeast Bread: Pams Toast sliced, "
				+ "Cooked: false]");
		
		testFood.cook();
		assertEquals(testFood.toString(), 
				"[Food: White Bread, Yeast Bread: Pams Toast sliced, "
				+ "Cooked: true]");
	}

}
