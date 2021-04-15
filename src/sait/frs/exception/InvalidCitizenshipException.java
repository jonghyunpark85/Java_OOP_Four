package sait.frs.exception;

public class InvalidCitizenshipException extends Exception{
	
	public InvalidCitizenshipException () {
		
		super("Error: You try to input invaild citizenship.");
	}

}
