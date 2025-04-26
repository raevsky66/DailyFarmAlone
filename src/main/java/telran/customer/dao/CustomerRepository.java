package telran.customer.dao;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import telran.customer.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, UUID> {

	boolean existsByEmail(String email);
	Optional<Customer> findByEmail(String email);

}
