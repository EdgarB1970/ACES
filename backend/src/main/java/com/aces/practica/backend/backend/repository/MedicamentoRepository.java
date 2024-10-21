package com.aces.practica.backend.backend.repository;

import com.aces.practica.backend.backend.model.Medicamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MedicamentoRepository extends JpaRepository<Medicamento, Long> {
    Optional<Medicamento> findByCodigo(String codigo);
    Optional<Medicamento> findByRegistroSanitario(String registroSanitario);
    boolean existsByCodigo(String codigo);
    boolean existsByRegistroSanitario(String registroSanitario);
}
