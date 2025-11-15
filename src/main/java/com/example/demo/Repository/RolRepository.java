package com.example.demo.Repository;

import com.example.demo.Model.Rol;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RolRepository extends JpaRepository<Rol, Integer> {



    Optional<Rol> findByTipoRol(String tipoRol);


     Optional<Rol> findById(Integer integer);
}
