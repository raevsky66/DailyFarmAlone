package telran.customer.dto;

import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class CustomerLoginDto {
	String email;
	String password;
}
