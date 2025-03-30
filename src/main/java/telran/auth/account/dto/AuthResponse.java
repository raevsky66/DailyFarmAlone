package telran.auth.account.dto;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AuthResponse {
	private String id;
	private String email;
	private String accessToken;
//	private String refreshToken;

}
