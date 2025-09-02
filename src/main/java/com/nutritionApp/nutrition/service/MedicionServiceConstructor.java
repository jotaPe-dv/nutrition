package com.nutritionApp.nutrition.service;

import com.nutritionApp.nutrition.model.Medicion;
import com.nutritionApp.nutrition.repository.MedicionRepository;
import com.nutritionApp.nutrition.repository.PacienteRepository;
import com.nutritionApp.nutrition.repository.NutricionistaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class MedicionServiceConstructor {
    
    // Las dependencias se declaran como 'final'
    private final MedicionRepository medicionRepository;
    private final PacienteRepository pacienteRepository;
    private final NutricionistaRepository nutricionistaRepository;
    
    // El constructor recibe los repositorios necesarios.
    // Spring automáticamente inyectará las implementaciones apropiadas.
    public MedicionServiceConstructor(
            MedicionRepository medicionRepository,
            PacienteRepository pacienteRepository,
            NutricionistaRepository nutricionistaRepository) {
        this.medicionRepository = medicionRepository;
        this.pacienteRepository = pacienteRepository;
        this.nutricionistaRepository = nutricionistaRepository;
    }
    
    @Transactional(readOnly = true)
    public List<Medicion> findAll() {
        return medicionRepository.findAll();
    }
    
    @Transactional(readOnly = true)
    public Optional<Medicion> findById(Long id) {
        return medicionRepository.findById(id);
    }
    
    // Otros métodos del servicio se añadirán más adelante
    @Transactional
    public Medicion createMedicion(Long pacienteId, Long nutricionistaId, Medicion medicion) {
        // Obtenemos el paciente y el nutricionista usando sus respectivos repositorios
        var paciente = pacienteRepository.findById(pacienteId)
                .orElseThrow(() -> new RuntimeException("Paciente no encontrado con id: " + pacienteId));
        
        var nutricionista = nutricionistaRepository.findById(nutricionistaId)
                .orElseThrow(() -> new RuntimeException("Nutricionista no encontrado con id: " + nutricionistaId));
        
        // Asignamos el paciente y el nutricionista a la medición
        medicion.setPaciente(paciente);
        medicion.setNutricionista(nutricionista);
        
        // Si no se especificó una fecha, usamos la fecha actual
        if (medicion.getFecha() == null) {
            medicion.setFecha(java.time.LocalDate.now());
        }
        
        // Guardamos la medición
        return medicionRepository.save(medicion);
    }
}
