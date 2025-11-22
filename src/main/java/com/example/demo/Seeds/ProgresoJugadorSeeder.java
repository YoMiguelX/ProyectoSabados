package com.example.demo.Seeds;

import com.example.demo.Model.Nivel;
import com.example.demo.Model.ProgresoJugador;
import com.example.demo.Repository.NivelRepository;
import com.example.demo.Repository.ProgresoJugadorRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ProgresoJugadorSeeder implements CommandLineRunner {

    private final ProgresoJugadorRepository progresoJugadorRepository;
    private final NivelRepository nivelRepository;

    public ProgresoJugadorSeeder(ProgresoJugadorRepository progresoJugadorRepository,
                                 NivelRepository nivelRepository) {
        this.progresoJugadorRepository = progresoJugadorRepository;
        this.nivelRepository = nivelRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        if (progresoJugadorRepository.count() == 0) {


            ProgresoJugador p1 = new ProgresoJugador();
            p1.setPuntajeNivel(50);
            p1.setNivelCompletado(false);
            p1.setTiempoJugado(120); // segundos
            p1.setFechaCompletado(LocalDateTime.now());
            p1.setNivel(nivelRepository.findById(1).orElse(null));

            progresoJugadorRepository.save(p1);



            ProgresoJugador p2 = new ProgresoJugador();
            p2.setPuntajeNivel(120);
            p2.setNivelCompletado(false);
            p2.setTiempoJugado(300);
            p2.setFechaCompletado(LocalDateTime.now());
            p2.setNivel(nivelRepository.findById(2).orElse(null));

            progresoJugadorRepository.save(p2);



            ProgresoJugador p3 = new ProgresoJugador();
            p3.setPuntajeNivel(200);
            p3.setNivelCompletado(true);
            p3.setTiempoJugado(450);
            p3.setFechaCompletado(LocalDateTime.now());
            p3.setNivel(nivelRepository.findById(3).orElse(null));

            progresoJugadorRepository.save(p3);
        }
    }
}
