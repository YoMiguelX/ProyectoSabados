package com.example.demo.Dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class RegistroUsuarioDto {
    @NotNull
    private Integer rolId;
    @NotBlank private String nombre;
    @NotBlank private String apellido;
    @Email private String correo;
    @NotBlank private String contrasena;
    @Size(min = 7, max = 15) private String telefono;
    // Constructor vacío
    public RegistroUsuarioDto() {}

    // Constructor con parámetros
    public RegistroUsuarioDto(Integer rolId, String nombre, String apellido, String correo, String contrasena, String telefono) {
        this.rolId = rolId;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.contrasena = contrasena;
        this.telefono = telefono;
    }

    // Getters y setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    public @NotNull Integer getRolId(){
        return rolId;
    }
    public void setRolId(Integer rolId){
        this.rolId = rolId;

    }
}
