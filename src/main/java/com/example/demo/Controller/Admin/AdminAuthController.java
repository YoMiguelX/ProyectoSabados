package com.example.demo.Controller.Admin;

import com.example.demo.Dto.LoginDto;
import com.example.demo.Dto.RegistroUsuarioDto;
import com.example.demo.Dto.Response.ApiResponse;
import com.example.demo.Dto.UsuarioDto;
import com.example.demo.Interface.IUsuarioService;
import com.example.demo.Model.Usuario;
import com.example.demo.Services.PasswordResetService;
import com.example.demo.Services.UsuarioService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping("/auth")
public class AdminAuthController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private PasswordEncoder passwordEncoder;



    @GetMapping("/login")
    public String mostrarLogin(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "login";
    }

    @PostMapping("/login")
    public String procesarLogin(@ModelAttribute Usuario usuario,
                                HttpSession session,
                                RedirectAttributes redirect) {

        Usuario encontrado = usuarioService.buscarPorCorreo(usuario.getCorreoUsuario());

        if (encontrado == null || !passwordEncoder.matches(usuario.getContrasena(), encontrado.getContrasena())) {
            redirect.addFlashAttribute("error", "Credenciales incorrectas");
            return "redirect:/auth/login";
        }

        // Guardamos datos en sesión
        session.setAttribute("usuarioId", encontrado.getIdUsuario());
        session.setAttribute("rol", encontrado.getRol());

        return (encontrado.getRol().getIdRol() == 1)
                ? "redirect:/admin/lista"
                : "redirect:/perfil";  // vista para usuario normal
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/auth/login";
    }
}
