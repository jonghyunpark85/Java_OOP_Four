package sait.frs.problemdomain;

import sait.frs.exception.*;

/**
 * 
 * Represents a Reservation.
 * 
 * @author Jonghyun Park
 * @version March 22, 2020
 *
 */
public class Reservation {

	private String code;
	private String flightCode;
	private String airline;
	private String name;
	private String citizenship;
	private double cost;
	private boolean active;

	/**
	 * User-defined constructor for Reservation.
	 * 
	 * @param code        - Existing reservation code.
	 * @param flightcode  - reservation is for
	 * @param airline
	 * @param name        - reservation is for.
	 * @param citizenship - Travelers citizenship
	 * @param cost
	 * @param active      - is the reservation active?
	 * @throws NullFlightException         - Thrown if flight is null
	 * @throws InvalidNameException        - Thrown if name is null or empty
	 * @throws InvalidCitizenshipException - Thrown if citizenship is null or empty
	 */
	public Reservation(String code, String flightCode, String airline, String name, String citizenship, double cost,
			boolean active) throws NullFlightException, InvalidNameException, InvalidCitizenshipException {
		this.code = code;
		this.flightCode = flightCode;
		this.airline = airline;
		this.setName(name);
		this.setCitizenship(citizenship);
		this.cost = cost;
		this.active = active;
	}

	/**
	 * Gets the reservation code.
	 * 
	 * @return Reservation code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * Gets the flight code the reservation is for.
	 * 
	 * @return the flightCode
	 */
	public String getFlightCode() {
		return flightCode;
	}

	/**
	 * Gets the name of the traveler.
	 * 
	 * @return Traveler's name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Gets the airline
	 * 
	 * @return the airline
	 */
	public String getAirline() {
		return airline;
	}

	/**
	 * Gets the travelers citizenship.
	 * 
	 * @return Traveler's citizenship.
	 */
	public String getCitizenship() {
		return citizenship;
	}

	/**
	 * Gets the cost
	 * 
	 * @return the cost
	 */
	public double getCost() {
		return cost;
	}

	/**
	 * Gets if the reservation is active.
	 * 
	 * @return True if reservation is active.
	 */
	public boolean isActive() {
		return active;
	}

	/**
	 * Sets the name of the traveler.
	 * 
	 * @param name - New name of traveler
	 * @throws InvalidNameException - Thrown if name is null or empty
	 */
	public void setName(String name) throws InvalidNameException {
		if (name != null && !(name.isEmpty())) {
			this.name = name;
		} else {
			throw new InvalidNameException();
		}
	}

	/**
	 * Sets the citizenship of the traveler
	 * 
	 * @param citizenship - New citizenship of traveler
	 * @throws InvalidCitizenshipException - Thrown if citizenship is null or empty
	 */
	public void setCitizenship(String citizenship) throws InvalidCitizenshipException {
		if (citizenship != null && !(citizenship.isEmpty())) {
			this.citizenship = citizenship;
		} else {
			throw new InvalidCitizenshipException();
		}
	}

	/**
	 * Sets if the reservation is active or inactive
	 * 
	 * @param active - True meaning reservation is active
	 */
	public void setActive(boolean active) {
		this.active = active;
	}

	/**
	 * Gets a human readable representation of the Reservation.
	 */
	@Override
	public String toString() {
		return this.getCode();
	}
}
