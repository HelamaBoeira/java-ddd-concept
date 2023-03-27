package com.desafio;

import com.desafio.application.dtos.EnderecoDTO;
import com.desafio.application.dtos.PessoaDTO;
import com.desafio.domain.entity.Endereco;
import com.desafio.domain.entity.Pessoa;

import java.time.LocalDate;
import java.util.UUID;

public class GenerateUtils {

    public static Endereco endereco(){
        return Endereco.builder()
                .cep("58059117")
                .cidade("João Pessoa")
                .estado("PB")
                .numero(45)
                .logradouro("Rua 505")
                .complemento("Proximo a praça vermelha")
                .build();
    }

    public static Pessoa pessoa(String uuid){
        return Pessoa.builder()
                .id(UUID.fromString(uuid))
                .nome("Pessoa Original")
                .sexo("Masculino")
                .dataNasc(LocalDate.parse("1999-05-25"))
                .endereco(endereco())
                .idade(23)
                .build();
    }

    public static Pessoa pessoa(){
        return Pessoa.builder()
                .nome("Pessoa Original")
                .sexo("Masculino")
                .dataNasc(LocalDate.parse("1999-05-25"))
                .endereco(endereco())
                .idade(23)
                .build();
    }

    public static EnderecoDTO enderecoDTO(){
        return EnderecoDTO.builder()
                .cep("58059117")
                .cidade("João Pessoa")
                .estado("PB")
                .numero(45)
                .logradouro("Rua 505")
                .complemento("Proximo a praça vermelha")
                .build();
    }

    public static PessoaDTO pessoaDTO(String uuid){
        return PessoaDTO.builder()
                .id(UUID.fromString(uuid))
                .nome("Pessoa Original")
                .sexo("Masculino")
                .dataNasc(LocalDate.parse("1999-05-25"))
                .endereco(enderecoDTO())
                .idade(23)
                .build();
    }

    public static PessoaDTO pessoaDTO(){
        return PessoaDTO.builder()
                .nome("Pessoa Original")
                .sexo("Masculino")
                .dataNasc(LocalDate.parse("1999-05-25"))
                .endereco(enderecoDTO())
                .idade(23)
                .build();
    }
}

