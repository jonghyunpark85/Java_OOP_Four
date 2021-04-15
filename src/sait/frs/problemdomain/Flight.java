package sait.frs.problemdomain;

/**
 * 
 * Represents a Flight.
 * 
 * @author Jonghyun Park
 * @version March 22, 2020
 *
 */
public class Flight {

	private String code;
	private String airlineName;
	private String from;
	private String to;
	private String weekday;
	private String time;
	private int seats;
	private double costPerSeat;

	/**
	 * User-defined constructor for Flight.
	 * 
	 * @param code
	 * @param airlineName
	 * @param from
	 * @param to
	 * @param weekday
	 * @param time
	 * @param seats
	 * @param costPerSeat
	 */
	public Flight(String code, String airlineName, String from, String to, String weekday, String time, int seats,
			double costPerSeat) {
		this.code = code;
		this.airlineName = airlineName;
		this.from = from;
		this.to = to;
		this.weekday = weekday;
		this.time = time;
		this.seats = seats;
		this.costPerSeat = costPerSeat;
	}

	/**
	 * Gets the flights code
	 * 
	 * @return Flight code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * Gets the airline
	 * 
	 * @return Name of airline
	 */
	public String getAirlineName() {
		return airlineName;
	}

	/**
	 * Gets the originating airport
	 * 
	 * @return Originating airport
	 */
	public String getFrom() {
		return from;
	}

	/**
	 * Gets the destination airport
	 * 
	 * @return Destination airport
	 */
	public String getTo() {
		return to;
	}

	/**
	 * Gets the day of the week the flights leaves
	 * 
	 * @return the weekday
	 */
	public String getWeekday() {
		return weekday;
	}

	/**
	 * Gets the time the flight leaves (in 24 hour format)
	 * 
	 * @return Time flight leaves
	 */
	public String getTime() {
		return time;
	}

	/**
	 * Gets the number of seats on the flight.
	 * 
	 * @return Total number of seats
	 */
	public int getSeats() {
		return seats;
	}

	/**
	 * Gets the cost per seat on the flight.
	 * 
	 * @return Cost per seat
	 */
	public double getCostPerSeat() {
		return costPerSeat;
	}

	/**
	 * Is this a domestic flight?
	 * 
	 * @return True if it is domestic or false if it is international
	 */
	public boolean isDomestic() {

		if (from.startsWith("Y") && to.startsWith("Y")) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Unused Method
	 * 
	 * @param code
	 */
	@SuppressWarnings("unused")
	private void parseCode(String code) {
		
	}

	/**
	 * Gets the human readable representation of a flight
	 * 
	 */
	@Override
	public String toString() {
		return String.format("%s%s%s%s%s%s%s%s%s%s%s%s%.2f", code, ", From: ", from, ", To: ", to, ", Day: ", weekday,
				", Time:", time, ", Seat:", seats, ", Cost: ", costPerSeat);
	}
}
