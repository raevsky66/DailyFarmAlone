package telran.customer.dto;

import lombok.Data;

@Data
public class CustomerRegisterDto {
	private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String password; 
}
