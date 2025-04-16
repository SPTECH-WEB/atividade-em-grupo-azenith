package com.azenithsolution.projetoentrega.v1.service.frete.observer;

import com.azenithsolution.projetoentrega.v1.model.Pedido;

public interface PedidoObserver {
    void notificar(Pedido pedido);

}

