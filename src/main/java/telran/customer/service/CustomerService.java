package telran.customer.service;

import org.springframework.http.ResponseEntity;

import telran.customer.dto.CustomerDto;
import telran.customer.dto.CustomerRegisterDto;

public interface CustomerService {


	CustomerDto registerCustomer(CustomerRegisterDto customerRegisterDto);

}
