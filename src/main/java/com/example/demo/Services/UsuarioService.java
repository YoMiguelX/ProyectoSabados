package com.example.demo.Services;

import com.example.demo.Dto.RegistroUsuarioDto;
import com.example.demo.Dto.UsuarioDto;
import com.example.demo.Dto.Response.ApiResponse;
import com.example.demo.Interface.IUsuarioService;
import com.example.demo.Model.Usuario;
import com.example.demo.Repository.UsuarioRepository;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityNotFoundException;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Service
public class UsuarioService implements IUsuarioService {

    private final UsuarioRepository repo;
    private final ModelMapper mapper;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();



    public UsuarioService(UsuarioRepository repo, ModelMapper mapper) {
        this.repo = repo;
        this.mapper = mapper;
    }

    @Override
    public ApiResponse<List<UsuarioDto>> findAll() {
        ApiResponse<List<UsuarioDto>> response = new ApiResponse<>();
        try {
            List<UsuarioDto> usuarios = repo.findAll()
                    .stream()
                    .map(u -> mapper.map(u, UsuarioDto.class))
                    .toList();

            response.setHttpStatusCode(HttpStatus.OK.value());
            response.setMessage(usuarios.isEmpty()
                    ? "No hay usuarios registrados."
                    : "Lista de usuarios obtenida correctamente.");
            response.setData(usuarios);
            response.setTotalRecords(usuarios.size());
        } catch (Exception ex) {
            response.setHttpStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setMessage("Error al obtener usuarios: " + ex.getMessage());
            response.setData(Collections.emptyList());
            response.setTotalRecords(0);
        }
        return response;
    }

    @Override
    public ApiResponse<UsuarioDto> findById(Integer id) {
        ApiResponse<UsuarioDto> response = new ApiResponse<>();
        try {
            Usuario usuario = repo.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado con ID: " + id));
            UsuarioDto dto = mapper.map(usuario, UsuarioDto.class);
            response.setHttpStatusCode(HttpStatus.OK.value());
            response.setMessage("Usuario obtenido correctamente");
            response.setData(dto);
        } catch (EntityNotFoundException ex) {
            response.setHttpStatusCode(HttpStatus.NOT_FOUND.value());
            response.setMessage(ex.getMessage());
        } catch (Exception ex) {
            response.setHttpStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setMessage("Error al obtener usuario: " + ex.getMessage());
        }
        return response;
    }



    @Transactional
    @Override
    public ApiResponse<UsuarioDto> registrarUsuario(RegistroUsuarioDto dto) {
        ApiResponse<UsuarioDto> response = new ApiResponse<>();
        try {
            if (repo.findByCorreoUsuario(dto.getCorreo()).isPresent()) {
                throw new RuntimeException("Ya existe un usuario con ese correo");
            }

            Usuario usuario = new Usuario();
            usuario.setNombreUsuario(dto.getNombre());
            usuario.setApellidoUsuario(dto.getApellido());
            usuario.setCorreoUsuario(dto.getCorreo());
            usuario.setContrasena(encoder.encode(dto.getContrasena()));
            usuario.setTelUsuario(dto.getTelefono());
            usuario.setEstadoUsuario("Activo");
            usuario.setFechaCreacion(LocalDateTime.now());
            usuario.setRolIdRol(2); // rol por defecto

            Usuario saved = repo.save(usuario);
            UsuarioDto mapped = mapper.map(saved, UsuarioDto.class);

            response.setHttpStatusCode(HttpStatus.CREATED.value());
            response.setMessage("Usuario registrado exitosamente");
            response.setData(mapped);
        } catch (Exception ex) {
            response.setHttpStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setMessage("Error al registrar usuario");
            response.setErrors(List.of(ex.getMessage()));
        }
        return response;
    }

    @Transactional
    @Override
    public ApiResponse<UsuarioDto> update(Integer id, UsuarioDto dto) {
        ApiResponse<UsuarioDto> response = new ApiResponse<>();
        try {
            Usuario usuario = repo.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado con ID: " + id));

            usuario.setNombreUsuario(dto.getNombre());
            usuario.setApellidoUsuario(dto.getApellido());
            usuario.setCorreoUsuario(dto.getCorreo());
            usuario.setTelUsuario(dto.getTelefono());
            usuario.setEstadoUsuario(dto.getEstado());

            Usuario updated = repo.save(usuario);
            UsuarioDto mapped = mapper.map(updated, UsuarioDto.class);

            response.setHttpStatusCode(HttpStatus.OK.value());
            response.setMessage("Usuario actualizado correctamente");
            response.setData(mapped);
        } catch (EntityNotFoundException ex) {
            response.setHttpStatusCode(HttpStatus.NOT_FOUND.value());
            response.setMessage(ex.getMessage());
        } catch (Exception ex) {
            response.setHttpStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setMessage("Error al actualizar usuario: " + ex.getMessage());
        }
        return response;
    }

    @Transactional
    @Override
    public void delete(Integer id) {
        try {
            Usuario usuario = repo.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado con ID: " + id));

            usuario.setEstadoUsuario("Inactivo");
            repo.save(usuario);
        } catch (Exception ex) {
            System.err.println("Error al eliminar usuario: " + ex.getMessage());
        }
    }

    @Override
    public ApiResponse<UsuarioDto> verificarUsuario(String correo, String contrasena) {
        return null;
    }

    @Override
    public ApiResponse<UsuarioDto> login(String correo, String contrasena) {
        ApiResponse<UsuarioDto> response = new ApiResponse<>();
        try {
            Usuario usuario = repo.findByCorreoUsuario(correo)
                    .filter(u -> encoder.matches(contrasena, u.getContrasena()))
                    .orElseThrow(() -> new EntityNotFoundException("Credenciales inválidas"));

            UsuarioDto dto = mapearUsuarioAUsuarioDto(usuario);

            response.setHttpStatusCode(HttpStatus.OK.value());
            response.setMessage("Inicio de sesión exitoso");
            response.setData(dto);
        } catch (EntityNotFoundException ex) {
            response.setHttpStatusCode(HttpStatus.UNAUTHORIZED.value());
            response.setMessage(ex.getMessage());
        } catch (Exception ex) {
            response.setHttpStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setMessage("Error al iniciar sesión: " + ex.getMessage());
        }
        return response;
    }

    public UsuarioDto mapearUsuarioAUsuarioDto(Usuario usuario) {
        return new UsuarioDto(
                usuario.getIdUsuario(),
                usuario.getNombreUsuario(),
                usuario.getApellidoUsuario(),
                usuario.getCorreoUsuario(),
                usuario.getTelUsuario(),
                usuario.getEstadoUsuario(),
                usuario.getRol() != null ? usuario.getRol().getIdRol() : null
        );
    }
    @PostConstruct
    public void configurarMapper() {
        mapper.typeMap(Usuario.class, UsuarioDto.class).addMappings(m -> {
            m.map(Usuario::getTelUsuario, UsuarioDto::setTelefono);
            m.map(src -> src.getRol() != null ? src.getRol().getIdRol() : null, UsuarioDto::setRolId);
        });
    }

}
