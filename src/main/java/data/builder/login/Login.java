package data.builder.login;

import data.model.Credentials;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Login {

	private Credentials credentials;


}

