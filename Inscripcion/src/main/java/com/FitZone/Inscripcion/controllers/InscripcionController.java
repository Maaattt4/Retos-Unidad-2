package com.FitZone.Inscripcion.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.FitZone.Inscripcion.services.InscripcionService;

// Data Transfer Object temporal para recibir el JSON
class InscripcionRequest {
    public Long idSocio;
    public Long idClase;
}

@RestController
@RequestMapping("/api/inscripciones")
public class InscripcionController {

    @Autowired
    private InscripcionService inscripcionService;

    // Endpoint POST (Síncrono - Petición/Respuesta)
    @PostMapping("/inscribir")
    public ResponseEntity<String> solicitarInscripcion(@RequestBody InscripcionRequest request) {
        
        try {
            // Mandamos a llamar al Servicio de Tarea (El que tiene el @Transactional)
            boolean exito = inscripcionService.inscribirEnClase(request.idSocio, request.idClase);
            
            if(exito) {
                // Retornamos 201 Created porque se guardó exitosamente
                return ResponseEntity.status(201).body("Inscripción realizada con éxito.");
            }
            return ResponseEntity.badRequest().body("Error desconocido.");
            
        } catch (RuntimeException e) {
            // Si la regla de negocio falla (ej. MembresiaVencida o ClaseLlena), lanzamos error
            return ResponseEntity.status(409).body("Conflicto: " + e.getMessage());
        }
    }
}