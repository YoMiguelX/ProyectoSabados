package com.example.demo.Model;


import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "progreso_jugador")
public class ProgresoJugador {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idProgresoJugador;

    private Integer puntajeNivel;
    private Boolean nivelCompletado;
    private Integer tiempoJugado;
    private LocalDateTime fechaCompletado;

    @ManyToOne
    @JoinColumn(name = "NIVELES_ID_NIVELES")
    private Nivel nivel;

    @OneToMany(mappedBy = "progresoJugador")
    private List<Jugador> jugadores;

    // Getters y setters

    public Integer getIdProgresoJugador() {
        return idProgresoJugador;
    }

    public void setIdProgresoJugador(Integer idProgresoJugador) {
        this.idProgresoJugador = idProgresoJugador;
    }

    public Integer getPuntajeNivel() {
        return puntajeNivel;
    }

    public void setPuntajeNivel(Integer puntajeNivel) {
        this.puntajeNivel = puntajeNivel;
    }

    public Boolean getNivelCompletado() {
        return nivelCompletado;
    }

    public void setNivelCompletado(Boolean nivelCompletado) {
        this.nivelCompletado = nivelCompletado;
    }

    public Integer getTiempoJugado() {
        return tiempoJugado;
    }

    public void setTiempoJugado(Integer tiempoJugado) {
        this.tiempoJugado = tiempoJugado;
    }

    public LocalDateTime getFechaCompletado() {
        return fechaCompletado;
    }

    public void setFechaCompletado(LocalDateTime fechaCompletado) {
        this.fechaCompletado = fechaCompletado;
    }

    public Nivel getNivel() {
        return nivel;
    }

    public void setNivel(Nivel nivel) {
        this.nivel = nivel;
    }

    public List<Jugador> getJugadores() {
        return jugadores;
    }

    public void setJugadores(List<Jugador> jugadores) {
        this.jugadores = jugadores;
    }
}
