package com.coutomariel.orangeapi.utils;

import com.coutomariel.orangeapi.dto.CustomerDTO;
import com.coutomariel.orangeapi.model.Customer;

import java.time.LocalDate;

public class CustomerUtils {
    private static final Long ID = 1l;
    private static final String NOME = "João das Neves";
    private static final String EMAIL = "jneves@email.com";
    private static final String CPF = "412.878.910-04";
    public static final LocalDate DATA_NASCIMENTO = LocalDate.of(2010,10,1);

    public static CustomerDTO createFakeDTO(){
        return CustomerDTO.builder()
                .id(ID)
                .nome(NOME)
                .email(EMAIL)
                .cpf(CPF)
                .dataNascimento(DATA_NASCIMENTO.toString())
                .build();
    }

    public static Customer createFakeEntity(){
        return Customer.builder()
                .id(ID)
                .nome(NOME)
                .email(EMAIL)
                .cpf(CPF)
                .dataNascimento(DATA_NASCIMENTO)
                .build();
    }

}
