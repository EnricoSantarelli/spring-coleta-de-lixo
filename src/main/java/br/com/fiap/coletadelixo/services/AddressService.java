package br.com.fiap.coletadelixo.services;

import br.com.fiap.coletadelixo.models.Address;
import br.com.fiap.coletadelixo.repositories.AddressRepository;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    public Address createAddress(Address address) {
        return addressRepository.save(address);
    }

    public Address updateAddress(Address address) {
        Optional<Address> addressOptional = addressRepository.findById(address.getId());

        if (addressOptional.isPresent()) {
            return addressRepository.save(address);
        } else {
            throw new RuntimeException("Endereço não encontrado");
        }
    }

    public Address getAddressById(Long id) {
        Optional<Address> addressOptional = addressRepository.findById(id);

        if (addressOptional.isPresent()) {
            return addressOptional.get();
        } else {
            throw new RuntimeException("Endereço não encontrado");
        }
    }

    public void deleteAddress(Long id) {
        Optional<Address> addressOptional = addressRepository.findById(id);

        if (addressOptional.isPresent()) {
            addressRepository.delete(addressOptional.get());
        } else {
            throw new RuntimeException("Endereço não encontrado");
        }
    }
}
