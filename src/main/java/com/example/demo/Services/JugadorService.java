package com.example.demo.Services;

import com.example.demo.Model.Jugador;
import com.example.demo.Repository.JugadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JugadorService {

    @Autowired
    private JugadorRepository jugadorRepository;

    public List<Jugador> obtenerTodos() {
        return jugadorRepository.findAll();
    }

    public Optional<Jugador> obtenerPorId(Integer id) {
        return jugadorRepository.findById(id);
    }

    public Jugador guardar(Jugador jugador) {
        return jugadorRepository.save(jugador);
    }

    public void eliminar(Integer id) {
        jugadorRepository.deleteById(id);
    }
}
