package com.FitZone.Inscripcion.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.FitZone.Inscripcion.models.ClaseModel;
import com.FitZone.Inscripcion.repositories.IClaseRepository;

@Service
public class ClaseService {

    @Autowired
    private IClaseRepository claseRepository;

    // Operación candidata: consultarCupo
    public boolean consultarCupo(Long idClase) {
        ClaseModel clase = claseRepository.findById(idClase)
                .orElseThrow(() -> new RuntimeException("ClaseInexistente"));
        
        return clase.getAlumnosInscritos() < clase.getCupoMaximo();
    }

    public ClaseModel obtenerClase(Long idClase) {
        return claseRepository.findById(idClase)
                .orElseThrow(() -> new RuntimeException("ClaseInexistente"));
    }

    // Método para actualizar el cupo cuando alguien se inscribe
    public void registrarAsistencia(ClaseModel clase) {
        clase.setAlumnosInscritos(clase.getAlumnosInscritos() + 1);
        claseRepository.save(clase);
    }
}