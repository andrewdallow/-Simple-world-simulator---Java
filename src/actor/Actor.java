package actor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Observable;

import place.Forest;
import place.Place;
import thing.Plant;
import thing.Thing;
/**
 * Models Actors in worlds. Actors have their own properties and can also go 
 * places and carry things.
 * @see thing.Thing
 * @see place.Place
 * @author Andrew Dallow
 * @version 1.0
 *
 */
public abstract class Actor extends Observable {
	/**
	 * Name of Person.
	 */
	private String actorName;
	/**
	 * Place location of Actor.
	 */
	private Place location;
	/**
	 * Collection of previous locations. 
	 */
	private Collection<Place> history;
	/**
	 * Inventory of Person.
	 */
	private Collection<Thing> inventory;
	
	
	
	/**
	 * Constructor.
	 * @param name name of actor.
	 */
	public Actor(final String name) {
		actorName = name;
		history = new ArrayList<Place>();
		inventory = new ArrayList<Thing>();
	}
	
	
	
	/**
	 * Tell others our name (i.e., name of person).
	 * @return Name of person.
	 */
	public final String name() {
		return actorName;
	}
	
	
	
	/**
	 * Tells others where we are (i.e., location of person).
	 * @return Current location of person (room).
	 */
	public final Place location() {
		return location;
	}
	
	
	
	/**
	 * Inventory of person suitable for iteration by clients.
	 * @return Inventory.
	 */
	public final Collection<Thing> inventory() {
		return inventory;
	}
	
	
		
	/**
	 * Add an item to inventory of a person. Remove it from the room contents.
	 * @param t Thing to add to inventory of person.
	 */
	public void take(final Thing t) {
		Collection<Thing> contents = location().contents();
		
		//check if thing is in current room contents, if so, then add to Person
		//inventory and remove from Room.	
		if (contents.remove(t)) {
			if (location instanceof Forest && t instanceof Plant) {
				((Forest) location()).removePlants((Plant) t);
				
			} else {
				location().contents().remove(t);
				}
			inventory.add(t);
			
			setChanged();
			notifyObservers(this.name() + " took " + t.name() + " from " 
					+ location.label());
			
		}
	}
	
	
	
	/**
	 * Remove item from inventory of person and add it to room inventory.
	 * @param t Thing to be removed.
	 * @return Removed item.
	 */
	public Thing drop(final Thing t) {
		if (location instanceof Forest && t instanceof Plant) {
			((Forest) location()).addPlants((Plant) t);
			
		} else {
			location().addThing(t);			
		}
		
		inventory.remove(t);
		setChanged();
		notifyObservers(this.name() + " dropped " + t.name() 
				+ " in " + location.label());
		return t;
	}
	
	
	
	/**
	 * Set or change location of actor and if previous location exists add it
	 * to the history.
	 * @param destination Determines (new) location of person.
	 */
	public void moveTo(final Place destination) {
		if (location != null) {
			history.add(location());
		}		
		location = destination;
		setChanged();
		notifyObservers(this.name() + " moving to " + location.label() 
				+ ".");
	}
	
	
	
	/**
	 * History of the previous locations suitable for iteration by clients.
	 * @return History
	 */
	public final Collection<Place> history() {
		return history;
	}
	
	
	
	/**
	 * Actor-specific version of toString().
	 * @return actor details.
	 */
	public abstract String toString();
	

}
