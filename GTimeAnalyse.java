package GrimmPackage.DateTime;

import java.time.*;
import java.util.*;

import static GrimmPackage.DateTime.GTimeCalculate.*;
import static GrimmPackage.DateTime.GTimeInfo.*;
import static GrimmPackage.DateTime.GTimeEdit.*;

/**
 * <b>Provides analysis helper methods for GTime objects.</b><p>
 * All methods are static.<p>
 * In order to use the methods without addressing this class, copy-paste the following line into your import session:<p>
 * <i>import static GrimmPackage.DateTime.GTimeAnalyse.*;</i><p>
 * <p><p>
 * PUBLIC METHODS:<p>
 * isDate(String year, String month, String day) -> boolean<p>
 * isDate(int year, int month, int day) -> boolean<p>
 * isLeapYear(GTime gTime) -> boolean<p>
 * isEqualForDateTimeTimezone(GTime gTime1, GTime gTime2) -> boolean<p>
 * isEqualForDays(GTime gTime1, GTime gTime2) -> boolean<p>
 * isEqualForHours(GTime gTime1, GTime gTime2) -> boolean<p>
 * isEqualForSeconds(GTime gTime1, GTime gTime2) -> boolean<p>
 * differenceInDays(GTime gTime1, GTime gTime2) -> long<p>
 * differenceInHours(GTime gTime1, GTime gTime2) -> long<p>
 * differenceInMinutes(GTime gTime1, GTime gTime2) -> long<p>
 * differenceInSeconds(GTime gTime1, GTime gTime2) -> long<p>
 * differenceInNanoseconds(GTime gTime1, GTime gTime2) -> long<p>
 * difference(GTime gTime1, GTime gTime2) -> long[]<p>
 *
 * @author	Laszlo Karoly Grimm
 * @since 2022-06-05
 */
public class GTimeAnalyse {
	/**
	 * <b>Checks whether the given parameters form a legal GTime date.</b>
	 * @param year	the String of year.
	 * @param month	the String of month.
	 * @param day	the String of day.
	 * @return		the boolean value marks whether the parameters qualify.
	 */
	public static boolean isDate(String year, String month, String day) {
		int yearInt, monthInt, dayInt;
		try {
			yearInt = Integer.parseInt(year);
			monthInt = Integer.parseInt(month);
			dayInt = Integer.parseInt(day);
			return isDate(yearInt, monthInt, dayInt);
		}
		catch (Exception e) {
			return false;
		}
	}

	/**
	 * <b>Checks whether the given parameters form a legal GTime date.</b>
	 * @param year	the int of year.
	 * @param month	the int of month.
	 * @param day	the int of day.
	 * @return		the boolean value marks whether the parameters qualify.
	 */
	public static boolean isDate(int year, int month, int day) {
		GTime gTime = new GTime();
		setDateTime(gTime, year, month, day, 1, 1, 1, 1, getTimeZoneID(gTime));
		return getEditSuccess();
	}

	/**
	 * <b>Checks if GTime is in a leap year.</b>
	 * @param gTime	the GTime of datetime.
	 * @return		the boolean value marks whether the year of GTime is a leap year.
	 */
	public static boolean isLeapYear(GTime gTime) {
		return new GregorianCalendar().isLeapYear(getYear(gTime));
	}

	/**
	 * <b>Check if two GTime objects equal for datetime and time zone.</b>
	 * @param gTime1 	the GTime of the first datetime.
	 * @param gTime2	the GTime of the second datetime.
	 * @return			the boolean value marks equality.
	 */
	public static boolean isEqualForDateTimeTimezone(GTime gTime1, GTime gTime2) {
		return gTime1.time.equals(gTime2.time);
	}

	/**
	 * <b>Checks whether two GTimes equal for whole days.</b>
	 * @param gTime1	the GTime of first datetime.
	 * @param gTime2	the GTime of second datetime.
	 * @return			the boolean value marks equality.
	 */
	public static boolean isEqualForDays(GTime gTime1, GTime gTime2) {
		return differenceInDays(gTime1, gTime2) == 0L;
	}

	/**
	 * <b>Checks whether two GTimes equal for whole hours.</b>
	 * @param gTime1	the GTime of first datetime.
	 * @param gTime2	the GTime of second datetime.
	 * @return			the boolean value marks equality.
	 */
	public static boolean isEqualForHours(GTime gTime1, GTime gTime2) {
		return differenceInHours(gTime1, gTime2) == 0L;
	}

