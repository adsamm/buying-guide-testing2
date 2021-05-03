package plugins.allure.builder;

import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import plugins.allure.dto.Result;
import plugins.allure.dto.Results;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class Report {

	@Builder.Default
	private final String location = "allure-results";

	public Results generate() throws IOException {

		Results.ResultsBuilder results = Results.builder();

		log.info("Generating allure report");

		log.info("Reading files");

		List<File> files = Files.walk(Paths.get(this.location))
				.filter(Files::isRegularFile)
				.map(Path::toFile)
				.peek(f -> log.info(f.getName()))
				.collect(Collectors.toList());

		for (File file : files) {
			String base64 = Base64.getEncoder().encodeToString(FileUtils.readFileToByteArray(file));
			results.result(new Result(file.getName(), base64));
		}

		return results.build();
	}

}
