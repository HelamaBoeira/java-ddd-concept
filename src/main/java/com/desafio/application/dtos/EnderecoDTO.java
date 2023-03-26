package com.desafio.application.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EnderecoDTO {
    private UUID id;
    private String cep;
    private String logradouro;
    private String cidade;
    private String estado;
    private String complemento;
    private Integer numero;
}
