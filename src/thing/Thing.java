package thing;

import java.util.Observable;

/**
 * Models things (items) in worlds. People may carry items and they may
 * also be located in a room.
 * @author Andrew Dallow
 * @version 1.0
 *
 */
public class Thing extends Observable {
	/**
	 * Name of thing.
	 */
	private String thingName;
	/**
	 * Brief description of thing.
	 */
	private String thingDescription;
	/**
	 * Constructor.
	 * @param name Name of thing.
	 */
	public Thing(final String name) {
		thingName = name;
		thingDescription = "N/A";
		setChanged();
		notifyObservers(this);
	}
	/**
	 * Constructor.
	 * @param name Name of thing
	 * @param description Description of thing.
	 */
	public Thing(final String name, final String description) {
		thingName = name;
		thingDescription = description;
		setChanged();
		notifyObservers(this);
	}
	/**
	 * Get description of thing.
	 * @return Brief description of thing.
	 */
	public final String description() {
		return thingDescription;
	}
	/**
	 * Get name of thing.
	 * @return Name of thing.
	 */
	public final String name() {
		return thingName;
	}
	/**
	 * Our own formatted string containing property details.
	 * @return String of Thing details
	 */
	public String toString() {
		return String.format("[Thing: %s, %s]", thingName, thingDescription);
	}

}
