package com.example.demo.Controller;

import com.example.demo.Model.Usuario;
import com.example.demo.Services.UsuarioService;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PerfilController {

    private final UsuarioService usuarioService;

    public PerfilController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/perfil")
    public String mostrarPerfil(HttpSession session, Model model) {
        String correo = (String) session.getAttribute("correo");

        if (correo == null) {
            return "redirect:/login";
        }

        Usuario usuario = usuarioService.buscarPorCorreo(correo);
        model.addAttribute("usuario", usuario);
        return "perfil";
    }

}
