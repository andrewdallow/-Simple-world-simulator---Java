package place;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import thing.Plant;
/**
 * Confirm that Forest's methods work as expected.
 * @author Andrew Dallow
 *
 */
public class ForestTest {
	/**
	 * Test forest.
	 */
	private Forest testForest;
	/**
	 * Setup a forest object before every test.
	 */
	@Before
	public final void setUp() {
		testForest = new Forest("Hamner Forest Park", "Alpine Forest", 
				"Warm, Mountain-like");	
	}

	/**
	 * Confirm the addPlants method adds only plants to the contents of forest
	 * and changes the density of the forest.
	 */
	@Test
	public final void testAddPlants() {
		double width = 500;
		double depth = 300;
		double tolorence = 1e-4;
		int numPlants = 5000;
		
		Plant plant = new Plant("Pine Tree", "long needle-shaped leaves", 
				"Tree", numPlants);
		
		testForest.setSize(width, depth);
		testForest.addPlants(plant);
		assertTrue("Contains added plant", 
				testForest.contents().contains(plant));
		assertEquals("Quantity of Plants", 
				testForest.numberOfPlants(), numPlants);
		
		double forestDensity = numPlants / (width * depth);
		
		assertEquals("Forest density", 
				testForest.forestDensity(), forestDensity, tolorence);
		
		testForest.removePlants(plant);
		forestDensity = 0.0;
		
		assertEquals("Remove: Forest density", 
				testForest.forestDensity(), forestDensity, tolorence);
		
	}

	/**
	 * Confirm that setClimate changes both the forest climate and description.
	 */
	@Test
	public final void testSetClimate() {
		assertEquals("Initial Climate", testForest.climate(),
				"Warm, Mountain-like");
		
		assertEquals("Initial Description", testForest.description(),
				"Type: " + testForest.forestType() 
				+ ", Climate: Warm, Mountain-like");
		
		testForest.setClimate("cold");
		assertEquals("Changed Climate", testForest.climate(),
				"cold");
		
		assertEquals("Changed Description", testForest.description(),
				"Type: " + testForest.forestType() + ", Climate: cold");
		
	}
	
	/**
	 * Confirm that setForestType changes both the forest type and description.
	 */
	@Test
	public final void testSetForestType() {
		String forestType1 = "Alpine Forest"; 
		String forestType2 = "Native and Exotic"; 
		
		assertEquals("Initial Forest Type", testForest.forestType(),
				forestType1);
		
		assertEquals("Initial Description", testForest.description(),
				"Type: " + forestType1 + ", Climate: Warm, Mountain-like");
		
		//Change forest type and re-test.
		testForest.setForestType(forestType2);
		
		assertEquals("Changed Forest Type", testForest.forestType(),
				forestType2);
		
		assertEquals("Changed Description", testForest.description(),
				"Type: " + forestType2	+ ", Climate: Warm, Mountain-like");
	}
	
	/**
	 * Confirm that toString produces the correct details.
	 */
	@Test
	public final void testToString() {
		double width = 500;
		double depth = 300;
		int numPlants = 5000;
		
		assertEquals(testForest.toString(), "[Forest: Hamner Forest Park, "
				+ "Type: Alpine Forest, Climate: Warm, Mountain-like, "
				+ "Density: 0.0000 plants/m_2]");
		
	
		
		Plant plant = new Plant("Pine Tree", "long needle-shaped leaves", 
				"Tree", numPlants);
		
		testForest.setSize(width, depth);
		testForest.addPlants(plant);
		
		double forestDensity = numPlants / (width * depth);
		
		assertEquals(testForest.toString(), "[Forest: Hamner Forest Park, "
				+ "Type: Alpine Forest, Climate: Warm, Mountain-like"
				+ String.format(", Density: %.4f plants/m_2]", forestDensity));
	}

}
