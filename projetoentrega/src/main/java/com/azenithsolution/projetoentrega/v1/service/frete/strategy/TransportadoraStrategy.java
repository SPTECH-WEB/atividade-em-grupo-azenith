package com.azenithsolution.projetoentrega.v1.service.frete.strategy;

public class TransportadoraStrategy implements FreteStrategy {
    @Override
    public double calcular(double peso) {
        return peso * 2.0;
    }

    @Override
    public String tipoEntrega() {
        return "transportadora";
    }
}
