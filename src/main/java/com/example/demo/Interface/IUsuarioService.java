package com.example.demo.Interface;

import com.example.demo.Dto.RegistroUsuarioDto;
import com.example.demo.Dto.RegistroUsuarioDto;
import com.example.demo.Dto.UsuarioDto;
import com.example.demo.Dto.Response.ApiResponse;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface IUsuarioService {
    ApiResponse<List<UsuarioDto>> findAll();
    ApiResponse<UsuarioDto> findById(Integer id);



    @Transactional
    ApiResponse<UsuarioDto> registrarUsuario(RegistroUsuarioDto dto);

    ApiResponse<UsuarioDto> update(Integer id, UsuarioDto dto);
    void delete(Integer id);
    ApiResponse<UsuarioDto> verificarUsuario(String correo, String contrasena);
    ApiResponse<UsuarioDto> login(String correo, String contrasena);


}
