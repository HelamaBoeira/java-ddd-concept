package com.desafio.application.dtos;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
public class PessoaDTO {
    private UUID id;
    private String nome;
    private String sexo;
    private LocalDate dataNasc;
    private Integer idade;

    private EnderecoDTO endereco;
}
