package com.aces.practica.backend.backend.service.impl;

import com.aces.practica.backend.backend.dto.MedicamentoDTO;
import com.aces.practica.backend.backend.model.Medicamento;
import com.aces.practica.backend.backend.model.TipoMedicamento;
import com.aces.practica.backend.backend.repository.MedicamentoRepository;
import com.aces.practica.backend.backend.service.MedicamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MedicamentoServiceImpl implements MedicamentoService {

    @Autowired
    private MedicamentoRepository medicamentoRepository;

    @Override
    public MedicamentoDTO save(MedicamentoDTO medicamentoDTO){
        if (medicamentoDTO.getCodigo() == null || medicamentoDTO.getCodigo().isEmpty()){
            throw new IllegalArgumentException("El código es requerido.");
        }
        if (medicamentoDTO.getRegistroSanitario() == null || medicamentoDTO.getRegistroSanitario().isEmpty()) {
            throw new IllegalArgumentException("El registro sanitario es requerido.");
        }
        if (medicamentoDTO.getFabricante() == null || medicamentoDTO.getFabricante().isEmpty()) {
            throw new IllegalArgumentException("El fabricante es requerido.");
        }
        if (medicamentoDTO.getTipo() == null ||
                !Arrays.asList(TipoMedicamento.values()).contains(TipoMedicamento.valueOf(medicamentoDTO.getTipo()))) {
            throw new IllegalArgumentException("Tipo de medicamento no válido.");
        }

        if (medicamentoRepository.existsByCodigo(medicamentoDTO.getCodigo())) {
            throw new IllegalArgumentException("El código ya existe.");
        }
        if (medicamentoRepository.existsByRegistroSanitario(medicamentoDTO.getRegistroSanitario())) {
            throw new IllegalArgumentException("El registro sanitario ya existe.");
        }


        Medicamento medicamento = new Medicamento();
        medicamento.setCodigo(medicamentoDTO.getCodigo());
        medicamento.setRegistroSanitario(medicamentoDTO.getRegistroSanitario());
        medicamento.setDescripcion(medicamentoDTO.getDescripcion());
        medicamento.setFabricante(medicamentoDTO.getFabricante());
        medicamento.setTipo(TipoMedicamento.valueOf(medicamentoDTO.getTipo()));
        Medicamento savedMedicamento = medicamentoRepository.save(medicamento);
        return convertToDTO(savedMedicamento);
    }

    @Override
    public MedicamentoDTO update(Long id, MedicamentoDTO medicamentoDTO) {

        Medicamento medicamento = medicamentoRepository.findById(id).orElseThrow(() -> new RuntimeException("Medicamento no encontrado"));

        if (medicamentoDTO.getCodigo() == null || medicamentoDTO.getCodigo().isEmpty()) {
            throw new IllegalArgumentException("El código es requerido.");
        }
        if (medicamentoDTO.getRegistroSanitario() == null || medicamentoDTO.getRegistroSanitario().isEmpty()) {
            throw new IllegalArgumentException("El registro sanitario es requerido.");
        }
        if (medicamentoDTO.getFabricante() == null || medicamentoDTO.getFabricante().isEmpty()) {
            throw new IllegalArgumentException("El fabricante es requerido.");
        }
        if (medicamentoDTO.getTipo() == null ||
                !Arrays.asList(TipoMedicamento.values()).contains(TipoMedicamento.valueOf(medicamentoDTO.getTipo()))) {
            throw new IllegalArgumentException("Tipo de medicamento no válido.");
        }

        if (!medicamento.getCodigo().equals(medicamentoDTO.getCodigo()) &&
                medicamentoRepository.existsByCodigo(medicamentoDTO.getCodigo())) {
            throw new IllegalArgumentException("El código ya existe.");
        }
        if (!medicamento.getRegistroSanitario().equals(medicamentoDTO.getRegistroSanitario()) &&
                medicamentoRepository.existsByRegistroSanitario(medicamentoDTO.getRegistroSanitario())) {
            throw new IllegalArgumentException("El registro sanitario ya existe.");
        }

        medicamento.setCodigo(medicamentoDTO.getCodigo());
        medicamento.setRegistroSanitario(medicamentoDTO.getRegistroSanitario());
        medicamento.setDescripcion(medicamentoDTO.getDescripcion());
        medicamento.setFabricante(medicamentoDTO.getFabricante());
        medicamento.setTipo(TipoMedicamento.valueOf(medicamentoDTO.getTipo()));
        Medicamento updatedMedicamento = medicamentoRepository.save(medicamento);
        return convertToDTO(updatedMedicamento);
    }

    @Override
    public void delete(Long id) {
        medicamentoRepository.deleteById(id);
    }

    @Override
    public Optional<MedicamentoDTO> findById(Long id) {
        return medicamentoRepository.findById(id).map(this::convertToDTO);
    }

    @Override
    public List<MedicamentoDTO> findAll() {
        return medicamentoRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private MedicamentoDTO convertToDTO(Medicamento medicamento) {
        MedicamentoDTO dto = new MedicamentoDTO();
        dto.setId(medicamento.getId());
        dto.setCodigo(medicamento.getCodigo());
        dto.setRegistroSanitario(medicamento.getRegistroSanitario());
        dto.setDescripcion(medicamento.getDescripcion());
        dto.setFabricante(medicamento.getFabricante());
        dto.setTipo(medicamento.getTipo().name()); // Convertimos el Enum a String
        return dto;
    }
}
