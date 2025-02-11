package com.api.mensalistas.dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class JogadorDto {


    @NotBlank
    @Size(max = 60)
    private String nome;
    @NotBlank
    @Size(max = 60)
    private String email;
    @NotBlank
    private String datanasc;

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getDatanasc() {
        return datanasc;
    }
    public void setDatanasc(String datanasc) {
        this.datanasc = datanasc;
    }
    
}
