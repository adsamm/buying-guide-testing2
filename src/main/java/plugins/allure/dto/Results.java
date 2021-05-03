package plugins.allure.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Singular;

import java.util.List;

@Data
@Builder
public class Results {

	@Singular
	List<Result> results;

}
