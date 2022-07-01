// https://docs.oracle.com/javase/8/docs/api/java/time/package-summary.html
// https://docs.oracle.com/javase/8/docs/api/java/time/ZonedDateTime.html
// https://docs.oracle.com/javase/8/docs/api/java/time/ZoneId.html
// https://howtodoinjava.com/java/date-time/zoneddatetime-class/

package GrimmPackage.DateTime;

import java.time.*;

/**
 * <b>Provides a zoned date and datetime type.</b><p>
 * <p><p>
 * PUBLIC METHODS:<p>
 * GTime()<p>
 * GTime(String timezone)<p>
 * GTime(int year, int month, int day, int hour24, int minute, int second, int nanosecond, String timezone)<p>
 * GTime(int[] dateComponents, int[] timeComponents, String timezone)<p>
 *
 * @author	Laszlo Karoly Grimm
 * @since 2022-05-28
 */
public class GTime {
	protected ZonedDateTime time;
	protected String ID, description;

	/**
	 * <b>Constructs GTime on current local datetime and local time zone.</b>
 	 */
	public GTime() {
		time = ZonedDateTime.now();
		ID = "";
		description = "";
	}

	/**
	 * <b>Constructs GTime on current local datetime, on given time zone.</b><p>
	 * Time zone ID identification no.1: offset from Greenwich Mean Time (e.g. GMT+01:30); will not use Daylight Saving Time (DST) offset.<p>
	 * Time zone ID identification no.2: offset from Universal Coordinated Time (e.g. UTM+01:30); will not use DST offset.<p>
	 * Time zone ID identification no.3: offset from Universal Time (e.g. UT+01:30); will not use DST offset.<p>
	 * Time zone ID identification no.4: regional time definition in continent/city format (e.g. America/Los_Angeles); will use DST offset.<p>
	 * Time zone ID identification no.5: short mosaic letters (e.g. CET) of time zone; due to having the same mosaic words for different zones on Earth, use it only when the ID is unique; will use DST offset.<p>
	 * Time zone ID identification no.6: Z for the absolute zeroth time zone; will not use DST offset.<p>
	 * In case wrong time zone is given, local time zone will be used.
	 * @param timezone	the String of time zone.
	 */
	public GTime(String timezone) {
		try {
			time = ZonedDateTime.now(ZoneId.of(timezone));
		}
		catch (Exception e) {
			time = ZonedDateTime.now();
		}
		ID = "";
		description = "";
	}

	/**
	 * <b>Constructs GTime on given datetime and time zone.</b><p>
	 * Time zone ID identification no.1: offset from Greenwich Mean Time (e.g. GMT+01:30); will not use Daylight Saving Time (DST) offset.<p>
	 * Time zone ID identification no.2: offset from Universal Coordinated Time (e.g. UTM+01:30); will not use DST offset.<p>
	 * Time zone ID identification no.3: offset from Universal Time (e.g. UT+01:30); will not use DST offset.<p>
	 * Time zone ID identification no.4: regional time definition in continent/city format (e.g. America/Los_Angeles); will use DST offset.<p>
	 * Time zone ID identification no.5: short mosaic letters (e.g. CET) of time zone; due to having the same mosaic words for different zones on Earth, use it only when the ID is unique; will use DST offset.<p>
	 * Time zone ID identification no.6: Z for the absolute zeroth time zone; will not use DST offset.<p>
	 * In case wrong time zone is given, local time zone will be used.<p>
	 * In case wrong date or time is given, local date and time will be used.
	 * @param year			the int of year.
	 * @param month			the int of month.
	 * @param day			the int of day.
	 * @param hour24		the int of hour.
	 * @param minute		the int of minute.
	 * @param second		the int of second.
	 * @param nanosecond	the int of nanosecond.
	 * @param timezone		the String of time zone.
	 */
	public GTime(int year, int month, int day, int hour24, int minute, int second, int nanosecond, String timezone) {
		ZoneId zi;
		try {
			zi = ZoneId.of(timezone);
			try {
				time = ZonedDateTime.of(year, month, day, hour24, minute, second, nanosecond, zi);
			}
			catch (Exception e) {
				time = ZonedDateTime.now(zi);
			}
		}
		catch (Exception e) {
			time = ZonedDateTime.now();
			zi = time.getZone();
			try {
				time = ZonedDateTime.of(year, month, day, hour24, minute, second, nanosecond, zi);
			}
			catch (Exception e2) {
				time = ZonedDateTime.now(zi);
			}
		}
		ID = "";
		description = "";
	}

	/**
	 * <b>Constructs GTime on given datetime and time zone.</b><p>
	 * Date components are year, month and day, in their respective order.<p>
	 * Time components are hour24, minute, second and nanosecond, in their respective order.<p>
	 * Time zone ID identification no.1: offset from Greenwich Mean Time (e.g. GMT+01:30); will not use Daylight Saving Time (DST) offset.<p>
	 * Time zone ID identification no.2: offset from Universal Coordinated Time (e.g. UTM+01:30); will not use DST offset.<p>
	 * Time zone ID identification no.3: offset from Universal Time (e.g. UT+01:30); will not use DST offset.<p>
	 * Time zone ID identification no.4: regional time definition in continent/city format (e.g. America/Los_Angeles); will use DST offset.<p>
	 * Time zone ID identification no.5: short mosaic letters (e.g. CET) of time zone; due to having the same mosaic words for different zones on Earth, use it only when the ID is unique; will use DST offset.<p>
	 * Time zone ID identification no.6: Z for the absolute zeroth time zone; will not use DST offset.<p>
	 * In case wrong time zone is given, local time zone will be used.<p>
	 * In case wrong date or time is given, local date and time will be used.
	 * @param dateComponents	the int[] of date components.
	 * @param timeComponents	the int[] of time components.
	 * @param timezone			the String of time zone.
	 */
	public GTime(int[] dateComponents, int[] timeComponents, String timezone) {
		ZoneId zi;
		try {
			zi = ZoneId.of(timezone);
			try {
				time = ZonedDateTime.of(dateComponents[0], dateComponents[1], dateComponents[2], timeComponents[0], timeComponents[1], timeComponents[2], timeComponents[3], zi);
			}
			catch (Exception e) {
				time = ZonedDateTime.now(zi);
			}
		}
		catch (Exception e) {
			time = ZonedDateTime.now();
			zi = time.getZone();
			try {
				time = ZonedDateTime.of(dateComponents[0], dateComponents[1], dateComponents[2], timeComponents[0], timeComponents[1], timeComponents[2], timeComponents[3], zi);
			}
			catch (Exception e2) {
				time = ZonedDateTime.now(zi);
			}
		}
		ID = "";
		description = "";
	}
}
