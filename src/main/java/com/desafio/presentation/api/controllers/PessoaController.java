package com.desafio.presentation.api.controllers;

import com.desafio.application.apps.interfaces.PessoaApplication;
import com.desafio.application.dtos.PessoaDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/pessoa")
public class PessoaController {
    private final PessoaApplication app;

    public PessoaController(PessoaApplication app) {
        this.app = app;
    }

    @GetMapping
    public List<PessoaDTO> getAll(){
        return app.getAll();
    }

    @GetMapping("/{id}")
    public PessoaDTO getById(@PathVariable UUID id){
        return app.getById(id);
    }

    @PostMapping
    public PessoaDTO create(@RequestBody PessoaDTO pessoaDTO){
        return app.create(pessoaDTO);
    }

    @PutMapping
    public PessoaDTO update(@RequestBody PessoaDTO pessoaDTO){
        return app.update(pessoaDTO);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id){
        app.delete(id);
    }
}
