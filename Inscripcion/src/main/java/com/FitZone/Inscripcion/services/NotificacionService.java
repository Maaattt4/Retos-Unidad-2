package com.FitZone.Inscripcion.services;

import org.springframework.stereotype.Service;

@Service
public class NotificacionService {

    // Operación candidata: enviarNotificacion (One-Way / Asíncrona)
    public void enviarNotificacion(String correoSocio, String nombreClase) {
        // En la vida real aquí usaríamos un API de correos como SendGrid.
        // Por ahora simulamos la acción "Disparar y olvidar" con un Log.
        System.out.println("=========================================");
        System.out.println("📩 ENVIANDO RECORDATORIO ASÍNCRONO...");
        System.out.println("Para: " + correoSocio);
        System.out.println("Mensaje: Tu clase de " + nombreClase + " está confirmada. ¡Te esperamos 2 horas antes!");
        System.out.println("=========================================");
    }
}