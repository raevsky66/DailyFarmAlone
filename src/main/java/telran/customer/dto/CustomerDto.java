package telran.customer.dto;

import lombok.Data;

@Data
public class CustomerDto {
	  private String id;
	    private String firstName;
	    private String lastName;
	    private String email;
	    private String phone;
	    private Boolean isVerified;
	    private String password;
}
