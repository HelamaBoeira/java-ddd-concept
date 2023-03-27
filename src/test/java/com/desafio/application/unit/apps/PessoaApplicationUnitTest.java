package com.desafio.application.unit.apps;

import com.desafio.GenerateUtils;
import com.desafio.application.apps.PessoaApplicationImpl;
import com.desafio.application.mappers.PessoaMapper;
import com.desafio.domain.services.PessoaService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PessoaApplicationUnitTest {
    @Mock
    private PessoaService service;
    @Mock
    private PessoaMapper mapper;
    @InjectMocks
    private PessoaApplicationImpl application;

    @Test
    public void shouldGetAllSuccess(){
        final var pessoas = List.of(GenerateUtils.pessoa("28dfdefa-0b4c-411d-88d5-cae704e408e4"));
        final var pessoasDTO = List.of(GenerateUtils.pessoaDTO("28dfdefa-0b4c-411d-88d5-cae704e408e4"));

        when(service.getAll()).thenReturn(pessoas);
        when(mapper.pessoaToPessoaDTOList(pessoas)).thenReturn(pessoasDTO);

        final var result = application.getAll();

        assertEquals(1, result.size());
        assertEquals("28dfdefa-0b4c-411d-88d5-cae704e408e4", result.get(0).getId().toString());
    }

    @Test
    public void shouldGetByIdSuccess(){
        final var id = UUID.fromString("28dfdefa-0b4c-411d-88d5-cae704e408e4");
        final var pessoa = GenerateUtils.pessoa("28dfdefa-0b4c-411d-88d5-cae704e408e4");
        final var pessoaDTO = GenerateUtils.pessoaDTO("28dfdefa-0b4c-411d-88d5-cae704e408e4");

        when(service.getById(id)).thenReturn(pessoa);
        when(mapper.pessoaToPessoaDTO(pessoa)).thenReturn(pessoaDTO);

        final var result = application.getById(id);

        assertEquals("28dfdefa-0b4c-411d-88d5-cae704e408e4", result.getId().toString());
    }

    @Test
    public void shouldCreateSuccess(){
        final var pessoaNova = GenerateUtils.pessoa();
        final var pessoaDTONova = GenerateUtils.pessoaDTO();
        final var pessoa = GenerateUtils.pessoa("28dfdefa-0b4c-411d-88d5-cae704e408e4");
        final var pessoaDTO = GenerateUtils.pessoaDTO("28dfdefa-0b4c-411d-88d5-cae704e408e4");

        when(service.create(pessoaNova)).thenReturn(pessoa);
        when(mapper.pessoaDTOToPessoa(pessoaDTONova)).thenReturn(pessoaNova);
        when(mapper.pessoaToPessoaDTO(pessoa)).thenReturn(pessoaDTO);

        final var result = application.create(pessoaDTONova);

        assertEquals("28dfdefa-0b4c-411d-88d5-cae704e408e4", result.getId().toString());
    }

    @Test
    public void shouldUpdateSuccess(){
        final var pessoa = GenerateUtils.pessoa("c29d35ce-4045-4a47-b269-5504d76a932e");
        final var pessoaDTO = GenerateUtils.pessoaDTO("c29d35ce-4045-4a47-b269-5504d76a932e");


        when(mapper.pessoaDTOToPessoa(pessoaDTO)).thenReturn(pessoa);
        when(service.update(pessoa)).thenReturn(pessoa);
        when(mapper.pessoaToPessoaDTO(pessoa)).thenReturn(pessoaDTO);

        application.update(pessoaDTO);

        verify(service, times(1)).update(pessoa);
    }

    @Test
    public void shouldDeleteSuccess(){
        final var id = UUID.fromString("28dfdefa-0b4c-411d-88d5-cae704e408e4");
        final var pessoa = GenerateUtils.pessoa("28dfdefa-0b4c-411d-88d5-cae704e408e4");


        when(service.getById(id)).thenReturn(pessoa);
        application.delete(id);

        verify(service, times(1)).delete(pessoa);
    }


}
