package com.example.demo.Controller;

import com.example.demo.Model.OpenTriviaResponse;
import com.example.demo.Services.OpenTriviaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PreguntaController {

    private final OpenTriviaService triviaService;

    public PreguntaController(OpenTriviaService triviaService) {
        this.triviaService = triviaService;
    }

    @GetMapping("/preguntas")
    public String mostrarPreguntas(Model model) {

        OpenTriviaResponse respuesta = triviaService.obtenerPreguntas();

        model.addAttribute("preguntas", respuesta.getResults());

        return "preguntas";
    }
}