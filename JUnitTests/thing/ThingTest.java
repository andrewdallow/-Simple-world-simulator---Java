package thing;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
/**
 * Confirm that the methods of thing work as expected. 
 * @author Andrew Dallow
 *
 */
public class ThingTest {
	/**
	 * An arbitrary Thing.
	 */
	private Thing item1;
	/**
	 * Another Arbitrary Thing.
	 */
	private Thing item2;
	/**
	 * Test name of Thing.
	 */
	private String name = "Test Thing";
	/**
	 * Test description of Thing.
	 */
	private String description = "Test Description of Thing";
	/**
	 * Setup two objects of type Thing one with just a name and one with both a
	 * name and description.
	 */
	@Before
	public final void setUp() {
		item1 = new Thing(name);
		item2 = new Thing(name, description);
	}
	/**
	 * Confirm that description() returns a brief description of Thing and that
	 * it returns null when it has not been set.
	 */
	@Test
	public final void testDescription() {
		assertEquals("N/A", item1.description());
		assertEquals(description, item2.description());
	}
	/**
	 * Confirm that name() returns the name of Thing.
	 */
	@Test
	public final void testName() {
		assertEquals(name, item1.name());
	}
	/**
	 * Confirm that toString returns the correctly formated string of the Thing
	 * name and description if it is set.
	 */
	@Test
	public final void testToString() {
		assertEquals("[Thing: " + name + ", N/A]", item1.toString());
		assertEquals("[Thing: " + name + ", " + description + "]",
				item2.toString());
	}

}
