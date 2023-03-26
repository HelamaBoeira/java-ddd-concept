package com.desafio;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.support.TransactionTemplate;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.IOException;

import java.util.Objects;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
public class AbstractIntegrationTests {

    @PersistenceContext
    protected EntityManager entityManager;

    @Autowired
    protected ObjectMapper objectMapper;

    @Autowired
    private TransactionTemplate writeTransactionTemplate;


    @Autowired
    protected MockMvc mockMvc;

    protected String readJsonFile(String path) throws IOException {
        return new String(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream(path)).readAllBytes());
    }

    protected final void doInWriteTransaction(final Runnable runnable) {
        writeTransactionTemplate.execute(s -> {
            runnable.run();
            return null;
        });
    }
}
