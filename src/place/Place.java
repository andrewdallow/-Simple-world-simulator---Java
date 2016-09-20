package place;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Observable;

import thing.Thing;

/**
 * Models a Place in a world. Actors and items may be located in a room.
 * @see thing.Thing
 * @see actor.Actor
 * @author Andrew Dallow
 * @version 1.0
 *
 */
public abstract class Place extends Observable {
	/**
	 * The label of the place.
	 */
	private String placeLabel;
	/**
	 * A short description of the place.
	 */
	private String placeDescription;
	/**
	 * The width of the place in metres.
	 */
	private double placeWidth;
	/**
	 * The depth of the place in metres. 
	 */
	private double placeDepth;
	/**
	 * A collection of Things in the place.
	 */
	private Collection<Thing> contents = new ArrayList<Thing>();
	
	
	/**
	 * Constructor.
	 * @param name Name of place.
	 */
	public Place(final String name) {
		placeLabel = name;
	}
	
	/**
	 * Constructor.
	 * @param name Name of place.
	 * @param description Brief description of place.
	 */
	public Place(final String name, final String description) {
		placeLabel = name;
		placeDescription = description;
		contents = new ArrayList<Thing>();
	}
	
	/**
	 * Get brief description of room.
	 * @return roomDescription Place description.
	 */
	public final String description() {
		return placeDescription;
	}
	
	/**
	 * Add something to the room's content.
	 * @param t item Thing to add to room.
	 */
	public final void addThing(final Thing t) {
		contents.add(t);
	}
	
	/**
	 * Contents of place.
	 * @return contents Collection containing contents of place.
	 */
	public final Collection<Thing> contents() {
		return contents;
	}
	
	/**
	 * Set the description for a room.
	 * @param description New or updated brief description of place.
	 */
	public final void setDescription(final String description) {
		if (!description.equals(placeDescription)) {
			setChanged();
			notifyObservers("Description changed from: '" 
					+ placeDescription + "' to '" + description 
					+ "' in " + this.label());
		}
		placeDescription = description;
	}
	
	/**
	 * Get label of place.
	 * @return Place label
	 */
	public final String label() {
		return placeLabel;
	}
	
	/**
	 * Set name of place.
	 * @param name Name of place.
	 */
	public final void setLabel(final String name) {
		if (!placeLabel.equals(name)) {
			setChanged();
			notifyObservers("Label changed from: '" + placeLabel 
					+ "' to '" + name + "' in " + this.label());
			placeLabel = name;
		}
		
	}
	
	/**
	 * Set size / dimensions of room in metres.
	 * @param width Room width.
	 * @param depth Room depth.
	 */
	public void setSize(final double width, final double depth) {
		if (width != placeWidth && depth != placeDepth) {
			setChanged();
			notifyObservers("Width now: '" + width 
					+ " m' and Depth now: '" + depth + " m' in " 
					+ this.label());
		} else if (width != placeWidth && depth == placeDepth) {
			setChanged();
			notifyObservers("Width now: '" + width 
					+ " m'" + "' in " + this.label());
		} else if (width == placeWidth && depth != placeDepth) {
			setChanged();
			notifyObservers("Depth now: '" + depth 
					+ " m'" + "' in " + this.label());
		}
		placeWidth = width;
		placeDepth = depth;
	}
	
	/**
	 * Get room width in metres.
	 * @return Width of a rectangular room.
	 */
	public final double width() {
		return placeWidth;
	}
	
	/**
	 * Get room depth in metres.
	 * @return  Depth of a rectangular room.
	 */
	public final double depth() {
		return placeDepth;
	}
	
	/**
	 * List content of place.
	 * @return String containing comma-delimited list of content item names.
	 */
	public final String contentsList() {
		String contentList = "";
		// iterate over contents and produce comma-delimited string.
		for (Iterator<Thing> item = contents().iterator(); item.hasNext();) {
			contentList += item.next().name();
			// only add comma if more Things in contents.
			if (item.hasNext()) {
				contentList += ", ";
			}
		}
		return contentList;
	}
	
	/**
	 * Our own formatted string giving state detail.
	 * @return Brief details of place status.
	 */
	public abstract String toString();

}
