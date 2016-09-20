package actor;

import place.Forest;
import place.Place;
import thing.Food;
/**
 * Models Animals in worlds. Animals have their own properties and can also go 
 * move around Forests, eat food in Forests and carry things. 
 * @author Andrew Dallow
 *
 */
public class Animal extends Actor {
	/**
	 * Animal type (e.g. mammal, insect, bird etc.)
	 */
	private String animalType;
	/**
	 * Number of legs the animal walks on.
	 */
	private int numberOfLegs;
	/**
	 * Gender of animal (e.g. male, female).
	 */
	private String animalGender;
	
	
	/**
	 * Constructor.
	 * @param name name of animal (e.g. mammal, insect, bird etc.).
	 * @param animal type of animal.
	 * @param gender gender of animal (e.g. male, female).
	 * @param numLegs number of legs.
	 */
	public Animal(final String name, final String animal, final String gender, 
			final int numLegs) {
		super(name);
		animalType = animal;
		animalGender = gender;
		numberOfLegs = numLegs;
	}
	
	
	
	@Override
	public final void moveTo(final Place destination) {
		if (destination instanceof Forest) {
			super.moveTo(destination);
		}		
	}
	
	
	/**
	 * Animal eats some food in the current location.
	 * @param food food to eat.
	 */
	public final void eat(final Food food) {
		setChanged();
		if (this.location().contents().contains(food)) {
			this.location().contents().remove(food);
			notifyObservers(food.name() 
					+ " consumed by " + this.name());
		}
	}
	

	/**
	 * Get the number of legs the animal has.
	 * @return the numberOfLegs
	 */
	public final int getNumberOfLegs() {
		return numberOfLegs;
	}



	/**
	 * Change the number of legs the animal has.
	 * @param numLegs the numberOfLegs to set
	 */
	public final void setNumberOfLegs(final int numLegs) {
		this.numberOfLegs = numLegs;
	}



	/**
	 * Get the type of the animal.
	 * @return the animalType
	 */
	public final String getAnimalType() {
		return animalType;
	}



	/**
	 * Get the gender of the animal.
	 * @return the animalGender
	 */
	public final String getAnimalGender() {
		return animalGender;
	}



	@Override
	public final String toString() {
		String status = "[Animal: " + name() + ", Type: " + animalType 
				+ ", Gender: " + animalGender + ", Legs: "  
				+ numberOfLegs; 
		if (this.location() != null) {
			status +=  " is in " + this.location().toString() + "]";
		} else {
			status += "]";
		}
		
		return status;
		
		
	}

}
