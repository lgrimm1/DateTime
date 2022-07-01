package GrimmPackage.DateTime;

import java.time.*;

import static GrimmPackage.DateTime.GTimeInfo.*;

/**
 * <b>Provides editing helper methods for GTime objects.</b><p>
 * All methods are static.<p>
 * The successfulness can be read by invoking getEditSuccess() method.<p>
 * In order to use the methods without addressing this class, copy-paste the following line into your import session:<p>
 * <i>import static GrimmPackage.DateTime.GTimeEdit.*;</i><p>
 * <p><p>
 * PUBLIC METHODS:<p>
 * getSuccess()<p>
 * cloneGTime(GTime gTime) -> GTime<p>
 * setID(GTime gTime, String newID)<p>
 * setDescription(GTime gTime, String newDescription)<p>
 * setCurrentTime(GTime gTime)<p>
 * setCurrentTime(GTime gTime, String timezone)<p>
 * setDateTime(GTime gTime, int newYear, int newMonth, int newDay, int newHour24, int newMinute, int newSecond, int newNanosecond, String newTimezone)<p>
 * setDateTime(GTime gTime, int[] newDateComponents, int[] newTimeComponents, String newTimezone)<p>
 * setYear(GTime gTime, int newYear)<p>
 * setMonth(GTime gTime, int newMonth)<p>
 * setDay(GTime gTime, int newDay)<p>
 * setHour(GTime gTime, int newHour24)<p>
 * setMinute(GTime gTime, int newMinute)<p>
 * setSecond(GTime gTime, int newSecond)<p>
 * setNanosecond(GTime gTime, int newNanosecond)<p>
 * setDayOfYear(GTime gTime, int newDayOfYear)<p>
 *
 * @author	Laszlo Karoly Grimm
 * @since 2022-05-28
 */
public class GTimeEdit {
	private static boolean editSuccess;

	/**
	 * <b>Returns the success flag of GTimeEdit.</b>
	 * @return	the boolean value of method success.
	 */
	public static boolean getEditSuccess() {
		return editSuccess;
	}

	/**
	 * <b>Returns a deep copy of GTime.</b>
	 * @param originalGTime	the GTime of original datetime.
	 * @return				the GTime of new GTime.
	 */
	public static GTime cloneGTime(GTime originalGTime) {
		GTime newGTime = new GTime(getYear(originalGTime), getMonth(originalGTime), getDay(originalGTime), getHour(originalGTime), getMinute(originalGTime), getSecond(originalGTime), getNanosecond(originalGTime), getTimeZoneID(originalGTime));
		setID(newGTime, getID(originalGTime));
		setDescription(newGTime, getDescription(originalGTime));
		editSuccess = true;
		return newGTime;
		}

	/**
	 * <b>Sets the ID of GTime.</b>
	 * @param gTime		the GTime of datetime.
	 * @param newID		the String of new ID.
	 */
	public static void setID(GTime gTime, String newID) {
		gTime.ID = newID;
		editSuccess = true;
	}

	/**
	 * <b>Sets the description of GTime.</b>
	 * @param gTime				the GTime of datetime.
	 * @param newDescription	the String of new description.
	 */
	public static void setDescription(GTime gTime, String newDescription) {
		gTime.description = newDescription;
		editSuccess = true;
	}

	/**
	 * <b>Sets GTime on current local datetime and local time zone.</b>
	 * @param gTime	the GTime of datetime.
	 */
	public static void setCurrentTime(GTime gTime) {
		gTime.time = ZonedDateTime.now();
		editSuccess = true;
	}

