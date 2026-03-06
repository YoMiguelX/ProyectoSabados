package com.example.demo.Controller;

import com.example.demo.Dto.PreguntaBatallaDto;
import com.example.demo.Model.Pregunta;
import com.example.demo.Repository.PreguntaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/batalla")
@CrossOrigin(origins = "*") // Permitir peticiones desde Unity
public class PreguntaApiController {

    @Autowired
    private PreguntaRepository preguntaRepository;

    // Endpoint: Obtener preguntas para batalla
    @GetMapping("/preguntas/{nivelId}")
    public ResponseEntity<?> getPreguntasBatalla(
            @PathVariable Integer nivelId,
            @RequestParam(defaultValue = "5") int cantidad) {

        try {
            List<Pregunta> preguntas = preguntaRepository.findRandomByNivelId(nivelId, cantidad);

            if (preguntas.isEmpty()) {
                Map<String, Object> error = new HashMap<>();
                error.put("success", false);
                error.put("message", "No hay preguntas disponibles para este nivel");
                return ResponseEntity.ok(error);
            }

            List<PreguntaBatallaDto> preguntasDto = preguntas.stream()
                    .map(p -> new PreguntaBatallaDto(
                            p.getIdPreguntas(),
                            p.getTextoPregunta(),
                            p.getOpcionesRespuesta(),
                            p.getRespuestaCorrecta(),
                            p.getExplicacion(),
                            p.getPuntos()))
                    .collect(Collectors.toList());

            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("data", preguntasDto);
            response.put("total", preguntasDto.size());

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            Map<String, Object> error = new HashMap<>();
            error.put("success", false);
            error.put("message", "Error al obtener preguntas: " + e.getMessage());
            return ResponseEntity.status(500).body(error);
        }
    }

    // Endpoint: Validar respuesta
    @PostMapping("/validar")
    public ResponseEntity<?> validarRespuesta(@RequestBody Map<String, Object> payload) {
        try {
            Integer preguntaId = Integer.parseInt(payload.get("preguntaId").toString());
            String respuestaUsuario = payload.get("respuesta").toString();

            Pregunta pregunta = preguntaRepository.findById(preguntaId).orElse(null);

            if (pregunta == null) {
                return ResponseEntity.ok(Map.of(
                        "success", false,
                        "message", "Pregunta no encontrada"
                ));
            }

            boolean esCorrecta = pregunta.getRespuestaCorrecta().equalsIgnoreCase(respuestaUsuario);

            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("valida", esCorrecta);
            response.put("explicacion", pregunta.getExplicacion());
            response.put("puntos", esCorrecta ? pregunta.getPuntos() : 0);

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            return ResponseEntity.status(500).body(Map.of(
                    "success", false,
                    "message", "Error al validar: " + e.getMessage()
            ));
        }
    }
}