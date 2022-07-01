package GrimmPackage.DateTime;

import java.time.*;
import java.time.temporal.*;
import java.util.*;

import static GrimmPackage.DateTime.GTimeConstant.*;

/**
 * <b>Provides information helper methods for GTime objects.</b><p>
 * All methods are static.<p>
 * In order to use the methods without addressing this class, copy-paste the following line into your import session:<p>
 * <i>import static GrimmPackage.DateTime.GTimeInfo.*;</i><p>
 * <p><p>
 * PUBLIC METHODS:<p>
 * getFullInfo(GTime gTime) -> String<p>
 * getID(GTime gTime) -> String<p>
 * getDescription(GTime gTime) -> String<p>
 * getInfo(GTime gTime) -> String<p>
 * getTimeZoneID(GTime gTime) -> String<p>
 * getTimeZoneOffset(GTime gTime) -> String<p>
 * getTimeZoneOffsetHours(GTime gTime) -> float<p>
 * getYear(GTime gTime) -> int<p>
 * getMonth(GTime gTime) -> int<p>
 * getDay(GTime gTime) -> int<p>
 * getDate(GTime gTime, int positionYear, int positionMonth, int positionDay, boolean nameForMonth, boolean paddingWithZeros, char separator) -> String<p>
 * getDate(GTime gTime) -> int[]<p>
 * getHour(GTime gTime) -> int<p>
 * getMinute(GTime gTime) -> int<p>
 * getSecond(GTime gTime) -> int<p>
 * getNanosecond(GTime gTime) -> int<p>
 * getTime(GTime gTime, int positionHour, int positionMinute, int positionSecond, boolean paddingWithZeros, char separator) -> String<p>
 * getTime(GTime gTime) -> int[]<p>
 * getDayOfWeek(GTime gTime) -> int<p>
 * getDayOfYear(GTime gTime) -> int<p>
 * getWeekOfYear(GTime gTime) -> int<p>
 * getFirstDayOfWeek(GTime gTime) -> int<p>
 * getLastDayOfWeek(GTime gTime) -> int<p>
 * getDaysOfYear(GTime gTime) -> int<p>
 * getWeeksOfYear(GTime gTime) -> int<p>
 * getMonthName(int month) -> String<p>
 * getDayOfWeekName(int dayOfWeek) -> String<p>
 *
 * @author	Laszlo Karoly Grimm
 * @since 2022-06-05
 */
public class GTimeInfo {
	/**
	 * <b>Returns ID, description and compact datetime information of GTime.</b>
	 * @param gTime	the GTime of datetime.
	 * @return		the String of information.
	 */
	public static String getFullInfo(GTime gTime) {
		String info = "";
		if (!gTime.ID.isEmpty()) {
			info += gTime.ID + ", ";
		}
		if (!gTime.description.isEmpty()) {
			info += gTime.description + ", ";
		}
		info += getInfo(gTime);
		return info;
	}

	/**
	 * <b>Returns the ID of the GTime.</b>
	 * @param gTime	the GTime of datetime.
	 * @return		the String of ID.
	 */
	public static String getID(GTime gTime) {
		return gTime.ID;
	}

	/**
	 * <b>Returns the description of the GTime.</b>
	 * @param gTime	the GTime of datetime.
	 * @return		the String of description.
	 */
	public static String getDescription(GTime gTime) {
		return gTime.description;
	}

	/**
	 * <b>Returns compact datetime information of GTime.</b>
	 * @param gTime	the GTime of datetime.
	 * @return		the String of information.
	 */
	public static String getInfo(GTime gTime) {
		return gTime.time.toString();
	}

	/**
	 * <b>Returns the time zone ID of the GTime.</b><p>
	 * The returned value can be used as time zone ID when construct a new GTime instance.
	 * @param gTime	the GTime of datetime.
	 * @return		the String of time zone ID.
	 */
	public static String getTimeZoneID(GTime gTime) {
		return gTime.time.getZone().getId();
	}

	/**
	 * <b>Returns the time zone offset of the GTime.</b><p>
	 * The returned value can be used as time zone value when construct a new GTime instance but the resulted GTime will not contain DST.
	 * @param gTime	the GTime of datetime.
	 * @return		the String of time zone offset.
	 */
	public static String getTimeZoneOffset(GTime gTime) {
		return gTime.time.getOffset().getId();
	}

	/**
	 * <b>Returns the time zone offset of the GTime as a number.</b><p>
	 * The method uses input from getTimeZoneOffset which returns a signed hours:minutes format (e.g. +04:30).
	 * @param gTime	the GTime of datetime.
	 * @return		the float of time zone offset.
	 */
	public static float getTimeZoneOffsetHours(GTime gTime) {
		String offset = getTimeZoneOffset(gTime);
		float hours = Float.parseFloat(offset.substring(1, 3)) + Float.parseFloat(offset.substring(4)) / 60;
		return (offset.charAt(0) == '+') ? hours : -1 * hours;
	}

