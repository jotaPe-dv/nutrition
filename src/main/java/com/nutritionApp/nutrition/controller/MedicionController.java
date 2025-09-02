package com.nutritionApp.nutrition.controller;

import com.nutritionApp.nutrition.model.Medicion;
import com.nutritionApp.nutrition.service.MedicionServiceConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/mediciones")
public class MedicionController {

    // Usamos la inyección por constructor (la recomendada)
    private final MedicionServiceConstructor medicionService;

    public MedicionController(MedicionServiceConstructor medicionService) {
        this.medicionService = medicionService;
    }

    @GetMapping
    public ResponseEntity<List<Medicion>> getAllMediciones() {
        return ResponseEntity.ok(medicionService.findAll());
    }

    @PostMapping("/paciente/{pacienteId}/nutricionista/{nutricionistaId}")
    public ResponseEntity<Medicion> createMedicion(
            @PathVariable Long pacienteId, 
            @PathVariable Long nutricionistaId,
            @RequestBody Medicion medicion) {
        
        // Este método aún no existe en nuestro servicio, lo añadiremos a continuación
        Medicion createdMedicion = medicionService.createMedicion(pacienteId, nutricionistaId, medicion);
        return new ResponseEntity<>(createdMedicion, HttpStatus.CREATED);
    }
    
}
    
