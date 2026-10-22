package com.FitZone.Inscripcion.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.FitZone.Inscripcion.models.InscripcionModel;

@Repository
public interface IInscripcionRepository extends JpaRepository<InscripcionModel, Long> {
}