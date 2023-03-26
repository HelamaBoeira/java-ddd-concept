package com.desafio.domain.services;

import com.desafio.domain.entity.Pessoa;

import java.util.List;
import java.util.UUID;

public interface PessoaService {

    List<Pessoa> getAll();

    Pessoa getById(UUID id);

    Pessoa create(Pessoa pessoa);

    Pessoa update(Pessoa pessoa);

    void delete(Pessoa pessoa);

}
