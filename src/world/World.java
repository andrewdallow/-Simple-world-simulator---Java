package world;

import java.util.ArrayList;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.Observable;
import java.util.Observer;

import place.Forest;
import place.Place;
import place.Room;
import thing.Computer;
import thing.Food;
import thing.Plant;
import thing.Thing;
import actor.Actor;
import actor.Animal;
import actor.Person;
import actor.Robot;

/**
 * Models simple worlds which comprises rooms, persons, and things. Currently 
 * stores these and can deliver to clients.
 * @see actor.Person
 * @see place.Room
 * @see thing.Thing
 * @author Andrew Dallow
 * @version 1.0
 *
 */
public class World extends Observable implements Observer {
	/**
	 * Collection of Person objects.
	 */
	private Collection<Actor> actors;
	/**
	 * Collection of Room objects.
	 */
	private Collection<Place> places;
	/**
	 * Collection of Thing objects.
	 */
	private Collection<Thing> items;
	/**
	 * Constructor. Makes an empty world; not really very useful unless more
	 * world-building methods are available.
	 */
	public World() {
		
		actors = new ArrayList<Actor>();
		places = new ArrayList<Place>();
		items = new ArrayList<Thing>();
	}
	/**
	 * Constructor. Either makes an empty world or a demo world with sufficient 
	 * content to demonstrate the available functionality.
	 */
	public final void demoWorld() {		
		actors = new ArrayList<Actor>();
		places = new ArrayList<Place>();
		items = new ArrayList<Thing>();
		
		//add items to items
		
		items.add(new Thing("torch", "Small LED torch"));
		items.add(new Thing("chair", "Swivel chair"));
		items.add(new Thing("screwdriver", "Phillips head screwdriver"));
		items.add(new Thing("Big Java", "Horstmann's Big Java Book"));
		items.add(new Thing("fax", "A fax machine"));
		
		items.add(new Food("Sandwhiches", "Bread and Meat", 
				"Some ham sandwiches", false));		
		items.add(new Food("Apple", "Fruit", "Large Red Apple", false));
		items.add(new Food("Pie", "Meat and Pastry", "Mince Pie", false));
		
		items.add(new Plant("Pine Tree", "long needle-shaped leaves", 
				"Tree", 5000));
		
		items.add(new Plant("Vase of Red Roses", "Rose Bush", 
				"Rose", 5));
		
		items.add(new Plant("Silver Fern Bush", "Feathery-like Leaves", 
				"Firn", 1000));
		
		items.add(new Plant("Podocarpaceae", "Evergren", "Tree", 10000));
		
		
		
		items.add(new Computer(201, "Standard Workstation"));
		items.add(new Computer(202, "Standard Workstation"));
		items.add(new Computer(203, "Standard Workstation"));
		items.add(new Computer(204, "Standard Workstation"));
		items.add(new Computer(205, "Standard Workstation"));
		
		items.add(new Computer(206, "Office Workstation"));
		items.add(new Computer(101, "Office Workstation"));
		items.add(new Computer(103, "Robot Interface"));
		items.add(new Computer(104, "Super-Computer"));
		
		
		//((Forest) getPlace("247", "CSSE resource room")).addPlants(
		//		(Plant) getThing("Podocarpaceae", "Tree: Evergren"));
		
		//add places
		places.add(new Room("031", -1, "CSSE lecture theatre"));
		places.add(new Room("247", 2, "SEVG lab"));			
		places.add(new Room("247", 2, "CSSE resource room"));
		places.add(new Room("112", 1, "Lab supervisor"));
		
		places.add(new Forest("Hamner Forest Park", "Alpine Forest", 
				"Warm, Mountain-like"));
		
		
		places.add(new Forest("Catlins Forest Park", "Costal", 
				"Cool, maritime temperate"));		
		
		//add actors to actors
		actors.add(new Person("Andrew Dallow", "male", 
				new GregorianCalendar(1988, 07, 11)));
		actors.add(new Person("John Doe", "male", 
				new GregorianCalendar(1952, 11, 05)));
		actors.add(new Person("Jane Doe", "female", 
				new GregorianCalendar(1992, 01, 29)));
		actors.add(new Robot(2001, "Move Large Items", 
				getPlace("247", "CSSE resource room")));
		
		actors.add(new Animal("Possum", "Mammal", "M", 4));
		actors.add(new Animal("Sparrow", "Bird", "F", 2));
		
		
		
		// add item to 247 SEVG lab
		getPlace("247", "SEVG lab").addThing(
				getThing("chair", "Swivel chair"));
		getPlace("247", "SEVG lab").addThing(
				getThing("CompID: 201", "Standard Workstation"));
		getPlace("247", "SEVG lab").addThing(
				getThing("chair", "Swivel chair"));
		getPlace("247", "SEVG lab").addThing(
				getThing("CompID: 202", "Standard Workstation"));
		getPlace("247", "SEVG lab").addThing(
				getThing("chair", "Swivel chair"));
		getPlace("247", "SEVG lab").addThing(
				getThing("CompID: 203", "Standard Workstation"));
		getPlace("247", "SEVG lab").addThing(
				getThing("chair", "Swivel chair"));
		getPlace("247", "SEVG lab").addThing(
				getThing("CompID: 204", "Standard Workstation"));
		getPlace("247", "SEVG lab").addThing(
				getThing("chair", "Swivel chair"));
		getPlace("247", "SEVG lab").addThing(
				getThing("CompID: 205", "Standard Workstation"));
		//add items to 247 CSSE resource room
		getPlace("247", "SEVG lab").addThing(
				getThing("chair", "Swivel chair"));
		getPlace("247", "CSSE resource room").addThing(
				getThing("CompID: 206", "Office Workstation"));
		getPlace("247", "CSSE resource room").addThing(
				getThing("fax", "A fax machine"));
		getPlace("247", "CSSE resource room").addThing(
				getThing("torch", "Small LED torch"));
		getPlace("247", "CSSE resource room").addThing(
				getThing("screwdriver", "Phillips head screwdriver"));
		getPlace("247", "CSSE resource room").addThing(
				getThing("Big Java", "Horstmann's Big Java Book"));			
		//add items to 112
		getPlace("112", "Lab supervisor").addThing(
				getThing("chair", "Swivel chair"));
		getPlace("112", "Lab supervisor").addThing(
				getThing("CompID: 101", "Office Workstation"));
		getPlace("112", "Lab supervisor").addThing(getThing(
				"Sandwhiches", "Bread and Meat: Some ham sandwiches"));
		//Andrew starts in 247 resource room, picks up Big Java, then 
		//moves to 247 SEVG lab
		getActor("Andrew Dallow").moveTo(getPlace(
				"247", "CSSE resource room"));
		getActor("Andrew Dallow").take(getThing(
				"Big Java", "Horstmann's Big Java Book"));
		getActor("Andrew Dallow").moveTo(getPlace(
				"247", "SEVG lab"));
		
		//Add things to Hamner Forest Park
		((Forest) getPlace("Hamner Forest Park", "Type: Alpine Forest, "
				+ "Climate: Warm, Mountain-like")).addPlants((Plant) getThing(
								"Pine Tree", 
								"Tree: long needle-shaped leaves"));
		((Forest) getPlace("Hamner Forest Park", "Type: Alpine Forest, "
				+ "Climate: Warm, Mountain-like")).addPlants((Plant) getThing(
						"Silver Fern Bush", "Firn: Feathery-like Leaves"));
		//Add Things to "Catlins Forest Park"
		((Forest) getPlace("Catlins Forest Park", "Type: Costal, "
				+ "Climate: Cool, maritime temperate")).addPlants(
						(Plant) getThing("Podocarpaceae", "Tree: Evergren"));
		
		((Forest) getPlace("Catlins Forest Park", "Type: Costal, "
				+ "Climate: Cool, maritime temperate")).addPlants(
						(Plant) getThing("Silver Fern Bush", 
								"Firn: Feathery-like Leaves"));
		
		
		
		//Phil Holland starts in 112, picks up a
		//food, moves to room 247, picks up a screwdriver, then moves to
		//room 112 again.
		getActor("John Doe").moveTo(getPlace(
				"112", "Lab supervisor"));
		getActor("John Doe").take(getThing(
				"Sandwhiches", "Bread and Meat: Some ham sandwiches"));
		getActor("John Doe").moveTo(getPlace(
				"247", "CSSE resource room"));
		getActor("John Doe").take(getThing(
				"screwdriver", "Phillips head screwdriver"));
		getActor("John Doe").moveTo(getPlace(
				"112", "Lab supervisor"));
		
		//Jane Doe starts in 
		getActor("Jane Doe").moveTo(getPlace(
				"Hamner Forest Park", "Type: Alpine Forest, Climate: Warm,"
						+ " Mountain-like"));
		//Animals start in
		getActor("Sparrow").moveTo(getPlace(
				"Hamner Forest Park", "Type: Alpine Forest, Climate: Warm,"
						+ " Mountain-like"));
		getActor("Possum").moveTo(getPlace("Catlins Forest Park", 
				"Type: Costal, Climate: Cool, maritime temperate"));
		
		items();
		places();
		actors();
	}
	/**
	 * Iterate through items to obtain chosen Thing object.
	 * @param name name of Thing.
	 * @param description description of Thing.
	 * @return Thing object matching parameters, otherwise null if Thing not in
	 * items Collection.
	 */
	private Thing getThing(final String name, final String description) {
		
		for (Thing item : items) {
			if (item.name().equals(name) 
					&& item.description().equals(description)) {
				return item;
			}
		}
		return null;
	}
	/**
	 * Iterate through places to find specified Room object.
	 * @param label label on Room.
	 * @param description brief description of Room.
	 * @return Room object matching the given parameters, otherwise null if Room
	 * not in places Collection.
	 */
	private Place getPlace(final String label, final String description) {
		
		boolean isLabel, isDescription;
		for (Place place : places) {
			isLabel = place.label().equals(label);
			isDescription = place.description().equals(description);
			if (isLabel && isDescription) {
				return place;
			}
		}
		return null;
	}
	/**
	 * Iterate through actors to find Person object.
	 * @param name name of Person
	 * @return Person object matching given parameters, otherwise null if 
	 * Person not in actors Collection.
	 */
	private Actor getActor(final String name) {
		
		for (Actor person : actors) {
			if (person.name().equals(name)) {
				return person;
			}
		}
		return null;
	}
	/**
	 * Actors (persons) in the world in a (possibly) empty collection of an 
	 * appropriate class (e.g., ArrayList).
	 * @return Collection of actors.
	 */
	public final Collection<Actor> actors() {
		for (Actor actor : actors) {
			actor.addObserver(this);
		}
		return actors;
	}
	/**
	 * Items (things) in the world in a (possibly) empty collection of an 
	 * appropriate class (e.g., ArrayList).
	 * @return Collection of items.
	 */
	public final Collection<Thing> items() {
		for (Thing item : items) {
			item.addObserver(this);
		}
		return items;
	}
	/**
	 * Places (rooms) in the world in a (possibly) empty collection of an 
	 * appropriate class (e.g., ArrayList).
	 * @return Collection of places.
	 */
	public final Collection<Place> places() {
		for (Place place : places) {
			place.addObserver(this);
		}
		return places;
	}
	@Override
	public final void update(final Observable arg0, final Object arg1) {
		setChanged();
		notifyObservers(arg1);
		
	}
}
