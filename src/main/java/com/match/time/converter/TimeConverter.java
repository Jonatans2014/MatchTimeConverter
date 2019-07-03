package com.match.time.converter;

import java.util.List;
import java.util.LinkedList;
import  static com.match.time.converter.MatchPeriods.*;
/**
 * converter which prints a string with a new format
 * normalTimeMinutes:normalTimeSeconds - period
 */

public class TimeConverter {

	private List<String> input;
	private LinkedList<String> periods;
	private LinkedList<String> minutes;
	private LinkedList<String> seconds;

	/**
	 * Takes a string: [period] minutes:seconds.milliseconds converts to:
	 * normalTimeMinutes:normalTimeSeconds - period
	 * 
	 * @param oldFormat
	 */
	public TimeConverter(List<String> oldFormat) {

		this.input = oldFormat;
		periods = new LinkedList<>();
		minutes = new LinkedList<>();
		seconds = new LinkedList<>();
	}

	public void parseMatch() {
		if (!input.isEmpty()) {
			input.forEach(in -> {
				periods.add(parsePeriod(in));
				minutes.add(parseMinutes(in, periods.getLast()));
				seconds.add(parseSeconds(in));
				displayNewFormat();
			});
		}
	}

	/*
	 * Check if string contains the right format
	 */
	private boolean isPeriodValid(String period) {

		return period.contains(" ");
	}

	/*
	 * Convert period into new format
	 */
	private String parsePeriod(String period) {

		if (isPeriodValid(period)) {
			period = period.substring(period.indexOf('['), period.lastIndexOf(' ' ));
			return newFormatOfPeriod(period).toString();
		}
		return INVALID.toString();
	}

	/*
	 * Split string at ":"
	 */
	private boolean isMinuteFormatValid(String format) {

		return format.contains(":");
	}

	/*
	 * Convert minutes  into new format
	 * 
	 */

	private String parseMinutes(String format, String period) {
		final int minuteFormatAsInteger;
		final StringBuilder newFormat = new StringBuilder();
		final String minute;
		if (isMinuteFormatValid(format)) {
			minute = format.substring(format.indexOf(' ') + 1, format.lastIndexOf(':'));

			minuteFormatAsInteger = Integer.parseInt(minute);

			if (minuteFormatAsInteger < 0)
				return INVALID.toString();
			else if (minuteFormatAsInteger >= 45 && period.equals(FIRST_HALF.toString()))
				return newFormat.append("45:00 +").append(String.format("%02d", minuteFormatAsInteger % 45)).toString();

			else if (minuteFormatAsInteger >= 90 && period.equals(SECOND_HALF.toString()))
				return newFormat.append("90:00 +").append(String.format("%02d", minuteFormatAsInteger % 90)).toString();

			else
				return String.format("%02d", minuteFormatAsInteger);
		}
		return INVALID.toString();

	}

	/*
	 * Checks if  string contains "."
	 */
	private boolean isSecondFormatValid(String format) {
		return format.contains(".");
	}

	/*
	 * Change the format of seconds
	 * 
	 * @return normalTimeSeconds +additionalMinutes:additionalSeconds
	 */
	private String parseSeconds(String format) {

		final int secondFormatAsInteger;
		final String getSeconds;

		if (isSecondFormatValid(format)) {

			getSeconds = format.substring(format.indexOf(':') + 1, format.indexOf('.'));
			secondFormatAsInteger = Integer.parseInt(getSeconds);

			if (milliseconds(format))
				return String.format("%02d", secondFormatAsInteger + 1);
			else
				return String.format("%02d", secondFormatAsInteger);
		}
		return INVALID.toString();

	}

	private boolean milliseconds( String format) {

		final String milliseconds = format.substring(format.indexOf('.') + 1);
		final int MILLISECONDS_AS_INT = Integer.parseInt(milliseconds);



		return MILLISECONDS_AS_INT >= 500;

	}

	/**
	 * Check if string is valid and print
	 * 
	 *
	 * 
	 * @print String in new format normalTimeMinutes:normalTimeSeconds - period
	 */

	public String displayNewFormat() {

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(minutes.getLast());
        stringBuilder.append(":");
        stringBuilder.append(seconds.getLast());
        stringBuilder.append(" - ");
        stringBuilder.append(periods.getLast());
        String newFormat = stringBuilder.toString();

        if (isNewFormatValid(newFormat)) {
			System.out.println(newFormat);

			return newFormat;
		} else {

			System.out.println(INVALID);

			return INVALID.toString();
		}

	}

	/**
	 * Check if contains invalid format
	 */
	private boolean isNewFormatValid(String format) {

		return !format.contains(INVALID.toString());
	}

}