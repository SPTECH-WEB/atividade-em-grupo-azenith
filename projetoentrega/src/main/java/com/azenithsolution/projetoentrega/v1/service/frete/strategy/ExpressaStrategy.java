package com.azenithsolution.projetoentrega.v1.service.frete.strategy;

public class ExpressaStrategy implements FreteStrategy{
    @Override
    public double calcular(double peso) {
        return peso * 1.5;
    }

    @Override
    public String tipoEntrega() {
        return "pac";
    }
}
