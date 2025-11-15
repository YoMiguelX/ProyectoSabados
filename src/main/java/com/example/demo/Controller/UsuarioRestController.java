package com.example.demo.Controller;

import com.example.demo.Dto.RegistroUsuarioDto;
import com.example.demo.Dto.Response.ApiResponse;
import com.example.demo.Dto.UsuarioDto;
import com.example.demo.Interface.IUsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioRestController {

    @Autowired
    private IUsuarioService usuarioService;

    @GetMapping
    public ApiResponse<List<UsuarioDto>> listar() {
        return usuarioService.findAll();
    }

    @PostMapping
    public ApiResponse<UsuarioDto> registrar(@Valid @RequestBody RegistroUsuarioDto dto) {
        return usuarioService.registrarUsuario(dto);
    }

    @PostMapping("/login")
    public ApiResponse<UsuarioDto> login(@RequestBody RegistroUsuarioDto dto) {
        return usuarioService.login(dto.getCorreo(), dto.getContrasena());
    }


    @GetMapping("/{id}")
    public ApiResponse<UsuarioDto> obtener(@PathVariable Integer id) {
        return usuarioService.findById(id);
    }

    @PutMapping("/{id}")
    public ApiResponse<UsuarioDto> actualizar(@PathVariable Integer id, @RequestBody UsuarioDto dto) {
        return usuarioService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Integer id) {
        usuarioService.delete(id);
    }
}
