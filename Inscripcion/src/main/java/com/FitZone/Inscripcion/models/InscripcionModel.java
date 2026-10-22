package com.FitZone.Inscripcion.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "inscripciones")
public class InscripcionModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_inscripcion")
    private Long idInscripcion;

    // Conexión con el socio que se inscribe
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_socio", nullable = false)
    @JsonIgnoreProperties({"inscripciones"})
    private SocioModel socio;

    // Conexión con la clase a la que asiste
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_clase", nullable = false)
    @JsonIgnoreProperties({"inscripciones"})
    private ClaseModel clase;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private EstadoInscripcion estado;

    @Column(name = "fecha_registro", nullable = false, updatable = false)
    private LocalDateTime fechaRegistro;

    public enum EstadoInscripcion {
        CONFIRMADA,
        CANCELADA
    }

    // Spring Boot asigna la fecha automáticamente al crear el registro
    @PrePersist
    public void onPrePersist() {
        if (this.fechaRegistro == null) {
            this.fechaRegistro = LocalDateTime.now();
        }
    }

    public InscripcionModel() {}

    public Long getIdInscripcion() {
        return idInscripcion;
    }

    public void setIdInscripcion(Long idInscripcion) {
        this.idInscripcion = idInscripcion;
    }

    public SocioModel getSocio() {
        return socio;
    }

    public void setSocio(SocioModel socio) {
        this.socio = socio;
    }

    public ClaseModel getClase() {
        return clase;
    }

    public void setClase(ClaseModel clase) {
        this.clase = clase;
    }

    public EstadoInscripcion getEstado() {
        return estado;
    }

    public void setEstado(EstadoInscripcion estado) {
        this.estado = estado;
    }

    public LocalDateTime getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDateTime fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    
}