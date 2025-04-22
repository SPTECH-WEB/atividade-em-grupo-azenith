package com.azenithsolution.projetoentrega.v1.service.frete.observer;

import com.azenithsolution.projetoentrega.v1.service.frete.observer.PedidoObserver;
import com.azenithsolution.projetoentrega.v1.model.Pedido;
import org.springframework.stereotype.Component;

import java.util.Observable;
import java.util.Observer;

@Component
public class LogObserver implements PedidoObserver {
    @Override
    public void notificar(Pedido pedido){
        System.out.println("Log registrado para o pedido: " + pedido);
    }
}
