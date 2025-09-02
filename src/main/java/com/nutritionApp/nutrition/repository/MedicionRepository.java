package com.nutritionApp.nutrition.repository;

import com.nutritionApp.nutrition.model.Medicion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface MedicionRepository extends JpaRepository<Medicion, Long> {

    List<Medicion> findByPacienteIdOrderByFechaDesc(Long pacienteId);

    Optional<Medicion> findFirstByPacienteIdOrderByFechaDesc(Long pacienteId);
}