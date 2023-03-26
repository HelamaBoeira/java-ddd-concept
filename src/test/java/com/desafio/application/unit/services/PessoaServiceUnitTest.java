package com.desafio.application.unit.services;


import com.desafio.GenerateUtils;
import com.desafio.application.services.PessoaServiceImpl;
import com.desafio.domain.entity.Pessoa;
import com.desafio.infra.repositories.PessoaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PessoaServiceUnitTest {
    @Mock
    private PessoaRepository repository;
    @InjectMocks
    private PessoaServiceImpl pessoaService;

    @Test
    public void shouldCreatePessoaSuccess(){
        String uuid = "39f88283-8b68-491e-a1c5-099b96413ec1";
        Pessoa pessoa = GenerateUtils.pessoa();

        when(repository.save(pessoa)).thenReturn(GenerateUtils.pessoa(uuid));

        Pessoa result = pessoaService.create(pessoa);

        assertEquals("39f88283-8b68-491e-a1c5-099b96413ec1", result.getId().toString());
    }
}
