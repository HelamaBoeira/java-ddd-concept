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

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PessoaServiceUnitTest {
    @Mock
    private PessoaRepository repository;
    @InjectMocks
    private PessoaServiceImpl pessoaService;

    @Test
    public void shouldCreateSuccess(){
        String uuid = "39f88283-8b68-491e-a1c5-099b96413ec1";
        Pessoa pessoa = GenerateUtils.pessoa();

        when(repository.save(pessoa)).thenReturn(GenerateUtils.pessoa(uuid));

        Pessoa result = pessoaService.create(pessoa);

        assertEquals("39f88283-8b68-491e-a1c5-099b96413ec1", result.getId().toString());
    }

    @Test
    public void shouldGetAllSuccess(){
        String uuid = "39f88283-8b68-491e-a1c5-099b96413ec1";
        Pessoa pessoa = GenerateUtils.pessoa(uuid);

        when(repository.findAll()).thenReturn(List.of(pessoa));

        var result = pessoaService.getAll();

        assertEquals(1, result.size());
        assertEquals("39f88283-8b68-491e-a1c5-099b96413ec1", result.get(0).getId().toString());
    }

    @Test
    public void shouldGetByIdSuccess(){
        UUID uuid = UUID.fromString("39f88283-8b68-491e-a1c5-099b96413ec1");
        Pessoa pessoa = GenerateUtils.pessoa(uuid.toString());

        when(repository.existsById(uuid)).thenReturn(true);
        when(repository.getReferenceById(uuid)).thenReturn(pessoa);

        var result = pessoaService.getById(uuid);

        assertEquals("39f88283-8b68-491e-a1c5-099b96413ec1", result.getId().toString());
    }

    @Test
    public void shouldGetByIdNotFound(){
        UUID uuid = UUID.fromString("39f88283-8b68-491e-a1c5-099b96413ec1");
        Pessoa pessoa = GenerateUtils.pessoa(uuid.toString());

        when(repository.existsById(uuid)).thenReturn(false);

        pessoaService.getById(uuid);

        verify(repository, times(0)).getReferenceById(uuid);
    }

    @Test
    public void shouldUpdatedSuccess(){
        UUID uuid = UUID.fromString("39f88283-8b68-491e-a1c5-099b96413ec1");
        Pessoa pessoa = GenerateUtils.pessoa(uuid.toString());

        when(repository.existsById(uuid)).thenReturn(true);


        pessoaService.update(pessoa);

        verify(repository, times(1)).save(pessoa);
    }

    @Test
    public void shouldUpdatedNotFound(){
        UUID uuid = UUID.fromString("39f88283-8b68-491e-a1c5-099b96413ec1");
        Pessoa pessoa = GenerateUtils.pessoa(uuid.toString());

        when(repository.existsById(uuid)).thenReturn(false);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            pessoaService.update(pessoa);
        });

        assertEquals("Pessoa não cadastrada", exception.getMessage());
    }

    @Test
    public void shouldDeleteSuccess(){
        UUID uuid = UUID.fromString("39f88283-8b68-491e-a1c5-099b96413ec1");
        Pessoa pessoa = GenerateUtils.pessoa(uuid.toString());

        when(repository.existsById(uuid)).thenReturn(true);

        pessoaService.delete(pessoa);

        verify(repository, times(1)).delete(pessoa);
    }

    @Test
    public void shouldNotDelete(){
        UUID uuid = UUID.fromString("39f88283-8b68-491e-a1c5-099b96413ec1");
        Pessoa pessoa = GenerateUtils.pessoa(uuid.toString());

        when(repository.existsById(uuid)).thenReturn(false);

        pessoaService.delete(pessoa);

        verify(repository, times(0)).delete(pessoa);
    }

}
