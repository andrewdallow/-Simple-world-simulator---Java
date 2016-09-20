package place;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import thing.Thing;
/**
 * JUnit test to confirm the method of Room work as expected.
 * @author Andrew Dallow
 * @version 1.0
 *
 */
public class RoomTest {
	/**
	 * Object of type Room to be tested.
	 */
	private Room room;
	/**
	 * Test label of room.
	 */
	private String label;
	/**
	 * test level of room.
	 */
	private int level;
	/**
	 * test description of room.
	 */
	private String description;
	/**
	 * test width of room.
	 */
	private final double width = 4.5;
	/**
	 * test depth of room.
	 */
	private final double depth = 2.4;
	/**
	 * tolerance when comparing double's.
	 */
	private final double tolerance = 0.01;
	/**
	 * Create a test room with predefined parameters.
	 */
	@Before
	public final void setUp() {
		label = "112";
		level = 1;
		description = "Lab supervisor";
		
		room = new Room(label, level, description);
	}
	/**
	 * Confirm the setLabel changes the label of the room.
	 */
	@Test
	public final void testSetLabel() {
		room.setLabel("247");
		assertEquals("247", room.label());
	}
	/**
	 * Confirm that label() returns the current label of the room.
	 */
	@Test
	public final void testLabel() {
		assertEquals(label, room.label());
	}
	/**
	 * Confirm that setLevel changes the level of the room.
	 */
	@Test
	public final void testSetLevel() {
		room.setLevel(2);
		assertEquals(2, room.level());
	}
	/**
	 * Confirm that level() returns the current level of the room.
	 */
	@Test
	public final void testLevel() {
		assertEquals(level, room.level());
		room = new Room(label);
		assertEquals(0, room.level());

	}
	/**
	 * Confirm that setDescription changes the description of the room.
	 */
	@Test
	public final void testSetDescription() {
		room.setDescription("CSSE resource room");
		assertEquals("CSSE resource room", room.description());
	}
	/**
	 * Confirm that description() returns the description of the room.
	 */
	@Test
	public final void testDescription() {
		assertEquals(description, room.description());
		room = new Room(label, level);
		assertEquals(null, room.description());


	}
	/**
	 * Confirm that width() returns the width of the room.
	 */
	@Test
	public final void testWidth() {
		room.setSize(width, depth);
		assertEquals(width, room.width(), tolerance);
	}
	/**
	 * Confirm that depth() returns the depth of the room.
	 */
	@Test
	public final void testDepth() {
		room.setSize(width, depth);
		assertEquals(depth, room.depth(), tolerance);
	}
	/**
	 * Confirm that setSize(width, depth) changes the width and depth of the 
	 * room.
	 */
	@Test
	public final void testSetSize() {
		room.setSize(width, depth);
		assertEquals(width, room.width(), tolerance);
		assertEquals(depth, room.depth(), tolerance);
	}
	/**
	 * Confrim that addThing(item) adds Thing's to the contents of the room. 
	 */
	@Test
	public final void testAddThing() {
		Thing item1 = new Thing("test Name", "test description");
		room.addThing(item1);
		assertTrue(room.contents().contains(item1));
	}
	/**
	 * Confirm that contents() returns the collection of contents in the room.
	 */
	@Test
	public final void testContents() {
		Thing item1 = new Thing("test Name", "test description");
		Thing item2 = new Thing("test Name2", "test description2");
		assertTrue(room.contents().isEmpty());
		room.addThing(item1);
		room.addThing(item2);
		assertTrue(room.contents().contains(item1));
		assertTrue(room.contents().contains(item2));
		
	}
	/**
	 * Confirm that contentsList() returns a String of the contents in 
	 * comma-delimited form.
	 */
	@Test
	public final void testContentsList() {
		Thing item1 = new Thing("test Name", "test description");
		Thing item2 = new Thing("test Name2", "test description2");
		room.addThing(item1);
		room.addThing(item2);
		
		assertEquals(item1.name() + ", " + item2.name(), room.contentsList());
	}
	/**
	 * Confirm that toString() returns a string of the room details correctly
	 * with or without any contents. 
	 */
	@Test
	public final void testToString() {
		assertEquals("[Room: number = " + label	+ ", level = " + level 
				+ ", " + description + "]", room.toString());
		
		
	}

}
