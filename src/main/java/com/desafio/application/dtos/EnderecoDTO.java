package com.desafio.application.dtos;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class EnderecoDTO {
    private UUID id;
    private String cep;
    private String logradouro;
    private String cidade;
    private String estado;
    private String complemento;
    private Integer numero;
}
