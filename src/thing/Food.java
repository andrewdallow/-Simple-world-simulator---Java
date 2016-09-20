package thing;

/**
 * Models Food in worlds. People may carry and eat Food, and they may
 * also be located in a room.
 * @author Andrew Dallow
 * @version 1.0
 *
 */
public class Food extends Thing {
	/**
	 * The type of food (e.g. meat, vegetables, fruit, nuts, etc.).
	 */
	private String foodType;
	/**
	 * True if food is cooked, false otherwise.
	 */
	private boolean isCooked;
	
	
	/**
	 * Constructor.
	 * @param name Name of Food.
	 * @param type type of food (e.g. meat, vegetables, fruit, nuts, etc.).
	 * @param description brief description of food.
	 * @param cooked True if food is cooked, false otherwise.
	 */
	public Food(final String name, final String type, final String description,
			final boolean cooked) {
		super(name, type + ": " + description);
		foodType = type;
		this.isCooked = cooked;
	}
	
	/**
	 * Get the type of the food .
	 * @return the food type.
	 */
	public final String getFoodType() {
		return foodType;
	}
	
	/**
	 * Cooked status of food.
	 * @return True if food is cooked, false otherwise.
	 */
	public final boolean isCooked() {
		return isCooked;
	}
	
	/**
	 * Cook the food if it is not already cooked (changes isCooked to true or
	 * give message that food is already cooked).
	 */
	public final void cook() {
		setChanged();
		if (!isCooked) {
			this.isCooked = true;
			notifyObservers(this.toString() + " is now cooked.");
		} else {
			notifyObservers(this.toString() + " is already cooked.");
		}
		
	}
	/**
	 *Our own formatted string containing Food details.
	 *@return string of Food details.
	 */
	@Override
	public final String toString() {
		return "[Food: " + name() + ", " + description() 
				+ ", Cooked: " + isCooked + "]";
	}
}
