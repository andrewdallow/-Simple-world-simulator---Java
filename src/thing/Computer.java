package thing;

import actor.Person;
import actor.Robot;
/**
 * Models computers in worlds. People may login to computers if in the same 
 * room and also control robots via the computer. 
 * @author Andrew Dallow
 * @version 1.0
 *
 */
public class Computer extends Thing {
	/**
	 * The person using this computer, null if not in use.
	 */
	private Person loggedOn;
	/**
	 * The robot controlled by this computer.
	 */
	private Robot robotController;
	
	
	/**
	 * Constructor.
	 * @param computerID integer ID of computer.
	 * @param description brief description of computer. 
	 */
	public Computer(final int computerID, final String description) {
		super("CompID: " + computerID, description);
	}
	
	/**
	 * set or change robot this computer controls.
	 * @param robot controlled robot.
	 */
	public final void setRobotControl(final Robot robot) {
		robotController = robot;
		robotController.setController(this);
		setChanged();
		notifyObservers(this.name() 
				+ " now controlling " + robot.name());
	}
	
	/**
	 * get the robot controlled by this computer.
	 * @return controlled robot
	 */
	public final Robot robotControlled() {
		return robotController;		
	}
	
	/**
	 * Log a Person on to this computer only if they are in the same room.
	 * @param loginID person logging on. 
	 */
	public final void logon(final Person loginID) {
		if (loggedOn == null) {	
			if (loginID.location().contents().contains(this)) {
				loggedOn = loginID;
				setChanged();
				notifyObservers(loggedOn.name() 
						+ " logged onto: " + this.name());				
				} else {
					setChanged();
					notifyObservers(loginID.name() 
							+ " not in same room as " + this.name());
				}			
		} else {
			setChanged();
			notifyObservers(this.name() + " already in use.");
		}		
	}
	
	/**
	 * Person logged on to this computer, null if not in use.
	 * @return person logged on.
	 */
	public final Person loggedOn() {
		return loggedOn;
	}
	
	/**
	 * Log off the current person on this computer.
	 */
	public final void logOff() {
		if (loggedOn != null) {			
			setChanged();
			notifyObservers(loggedOn.name() 
					+ " has logged off: " + this.name());
			loggedOn = null;
		}
		
	}
	
	/**
	 * Our own formatted string containing computer details.
	 * @return String of computer details
	 */
	public final String toString() {
		String status = "[" + name() + ", " + description();
		if (loggedOn != null) {
			status += ", " + loggedOn.name() + " is logged on";
			if (robotController != null) {
				status += ", Controlling: " + robotController.name() + "]";
			} else {
				status += "]";
			}
		} else {
			status += "]";
		}
		return status;
		
	}
}
