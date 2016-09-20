package place;

import thing.Plant;
import thing.Thing;
/**
 * Models a forest in the world. People and plants can be located in forest.
 * @see thing.Plant
 * @author Andrew Dallow
 *
 */
public class Forest extends Place {
	/**
	 * Forest density in plants per metre^2.
	 */
	private double forestDensity;
	/**
	 * Climate of the forest (e.g tropical, warm, cold, freezing).
	 */
	private String forestClimate;
	/**
	 * Type of forest (e.g. Evergreen Needleleaf, 
	 * Deciduous broadleaf, Lowland evergreen broadleaf rain forest).
	 */
	private String forestType;
	/**
	 * Number of plants in the Forest.
	 */
	private int numberOfPlants;
	
	
	/**
	 * Constructor.
	 * @param name Official name of forest.
	 * @param type Type of forest (e.g. Evergreen Needleleaf, 
	 * Deciduous broadleaf, Lowland evergreen broadleaf rain forest).
	 * @param climate Climate of the forest (e.g tropical, warm, cold).
	 */
	public Forest(final String name, final String type, final String climate) {
		super(name, "Type: " + type + ", Climate: " + climate);
		forestType = type;
		forestClimate = climate;
	}
	
	/**
	 * Add plants of type plant to the forest contents.
	 * @param plant Plant to be added.
	 */
	public final void addPlants(final Plant plant) {
		addThing(plant);
		numberOfPlants += plant.quantity();
		if (width() > 0 && depth() > 0) {
			forestDensity = (numberOfPlants) / (width() * depth());
		}
		
	}
	
	/**
	 * Remove plants of type plant to the forest contents.
	 * @param plant Plant to be added.
	 */
	public final void removePlants(final Plant plant) {
		contents().remove(plant);
		numberOfPlants -= plant.quantity();
		if (width() > 0 && depth() > 0) {
			forestDensity = (numberOfPlants) / (width() * depth());
		}
		
	}
	
	
	/**
	 * Get the number of plants in the forest.
	 * @return total number of plants
	 */
	public final int numberOfPlants() {
		return numberOfPlants;
	}
	
	/**
	 * Return the plant density of the forest.
	 * @return plant density of forest.
	 */
	public final double forestDensity() {
		return forestDensity;
	}
	
	@Override
	public final void setSize(final double width, final double depth) {
		super.setSize(width, depth);
		if (width() > 0 && depth() > 0) {
			forestDensity = (numberOfPlants) / (width() * depth());
		}
		
	}
	/**
	 * Return the climate of the forest.
	 * @return climate of forest
	 */
	public final String climate() {
		return forestClimate;
	}
	
	/**
	 * change the climate of the forest.
	 * @param climate Climate of the forest (e.g tropical, warm, cold, 
	 * freezing). 
	 */
	public final void setClimate(final String climate) {
		if (!forestClimate.equals(climate)) {
			forestClimate = climate;
			setDescription("Type: " + forestType + ", Climate: " 
			+ forestClimate);
		}
		
	}
	
	/**
	 * Return the type of the forest.
	 * @return forest Type.
	 */
	public final String forestType() {
		return forestType;
	}
	
	/**
	 * Change the type of forest.
	 * @param type Type of forest (e.g. Evergreen Needleleaf, 
	 * Deciduous broadleaf, Lowland evergreen broadleaf rain forest).
	 */
	public final void setForestType(final String type) {
		if (!forestType.equals(type)) {
			forestType = type;
			setDescription("Type: " + forestType + ", Climate: " 
			+ forestClimate);
		}
	}
	
	/**
	 * Our own formatted string giving forest detail.
	 * @return Brief details of Forest.
	 */
	@Override
	public final String toString() {
		String forestStatus =  "[Forest: " + label() + ", " + description(); 
		forestStatus +=	String.format(", Density: %.4f plants/m_2]", 
				forestDensity);
		
		return forestStatus;
	}
	

}
