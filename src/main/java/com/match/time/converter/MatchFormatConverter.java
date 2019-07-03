package com.match.time.converter;

import java.util.List;

public class MatchFormatConverter {
	public static void main(String[] args) {

		String filePath = "default_input.txt";
		if (args.length > 0)
			filePath = args[0];
		List<String> get = ReadFilesUtils.readLineByLine(filePath);

		if (get == null) {
			System.out.println(MatchPeriods.INVALID);
		} else {
			TimeConverter footballParser = new TimeConverter(get);
			footballParser.parseMatch();
		}

	}
}
