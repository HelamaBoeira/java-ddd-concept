package com.desafio.presentation.bean.controllers;

import com.desafio.application.apps.interfaces.PessoaApplication;
import com.desafio.application.dtos.PessoaDTO;
import com.desafio.presentation.bean.utils.DateUtils;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.component.message.Message;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@Named(value = "pessoaMB")
@ViewScoped
public class PessoaMB {

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
    private List<PessoaDTO> carregar(){
        pessoas = application.getAll();
        return pessoas;
    }

    public void novoCadastro() throws IOException {
        limpar();
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Teste"));
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        ec.redirect(ec.getRequestContextPath() + "/cadastro.xhtml");
    }


    public String salvar(){
        pessoa.getEndereco().setCep(pessoa.getEndereco().getCep().replaceAll("[^a-zA-Z0-9]", ""));
        pessoa.setDataNasc(DateUtils.stringToLocalDate(dataNascimento));
        pessoa.setIdade(DateUtils.diffInYears(pessoa.getDataNasc(), LocalDate.now()));
        application.create(pessoa);
        limpar();
        return "deu certo";
    }

    public void editar(PessoaDTO pessoa){
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Teste"));
    }

    private void limpar(){
        pessoa = new PessoaDTO();
    }
}
