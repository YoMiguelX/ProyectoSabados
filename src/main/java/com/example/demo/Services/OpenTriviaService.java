package com.example.demo.Services;

import com.example.demo.Model.OpenTriviaResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OpenTriviaService {

    private final String API_URL =
            "https://opentdb.com/api.php?amount=5&category=18&type=multiple";

    public OpenTriviaResponse obtenerPreguntas() {

        RestTemplate restTemplate = new RestTemplate();

        return restTemplate.getForObject(API_URL, OpenTriviaResponse.class);
    }
}