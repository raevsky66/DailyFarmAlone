package telran.customer.dao;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import telran.common.entity.Credential;
import telran.customer.entity.Customer;


@Repository
public interface CustomerCredentialRepository extends JpaRepository<Credential, UUID> {



}