	/**
	 * <b>Returns the year of GTime.</b>
	 * @param gTime	the GTime of datetime.
	 * @return		the int of year.
	 */
	public static int getYear(GTime gTime) {
		return gTime.time.getYear();
	}

	/**
	 * <b>Returns the month of GTime.</b>
	 * @param gTime	the GTime of datetime.
	 * @return		the int of month.
	 */
	public static int getMonth(GTime gTime) {
		return gTime.time.getMonthValue();
	}

	/**
	 * <b>Returns the day of GTime.</b>
	 * @param gTime	the GTime of datetime.
	 * @return		the int of day.
	 */
	public static int getDay(GTime gTime) {
		return gTime.time.getDayOfMonth();
	}

	/**
	 * <b>Returns the formatted date of GTime.</b><p>
	 * In case of wrong position numbers, returns an empty String.<p>
	 * In case of name for month, ignores the given separator and uses white space instead.
	 * @param gTime				the GTime of datetime.
	 * @param positionYear		the int of position for the year.
	 * @param positionMonth		the int of position for the month.
	 * @param positionDay		the int of position for the day.
	 * @param nameForMonth		the boolean value marks whether month name should be used instead of month number.
	 * @param paddingWithZeros	the boolean value marks whether the numbers should be drawn in their full width by using zeros (e.g. 01 instead of 1).
	 * @param separator			the char of separator between the date components.
	 * @return					the String of date.
	 */
	public static String getDate(GTime gTime, int positionYear, int positionMonth, int positionDay, boolean nameForMonth, boolean paddingWithZeros, char separator) {
		if ((positionYear > 0) && (positionYear < 4) && (positionMonth > 0) && (positionMonth < 4) && (positionDay > 0) && (positionDay < 4) && (positionYear != positionMonth) && (positionYear != positionDay) && (positionMonth != positionDay)) {
			String[] dateParts = new String[3];
			dateParts[positionYear - 1] = (paddingWithZeros) ? String.format("%04d", getYear(gTime)) : String.valueOf(getYear(gTime));
			dateParts[positionMonth - 1] = (nameForMonth) ? getMonthName(getMonth(gTime)) : (paddingWithZeros) ? String.format("%02d", getMonth(gTime)) : String.valueOf(getMonth(gTime));
			dateParts[positionDay - 1] = (paddingWithZeros) ? String.format("%02d", getDay(gTime)) : String.valueOf(getDay(gTime));
			if (nameForMonth) {
				separator = ' ';
			}
			return dateParts[0] + separator + dateParts[1] + separator + dateParts[2];
		}
		else {
			return "";
		}
	}

	/**
	 * <b>Returns the components of date of GTime in an int[] array.</b><p>
	 * Order of the components: year, month, day.
	 * @param gTime	the GTime of datetime.
	 * @return		the int[] of date components.
	 */
	public static int[] getDate(GTime gTime) {
		return new int[]{getYear(gTime), getMonth(gTime), getDay(gTime)};
	}

	/**
	 * <b>Returns the hour of GTime.</b>
	 * @param gTime	the GTime of datetime.
	 * @return		the int of hour.
	 */
	public static int getHour(GTime gTime) {
		return gTime.time.getHour();
	}

	/**
	 * <b>Returns the minute of GTime.</b>
	 * @param gTime	the GTime of datetime.
	 * @return		the int of minute.
	 */
	public static int getMinute(GTime gTime) {
		return gTime.time.getMinute();
	}

	/**
	 * <b>Returns the second of GTime.</b>
	 * @param gTime	the GTime of datetime.
	 * @return		the int of second.
	 */
	public static int getSecond(GTime gTime) {
		return gTime.time.getSecond();
	}

	/**
	 * <b>Returns the nanosecond of GTime.</b>
	 * @param gTime	the GTime of datetime.
	 * @return		the int of nanosecond.
	 */
	public static int getNanosecond(GTime gTime) {
		return gTime.time.getNano();
	}

