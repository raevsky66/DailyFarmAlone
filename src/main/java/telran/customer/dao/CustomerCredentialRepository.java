package telran.customer.dao;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import telran.common.entity.Credential;


@Repository
public interface CustomerCredentialRepository extends JpaRepository<Credential, UUID> {


	


}
