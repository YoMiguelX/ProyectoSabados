package com.example.demo.Repository;

import com.example.demo.Model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    Optional<Usuario> findByCorreoUsuario(String correoUsuario);
    Optional<Usuario> findByResetToken(String resetToken);

    @Override
    Optional<Usuario> findById(Integer integer);

    boolean existsByCorreoUsuario(String mail);
}
