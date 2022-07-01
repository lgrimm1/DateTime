package GrimmPackage.DateTime;

import java.time.*;

/**
 * <b>Provides calculating helper methods for GTime objects.</b><p>
 * All methods are static.<p>
 * The successfulness can be read by invoking getCalculateSuccess() method.<p>
 * In order to use the methods without addressing this class, copy-paste the following line into your import session:<p>
 * <i>import static GrimmPackage.DateTime.GTimeCalculate.*;</i><p>
 * <p><p>
 * PUBLIC METHODS:<p>
 * getCalculateSuccess()<p>
 * increaseYear(GTime gTime, long yearOffset)<p>
 * increaseMonth(GTime gTime, long monthOffset)<p>
 * increaseDay(GTime gTime, long dayOffset)<p>
 * increaseHour(GTime gTime, long hourOffset)<p>
 * increaseMinute(GTime gTime, long minuteOffset)<p>
 * increaseSecond(GTime gTime, long secondOffset)<p>
 * increaseNanosecond(GTime gTime, long nanosecondOffset)<p>
 * increaseWeek(GTime gTime, long weekOffset)<p>
 * changeTimezone(GTime gTime, String newTimezone)<p>
 *
 * @author	Laszlo Karoly Grimm
 * @since 2022-05-28
 */
public class GTimeCalculate {
	private static boolean calculateSuccess;

	/**
	 * <b>Returns the success flag of GTimeEdit.</b>
	 * @return	the boolean value of method success.
	 */
	public static boolean getCalculateSuccess() {
		return calculateSuccess;
	}

	/**
	 * <b>Increase/decrease the year of GTime.</b><p>
	 * Giving negative offset results decreasing.
	 * @param gTime			the GTime of datetime.
	 * @param yearOffset	the long of year offset.
	 */
	public static void increaseYear(GTime gTime, long yearOffset) {
		try {
			gTime.time = gTime.time.plusYears(yearOffset);
			calculateSuccess = true;
		}
		catch (Exception e) {
			calculateSuccess = false;
		}
	}

	/**
	 * <b>Increase/decrease the month of GTime.</b><p>
	 * Giving negative offset results decreasing.
	 * @param gTime			the GTime of datetime.
	 * @param monthOffset	the long of month offset.
	 */
	public static void increaseMonth(GTime gTime, long monthOffset) {
		try {
			gTime.time = gTime.time.plusMonths(monthOffset);
			calculateSuccess = true;
		}
		catch (Exception e) {
			calculateSuccess = false;
		}
	}

	/**
	 * <b>Increase/decrease the day of GTime.</b><p>
	 * Giving negative offset results decreasing.
	 * @param gTime			the GTime of datetime.
	 * @param dayOffset	the long of day offset.
	 */
	public static void increaseDay(GTime gTime, long dayOffset) {
		try {
			gTime.time = gTime.time.plusDays(dayOffset);
			calculateSuccess = true;
		}
		catch (Exception e) {
			calculateSuccess = false;
		}
	}

	/**
	 * <b>Increase/decrease the hour of GTime.</b><p>
	 * Giving negative offset results decreasing.
	 * @param gTime			the GTime of datetime.
	 * @param hourOffset	the long of hour offset.
	 */
	public static void increaseHour(GTime gTime, long hourOffset) {
		try {
			gTime.time = gTime.time.plusHours(hourOffset);
			calculateSuccess = true;
		}
		catch (Exception e) {
			calculateSuccess = false;
		}
	}

	/**
	 * <b>Increase/decrease the minute of GTime.</b><p>
	 * Giving negative offset results decreasing.
	 * @param gTime			the GTime of datetime.
	 * @param minuteOffset	the long of minute offset.
	 */
	public static void increaseMinute(GTime gTime, long minuteOffset) {
		try {
			gTime.time = gTime.time.plusMinutes(minuteOffset);
			calculateSuccess = true;
		}
		catch (Exception e) {
			calculateSuccess = false;
		}
	}

	/**
	 * <b>Increase/decrease the second of GTime.</b><p>
	 * Giving negative offset results decreasing.
	 * @param gTime			the GTime of datetime.
	 * @param secondOffset	the long of second offset.
	 */
	public static void increaseSecond(GTime gTime, long secondOffset) {
		try {
			gTime.time = gTime.time.plusSeconds(secondOffset);
			calculateSuccess = true;
		}
		catch (Exception e) {
			calculateSuccess = false;
		}
	}

	/**
	 * <b>Increase/decrease the nanosecond of GTime.</b><p>
	 * Giving negative offset results decreasing.
	 * @param gTime				the GTime of datetime.
	 * @param nanosecondOffset	the long of nanosecond offset.
	 */
	public static void increaseNanosecond(GTime gTime, long nanosecondOffset) {
		try {
			gTime.time = gTime.time.plusNanos(nanosecondOffset);
			calculateSuccess = true;
		}
		catch (Exception e) {
			calculateSuccess = false;
		}
	}

	/**
	 * <b>Increase/decrease the week of GTime.</b><p>
	 * Giving negative offset results decreasing.
	 * @param gTime				the GTime of datetime.
	 * @param weekOffset	the long of week offset.
	 */
	public static void increaseWeek(GTime gTime, long weekOffset) {
		try {
			gTime.time = gTime.time.plusWeeks(weekOffset);
			calculateSuccess = true;
		}
		catch (Exception e) {
			calculateSuccess = false;
		}
	}

	/**
	 * <b>Sets GTime on a given time zone.</b><p>
	 * Does not change the instance therefore the datetime will change following the new time zone.<p>
	 * Time zone ID identification no.1: offset from Greenwich Mean Time (e.g. GMT+01:30); will not use Daylight Saving Time (DST) offset.<p>
	 * Time zone ID identification no.2: offset from Universal Coordinated Time (e.g. UTM+01:30); will not use DST offset.<p>
	 * Time zone ID identification no.3: offset from Universal Time (e.g. UT+01:30); will not use DST offset.<p>
	 * Time zone ID identification no.4: regional time definition in continent/city format (e.g. America/Los_Angeles); will use DST offset.<p>
	 * Time zone ID identification no.5: short mosaic letters (e.g. CET) of time zone; due to having the same mosaic words for different zones on Earth, use it only when the ID is unique; will use DST offset.<p>
	 * Time zone ID identification no.6: Z for the absolute zeroth time zone; will not use DST offset.<p>
	 * In case wrong time zone is given, datetime and timezone will not change.
	 * @param gTime			the GTime of datetime.
	 * @param newTimezone	the String of new time zone.
	 */
	public static void changeTimezone(GTime gTime, String newTimezone) {
		try {
			gTime.time = gTime.time.withZoneSameInstant(ZoneId.of(newTimezone));
			calculateSuccess = true;
		}
		catch (Exception e) {
			calculateSuccess = false;
		}
	}
}
