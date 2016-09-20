package place;

/**
 * Models a room in a world. People and items may be located in a room.
 * @see thing.Thing
 * @author Andrew Dallow
 * @version 1.0
 *
 */
public class Room extends Place {
	
	/**
	 * The integer level of the room.
	 */
	private int roomLevel;
	
	/**
	 * Constructor.
	 * @param label Label (e.g., name, room number, door sign) for room.
	 */
	public Room(final String label) {
		super(label);
	}
	/**
	 * Constructor.
	 * @param label Label (e.g., name, room number, door sign) for room.
	 * @param level Level (e.g., floor) of room; can be negative, zero is 
	 * ground floor.
	 */
	public Room(final String label, final int level) {
		super(label);
		roomLevel = level;
	}
	/**
	 * Constructor.
	 * @param label Label (e.g., name, room number, door sign) for room.
	 * @param level Level (e.g., floor) of room; zero is ground floor.
	 * @param description Brief description of room.
	 */
	public Room(final String label, final int level, final String description) {
		super(label, description);
		roomLevel = level;

	}
	
	/**
	 * Set level of room.
	 * @param level Level (e.g., floor) of room; can be negative, zero is 
	 * ground floor.
	 */
	public final void setLevel(final int level) {
		if (level != roomLevel) {
			setChanged();
			notifyObservers("Level changed from: '" + roomLevel 
					+ "' to '" + level + "' in " + this.toString());			
		}
		roomLevel = level;
				
	}
	/**
	 * Get level the room is on.
	 * @return roomLevel Room level; can be negative, zero is ground floor.
	 */
	public final int level() {
		return roomLevel;
	}
	

	/**
	 * Our own formatted string giving state detail.
	 * @return Brief details of Room.
	 */
	public final String toString() {
		String roomStatus = "[Room: number = " + label() 
				+ ", level = " + roomLevel + ", " + description() + "]";		
		return roomStatus;
	}
	
}
