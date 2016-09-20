package thing;

/**
 * Models plants in worlds. People may carry plants depending on plant
 * dimensions and they may also be located in a room or forest.
 * @author Andrew Dallow
 * @version 1.0
 *
 */
public class Plant extends Thing {
	/**
	 * Plant type (e.g. tree, bush, flowers, etc.).
	 */
	private String plantType;

	/**
	 * The quantity of this plant (i.e. the number of this type of plant). 
	 */
	private int plantQuantity;
	
	/**
	 * Constructor.
	 * @param name name of plant.
	 * @param description brief description of plant.
	 * @param type Plant Type (e.g. tree, bush, flowers, etc.).
	 * @param quantity The quantity of this plant (i.e. the number of this type 
	 * of plant).
	 */
	public Plant(final String name, final String description, 
			final String type, final int quantity) {
		super(name, type + ": " + description);	
		plantType = type;
		plantQuantity = quantity;
	}
	
	/**
	 * Get the quantity of this plant.
	 * @return plant quantity.
	 */
	public final int quantity() {
		return plantQuantity;
	}
	
	/**
	 * Change the quantity of this plant.
	 * @param quantity The quantity of this plant (i.e. the number of this 
	 * type of plant).
	 */
	public final void setQuantity(final int quantity) {
		plantQuantity = quantity;
	}

	/**
	 * Get the type of the plant.
	 * @return plant type.
	 */
	public final String getPlantType() {
		return plantType;
	}
	
	/**
	 * Our own formatted string containing Plant details.
	 * @return String of plant details.
	 */
	@Override
	public final String toString() {
		return "[ Plant: " + name() + ", " + description() 
				+ ", Quantity: " + plantQuantity + "]";
	}
}
