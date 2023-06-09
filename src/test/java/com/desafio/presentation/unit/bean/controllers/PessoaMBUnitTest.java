package com.desafio.presentation.unit.bean.controllers;

import com.desafio.GenerateUtils;
import com.desafio.application.apps.interfaces.PessoaApplication;
import com.desafio.application.dtos.EnderecoDTO;
import com.desafio.application.dtos.PessoaDTO;
import com.desafio.presentation.bean.controllers.PessoaMB;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.extension.ExtendWith;


import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PessoaMBUnitTest extends BaseBeanTest {


    @Mock
    private PessoaApplication application;

    @InjectMocks
    private PessoaMB pessoaMB;

    private PessoaDTO pessoaDTO;
    private List<PessoaDTO> pessoas;

    @BeforeEach
    @Override
    public void setUp() {
        super.setUp();
        pessoaDTO = new PessoaDTO();
        pessoaDTO.setId(UUID.randomUUID());
        pessoaDTO.setNome("Teste");
        pessoaDTO.setEndereco(new EnderecoDTO());
        pessoaDTO.getEndereco().setCep("12345-678");

        pessoas = new ArrayList<>();
        pessoas.add(pessoaDTO);
    }

    @Test
    public void testCarregar() {
        when(application.getAll()).thenReturn(pessoas);

        pessoaMB.carregar();

        assertEquals(pessoas, pessoaMB.getPessoas());

        verify(application, times(1)).getAll();
    }

    @Test
    public void testSalvarCreate() throws IOException {
        var pessoaDTO = GenerateUtils.pessoaDTO();
        var pessoaDTOCadastrada = GenerateUtils.pessoaDTO("72ac75bc-55d0-42f4-a5ab-ad421dc97cd5");
        when(application.create(pessoaDTO)).thenReturn(pessoaDTOCadastrada);

        pessoaMB.setPessoa(pessoaDTO);
        pessoaMB.setDataNascimento("25/05/1999");

        pessoaMB.salvar();

        verify(application, times(1)).create(pessoaDTO);
    }
}

