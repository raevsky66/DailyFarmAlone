package telran.customer.controller;

import static telran.api.ApiConstants.CUSTOMER_REGISTER;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import telran.customer.dto.CustomerDto;
import telran.customer.dto.CustomerRegisterDto;
import telran.customer.service.CustomerService;

@Tag(name = "Customer API", description = "Methods for customers")
@RestController
@RequiredArgsConstructor
public class CustomerController {
	private final CustomerService customerService;

	@PostMapping(CUSTOMER_REGISTER)
	public ResponseEntity<CustomerDto> registerCustomer(CustomerRegisterDto customerRegisterDto) {
		System.out.println("Registering customer: " + customerRegisterDto);
		System.out.println("UUID: " + UUID.randomUUID());
		CustomerDto response = customerService.registerCustomer(customerRegisterDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

//	@GetMapping(CUSTOMER_CURRENT)
//	public ResponseEntity<CustomerDto> getCurrentCustomer(Principal principal) {
//		return customerService.getUserByEmail(principal.getName());
//	}
//
//	@PutMapping(CUSTOMER_EDIT)
//	public ResponseEntity<CustomerDto> updateCustomer(@Valid @RequestBody CustomerEditDto customerEditDto,
//			@AuthenticationPrincipal CustomUserDetails user) {
//		return customerService.updateCustomer(user.getId(), customerEditDto);
//	}
//
//	@DeleteMapping(CUSTOMER_REMOVE)
//	public ResponseEntity<String> removeCustomer(@AuthenticationPrincipal CustomUserDetails user) {
//		return customerService.removeCustomerById(user.getId());
//	}

}