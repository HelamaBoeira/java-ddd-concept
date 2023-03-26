package com.desafio.application.services;

import com.desafio.domain.entity.Pessoa;
import com.desafio.domain.services.PessoaService;
import com.desafio.infra.repositories.PessoaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PessoaServiceImpl implements PessoaService {

    private final PessoaRepository repository;

    public PessoaServiceImpl(PessoaRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Pessoa> getAll() {return repository.findAll();}

    @Override
    public Pessoa getById(UUID uuid) {
        if(repository.existsById(uuid)){
            return repository.getReferenceById(uuid);

        }
       return null;
    }

    @Override
    public Pessoa create(Pessoa pessoa) {
        return save(pessoa);
    }

    @Override
    public Pessoa update(Pessoa pessoa) {
        if(!repository.existsById(pessoa.getId())){
            throw new IllegalArgumentException("Pessoa não cadastrada");
        }

        return save(pessoa);
    }

    @Override
    public void delete(Pessoa pessoa) {
        if(repository.existsById(pessoa.getId())){
            repository.delete(pessoa);
        }
    }

    private Pessoa save(Pessoa pessoa) {
        var endereco = pessoa.getEndereco();
        endereco.setPessoa(pessoa);
        return repository.save(pessoa);
    }

}
