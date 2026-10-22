package com.FitZone.Inscripcion.models;

import jakarta.persistence.*;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "clases")
public class ClaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_clase")
    private Long idClase;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private TipoClase tipo;

    // Atributos clave para la regla "consultarCupo"
    @Column(name = "cupo_maximo", nullable = false)
    private Integer cupoMaximo = 20;

    @Column(name = "alumnos_inscritos", nullable = false)
    private Integer alumnosInscritos = 0;

    // Una clase tiene muchas inscripciones
    @OneToMany(mappedBy = "clase", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<InscripcionModel> inscripciones;

    // Enum para controlar estrictamente las clases que ofrece FitZone
    public enum TipoClase {
        YOGA,
        SPINNING,
        FUNCIONAL
    }

    public ClaseModel() {}

    public Long getIdClase() {
        return idClase;
    }

    public void setIdClase(Long idClase) {
        this.idClase = idClase;
    }

    public TipoClase getTipo() {
        return tipo;
    }

    public void setTipo(TipoClase tipo) {
        this.tipo = tipo;
    }

    public Integer getCupoMaximo() {
        return cupoMaximo;
    }

    public void setCupoMaximo(Integer cupoMaximo) {
        this.cupoMaximo = cupoMaximo;
    }

    public Integer getAlumnosInscritos() {
        return alumnosInscritos;
    }

    public void setAlumnosInscritos(Integer alumnosInscritos) {
        this.alumnosInscritos = alumnosInscritos;
    }

    public List<InscripcionModel> getInscripciones() {
        return inscripciones;
    }

    public void setInscripciones(List<InscripcionModel> inscripciones) {
        this.inscripciones = inscripciones;
    }

    
}