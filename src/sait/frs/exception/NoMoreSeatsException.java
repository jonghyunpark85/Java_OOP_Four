package sait.frs.exception;

public class NoMoreSeatsException extends Exception {

	public NoMoreSeatsException() {
		super("Error: The flight has no more seat.");
	}

}
