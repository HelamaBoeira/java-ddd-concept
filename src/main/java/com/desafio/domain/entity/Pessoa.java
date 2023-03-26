package com.desafio.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
@Entity
@Table(name = "pessoa")
@NoArgsConstructor
@AllArgsConstructor
public class Pessoa {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, unique = true, nullable = false)
    private UUID id;
    @Column(nullable = false, length = 150)
    private String nome;

    @Column(nullable = false, length = 20)
    private String sexo;

    @Column(nullable = false)
    private LocalDate dataNasc;

    @Column(nullable = false)
    private Integer idade;

    @OneToOne(fetch = FetchType.LAZY,
            cascade =  CascadeType.ALL,
            mappedBy = "pessoa",
            orphanRemoval = true)
    private Endereco endereco;
}
