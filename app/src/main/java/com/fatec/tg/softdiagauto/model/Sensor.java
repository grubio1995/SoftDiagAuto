package com.fatec.tg.softdiagauto.model;

import java.util.ArrayList;

/**
 * Created by Gabriel Rubio on 29/10/2016.
 */

public class Sensor{

    private String nome;
    private String unidade;
    private ArrayList<Double> valores;
    private int iconDrawable;
    private int status;

    public Sensor(String nome, String unidade, int iconDrawable, int status) {
        this.nome = nome;
        this.unidade = unidade;
        this.iconDrawable = iconDrawable;
        this.status  = status;
        valores = new ArrayList<>();

        for(int i = 0; i < 20; i++){
            valores.add(0.0);
        }
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUnidade() {
        return unidade;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }

    public void addValor(Double valor){
        valores.add(valor);
        valores.remove(0);
    }

    public int getIconDrawable() {
        return iconDrawable;
    }

    public void setIconDrawable(int iconDrawable) {
        this.iconDrawable = iconDrawable;
    }

    public ArrayList<Double> getValores() {
        return valores;
    }

    public void setValores(ArrayList<Double> valores) {
        this.valores = valores;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
