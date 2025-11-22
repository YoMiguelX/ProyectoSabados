package com.example.demo.Seeds;

import com.example.demo.Model.Mundo;
import com.example.demo.Repository.MundosRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class MundosSeeder implements CommandLineRunner {

    private final MundosRepository mundosRepository;

    public MundosSeeder(MundosRepository mundosRepository) {
        this.mundosRepository = mundosRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        if (mundosRepository.count() == 0) {

            Mundo m1 = new Mundo();
            m1.setNombre("MATEMATICAS");
            mundosRepository.save(m1);

            Mundo m2 = new Mundo();
            m2.setNombre("FISICA");
            mundosRepository.save(m2);

            Mundo m3 = new Mundo();
            m3.setNombre("INGLES");
            mundosRepository.save(m3);

            Mundo m4 = new Mundo();
            m4.setNombre("HISTORIA");
            mundosRepository.save(m4);

            Mundo m5 = new Mundo();
            m5.setNombre("GEOGRAFIA");
            mundosRepository.save(m5);

            Mundo m6 = new Mundo();
            m6.setNombre("BIOLOGIA");
            mundosRepository.save(m6);

            Mundo m7 = new Mundo();
            m7.setNombre("QUIMICA");
            mundosRepository.save(m7);

            Mundo m8 = new Mundo();
            m8.setNombre("LOGICA");
            mundosRepository.save(m8);

            Mundo m9 = new Mundo();
            m9.setNombre("PROGRAMACION");
            mundosRepository.save(m9);

            Mundo m10 = new Mundo();
            m10.setNombre("ARTE");
            mundosRepository.save(m10);
        }
    }
}
