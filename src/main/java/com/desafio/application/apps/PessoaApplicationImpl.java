package com.desafio.application.apps;

import com.desafio.application.apps.interfaces.PessoaApplication;
import com.desafio.application.dtos.PessoaDTO;
import com.desafio.application.mappers.PessoaMapper;
import com.desafio.domain.services.PessoaService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PessoaApplicationImpl implements PessoaApplication {

    private final PessoaService service;
    private final PessoaMapper mapper;

    public PessoaApplicationImpl(PessoaService service, PessoaMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @Override
    public List<PessoaDTO> getAll() {
        return mapper.pessoaToPessoaDTOList(service.getAll());
    }

    @Override
    public PessoaDTO getById(UUID id) {
        return mapper.pessoaToPessoaDTO(service.getById(id));
    }

    @Override
    public PessoaDTO create(PessoaDTO pessoa) {
        return mapper.pessoaToPessoaDTO(service.create(mapper.pessoaDTOToPessoa(pessoa)));
    }

    @Override
    public PessoaDTO update(PessoaDTO pessoa) {
        return mapper.pessoaToPessoaDTO(service.update(mapper.pessoaDTOToPessoa(pessoa)));
    }

    @Override
    public void delete(UUID id) {
        service.delete(service.getById(id));
    }
}
