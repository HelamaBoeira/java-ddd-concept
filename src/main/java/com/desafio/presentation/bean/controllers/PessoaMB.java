package com.desafio.presentation.bean.controllers;

import com.desafio.application.apps.interfaces.PessoaApplication;
import com.desafio.application.dtos.PessoaDTO;
import com.desafio.presentation.bean.utils.DateUtils;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Named;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Named(value = "pessoaMB")
@ViewScoped
public class PessoaMB extends BaseMB{

    @Getter
    @Setter
    private PessoaDTO pessoa = new PessoaDTO();
    @Getter
    @Setter
    private String dataNascimento;
    @Getter
    private List<PessoaDTO> pessoas;

    @Autowired
    private PessoaApplication application;

    @PostConstruct
    public void carregar(){
        pessoas = application.getAll();
    }

    public void novoCadastro() throws IOException {

        limpar();
        chamarForm("/cadastro.xhtml");
    }


    public void salvar() throws IOException {
        try {
            pessoa.getEndereco().setCep(pessoa.getEndereco().getCep().replaceAll("[^a-zA-Z0-9]", ""));
            pessoa.setDataNasc(DateUtils.stringToLocalDate(dataNascimento));
            pessoa.setIdade(DateUtils.diffInYears(pessoa.getDataNasc(), LocalDate.now()));
            if(pessoa.getId() == null){
                application.create(pessoa);

            }else {
                application.update(pessoa);
            }
            limpar();
            carregar();
            chamarForm("/index.xhtml");
        } catch (Exception e){
            chamarMensagemErro("Um erro inesperado aconteceu");
        }
    }

    public void editar(PessoaDTO pessoa) throws IOException {
        this.pessoa = pessoa;
        this.dataNascimento = DateUtils.localDateToString(pessoa.getDataNasc());
        chamarForm("/cadastro.xhtml");

    }

    public void confirmarExclusao(UUID id){
        application.delete(id);
        carregar();
    }

    private void limpar(){
        pessoa = new PessoaDTO();
        dataNascimento = "";
    }
}
