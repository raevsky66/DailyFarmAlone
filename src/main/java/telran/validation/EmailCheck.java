package telran.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;



import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Constraint(validatedBy= PasswordValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)

public @interface EmailCheck {

    String message() default "Password must contain at least one uppercase letter, one digit, and one special character and be at least 6 characters long";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}