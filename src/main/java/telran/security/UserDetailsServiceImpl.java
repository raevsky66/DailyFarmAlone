package telran.security;

import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import telran.common.entity.Credential;
import telran.customer.dao.CustomerCredentialRepository;
import telran.customer.dao.CustomerRepository;
import telran.customer.entity.Customer;
import telran.security.login.LoginRoleContext;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

	private final CustomerRepository customerRepo;
	//private final FarmerCredentialRepository farmerCredentialRepo;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
	    String role = LoginRoleContext.getRole();

	    if ("CUSTOMER".equals(role)) {
	              
	        Customer customer = customerRepo.findByEmail(email) 
	        		.orElseThrow(() -> new UsernameNotFoundException("Customer not found: " + email));
	       
	        Credential credential = customer.getCredential();
			if (credential == null) {
				throw new UsernameNotFoundException("Credential not found for customer: " + email);
			}
	        
	        return new CustomUserDetails(
	                customer.getEmail(),
	                credential.getPassword(),
	                List.of(new SimpleGrantedAuthority("ROLE_CUSTOMER")),
	                customer.getId()
	        );
	    }

//	    if ("FARMER".equals(role)) {
//	        FarmerCredential credential = farmerCredentialRepo.findByFarmerEmail(email) 
//	        		.orElseThrow(() -> new UsernameNotFoundException("Farmer not found: " + email));
//	        Farmer farmer = credential.getFarmer();
//	        return new CustomUserDetails(
//	                farmer.getEmail(),
//	                credential.getHashedPassword(),
//	                List.of(new SimpleGrantedAuthority("ROLE_FARMER")),
//	                farmer.getId()
//	        );
//	    }

	    throw new UsernameNotFoundException("Unknown login role or user not found: " + email);
	}
}
