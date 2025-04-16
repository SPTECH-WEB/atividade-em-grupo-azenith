package com.azenithsolution.projetoentrega.v1.service.frete.strategy;

public interface FreteStrategy {
    double calcular(double peso);
    String tipoEntrega();
}
