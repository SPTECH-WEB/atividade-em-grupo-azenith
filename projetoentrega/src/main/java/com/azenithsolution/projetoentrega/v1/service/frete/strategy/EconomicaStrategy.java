package com.azenithsolution.projetoentrega.v1.service.frete.strategy;

import org.springframework.stereotype.Component;

@Component
public class EconomicaStrategy implements FreteStrategy {
    @Override
    public double calcular(double peso) {
        return peso * 1.2;
    }

    @Override
    public String tipoEntrega() {
        return "economica";
    }
}
