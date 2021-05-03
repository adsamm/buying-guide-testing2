package data.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Credentials {

	String email;

	String password;


}
