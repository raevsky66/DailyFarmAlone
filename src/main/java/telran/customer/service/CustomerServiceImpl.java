package telran.customer.service;

import java.time.LocalDateTime;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import telran.common.entity.Credential;
import telran.customer.dao.CustomerCredentialRepository;
import telran.customer.dao.CustomerRepository;
import telran.customer.dto.CustomerDto;
import telran.customer.dto.CustomerRegisterDto;
import telran.customer.entity.Customer;


@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerCredentialRepository customerCredentialRepository;
	 private final CustomerRepository customerRepository;
	 private final ModelMapper modelMapper;

	 @Transactional
	public CustomerDto registerCustomer(CustomerRegisterDto customerRegisterDto) {
			Customer customer = modelMapper.map(customerRegisterDto, Customer.class);
			customer.setId(UUID.randomUUID()); 
	        Credential credential = new Credential();
	        credential.setPassword(customerRegisterDto.getPassword());
	        credential.setIsVerified(false);
	        credential.setCreatedAt(LocalDateTime.now());

	        customer.setCredential(credential);
	        Customer savedCustomer = customerRepository.save(customer);
	        credential.setUserId(savedCustomer.getId());

	        customerCredentialRepository.save(credential);
	        CustomerDto customerDto = modelMapper.map(savedCustomer, CustomerDto.class);
	        customerDto.setPassword(credential.getPassword());
	        customerDto.setIsVerified(credential.getIsVerified());
	        return customerDto;
	        
	}

}
