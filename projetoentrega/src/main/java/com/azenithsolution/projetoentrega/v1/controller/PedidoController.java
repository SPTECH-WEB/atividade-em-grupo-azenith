package com.azenithsolution.projetoentrega.v1.controller;

import com.azenithsolution.projetoentrega.v1.model.Pedido;
import com.azenithsolution.projetoentrega.v1.repository.PedidoRepository;
import com.azenithsolution.projetoentrega.v1.service.frete.FreteService;
import com.azenithsolution.projetoentrega.v1.service.pedido.PedidoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {
    private final PedidoService pedidoService;
    private final FreteService freteService;

    public PedidoController(PedidoService pedidoService, FreteService freteService) {
        this.pedidoService = pedidoService;
        this.freteService = freteService;
    }

    @PostMapping
    public Pedido salvar(@RequestBody Pedido pedido) {
        return pedidoService.salvar(pedido);
    }

    @GetMapping
    public List<Pedido> listarPedidos() {
        return pedidoService.listarPedidos();
    }

    @GetMapping("/frete")
    public String calcularFrete(@RequestParam String tipoEntrega, @RequestParam double peso) {
        double valor = freteService.calcular(tipoEntrega, peso);
        return "Frete "+ tipoEntrega+" para "+peso+"Kg: R$"+valor;
    }

}
