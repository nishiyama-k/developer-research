package jp.co.developer.research.util;

import java.util.Calendar;

public class BirthdayUtil {

	/**
	 * Calculate age
	 * 
	 * @param birthday
	 * @return
	 */
	public static int calculateAge(Calendar birthday) {
		Calendar now = Calendar.getInstance();
		int yearDiff = now.get(Calendar.YEAR) - birthday.get(Calendar.YEAR);
		if (now.get(Calendar.MONTH) < birthday.get(Calendar.MONTH)) {
			yearDiff--;
		} else if (now.get(Calendar.MONTH) == birthday.get(Calendar.MONTH)){
			if (now.get(Calendar.DAY_OF_MONTH) < birthday.get(Calendar.DAY_OF_MONTH)){
				yearDiff--;				
			}
		}
		return yearDiff;
	}
	
	/**
	 * Cut time part of Calendar.
	 * 
	 * @param cal
	 * @return
	 */
	public static Calendar trimTime(Calendar cal){
		cal.set(Calendar.HOUR, 0);
		cal.set(Calendar.MINUTE,0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal;
	}

}
