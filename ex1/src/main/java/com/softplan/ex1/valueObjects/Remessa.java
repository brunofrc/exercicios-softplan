package com.softplan.ex1.valueObjects;

public class Remessa {

    private final int numero;
    private final double valor;

    public Remessa(int numero, double valor) {
        this.numero = numero;
        this.valor = valor;
    }

    public int getNumero() {
        return numero;
    }

    public double getValor() {
        return valor;
    }
}
