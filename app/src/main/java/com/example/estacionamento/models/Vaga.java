package com.example.estacionamento.models;

public class Vaga {
    private int id_vaga, numero_vaga, mensalidade;

    public Vaga() {
    }

    public Vaga(int numero_vaga, int mensalidade) {
        this.numero_vaga = numero_vaga;
        this.mensalidade = mensalidade;
    }

    public int getId_vaga() {
        return id_vaga;
    }

    public void setId_vaga(int id_vaga) {
        this.id_vaga = id_vaga;
    }

    public int getNumero_vaga() {
        return numero_vaga;
    }

    public void setNumero_vaga(int numero_vaga) {
        this.numero_vaga = numero_vaga;
    }

    public int getMensalidade() {
        return mensalidade;
    }

    public void setMensalidade(int mensalidade) {
        this.mensalidade = mensalidade;
    }
}
