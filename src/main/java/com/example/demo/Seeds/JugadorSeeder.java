package com.example.demo.Seeds;

import com.example.demo.Model.Jugador;
import com.example.demo.Model.ProgresoJugador;
import com.example.demo.Model.Usuario;
import com.example.demo.Repository.JugadorRepository;
import com.example.demo.Repository.ProgresoJugadorRepository;
import com.example.demo.Repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class JugadorSeeder implements CommandLineRunner {

    private final JugadorRepository jugadorRepository;
    private final ProgresoJugadorRepository progresoJugadorRepository;
    private final UsuarioRepository usuarioRepository;

    public JugadorSeeder(JugadorRepository jugadorRepository,
                         ProgresoJugadorRepository progresoJugadorRepository,
                         UsuarioRepository usuarioRepository) {
        this.jugadorRepository = jugadorRepository;
        this.progresoJugadorRepository = progresoJugadorRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        if (jugadorRepository.count() == 0) {


            Jugador j1 = new Jugador();
            j1.setNombre("sebas");
            j1.setFechaRegistro(LocalDate.now());
            j1.setUltimaConexion(LocalDate.now());
            j1.setEstado("Activo");
            j1.setProgreso(0);

            j1.setUsuario(usuarioRepository.findById(5).orElse(null));
            j1.setProgresoJugador(progresoJugadorRepository.findById(1).orElse(null));

            jugadorRepository.save(j1);



            Jugador j2 = new Jugador();
            j2.setNombre("DarkKnight");
            j2.setFechaRegistro(LocalDate.now());
            j2.setUltimaConexion(LocalDate.now());
            j2.setEstado("Activo");
            j2.setProgreso(0);

            j2.setUsuario(usuarioRepository.findById(29).orElse(null));
            j2.setProgresoJugador(progresoJugadorRepository.findById(2).orElse(null));

            jugadorRepository.save(j2);


            Jugador j3 = new Jugador();
            j3.setNombre("StarLuna");
            j3.setFechaRegistro(LocalDate.now());
            j3.setUltimaConexion(LocalDate.now());
            j3.setEstado("Activo");
            j3.setProgreso(0);

            j3.setUsuario(usuarioRepository.findById(32).orElse(null));
            j3.setProgresoJugador(progresoJugadorRepository.findById(3).orElse(null));

            jugadorRepository.save(j3);
        }
    }
}
