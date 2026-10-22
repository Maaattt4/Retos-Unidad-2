package com.FitZone.Inscripcion.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.FitZone.Inscripcion.models.SocioModel;

@Repository
public interface ISocioRepository extends JpaRepository<SocioModel, Long> {
}