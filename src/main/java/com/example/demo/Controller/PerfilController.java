package com.example.demo.Controller;

import com.example.demo.Dto.Response.ApiResponse;
import com.example.demo.Dto.UsuarioDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
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



        public PerfilController(UsuarioService usuarioService) {
            this.usuarioService = usuarioService;
        }
    @GetMapping("/perfil")
    public String mostrarPerfil(HttpSession session, Model model) {
        Usuario usuario = (Usuario) session.getAttribute("usuarioLog");

        if (usuario == null) {
            return "redirect:/login";
        }

        model.addAttribute("usuario", usuario);
        return "perfil"; // nombre de la vista
    }

}

