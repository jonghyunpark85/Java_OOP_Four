package sait.frs.manager;

import java.io.*;
import java.util.*;
import sait.frs.exception.*;
import sait.frs.problemdomain.*;

/**
 * This class is responsible for managing flights, reservations, and airports
 * 
 * @author Jonghyun Park
 * @version March 22, 2020
 */
public class Manager {

	public static final String WEEKDAY_ANY = "Any";
	public static final String WEEKDAY_SUNDAY = "Sunday";
	public static final String WEEKDAY_MONDAY = "Monday";
	public static final String WEEKDAY_TUESDAY = "Tuesday";
	public static final String WEEKDAY_WEDNESDAY = "Wednesday";
	public static final String WEEKDAY_THURSDAY = "Thursday";
	public static final String WEEKDAY_FRIDAY = "Friday";
	public static final String WEEKDAY_SATURDAY = "Saturday";

	private ArrayList<Flight> flights = new ArrayList<Flight>();
	private ArrayList<String> airports = new ArrayList<String>();
	private ArrayList<Reservation> reservations = new ArrayList<Reservation>();

	/**
	 * Default constructor for Manager
	 * 
	 * @throws IOException Thrown when the file could not be accessed.
	 */
	public Manager() throws IOException {

		populateAirports();
		populateFlights();
		populateFromBinary();
	}

	/**
	 * Gets all of the airports
	 * 
	 * @return ArrayList of airports.
	 */
	public ArrayList<String> getAirports() {
		return new ArrayList<String>(airports);
	}

	/**
	 * Gets all of the flights
	 * 
	 * @return ArrayList of Flight.
	 */
	public ArrayList<Flight> getFlights() {
		return new ArrayList<Flight>(flights);
	}

	/**
	 * Unused method
	 * 
	 * @param code
	 * @return null
	 */
	public String findAirportByCode(String code) {
		return null;
	}

	/**
	 * Finds a flight using code
	 * 
	 * @param code
	 * @return Flight object or null if code is not found.
	 */
	public Flight findFlightByCode(String code) {
		for (Flight flight : flights) {
			if (flight.getCode() == code) {
				return flight;
			} else {
				continue;
			}
		}
		return null;
	}

	/**
	 * Finds flights going between airports on a specified weekday
	 * 
	 * @param from    - From airport
	 * @param to      - To airport
	 * @param weekday - Day of week (one of WEEKDAY_* constants). Use WEEKDAY_ANY
	 *                for any day of the week
	 * @return Any f Flight objects.
	 */
	public ArrayList<Flight> findFlights(String from, String to, String weekday) {
		ArrayList<Flight> f = new ArrayList<Flight>();
		for (Flight flight : this.flights) {
			if (!flight.getFrom().equals(from) || !flight.getTo().equals(to)
					|| !weekday.equals(WEEKDAY_ANY) && !flight.getWeekday().equals(weekday)) {
				continue;
			}
			f.add(flight);
		}
		return f;
	}

	/**
	 * Makes a reservation
	 * 
	 * @param flight      - Flight to book reservation for
	 * @param name        - Name of person (cannot be null or empty)
	 * @param citizenship - Citizenship of person (cannot be null or empty)
	 * @return Created reservation instance
	 * @throws NullFlightException         - Thrown if flight is null
	 * @throws NoMoreSeatsException        - Thrown if flight is booked up
	 * @throws InvalidNameException        - Thrown if name is null or empty
	 * @throws InvalidCitizenshipException - Thrown if citizenship is null or empty
	 */
	public Reservation makeReservation(Flight flight, String name, String citizenship)
			throws InvalidNameException, InvalidCitizenshipException, NullFlightException, NoMoreSeatsException {
		if (flight == null) {
			throw new NullFlightException();
		}
		if (this.getAvailableSeats(flight) == 0) {
			throw new NoMoreSeatsException();
		}

		String code = generateReservationCode(flight);
		String flightCode = flight.getCode();
		String airline = flight.getAirlineName();
		double cost = flight.getCostPerSeat();
		boolean active = true;

		Reservation reservation = new Reservation(code, flightCode, airline, name, citizenship, cost, active);
		reservations.add(reservation);
		persist();
		return reservation;
	}

	/**
	 * Finds reservations containing either reservation code or airline
	 * 
	 * @param reservationCode - Reservation code to search for
	 * @param airline         - Airline to search for
	 * @param name            - Travelers name to search for
	 * @return Any matching Reservation objects.
	 */
	public ArrayList<Reservation> findReservations(String reservationCode, String airline, String name) {
		ArrayList<Reservation> r = new ArrayList<Reservation>();
		if (reservationCode.contentEquals("") && airline.contentEquals("") && name.contentEquals("")) {
			return r;
		}
		for (Reservation reservation : reservations) {
			if (reservation.getCode().contains(reservationCode) && reservation.getAirline().contains(airline)
					&& reservation.getName().contains(name)) {
				r.add(reservation);
			} else {
				continue;
			}
		}
		return r;
	}

