package plugins.allure.builder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import config.Config;
import lombok.extern.slf4j.Slf4j;
import plugins.allure.dto.Results;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Slf4j
public class Api {

	private static final String BASE_URI = "https://allure.cleevio.dev";

	private final HttpClient client = java.net.http.HttpClient.newHttpClient();

	private final Config config;

	public Api(Config config) {
		this.config = config;
	}

	public void send(Results results) throws IOException, InterruptedException {

		log.info("Sending report");

		ObjectMapper mapper = new ObjectMapper();
		mapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);

		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create(BASE_URI + "/send-results?project_id=" + config.get("allure.project")))
				.header("Content-Type", "application/json")
				.POST(HttpRequest.BodyPublishers.ofString(mapper.writeValueAsString(results)))
				.build();

		HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
		log.info(response.body());
	}

	public void generate() throws InterruptedException, IOException {
		log.info("[API] generate-report");

		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create(BASE_URI + "/generate-report?project_id=" + config.get("allure.project")))
				.GET()
				.build();

		HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
		log.info(response.body());
	}

}
