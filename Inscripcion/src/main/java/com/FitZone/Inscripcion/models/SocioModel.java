package com.FitZone.Inscripcion.models;

import jakarta.persistence.*;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "socios")
public class SocioModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_socio")
    private Long idSocio;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(nullable = false, unique = true, length = 100)
    private String correo;

    // Atributo clave para la regla "verificarMembresia"
    @Column(name = "membresia_vigente", nullable = false)
    private Boolean membresiaVigente;

    // Un socio puede tener muchas inscripciones a distintas clases
    @OneToMany(mappedBy = "socio", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<InscripcionModel> inscripciones;

    public SocioModel() {}

    public Long getIdSocio() {
        return idSocio;
    }

    public void setIdSocio(Long idSocio) {
        this.idSocio = idSocio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Boolean getMembresiaVigente() {
        return membresiaVigente;
    }

    public void setMembresiaVigente(Boolean membresiaVigente) {
        this.membresiaVigente = membresiaVigente;
    }

    public List<InscripcionModel> getInscripciones() {
        return inscripciones;
    }

    public void setInscripciones(List<InscripcionModel> inscripciones) {
        this.inscripciones = inscripciones;
    }

    
}