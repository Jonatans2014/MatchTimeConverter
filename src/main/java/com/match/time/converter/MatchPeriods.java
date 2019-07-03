package com.match.time.converter;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * Look up table for match periods
 * @return MatchPeriods
 */
public enum MatchPeriods {

	PRE_MATCH("[PM]"), FIRST_HALF("[H1]"), HALF_TIME("[HT]"), SECOND_HALF("[H2]"), FULL_TIME("[FT]"),
	INVALID("INVALID");

	private static final Map<String, MatchPeriods> PERIOD = new HashMap<>();

	static {
		for (MatchPeriods e : values()) {
			PERIOD.put(e.format, e);
		}
	}

	public final String format;

	 MatchPeriods(String format) {
		this.format = format;
	}

	public static MatchPeriods newFormatOfPeriod(String label) {
		if (PERIOD.containsKey(label)) {
			return PERIOD.get(label);
		}

		return INVALID;
	}
}
