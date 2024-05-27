package com.ibm.www.bancoJaver.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.Objects;

@NoArgsConstructor
@Entity
@Data
@Table(name = "cliente")
public class Cliente{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "nome", nullable = false, length = 180)
    private String nome;

    @Column(name = "telefone", length = 11)
    private Long telefone;

    @Column(name = "correntista", nullable = false)
    private boolean correntista;

    @Column(name ="score_credito", nullable = false)
    private float score_credito;

    @Column(name = "saldo_cc", nullable = false)
    private float saldo_cc;

    public Cliente(Long id, String nome, Long telefone, boolean correntista, float score_credito, float saldo_cc) {
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
        this.correntista = correntista;
        this.score_credito = score_credito;
        this.saldo_cc = saldo_cc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return correntista == cliente.correntista && Float.compare(score_credito, cliente.score_credito) == 0 && Float.compare(saldo_cc, cliente.saldo_cc) == 0 && Objects.equals(id, cliente.id) && Objects.equals(nome, cliente.nome) && Objects.equals(telefone, cliente.telefone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, telefone, correntista, score_credito, saldo_cc);
    }

}