	/**
	 * <b>Returns the formatted time of GTime.</b><p>
	 * In case of wrong position numbers, returns an empty String.<p>
	 * @param gTime				the GTime of datetime.
	 * @param positionHour		the int of position for the hour.
	 * @param positionMinute	the int of position for the minute.
	 * @param positionSecond	the int of position for the second.
	 * @param paddingWithZeros	the boolean value marks whether the numbers should be drawn in their full width by using zeros (e.g. 01 instead of 1).
	 * @param separator			the char of separator between the time components.
	 * @return					the String of time.
	 */
	public static String getTime(GTime gTime, int positionHour, int positionMinute, int positionSecond, boolean paddingWithZeros, char separator) {
		if ((positionHour > 0) && (positionHour < 4) && (positionMinute > 0) && (positionMinute < 4) && (positionSecond > 0) && (positionSecond < 4) && (positionHour != positionMinute) && (positionHour != positionSecond) && (positionMinute != positionSecond)) {
			String[] dateParts = new String[3];
			dateParts[positionHour - 1] = (paddingWithZeros) ? String.format("%02d", getHour(gTime)) : String.valueOf(getHour(gTime));
			dateParts[positionMinute - 1] = (paddingWithZeros) ? String.format("%02d", getMinute(gTime)) : String.valueOf(getMinute(gTime));
			dateParts[positionSecond - 1] = (paddingWithZeros) ? String.format("%02d", getSecond(gTime)) : String.valueOf(getSecond(gTime));
			return dateParts[0] + separator + dateParts[1] + separator + dateParts[2];
		}
		else {
			return "";
		}
	}

	/**
	 * <b>Returns the components of time of GTime in an int[] array.</b><p>
	 * Order of the components: hour, minute, second, nanosecond.
	 * @param gTime	the GTime of datetime.
	 * @return		the int[] of time components.
	 */
	public static int[] getTime(GTime gTime) {
		return new int[]{getHour(gTime), getMinute(gTime), getSecond(gTime), getNanosecond(gTime)};
	}

	/**
	 * <b>Returns the day-of-week of GTime.</b>
	 * @param gTime	the GTime of datetime.
	 * @return		the int of day-of-week.
	 */
	public static int getDayOfWeek(GTime gTime) {
		return gTime.time.getDayOfWeek().getValue();
	}

	/**
	 * <b>Returns the day-of-year of GTime.</b>
	 * @param gTime	the GTime of datetime.
	 * @return		the int of day-of-year.
	 */
	public static int getDayOfYear(GTime gTime) {
		return gTime.time.getDayOfYear();
	}

	/**
	 * <b>Returns the week-of-year of GTime.</b>
	 * @param gTime	the GTime of datetime.
	 * @return		the int of week-of-year.
	 */
	public static int getWeekOfYear(GTime gTime) {
		LocalDate ld = gTime.time.toLocalDate();
		WeekFields weekFields = WeekFields.of(Locale.getDefault());
		return ld.get(weekFields.weekOfWeekBasedYear());
	}

	/**
	 * <b>Returns the day-of-week for the first day of the year of GTime.</b>
	 * @param gTime	the GTime of datetime.
	 * @return		the int of day-of-year.
	 */
	public static int getFirstDayOfWeek(GTime gTime) {
		GTime first = new GTime(getYear(gTime), 1, 1, 1, 0, 0, 0, getTimeZoneID(gTime));
		return getDayOfWeek(first);
	}

	/**
	 * <b>Returns the day-of-week for the last day of the year of GTime.</b>
	 * @param gTime	the GTime of datetime.
	 * @return		the int of day-of-year.
	 */
	public static int getLastDayOfWeek(GTime gTime) {
		GTime last = new GTime(getYear(gTime), 12, 31, 1, 0, 0, 0, getTimeZoneID(gTime));
		return getDayOfWeek(last);
	}

	/**
	 * <b>Returns the number-of-days-in-year of GTime.</b>
	 * @param gTime	the GTime of datetime.
	 * @return		the int of number-of-days-in-year.
	 */
	public static int getDaysOfYear(GTime gTime) {
		GTime lastDay = new GTime(getYear(gTime), 12, 31, 1, 0, 0, 0, getTimeZoneID(gTime));
		return getDayOfYear(lastDay);
	}

	/**
	 * <b>Returns the number-of-weeks-in-year of GTime.</b>
	 * @param gTime	the GTime of datetime.
	 * @return		the int of number-of-weeks-in-year.
	 */
	public static int getWeeksOfYear(GTime gTime) {
		GTime lastDay = new GTime(getYear(gTime), 12, 31, 1, 0, 0, 0, getTimeZoneID(gTime));
		return getWeekOfYear(lastDay);
	}

	/**
	 * <b>Returns the month name of a month number.</b><p>
	 * In case the given month is not proper, returns an empty String.
	 * @param month	the int of month.
	 * @return		the String of month.
	 */
	public static String getMonthName(int month) {
		if ((month > -1) && (month < monthNames.length)) {
			return monthNames[month - 1];
		}
		else {
			return "";
		}
	}

	/**
	 * <b>Returns the day-of-week name of a day-of-week number.</b><p>
	 * In case the given day-of-week is not proper, returns an empty String.
	 * @param dayOfWeek	the int of day-of-week.
	 * @return			the String of day-of-week.
	 */
	public static String getDayOfWeekName(int dayOfWeek) {
		if ((dayOfWeek > 0) && (dayOfWeek < daysOfWeekNames.length + 1)) {
			return daysOfWeekNames[dayOfWeek - 1];
		}
		else {
			return "";
		}
	}
}
