package com.FitZone.Inscripcion.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.FitZone.Inscripcion.models.SocioModel;
import com.FitZone.Inscripcion.repositories.ISocioRepository;

@Service
public class SocioService {

    @Autowired
    private ISocioRepository socioRepository;

    // Operación candidata: verificarMembresia
    public boolean verificarMembresia(Long idSocio) {
        SocioModel socio = socioRepository.findById(idSocio)
                .orElseThrow(() -> new RuntimeException("SocioNoEncontrado"));
        return socio.getMembresiaVigente();
    }
    
    public SocioModel obtenerSocio(Long idSocio) {
        return socioRepository.findById(idSocio)
                .orElseThrow(() -> new RuntimeException("SocioNoEncontrado"));
    }
}