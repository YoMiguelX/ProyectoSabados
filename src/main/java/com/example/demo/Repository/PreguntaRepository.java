package com.example.demo.Repository;

import com.example.demo.Model.Pregunta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PreguntaRepository extends JpaRepository<Pregunta, Integer> {

    // Obtener preguntas aleatorias por nivel
    @Query(value = "SELECT * FROM preguntas WHERE NIVELES_ID_NIVELES = :nivelId ORDER BY RAND() LIMIT :cantidad",
            nativeQuery = true)
    List<Pregunta> findRandomByNivelId(@Param("nivelId") Integer nivelId, @Param("cantidad") int cantidad);

    // Contar preguntas por nivel
    @Query("SELECT COUNT(p) FROM Pregunta p WHERE p.nivel.idNiveles = :nivelId")
    long countByNivelId(@Param("nivelId") Integer nivelId);
}




