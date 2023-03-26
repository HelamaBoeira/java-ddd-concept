package com.desafio.application.mappers;

import com.desafio.application.dtos.EnderecoDTO;
import com.desafio.application.dtos.PessoaDTO;
import com.desafio.domain.entity.Endereco;
import com.desafio.domain.entity.Pessoa;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class PessoaMapper {

    public abstract PessoaDTO pessoaToPessoaDTO(Pessoa pessoa);
    public  abstract Pessoa pessoaDTOToPessoa(PessoaDTO pessoaDTO);
    public abstract EnderecoDTO enderecoToEnderecoDTO(Endereco endereco);
    public abstract Endereco enderedoDTOToEndereco(EnderecoDTO enderecoDTO);
    public abstract List<PessoaDTO> pessoaToPessoaDTOList(List<Pessoa> pessoas);
}
