package com.aces.practica.backend.backend.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "medicamentos")
@Data
public class Medicamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String codigo;

    @Column(nullable = false, unique = true)
    private String registroSanitario;

    private String descripcion;

    @Column(nullable = false)
    private String fabricante;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoMedicamento tipo;

}
