package com.azenithsolution.projetoentrega.v1.service.frete;

import com.azenithsolution.projetoentrega.v1.service.frete.strategy.FreteStrategy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FreteService {
    private final List<FreteStrategy> estrategias;

    public FreteService(List<FreteStrategy> estrategias) {
        this.estrategias = estrategias;
    }

    public double calcular(String tipoEntrega, double peso) {
        System.out.println("Calculando frete para tipo de entrega: " + tipoEntrega);
        double valor = estrategias.stream()
                .filter(estrategia -> estrategia.tipoEntrega().equalsIgnoreCase(tipoEntrega))
                .findFirst()
                .orElseThrow()
                .calcular(peso);
        return valor;
    }

}
