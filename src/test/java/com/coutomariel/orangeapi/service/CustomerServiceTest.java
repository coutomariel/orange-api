package com.coutomariel.orangeapi.service;

import com.coutomariel.orangeapi.dto.CustomerDTO;
import com.coutomariel.orangeapi.exception.CustomerWithCpfAlreadyRegisteredException;
import com.coutomariel.orangeapi.exception.CustomerWithEmailAlreadyRegisteredException;
import com.coutomariel.orangeapi.model.Customer;
import com.coutomariel.orangeapi.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.coutomariel.orangeapi.utils.CustomerUtils.createFakeDTO;
import static com.coutomariel.orangeapi.utils.CustomerUtils.createFakeEntity;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;
    @InjectMocks
    private CustomerService customerService;

    final String EMAIL_EXCEPTION_MESSAGE = "Já existe um cadastro com este email";
    final String CPF_EXCEPTION_MESSAGE = "Já existe um cadastro com este cpf";

    @Test
    void testGivenCustomerDTOThenReturnSaved(){
        Customer customerToSave = createFakeEntity();
        CustomerDTO dto = createFakeDTO();
        when(customerRepository.save(any(Customer.class)))
                .thenReturn(customerToSave);
        CustomerDTO savedCustomer = customerService.insert(dto);
        assertEquals(dto, savedCustomer);
    }

    @Test
    void testGivenCustomerEmailDuplicateDTOThenReturnSaved(){
        Customer customerToSave = createFakeEntity();
        CustomerDTO dto = createFakeDTO();

        when(customerRepository.findByEmail(customerToSave.getEmail()))
                .thenReturn(customerToSave);

        Exception exception = assertThrows(
                CustomerWithEmailAlreadyRegisteredException.class,
                () -> customerService.insert(dto));

        assertEquals(exception.getMessage(), EMAIL_EXCEPTION_MESSAGE);
    }

    @Test
    void testGivenCustomerCpfDuplicateDTOThenReturnSaved(){
        Customer customerToSave = createFakeEntity();
        CustomerDTO dto = createFakeDTO();

        when(customerRepository.findByCpf(customerToSave.getCpf()))
                .thenReturn(customerToSave);

        Exception exception = assertThrows(
                CustomerWithCpfAlreadyRegisteredException.class,
                () -> customerService.insert(dto));
        assertEquals(exception.getMessage(), CPF_EXCEPTION_MESSAGE);

    }

}
