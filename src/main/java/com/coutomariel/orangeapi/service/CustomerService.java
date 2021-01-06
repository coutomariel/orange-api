package com.coutomariel.orangeapi.service;

import com.coutomariel.orangeapi.dto.CustomerDTO;
import com.coutomariel.orangeapi.exception.CustomerWithCpfAlreadyRegisteredException;
import com.coutomariel.orangeapi.exception.CustomerWithEmailAlreadyRegisteredException;
import com.coutomariel.orangeapi.model.Customer;
import com.coutomariel.orangeapi.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class CustomerService {
    private CustomerRepository customerRepository;

    private final String EMAIL_EXCEPTION_MESSAGE = "Já existe um cadastro com este email";
    private final String CPF_EXCEPTION_MESSAGE = "Já existe um cadastro com este cpf";

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public CustomerDTO insert(CustomerDTO customerDTO){
        boolean customerWithEmailAlreadyRegistered = customerRepository
                .findByEmail(customerDTO.getEmail()) != null;
        if(customerWithEmailAlreadyRegistered){
            throw new CustomerWithEmailAlreadyRegisteredException(EMAIL_EXCEPTION_MESSAGE);
        }

        boolean customerWithCpfAlreadyRegistered = customerRepository
                .findByCpf(customerDTO.getCpf()) != null;

        if(customerWithCpfAlreadyRegistered){
            throw new CustomerWithCpfAlreadyRegisteredException(CPF_EXCEPTION_MESSAGE);
        }
        Customer customerSaved = customerRepository.save(toModel(customerDTO));
        return toDTO(customerSaved);
    }

    private Customer toModel(CustomerDTO customerDTO){
        Customer customer = new Customer();
        customer.setNome(customerDTO.getNome());
        customer.setEmail(customerDTO.getEmail());
        customer.setCpf(customerDTO.getCpf());
        customer.setDataNascimento(LocalDate.parse(customerDTO.getDataNascimento()));
        return customer;
    }

    private CustomerDTO toDTO(Customer customer){
        CustomerDTO dto = new CustomerDTO();
        dto.setId(customer.getId());
        dto.setNome(customer.getNome());
        dto.setEmail(customer.getEmail());
        dto.setCpf(customer.getCpf());
        dto.setDataNascimento(customer.getDataNascimento().toString());
        return dto;
    }
}
