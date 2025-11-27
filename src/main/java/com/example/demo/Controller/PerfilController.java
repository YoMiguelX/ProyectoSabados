package com.example.demo.Controller;

import com.example.demo.Dto.Response.ApiResponse;
import com.example.demo.Dto.UsuarioDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.Services.UsuarioService;
import com.example.demo.Model.Usuario;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
public class PerfilController {

    @Autowired
    private UsuarioService usuarioService;
    @GetMapping("/perfil")
    public String mostrarPerfil(HttpSession session, Model model , HttpServletResponse response) {

        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);

        Integer usuarioId = (Integer) session.getAttribute("usuarioId");
        if (usuarioId == null) {
            return "redirect:/login";
        }

        ApiResponse<UsuarioDto> respuesta = usuarioService.findById(usuarioId);
        if (respuesta == null || respuesta.getData() == null) {
            return "redirect:/login";
        }

        //  aquí pasa solo el DTO
        model.addAttribute("usuario", respuesta.getData());
        return "perfil";
    }



}
