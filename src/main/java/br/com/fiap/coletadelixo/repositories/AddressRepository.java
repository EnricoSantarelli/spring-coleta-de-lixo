package br.com.fiap.coletadelixo.repositories;

import br.com.fiap.coletadelixo.models.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
}
