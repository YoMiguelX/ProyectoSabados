package com.example.demo.Seeds;

import com.example.demo.Model.Mundo;
import com.example.demo.Model.Nivel;
import com.example.demo.Repository.MundosRepository;
import com.example.demo.Repository.NivelRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class NivelSeeder implements CommandLineRunner {

    private final NivelRepository nivelRepository;
    private final MundosRepository mundosRepository;

    public NivelSeeder(NivelRepository nivelRepository,
                       MundosRepository mundosRepository) {
        this.nivelRepository = nivelRepository;
        this.mundosRepository = mundosRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        if (nivelRepository.count() == 0) {


            Mundo m1 = mundosRepository.findById(1L).orElse(null);

            Nivel n1 = new Nivel();
            n1.setNombreNivel("Nivel 1");
            n1.setDescripcion("Introducción básica");
            n1.setPuntajeMinimo(0);
            n1.setPuntajeMaximo(100);
            n1.setCompletado(false);
            n1.setMundo(m1);
            nivelRepository.save(n1);

            Nivel n2 = new Nivel();
            n2.setNombreNivel("Nivel 2");
            n2.setDescripcion("Operaciones básicas");
            n2.setPuntajeMinimo(100);
            n2.setPuntajeMaximo(200);
            n2.setCompletado(false);
            n2.setMundo(m1);
            nivelRepository.save(n2);



            Mundo m2 = mundosRepository.findById(2L).orElse(null);

            Nivel n3 = new Nivel();
            n3.setNombreNivel("Nivel 1");
            n3.setDescripcion("Conceptos fundamentales");
            n3.setPuntajeMinimo(0);
            n3.setPuntajeMaximo(100);
            n3.setCompletado(false);
            n3.setMundo(m2);
            nivelRepository.save(n3);

            Nivel n4 = new Nivel();
            n4.setNombreNivel("Nivel 2");
            n4.setDescripcion("Movimiento y fuerza");
            n4.setPuntajeMinimo(100);
            n4.setPuntajeMaximo(200);
            n4.setCompletado(false);
            n4.setMundo(m2);
            nivelRepository.save(n4);



            Mundo m3 = mundosRepository.findById(3L).orElse(null);

            Nivel n5 = new Nivel();
            n5.setNombreNivel("Nivel 1");
            n5.setDescripcion("Saludos y vocabulario básico");
            n5.setPuntajeMinimo(0);
            n5.setPuntajeMaximo(100);
            n5.setCompletado(false);
            n5.setMundo(m3);
            nivelRepository.save(n5);

            Nivel n6 = new Nivel();
            n6.setNombreNivel("Nivel 2");
            n6.setDescripcion("Gramática básica");
            n6.setPuntajeMinimo(100);
            n6.setPuntajeMaximo(200);
            n6.setCompletado(false);
            n6.setMundo(m3);
            nivelRepository.save(n6);
        }
    }
}
