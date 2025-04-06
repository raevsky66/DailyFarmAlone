package telran.customer.service;

import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import telran.common.entity.Credential;
import telran.customer.dao.CustomerRepository;
import telran.customer.dto.CustomerDto;
import telran.customer.dto.CustomerRegisterDto;
import telran.customer.entity.Customer;


@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
	 private final CustomerRepository customerRepository;
	 private final ModelMapper modelMapper;
	    
	public CustomerDto registerCustomer(CustomerRegisterDto customerRegisterDto) {
			Customer customer = modelMapper.map(customerRegisterDto, Customer.class);
			customer.setId(UUID.randomUUID()); 
	        Credential credential = new Credential();
	     //   credential.setPassword(customerRegisterDto.getPassword());
	        credential.setIsVerified(false);

	        // Устанавливаем userId на основе customer.getId()
	        customer.setCredential(credential);

	        // Сохраняем customer, чтобы Hibernate сгенерировал ID
	        Customer savedCustomer = customerRepository.save(customer);

	        // Устанавливаем userId после генерации ID у Customer
	        credential.setUserId(savedCustomer.getId());

	        return modelMapper.map(savedCustomer, CustomerDto.class);
	}

}
