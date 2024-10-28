package com.example.estacionamento.models;

public class Veiculo {
    private int id_veiculo, mensalidade, ano, fk_proprietario;
    private String placa;

    public Veiculo() {
    }

    public Veiculo(int mensalidade, int ano, int fk_proprietario, String placa) {
        this.mensalidade = mensalidade;
        this.ano = ano;
        this.fk_proprietario = fk_proprietario;
        this.placa = placa;
    }

    public int getId_veiculo() {
        return id_veiculo;
    }

    public void setId_veiculo(int id_veiculo) {
        this.id_veiculo = id_veiculo;
    }

    public int getMensalidade() {
        return mensalidade;
    }

    public void setMensalidade(int mensalidade) {
        this.mensalidade = mensalidade;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public int getFk_proprietario() {
        return fk_proprietario;
    }

    public void setFk_proprietario(int fk_proprietario) {
        this.fk_proprietario = fk_proprietario;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }
}
