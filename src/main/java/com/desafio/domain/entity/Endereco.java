package com.desafio.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.FetchMode;
import org.hibernate.annotations.Fetch;

import javax.persistence.*;
import java.util.UUID;

@Data
@Builder
@Entity
@Table(name = "endereco")
@NoArgsConstructor
@AllArgsConstructor
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, unique = true, nullable = false)
    private UUID id;
    @Column(name = "cep", nullable = false, length = 8)
    private String cep;
    @Column(nullable = false, length = 100)
    private String logradouro;
    @Column(nullable = false, length = 100)
    private String cidade;
    @Column(nullable = false, length = 2)
    private String estado;
    private String complemento;
    @Column(nullable = false)
    private Integer numero;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "pessoa_id", nullable = false)
    @JsonIgnore
    @ToString.Exclude
    private Pessoa pessoa;
}
