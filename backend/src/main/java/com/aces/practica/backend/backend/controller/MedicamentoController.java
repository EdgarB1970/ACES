package com.aces.practica.backend.backend.controller;

import com.aces.practica.backend.backend.dto.MedicamentoDTO;
import com.aces.practica.backend.backend.service.MedicamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/medicamentos")
public class MedicamentoController {

    @Autowired
    private MedicamentoService medicamentoService;

    @PostMapping
    public ResponseEntity<MedicamentoDTO> create(@RequestBody MedicamentoDTO medicamentoDTO){
       MedicamentoDTO savedMedicamento = medicamentoService.save(medicamentoDTO);
       return ResponseEntity.ok(savedMedicamento);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MedicamentoDTO> update(@PathVariable Long id, @RequestBody MedicamentoDTO medicamentoDTO){
        MedicamentoDTO updatedMedicamento = medicamentoService.update(id, medicamentoDTO);
        return ResponseEntity.ok(updatedMedicamento);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        medicamentoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicamentoDTO> findById(@PathVariable Long id){
        return  medicamentoService.findById(id).
                map(ResponseEntity::ok).
                orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<MedicamentoDTO> findAll(){
        return medicamentoService.findAll();
    }
}
