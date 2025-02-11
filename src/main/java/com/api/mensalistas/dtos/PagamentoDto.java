package com.api.mensalistas.dtos;

import javax.validation.constraints.NotNull;


public class PagamentoDto {

    private short ano;
    private short mes;

    @NotNull
    private float valor;

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

    @NotNull
    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }
}
