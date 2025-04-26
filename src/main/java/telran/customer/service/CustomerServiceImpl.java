package telran.customer.service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import telran.common.entity.Credential;
import telran.customer.dao.CustomerCredentialRepository;
import telran.customer.dao.CustomerRepository;
import telran.customer.dto.CustomerDto;
import telran.customer.dto.CustomerLoginDto;
import telran.customer.dto.CustomerRegisterDto;
import telran.customer.dto.TokenResponseDto;
import telran.customer.entity.Customer;
import telran.security.JWTConverter;
import telran.validation.CustomerAlreadyExistsException;
import telran.validation.CustomerNotFoundException;
import telran.validation.InvalidTokenException;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

	private final CustomerCredentialRepository customerCredentialRepository;
	private final CustomerRepository customerRepository;
	private final ModelMapper modelMapper;
	private final JWTConverter jwtConverter;
	private final PasswordEncoder passwordEncoder;

	@Transactional
	public CustomerDto registerCustomer(CustomerRegisterDto customerRegisterDto) {
		if (customerRepository.existsByEmail(customerRegisterDto.getEmail())) {
			throw new CustomerAlreadyExistsException("Customer already exists");
		}
		Customer customer = modelMapper.map(customerRegisterDto, Customer.class);
		customer.setId(UUID.randomUUID());
		Credential credential = new Credential();
		credential.setPassword(passwordEncoder.encode(customerRegisterDto.getPassword()));
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

	@Override
	public ResponseEntity<TokenResponseDto> loginCustomer(@Valid CustomerLoginDto loginRequestDto) {
		String email = loginRequestDto.getEmail();
	    String password = loginRequestDto.getPassword();

	    Optional<Customer> customerOpt = customerRepository.findByEmail(email);
	    Customer customer = customerOpt.orElseThrow(() -> new CustomerNotFoundException("Customer not found with email: " + email));

		Credential credential = customer.getCredential();

	    if (!passwordEncoder.matches(password, credential.getPassword())) {
	        throw new BadCredentialsException("Неверный пароль");
	    }

	 	String accessToken = jwtConverter.generateAccessToken(email);
	    String refreshToken = jwtConverter.generateRefreshToken(email);

	    TokenResponseDto tokens = new TokenResponseDto(accessToken, refreshToken);

	    return ResponseEntity.ok(tokens);

	}

	@Override
	public TokenResponseDto refreshToken(String refreshToken) {

		  if (!jwtConverter.validateToken(refreshToken)) {
		        new InvalidTokenException("Неверный или просроченный refresh-токен");
		    }

		    String email = jwtConverter.getUsernameFromToken(refreshToken);

		    String newAccessToken = jwtConverter.generateAccessToken(email);

		    return new TokenResponseDto(newAccessToken, refreshToken);
	}

}
