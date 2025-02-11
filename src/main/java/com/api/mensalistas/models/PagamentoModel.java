package com.api.mensalistas.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "TB_PAGAMENTOS")
public class PagamentoModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "cod_pagamento")
    private UUID id;
    @Column(columnDefinition = "SMALLINT")
    private short ano;
    @Column(columnDefinition = "SMALLINT")
    private short mes;
    @Column(length = 15)
    private float valor;
    @ManyToOne
    @JoinColumn(name = "cod_jogador")
    private JogadorModel jogador;

    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }
    public short getAno() {
        return ano;
    }
    public void setAno(short ano) {
        this.ano = ano;
    }
    public short getMes() {
        return mes;
    }
    public void setMes(short mes) {
        this.mes = mes;
    }
    public float getValor() {
        return valor;
    }
    public void setValor(float valor) {
        this.valor = valor;
    }
    public JogadorModel getJogador() {
        return jogador;
    }
    public void setJogador(JogadorModel jogador) {
        this.jogador = jogador;
    }

}