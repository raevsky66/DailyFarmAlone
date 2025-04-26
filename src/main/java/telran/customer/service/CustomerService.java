package telran.customer.service;

import org.springframework.http.ResponseEntity;

import jakarta.validation.Valid;
import telran.customer.dto.CustomerDto;
import telran.customer.dto.CustomerLoginDto;
import telran.customer.dto.CustomerRegisterDto;
import telran.customer.dto.TokenResponseDto;

public interface CustomerService {


	CustomerDto registerCustomer(CustomerRegisterDto customerRegisterDto);

	ResponseEntity<TokenResponseDto> loginCustomer(@Valid CustomerLoginDto loginRequestDto);
	
	TokenResponseDto refreshToken(String refreshToken);
}
