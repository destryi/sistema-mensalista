package com.api.mensalistas.models;

import javax.persistence.*;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "TB_JOGADORES")
public class JogadorModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "cod_jogador")
    private UUID id;
    @Column(nullable = false, unique = true, length = 60)
    private String nome;
    @Column(nullable = false, unique = true, length = 60)
    private String email;
    @Column(nullable = false, length = 15)
    private String datanasc;
    // @OneToMany(mappedBy = "jogador", cascade = CascadeType.ALL)
    // private List<PagamentoModel> pagamentos;


    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }
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
    // public List<PagamentoModel> getPagamentos() {
    //     return pagamentos;
    // }
    // public void setPagamentos(List<PagamentoModel> pagamentos) {
    //     this.pagamentos = pagamentos;
    // }
}

