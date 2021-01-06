package com.coutomariel.orangeapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDTO {
    private Long id;
    @NotEmpty
    @Size(max = 100)

    private String nome;
    @CPF
    @Size(max=50)
    private String cpf;
    @Email
    @Size(max=50)
    private String email;
    @NotEmpty
    private String dataNascimento;
}