	/**
	 * <b>Sets GTime on current local datetime, on given time zone.</b><p>
	 * Time zone ID identification no.1: offset from Greenwich Mean Time (e.g. GMT+01:30); will not use Daylight Saving Time (DST) offset.<p>
	 * Time zone ID identification no.2: offset from Universal Coordinated Time (e.g. UTM+01:30); will not use DST offset.<p>
	 * Time zone ID identification no.3: offset from Universal Time (e.g. UT+01:30); will not use DST offset.<p>
	 * Time zone ID identification no.4: regional time definition in continent/city format (e.g. America/Los_Angeles); will use DST offset.<p>
	 * Time zone ID identification no.5: short mosaic letters (e.g. CET) of time zone; due to having the same mosaic words for different zones on Earth, use it only when the ID is unique; will use DST offset.<p>
	 * Time zone ID identification no.6: Z for the absolute zeroth time zone; will not use DST offset.<p>
	 * In case wrong time zone is given, datetime will not change.
	 * @param gTime			the GTime of datetime.
	 * @param newTimezone	the String of new time zone.
	 */
	public static void setCurrentTime(GTime gTime, String newTimezone) {
		try {
			gTime.time = ZonedDateTime.now(ZoneId.of(newTimezone));
			editSuccess = true;
		}
		catch (Exception e) {
			editSuccess = false;
		}
	}

	/**
	 * <b>Sets GTime on given datetime and time zone.</b><p>
	 * Time zone ID identification no.1: offset from Greenwich Mean Time (e.g. GMT+01:30); will not use Daylight Saving Time (DST) offset.<p>
	 * Time zone ID identification no.2: offset from Universal Coordinated Time (e.g. UTM+01:30); will not use DST offset.<p>
	 * Time zone ID identification no.3: offset from Universal Time (e.g. UT+01:30); will not use DST offset.<p>
	 * Time zone ID identification no.4: regional time definition in continent/city format (e.g. America/Los_Angeles); will use DST offset.<p>
	 * Time zone ID identification no.5: short mosaic letters (e.g. CET) of time zone; due to having the same mosaic words for different zones on Earth, use it only when the ID is unique; will use DST offset.<p>
	 * Time zone ID identification no.6: Z for the absolute zeroth time zone; will not use DST offset.<p>
	 * In case wrong time zone, date or time is given, datetime will not change.
	 * @param gTime			the GTime of datetime.
	 * @param newYear		the int of new year.
	 * @param newMonth		the int of new month.
	 * @param newDay		the int of new day.
	 * @param newHour24		the int of new hour.
	 * @param newMinute		the int of minute.
	 * @param newSecond		the int of new second.
	 * @param newNanosecond	the int of new nanosecond.
	 * @param newTimezone	the String of new time zone.
	 */
	public static void setDateTime(GTime gTime, int newYear, int newMonth, int newDay, int newHour24, int newMinute, int newSecond, int newNanosecond, String newTimezone) {
		ZoneId zi;
		try {
			zi = ZoneId.of(newTimezone);
			try {
				gTime.time = ZonedDateTime.of(newYear, newMonth, newDay, newHour24, newMinute, newSecond, newNanosecond, zi);
				editSuccess = true;
			}
			catch (Exception e) {
				editSuccess = false;
			}
		}
		catch (Exception e) {
			editSuccess = false;
		}
	}

	/**
	 * <b>Sets GTime on given datetime and time zone.</b><p>
	 * Date components are year, month and day, in their respective order.<p>
	 * Time components are hour24, minute, second and nanosecond, in their respective order.<p>
	 * Time zone ID identification no.1: offset from Greenwich Mean Time (e.g. GMT+01:30); will not use Daylight Saving Time (DST) offset.<p>
	 * Time zone ID identification no.2: offset from Universal Coordinated Time (e.g. UTM+01:30); will not use DST offset.<p>
	 * Time zone ID identification no.3: offset from Universal Time (e.g. UT+01:30); will not use DST offset.<p>
	 * Time zone ID identification no.4: regional time definition in continent/city format (e.g. America/Los_Angeles); will use DST offset.<p>
	 * Time zone ID identification no.5: short mosaic letters (e.g. CET) of time zone; due to having the same mosaic words for different zones on Earth, use it only when the ID is unique; will use DST offset.<p>
	 * Time zone ID identification no.6: Z for the absolute zeroth time zone; will not use DST offset.<p>
	 * In case wrong time zone, date or time is given, datetime will not change.
	 * @param gTime				the GTime of datetime.
	 * @param newDateComponents	the int[] of new date components.
	 * @param newTimeComponents	the int[] of new time components.
	 * @param newTimezone		the String of new time zone.
	 */
	public static void setDateTime(GTime gTime, int[] newDateComponents, int[] newTimeComponents, String newTimezone) {
		ZoneId zi;
		try {
			zi = ZoneId.of(newTimezone);
			try {
				gTime.time = ZonedDateTime.of(newDateComponents[0], newDateComponents[1], newDateComponents[2], newTimeComponents[0], newTimeComponents[1], newTimeComponents[2], newTimeComponents[3], zi);
				editSuccess = true;
			}
			catch (Exception e) {
				editSuccess = false;
			}
		}
		catch (Exception e) {
			editSuccess = false;
		}
	}