	/**
	 * Finds reservation with the exact reservation code
	 * 
	 * @param reservationCode - Reservation code.
	 * @return Reservation object or null if none found.
	 */
	public Reservation findReservationByCode(String reservationCode) {
		for (Reservation reservation : reservations) {
			if (reservation.getCode() == reservationCode) {
				return reservation;
			} else {
				continue;
			}
		}
		return null;
	}

	/**
	 * Gets the number of available seats for a flight
	 * 
	 * @param flight - Flight instance
	 * @return Number of available seats
	 */
	private int getAvailableSeats(Flight flight) {
		int used = 0;
		for (Reservation reservation : reservations) {
			if (reservation.isActive() && reservation.getFlightCode().equals(flight)) {
				++used;
			} else {
				continue;
			}
		}
		return flight.getSeats() - used;
	}

	/**
	 * Gets reservation code using a flight
	 * 
	 * @param flight - Flight instance
	 * @return Reservation code
	 */
	private String generateReservationCode(Flight flight) {
		String first;
		if (flight.isDomestic()) {
			first = "D";
		} else {
			first = "I";
		}
		Random rand = new Random();
		return String.format("%s%d", first, rand.nextInt(9999) + 1000);
	}

	/**
	 * Populates flights from text file
	 * 
	 * @throws IOException Thrown when the file could not be accessed.
	 */
	private void populateFlights() throws IOException {

		File flight = new File("res/flights.csv");
		Scanner inFile = new Scanner(flight);

		while (inFile.hasNextLine()) {
			String line = inFile.nextLine();
			String[] seperateLine = line.split(",");
			String code = seperateLine[0];
			String from = seperateLine[1];
			String to = seperateLine[2];
			String weekday = seperateLine[3];
			String time = seperateLine[4];
			int seats = Integer.parseInt(seperateLine[5]);
			double costPerSeat = Double.parseDouble(seperateLine[6]);
			String airline = "";

			if (code.startsWith("OA")) {
				airline = "Otto Airlines";
			} else if (code.startsWith("CA")) {
				airline = "Conned Air";
			} else if (code.startsWith("TB")) {
				airline = "Try a Bus Airways";
			} else if (code.startsWith("VA")) {
				airline = "Verical Airways";
			} else {
				airline = "Unidentified Flying Object";
			}

			Flight f = new Flight(code, airline, from, to, weekday, time, seats, costPerSeat);
			flights.add(f);
		}
		inFile.close();
	}

	/**
	 * Populates airports with the airports from CSV file.
	 * 
	 * @throws IOException Thrown when the file could not be accessed.
	 */
	private void populateAirports() throws IOException {
		File air = new File("res/airports.csv");
		Scanner inFile = new Scanner(air);
		while (inFile.hasNextLine()) {
			String line = inFile.nextLine();
			String[] seperateLine = line.split(",");
			String airport = seperateLine[0];
			String fullName = seperateLine[1];
			airports.add(airport);
		}
		inFile.close();
	}

	/**
	 * Saves records in memory to hard drive
	 */
	public void persist() {
		try {
			RandomAccessFile r = new RandomAccessFile("res/Reservations.bin", "rw");
			for (Reservation reservation : reservations) {
				r.writeUTF(String.format("%-7s", reservation.getCode()));
				r.writeUTF(String.format("%-7s", reservation.getFlightCode()));
				r.writeUTF(String.format("%-30s", reservation.getAirline()));
				r.writeUTF(String.format("%-50s", reservation.getName()));
				r.writeUTF(String.format("%-50s", reservation.getCitizenship()));
				r.writeBoolean(reservation.isActive());
			}
			r.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Populates reservations with Reservation objects from Random Access File
	 */
	private void populateFromBinary() {
		try {
			RandomAccessFile r = new RandomAccessFile("res/Reservations.bin", "rw");
			for (int i = 0; i < r.length(); i += 171) {
				String res = r.readUTF();
				String flightCode = r.readUTF();
				String airline = r.readUTF().trim();
				String name = r.readUTF().trim();
				String citizenship = r.readUTF().trim();
				double cost = r.readDouble();
				boolean active = r.readBoolean();

				try {
					Reservation reservation = new Reservation(res, flightCode, airline, name, citizenship, cost,
							active);
					reservations.add(reservation);
					continue;
				} catch (InvalidNameException e) {
					e.printStackTrace();
					continue;
				} catch (InvalidCitizenshipException e) {
					e.printStackTrace();
					continue;
				} catch (NullFlightException e) {
					e.printStackTrace();
				}
			}
			r.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

}
