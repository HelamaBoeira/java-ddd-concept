package com.desafio.application.dtos;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;
import java.time.Period;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PessoaDTO {
    private UUID id;
    private String nome;
    private String sexo;
    private LocalDate dataNasc;
    private Integer idade;

    private EnderecoDTO endereco = new EnderecoDTO();

}
