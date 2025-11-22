package com.example.demo.Repository;

import com.example.demo.Model.ProgresoJugador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProgresoJugadorRepository extends JpaRepository<ProgresoJugador, Integer> {
}
