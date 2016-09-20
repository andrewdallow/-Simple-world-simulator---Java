package actor;

import place.Place;
import thing.Computer;
import thing.Thing;


/**
 * Models Robots in worlds. Robots can move places and carry Things only if
 * powered on and controlled by a computer operated by a person. 
 * @author Andrew Dallow
 *
 */
public class Robot extends Actor {
	/**
	 * Purpose of Robot (e.g. move heavy items, clean, precision movement 
	 * tasks, enter hazardous places)
	 */
	private String robotPurpose;
	/**
	 * Computer controlling the robot.
	 */
	private Computer robotController;
	/**
	 * False if powered off or true when powered on.
	 */
	private boolean isPoweredOn;
	
	
	/**
	 * Constructor.
	 * @param robotId integer ID of robot.
	 * @param purpose purpose of robot.
	 * @param startPlace starting position of robot.
	 */
	public Robot(final int robotId, final String purpose, final Place startPlace) {
		super("Robot: ID" + robotId);
		super.moveTo(startPlace);
		robotPurpose = purpose;
		isPoweredOn = false;
	}	
	

	/**
	 * Get the purpose of the robot.
	 * @return robots purpose.
	 */
	public final String purpose() {
		return robotPurpose;
	}
	
	
	/**
	 * Change the purpose of the robot.
	 * @param purpose Purpose of Robot (e.g. move heavy items, clean, 
	 * precision movement tasks, enter hazardous places)
	 */
	public final void setPurpose(final String purpose) {
		this.robotPurpose = purpose;
	}
	
	

	/**
	 * Check if the robot is powered on.
	 * @return true if powered on, false otherwise.
	 */
	public final boolean isPoweredOn() {
		return isPoweredOn;
	}
	
	

	/**
	 * Power on the robot.
	 */
	public final void powerOn() {
		setChanged();
		if (robotController != null && robotController.loggedOn() != null) {
			if (!isPoweredOn) {
				this.isPoweredOn = true;
				notifyObservers(this.name() + " now turned on.");
			} else {
				notifyObservers("Already powered on.");
			}
		} else {
			notifyObservers("Robot has to be controlled by a Computer to "
					+ "power on.");			
		}		
	}
	
	
	/**
	 * Power off the robot.
	 */
	public final void powerOff() {
		setChanged();
		if (robotController != null && robotController.loggedOn() != null) {
			if (isPoweredOn) {
				this.isPoweredOn = false;
				robotController = null;			
				notifyObservers(this.name() + " now turned off.");
			} else {
				notifyObservers("Already powered off.");
			}			
		}		
	}
	
	

	/**
	 * Change or set the robots computer controller.
	 * @param controller Computer controller.
	 */
	public final void setController(final Computer controller) {
		robotController = controller;
	}
	/**
	 * Get the computer controller of the robot.
	 * @return computer controller
	 */
	public final Computer controller() {
		return robotController;
	}
	
	/**
	 * Get the computer controlling this robot.
	 * @return robot computer controller.
	 */
	public final Computer robotController() {
		return robotController;
	}
	
	@Override
	public final void take(final Thing t) {
		if (robotController != null && isPoweredOn) {
			if (robotController.loggedOn() != null) {
				super.take(t); 
			} else {
				setChanged();
				notifyObservers("Person needs to be logged on to: " 
												+ robotController.name());
			}
		} else {
			setChanged();
			notifyObservers("Robot has to be controlled by a Computer.");
		}
	}
	
	
	@Override
	public final Thing drop(final Thing t) {
		if (robotController != null && isPoweredOn) {
			if (robotController.loggedOn() != null) {
				super.drop(t); 
			} else {
				setChanged();
				notifyObservers("Person needs to be logged on to: " 
												+ robotController.name());
			}
		} else {
			setChanged();
			notifyObservers("Robot has to be controlled by a Computer.");
			
		}
		return t;
	}

	@Override
	public final void moveTo(final Place destination) {
		
		if (robotController != null && isPoweredOn) {
			if (robotController.loggedOn() != null) {
				super.moveTo(destination); 
			} else {
				setChanged();
				notifyObservers("Person needs to be logged on to: " 
												+ robotController.name());
			}
		} else {
			setChanged();
			notifyObservers("Robot has to be controlled by a Computer.");
		}		
	}
	

	@Override
	public final String toString() {
		String status = "[" + name() + ", is in " + location().toString() 
				+ ", Power: ";
		
	if (isPoweredOn) {
		status += "ON";
	} else {
		status += "OFF";
	}
	status += "]";		
			
	return status;
	}

}
