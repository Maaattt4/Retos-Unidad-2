package com.FitZone.Inscripcion.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.FitZone.Inscripcion.models.ClaseModel;

@Repository
public interface IClaseRepository extends JpaRepository<ClaseModel, Long> {
}