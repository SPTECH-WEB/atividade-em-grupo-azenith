package com.azenithsolution.projetoentrega.v1.service.frete.observer;

import com.azenithsolution.projetoentrega.v1.model.Pedido;
import org.springframework.stereotype.Component;

@Component
public class SmsObserver implements PedidoObserver {

    @Override
    public void notificar(Pedido pedido) {
        System.out.println("SMS enviada para: " + pedido.getCliente());
    }

}
