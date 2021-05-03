package plugins.allure.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Result {

	String fileName;

	String contentBase64;

}
