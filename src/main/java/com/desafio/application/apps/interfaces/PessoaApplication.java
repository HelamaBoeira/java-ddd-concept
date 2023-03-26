package com.desafio.application.apps.interfaces;

import com.desafio.application.dtos.PessoaDTO;

import java.util.List;
import java.util.UUID;

public interface PessoaApplication {

    List<PessoaDTO> getAll();

    PessoaDTO getById(UUID uuid);

    PessoaDTO create(PessoaDTO pessoaDTO);

    PessoaDTO update(PessoaDTO pessoaDTO);

    void delete(UUID id);
}
