package com.azenithsolution.projetoentrega.v1.repository;

import com.azenithsolution.projetoentrega.v1.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}
