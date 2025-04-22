package com.azenithsolution.projetoentrega.v1.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.Instant;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ApiResponseDTO<T> {
    private final LocalDateTime timestamp;
    private final int status;
    private final String message;
    private final T data;
}
