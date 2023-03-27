package com.desafio.presentation.integration.api;

import com.desafio.AbstractIntegrationTests;
import com.desafio.GenerateUtils;
import com.desafio.application.dtos.PessoaDTO;
import org.junit.jupiter.api.*;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.UUID;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class PessoaControllerIntegrationTests extends AbstractIntegrationTests {

    private UUID pessoaId;

    @BeforeEach
    public void init(){
        doInWriteTransaction(() -> {
            final var pessoa = GenerateUtils.pessoa();
            pessoa.getEndereco().setPessoa(pessoa);
            entityManager.persist(pessoa);
            pessoaId = pessoa.getId();
        });
    }

    @AfterEach
    public void down(){
        doInWriteTransaction(() -> {
            entityManager.createQuery("delete from Endereco")
                    .executeUpdate();
            entityManager.createQuery("delete from Pessoa")
                    .executeUpdate();
            entityManager.flush();
        });
    }

    @Test
    public void shouldCreatePessoaSuccessTest() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders
            .post("/api/pessoa")
            .content(readJsonFile("jsons/pessoa-dto.json"))
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").exists())
            .andExpect(jsonPath("$.nome").value("Pessoa Nova"));
    }

    @Test
    public void shouldGetAllPessoaSuccessTest() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/pessoa"))
                        .andExpect(jsonPath("$[0].id").exists())
                        .andExpect(jsonPath("$[0].nome").value("Pessoa Original"));
    }

    @Test
    public void shouldGetByIdPessoaSuccessTest() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/pessoa/" + pessoaId.toString()))
                        .andExpect(jsonPath("$.id").exists())
                        .andExpect(jsonPath("$.nome").value("Pessoa Original"));
    }

    @Test
    public void shouldUpdatePessoaSuccessTest() throws Exception {

        var result = mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/pessoa/" + pessoaId.toString()))
                    .andExpect(status().isOk())
                    .andReturn();

        var resultOjb = objectMapper.readValue(result.getResponse().getContentAsString(), PessoaDTO.class);
        resultOjb.setNome("Pessoa Alterada");
        resultOjb.getEndereco().setComplemento("Adicionado Complemento");
        resultOjb.getEndereco().setCidade("Natal");
        resultOjb.getEndereco().setEstado("RN");
        resultOjb.getEndereco().setNumero(990);

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/api/pessoa")
                        .content(objectMapper.writeValueAsString(resultOjb))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Pessoa Alterada"))
                .andExpect(jsonPath("$.endereco.cidade").value("Natal"))
                .andExpect(jsonPath("$.endereco.estado").value("RN"))
                .andExpect(jsonPath("$.endereco.complemento").value("Adicionado Complemento"))
                .andExpect(jsonPath("$.endereco.numero").value("990"));
    }

    @Test
    public void shouldDeletePessoaSuccessTest() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/api/pessoa/" + pessoaId.toString()))
                .andExpect(jsonPath("$").doesNotExist());

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/pessoa/" + pessoaId.toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").doesNotExist());
    }
}
