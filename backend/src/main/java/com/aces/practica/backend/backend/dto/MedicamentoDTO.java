package com.aces.practica.backend.backend.dto;

import lombok.Data;

@Data
public class MedicamentoDTO {
    private Long id;
    private String codigo;
    private String registroSanitario;
    private String descripcion;
    private String fabricante;
    private String tipo;
}
