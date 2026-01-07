package system.inventory.commonutilities.exceptions;

import java.time.format.DateTimeParseException;

public class CustomExceptions extends RuntimeException{
	public void DateTimeExceptions() {
		throw new DateTimeParseException("Invalid date format", "input string", 0);
	}

	
}
