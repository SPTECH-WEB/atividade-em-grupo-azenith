package com.azenithsolution.projetoentrega.v1.controller;

import com.azenithsolution.projetoentrega.v1.dto.ApiResponseDTO;
import com.azenithsolution.projetoentrega.v1.dto.FreteResponseDTO;
import com.azenithsolution.projetoentrega.v1.dto.PedidoRequestDTO;
import com.azenithsolution.projetoentrega.v1.dto.PedidoResponseDTO;
import com.azenithsolution.projetoentrega.v1.model.Pedido;
import com.azenithsolution.projetoentrega.v1.service.frete.FreteService;
import com.azenithsolution.projetoentrega.v1.service.pedido.PedidoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/v1/pedidos")
@Tag(name = "Pedidos", description = "Endpoints para gerenciamento de pedidos e cálculo de frete")
public class PedidoController {
    private final PedidoService pedidoService;
    private final FreteService freteService;

    public PedidoController(PedidoService pedidoService, FreteService freteService) {
        this.pedidoService = pedidoService;
        this.freteService = freteService;
    }

    @PostMapping
    @Operation(summary = "Cria um novo pedido", description = "Registra um novo pedido no sistema e calcula o valor total com base no frete.")
    public ResponseEntity<ApiResponseDTO<?>> salvar(@RequestBody PedidoRequestDTO body) {
        Pedido pedido = new Pedido();
        pedido.setCliente(body.getCliente());
        pedido.setPeso(body.getPeso());
        pedido.setProduto(body.getProduto());
        pedido.setTipoEntrega(body.getTipoEntrega());

        try{
            pedido.setValorTotal(freteService.calcular(body.getTipoEntrega(), body.getPeso()));

            Pedido pedidoCriado = pedidoService.salvar(pedido);
            return ResponseEntity.status(HttpStatus.CREATED).body(
                    new ApiResponseDTO<>(
                            LocalDateTime.now(),
                            HttpStatus.CREATED.value(),
                            "Created",
                            new PedidoResponseDTO(pedidoCriado)
                    )
            );
        }catch (NoSuchElementException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ApiResponseDTO<>(
                            LocalDateTime.now(),
                            HttpStatus.NOT_FOUND.value(),
                            "Not Found",
                            e.getMessage()
                    )
            );
        }


    }

    @GetMapping
    @Operation(summary = "Lista todos os pedidos", description = "Retorna uma lista com todos os pedidos cadastrados.")
    public ResponseEntity<ApiResponseDTO<List<Pedido>>> listarPedidos() {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                new ApiResponseDTO<>(
                        LocalDateTime.now(),
                        HttpStatus.OK.value(),
                        "OK",
                        pedidoService.listarPedidos()
                )
        );
    }

    @GetMapping("/frete/{modalidade}")
    @Operation(summary = "Calcula o valor do frete", description = "Calcula o valor do frete com base na modalidade e peso informados.")
    public ResponseEntity<ApiResponseDTO<?>> calcularFrete(@PathVariable String modalidade, @RequestParam double peso) {
        try{
            Double valor = freteService.calcular(modalidade, peso);

            return ResponseEntity.status(HttpStatus.OK).body(
                    new ApiResponseDTO<>(
                            LocalDateTime.now(),
                            HttpStatus.OK.value(),
                            "OK",
                            new FreteResponseDTO(
                                    modalidade,
                                    peso,
                                    valor
                            )
                    )
            );
        }catch (NoSuchElementException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ApiResponseDTO<>(
                            LocalDateTime.now(),
                            HttpStatus.NOT_FOUND.value(),
                            "Not Found",
                            e.getMessage()
                    )
            );
        }
    }

}
