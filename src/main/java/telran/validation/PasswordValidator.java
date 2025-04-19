package telran.validation;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import telran.customer.dto.CustomerRegisterDto;

public class PasswordValidator implements ConstraintValidator<EmailCheck,CustomerRegisterDto> {
	  private static final String PASSWORD_PATTERN =
	            "^(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{6,}$";



	@Override
	public boolean isValid(CustomerRegisterDto value, ConstraintValidatorContext context) {
		 if (value.getPassword() == null || !value.getPassword().matches(PASSWORD_PATTERN)) {
		        context.disableDefaultConstraintViolation();
		        context.buildConstraintViolationWithTemplate("Password must contain at least one uppercase letter, one digit, and one special character and be at least 6 characters long")
		               .addPropertyNode("password")
		               .addConstraintViolation();
		        return false;
		    }
		    return true;
	    }

	
}
