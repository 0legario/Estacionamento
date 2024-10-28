package com.example.estacionamento.models;

public class Proprietario {
    private int id_proprietario, fk_vaga;
    private String nome, cpf, email, senha;

    public Proprietario() {
    }

    public Proprietario(int fk_vaga, String nome, String cpf, String email, String senha) {
        this.fk_vaga = fk_vaga;
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.senha = senha;
    }

    public int getId_proprietario() {
        return id_proprietario;
    }

    public void setId_proprietario(int id_proprietario) {
        this.id_proprietario = id_proprietario;
    }

    public int getFk_vaga() {
        return fk_vaga;
    }

    public void setFk_vaga(int fk_vaga) {
        this.fk_vaga = fk_vaga;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
