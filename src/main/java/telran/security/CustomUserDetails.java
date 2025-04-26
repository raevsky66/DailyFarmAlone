package telran.security;

import java.util.Collection;
import java.util.UUID;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import lombok.Getter;
@Getter
public class CustomUserDetails extends User {

	private static final long serialVersionUID = 6939266392492741393L;
	@Getter
	private final UUID id;
	public CustomUserDetails(String username, String password, Collection<? extends GrantedAuthority> authorities,
			UUID id) {
		super(username, password, authorities);
		this.id = id;
	}
}
