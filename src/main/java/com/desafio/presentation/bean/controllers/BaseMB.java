package com.desafio.presentation.bean.controllers;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import java.io.IOException;

public abstract class BaseMB {
    protected void chamarForm(String path) throws IOException {
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        ec.redirect(ec.getRequestContextPath() + path);
    }

    protected void chamarMensagemErro(String message){
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                "Error", message));
    }
}
