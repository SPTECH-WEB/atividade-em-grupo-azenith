package com.azenithsolution.projetoentrega.v1.service.frete.adapter;

import com.azenithsolution.projetoentrega.v1.service.frete.strategy.FreteStrategy;
import com.azenithsolution.projetoentrega.v1.service.frete.strategy.TransportadoraStrategy;

public class TransportadoraExternaStrategy implements FreteStrategy {
    private final TransportadoraStrategy transportadoraStrategy;

    public TransportadoraExternaStrategy() {
        this.transportadoraStrategy = new TransportadoraStrategy();
    }

    @Override
    public double calcular(double peso) {
        return transportadoraStrategy.calcular(peso) * 1.5;
    }

    @Override
    public String tipoEntrega() {
        return "transportadoraExterna";
    }
}
