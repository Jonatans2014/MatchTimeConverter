package com.match.time.converter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * Read data from a file
 *
 */
public class ReadFilesUtils {

	public static List<String> readLineByLine(String filePath) {
		List<String> contentBuilder = new ArrayList<>();

		try (Stream<String> stream = Files.lines(Paths.get(filePath), StandardCharsets.UTF_8)) {
			stream.forEach(contentBuilder::add);

		} catch (IOException e) {
			e.printStackTrace();
		}
		return contentBuilder;
	}
}
