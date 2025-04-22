package com.azenithsolution.projetoentrega.v1.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor @NoArgsConstructor
public class PedidoRequestDTO {
    private String cliente;
    private String produto;
    private Double peso;
    private String tipoEntrega;
}
