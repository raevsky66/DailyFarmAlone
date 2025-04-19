package telran.customer.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import telran.validation.EmailCheck;

@Data
@EmailCheck
public class CustomerRegisterDto {
	@NotEmpty(message = "First name is required")
	private String firstName;

	@Size(min = 3, max = 20, message = "Last name must be between 3 and 20 characters long")
	private String lastName;

	@Email(message = "Invalid email format")
	private String email;

	@Pattern(regexp = "^\\+?[0-9]{10,15}$", message = "Phone number must be between 10 and 15 digits long")
	private String phone;

	@Size(min = 4, message = "Password must be at least 4 characters long")
	private String password;
}
