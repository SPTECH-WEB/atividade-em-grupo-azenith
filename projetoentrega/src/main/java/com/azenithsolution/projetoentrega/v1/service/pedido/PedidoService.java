package com.azenithsolution.projetoentrega.v1.service.pedido;

import com.azenithsolution.projetoentrega.v1.model.Pedido;
import com.azenithsolution.projetoentrega.v1.repository.PedidoRepository;
import com.azenithsolution.projetoentrega.v1.service.frete.observer.PedidoObserver;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidoService {
    private final PedidoRepository pedidoRepository;
    private final List<PedidoObserver> pedidoObservers;

    public PedidoService(PedidoRepository pedidoRepository, List<PedidoObserver> pedidoObservers) {
        this.pedidoRepository = pedidoRepository;
        this.pedidoObservers = pedidoObservers;
    }

    public Pedido salvar(Pedido pedido) {
        Pedido pedidoSalvo = pedidoRepository.save(pedido);
        pedidoObservers.forEach(observer -> observer.notificar(pedidoSalvo));
        return pedidoSalvo;
    }

    public List<Pedido> listarPedidos() {
        return pedidoRepository.findAll();
    }
}
