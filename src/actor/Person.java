package actor;

import java.util.Calendar;
import java.util.GregorianCalendar;

import thing.Food;

/**
 * Models people in worlds. People have their own properties and can also go 
 * places and carry things.
 * @see thing.Thing
 * @see place.Room
 * @author Andrew Dallow
 * @version 1.0
 *
 */
public class Person extends Actor {	
	/**
	 * Date of birth.
	 */
	private Calendar personDOB;
	/**
	 * Gender of person (e.g. male, female)
	 */
	private String personGender;
	
	/**
	 * Constructor.
	 * @param fullName First name.
	 * @param gender Gender of person.
	 * @param dob Age of person in years
	 */
	public Person(final String fullName, final String gender, 
			final Calendar dob) {
		super(fullName);
		personGender = gender;		
		personDOB = dob;
	}
	
	
	/**
	 * Person consumes Thing of type Food only if in the persons inventory.
	 * @param food food to consume.
	 */
	public final void eat(final Food food) {
		setChanged();
		if (this.inventory().remove(food)) {
			notifyObservers(food.name() 
					+ " consumed by " + this.name());
		
		} else {
			notifyObservers(food.name() + " not in inventory");
		}
	}
	
	
	/**
	 * Get the Date of birth of the Person.
	 * @return Date of Birth.
	 */
	public final Calendar getPersonDOB() {
		return personDOB;
	}
	
	
	/**
	 * Calculate and return the current age of the person from DOB depending 
	 * on the specified date.
	 * @param currentDate Date to calculate age difference from.
	 * @return age of person.
	 */
	public final int getAge(final Calendar currentDate) {
		int age = currentDate.get(Calendar.YEAR) - personDOB.get(Calendar.YEAR);
		
		boolean isMonthGreater = personDOB.get(Calendar.MONTH) 
				> currentDate.get(Calendar.MONTH);
		
		boolean isMonthSameDayGreater = personDOB.get(Calendar.MONTH) 
				== currentDate.get(Calendar.MONTH) 
				&& personDOB.get(Calendar.DAY_OF_MONTH) 
				> currentDate.get(Calendar.DAY_OF_MONTH);
				
		if (isMonthGreater || isMonthSameDayGreater) {
			age = age - 1;
		}	
		
		return age;		
	}	
	
	
	/**
	 * Get the Persons gender.
	 * @return Gender.
	 */
	public final String getPersonGender() {
		return personGender;
	}

	/**
	 * Person-specific version of toString().
	 * @return person details.
	 */
	@Override
	public final String toString() {
		String personStatus = "[Person: " + name();
		personStatus += ", Age: " + getAge(new GregorianCalendar());
		
		if (location() != null) {
			personStatus += ", in " + location().toString(); 
			}	
		
		personStatus += ", Gender: " + personGender + "]";	
		return personStatus;
	}
}
