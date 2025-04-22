package com.azenithsolution.projetoentrega.v1.service.frete;

import com.azenithsolution.projetoentrega.v1.service.frete.strategy.FreteStrategy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class FreteService {
    private final List<FreteStrategy> estrategias;

    public FreteService(List<FreteStrategy> estrategias) {
        this.estrategias = estrategias;
    }


    public Double calcular(String tipoEntrega, Double peso) {
        System.out.println("Calculando frete para tipo de entrega: " + tipoEntrega);
        Double valor = estrategias.stream()
                .filter(estrategia -> estrategia.tipoEntrega().equalsIgnoreCase(tipoEntrega))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Tipo de entrega não disponível ou não existe."))
                .calcular(peso);
        return valor;
    }
}
