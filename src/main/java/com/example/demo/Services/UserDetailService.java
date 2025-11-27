package com.example.demo.Services;

import com.example.demo.Interface.IUserDetailService;
import com.example.demo.Model.Usuario;
import com.example.demo.Repository.UsuarioRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class UserDetailService implements IUserDetailService {

    private final UsuarioRepository usuarioRepository;

    public UserDetailService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }


    public UserDetails loadUserByCorreo(String correo) {
        Usuario usuario = usuarioRepository.findByCorreoUsuario(correo)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        return User.withUsername(usuario.getCorreoUsuario())
                .password(usuario.getContrasena())  // encriptada en DB
                .roles(usuario.getRol().getNombreRol()) // si usas roles
                .build();
    }
}