	/**
	 * Sets the year of GTime.
	 * @param gTime		the GTime of datetime.
	 * @param newYear	the int of year.
	 */
	public static void setYear(GTime gTime, int newYear) {
		try {
			gTime.time = gTime.time.withYear(newYear);
			editSuccess = true;
		}
		catch (Exception e) {
			editSuccess = false;
		}
	}

	/**
	 * Sets the month of GTime.
	 * @param gTime		the GTime of datetime.
	 * @param newMonth	the int of month.
	 */
	public static void setMonth(GTime gTime, int newMonth) {
		try {
			gTime.time = gTime.time.withMonth(newMonth);
			editSuccess = true;
		}
		catch (Exception e) {
			editSuccess = false;
		}
	}

	/**
	 * Sets the day of GTime.
	 * @param gTime		the GTime of datetime.
	 * @param newDay	the int of day.
	 */
	public static void setDay(GTime gTime, int newDay) {
		try {
			gTime.time = gTime.time.withDayOfMonth(newDay);
			editSuccess = true;
		}
		catch (Exception e) {
			editSuccess = false;
		}
	}

	/**
	 * Sets the hour of GTime.
	 * @param gTime		the GTime of datetime.
	 * @param newHour24	the int of hour.
	 */
	public static void setHour(GTime gTime, int newHour24) {
		try {
			gTime.time = gTime.time.withHour(newHour24);
			editSuccess = true;
		}
		catch (Exception e) {
			editSuccess = false;
		}
	}

	/**
	 * Sets the minute of GTime.
	 * @param gTime		the GTime of datetime.
	 * @param newMinute	the int of minute.
	 */
	public static void setMinute(GTime gTime, int newMinute) {
		try {
			gTime.time = gTime.time.withMinute(newMinute);
			editSuccess = true;
		}
		catch (Exception e) {
			editSuccess = false;
		}
	}

	/**
	 * Sets the second of GTime.
	 * @param gTime		the GTime of datetime.
	 * @param newSecond	the int of second.
	 */
	public static void setSecond(GTime gTime, int newSecond) {
		try {
			gTime.time = gTime.time.withSecond(newSecond);
			editSuccess = true;
		}
		catch (Exception e) {
			editSuccess = false;
		}
	}

	/**
	 * Sets the nanosecond of GTime.
	 * @param gTime			the GTime of datetime.
	 * @param newNanosecond	the int of nanosecond.
	 */
	public static void setNanosecond(GTime gTime, int newNanosecond) {
		try {
			gTime.time = gTime.time.withNano(newNanosecond);
			editSuccess = true;
		}
		catch (Exception e) {
			editSuccess = false;
		}
	}

	/**
	 * Sets the day-of-year of GTime.
	 * @param gTime			the GTime of datetime.
	 * @param newDayOfYear	the int of day-of-year.
	 */
	public static void setDayOfYear(GTime gTime, int newDayOfYear) {
		try {
			gTime.time = gTime.time.withDayOfYear(newDayOfYear);
			editSuccess = true;
		}
		catch (Exception e) {
			editSuccess = false;
		}
	}
}
