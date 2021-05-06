package plugins.allure;

import config.Config;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import plugins.allure.builder.Api;
import plugins.allure.builder.Report;
import plugins.allure.dto.Results;

@Slf4j
public class Allure {

	@SneakyThrows
	public static void main(String[] args) {
		Config config = new Config();

		Results report = new Report().generate();

		Api api = new Api(config);

		api.send(report);
		api.generate();
	}
}
