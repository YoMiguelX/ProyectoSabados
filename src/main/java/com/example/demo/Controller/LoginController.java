package com.example.demo.Controller;

import com.example.demo.Model.Usuario;
import com.example.demo.Services.UsuarioService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    private final UsuarioService usuarioService;

    public LoginController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }
    @PostMapping("/login")
    public String login(@RequestParam String correo,
                        @RequestParam String contrasena,
                        HttpSession session,
                        Model model) {

        try {
            Usuario usuario = usuarioService.loginWeb(correo, contrasena);

            session.setAttribute("usuarioId", usuario.getIdUsuario());
            session.setAttribute("correo", usuario.getCorreoUsuario());
            session.setAttribute("rol", usuario.getRol().getIdRol());

            if (usuario.getRol().getIdRol() == 1) {
                return "redirect:/admin/lista";
            } else {
                return "redirect:/perfil";
            }

        } catch (Exception e) {
            model.addAttribute("error", "Correo o contraseña incorrectos");
            return "login";
        }
    }

}