	/**
	 * <b>Checks whether two GTimes equal for whole seconds.</b>
	 * @param gTime1	the GTime of first datetime.
	 * @param gTime2	the GTime of second datetime.
	 * @return			the boolean value marks equality.
	 */
	public static boolean isEqualForSeconds(GTime gTime1, GTime gTime2) {
		return differenceInSeconds(gTime1, gTime2) == 0L;
	}

	/**
	 * <b>Calculates the difference between two GTimes in whole days.</b><p>
	 * Positive result marks the second is greater, negative marks the first is greater.
	 * @param gTime1	the GTime of first datetime.
	 * @param gTime2	the GTime of second datetime.
	 * @return			the long of difference.
	 */
	public static long differenceInDays(GTime gTime1, GTime gTime2) {
		GTime gTime1Clone = cloneGTime(gTime1);
		changeTimezone(gTime1Clone, "GMT+00:00");
		GTime gTime2Clone = cloneGTime(gTime2);
		changeTimezone(gTime2Clone, "GMT+00:00");
		return Duration.between(gTime1Clone.time, gTime2Clone.time).toDays();
	}

	/**
	 * <b>Calculates the difference between two GTimes in whole hours.</b><p>
	 * Positive result marks the second is greater, negative marks the first is greater.
	 * @param gTime1	the GTime of first datetime.
	 * @param gTime2	the GTime of second datetime.
	 * @return			the long of difference.
	 */
	public static long differenceInHours(GTime gTime1, GTime gTime2) {
		GTime gTime1Clone = cloneGTime(gTime1);
		changeTimezone(gTime1Clone, "GMT+00:00");
		GTime gTime2Clone = cloneGTime(gTime2);
		changeTimezone(gTime2Clone, "GMT+00:00");
		return Duration.between(gTime1Clone.time, gTime2Clone.time).toHours();
	}

	/**
	 * <b>Calculates the difference between two GTimes in whole seconds.</b><p>
	 * Positive result marks the second is greater, negative marks the first is greater.
	 * @param gTime1	the GTime of first datetime.
	 * @param gTime2	the GTime of second datetime.
	 * @return			the long of difference.
	 */
	public static long differenceInSeconds(GTime gTime1, GTime gTime2) {
		GTime gTime1Clone = cloneGTime(gTime1);
		changeTimezone(gTime1Clone, "GMT+00:00");
		GTime gTime2Clone = cloneGTime(gTime2);
		changeTimezone(gTime2Clone, "GMT+00:00");
		return Duration.between(gTime1Clone.time, gTime2Clone.time).toSeconds();
	}

	/**
	 * <b>Calculates the difference between two GTimes in whole nanoseconds.</b><p>
	 * Positive result marks the second is greater, negative marks the first is greater.
	 * @param gTime1	the GTime of first datetime.
	 * @param gTime2	the GTime of second datetime.
	 * @return			the long of difference.
	 */
	public static long differenceInNanoseconds(GTime gTime1, GTime gTime2) {
		GTime gTime1Clone = cloneGTime(gTime1);
		changeTimezone(gTime1Clone, "GMT+00:00");
		GTime gTime2Clone = cloneGTime(gTime2);
		changeTimezone(gTime2Clone, "GMT+00:00");
		return Duration.between(gTime1Clone.time, gTime2Clone.time).toNanos();
	}

	/**
	 * <b>Calculates the true difference between two GTimes.</b><p>
	 * Positive result marks the second is greater, negative marks the first is greater.<p>
	 * The resulted array contains difference in days, hours, minutes, seconds and nanoseconds, in their respective order.
	 * @param gTime1	the GTime of first datetime.
	 * @param gTime2	the GTime of second datetime.
	 * @return			the long[] of difference.
	 */
	public static long[] difference(GTime gTime1, GTime gTime2) {
		GTime gTime1Clone = cloneGTime(gTime1);
		changeTimezone(gTime1Clone, "GMT+00:00");
		GTime gTime2Clone = cloneGTime(gTime2);
		changeTimezone(gTime2Clone, "GMT+00:00");
		Duration d = Duration.between(gTime1Clone.time, gTime2Clone.time);
		return new long[]{d.toDaysPart(), d.toHoursPart(), d.toMinutesPart(), d.toSecondsPart(), d.toNanosPart()};
	}
}
