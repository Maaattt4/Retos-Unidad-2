package com.FitZone.Inscripcion.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.FitZone.Inscripcion.models.ClaseModel;
import com.FitZone.Inscripcion.models.InscripcionModel;
import com.FitZone.Inscripcion.models.SocioModel;
import com.FitZone.Inscripcion.repositories.IInscripcionRepository;

@Service
public class InscripcionService {

    @Autowired
    private SocioService socioService;

    @Autowired
    private ClaseService claseService;

    @Autowired
    private NotificacionService notificacionService;

    @Autowired
    private IInscripcionRepository inscripcionRepository;

    // Operación principal coordinadora (Síncrona - Petición/Respuesta)
    @Transactional
    public boolean inscribirEnClase(Long idSocio, Long idClase) {
        
        // PASO 1: verificarMembresia()
        if (!socioService.verificarMembresia(idSocio)) {
            throw new RuntimeException("MembresiaVencida");
        }

        // PASO 2: consultarCupo()
        if (!claseService.consultarCupo(idClase)) {
            throw new RuntimeException("ClaseLlena");
        }

        // Recuperamos los objetos completos
        SocioModel socio = socioService.obtenerSocio(idSocio);
        ClaseModel clase = claseService.obtenerClase(idClase);

        // PASO 3: Se registra la inscripción en la base de datos
        InscripcionModel nuevaInscripcion = new InscripcionModel();
        nuevaInscripcion.setSocio(socio);
        nuevaInscripcion.setClase(clase);
        nuevaInscripcion.setEstado(InscripcionModel.EstadoInscripcion.CONFIRMADA);
        
        inscripcionRepository.save(nuevaInscripcion);
        claseService.registrarAsistencia(clase);

        // PASO 4: enviarNotificacion()
        notificacionService.enviarNotificacion(socio.getCorreo(), clase.getTipo().toString());

        // =========================================================
        // REQUISITO DEL PROFESOR: Imprimir confirmación como un Log
        // =========================================================
        System.out.println("=========================================");
        System.out.println("LOG DE SISTEMA - INSCRIPCIÓN CONFIRMADA");
        System.out.println("Socio inscrito: " + socio.getNombre());
        System.out.println("Clase asignada: " + clase.getTipo());
        System.out.println("Lugares ocupados actualmente: " + clase.getAlumnosInscritos() + "/20");
        System.out.println("=========================================");

        return true; 
    }
}