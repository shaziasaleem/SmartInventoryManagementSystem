package system.inventory.commonutilities.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class CommonUtils {

	public Date formatDate(Date d) {
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yy");
		Date result = null;
		try {
			String newDate = format.format(d);
			
			result= format.parse(newDate);
		} catch (ParseException e) {
			System.out.println("Exception in parsing date :"+e.getMessage());
		}
		
		return result;
	}
}
