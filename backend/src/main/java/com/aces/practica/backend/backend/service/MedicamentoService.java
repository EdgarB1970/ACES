package com.aces.practica.backend.backend.service;

import com.aces.practica.backend.backend.dto.MedicamentoDTO;

import java.util.List;
import java.util.Optional;

public interface MedicamentoService {
    MedicamentoDTO save(MedicamentoDTO medicamentoDTO);
    MedicamentoDTO update(Long id, MedicamentoDTO medicamentoDTO);
    void delete(Long id);
    Optional<MedicamentoDTO> findById(Long id);
    List<MedicamentoDTO> findAll();
}
