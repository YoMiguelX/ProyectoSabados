package com.example.demo.Controller;

import com.example.demo.Dto.RegistroUsuarioDto;
import com.example.demo.Dto.Response.ApiResponse;
import com.example.demo.Dto.UsuarioDto;
import com.example.demo.Interface.IUsuarioService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UsuarioController {

    @Autowired
    private IUsuarioService usuarioService;

    @GetMapping("/registro")
    public String mostrarRegistro(Model model) {
        model.addAttribute("usuario", new RegistroUsuarioDto());
        return "registro";
    }

    @PostMapping("/registro")
    public String registrarUsuario(@ModelAttribute("usuario") RegistroUsuarioDto dto, Model model) {
        ApiResponse<UsuarioDto> respuesta = usuarioService.registrarUsuario(dto);

        if (respuesta.getHttpStatusCode() == 201) {
            return "redirect:/login?registro=exitoso";
        } else {
            if (respuesta.getMessage().contains("correo")) {
                return "redirect:/login?error=correoExistente";
            }
            model.addAttribute("error", respuesta.getMessage());
            return "registro";
        }
    }

    @GetMapping("/login")
    public String mostrarLogin(HttpSession session) {
        if (session.getAttribute("usuarioLogueado") != null) {
            return "redirect:/home";
        }
        return "login";
    }

    @PostMapping("/login")
    public String procesarLogin(
            @RequestParam String correo,
            @RequestParam String contrasena,
            HttpSession session,
            Model model) {

        ApiResponse<UsuarioDto> respuesta = usuarioService.login(correo, contrasena);

        if (respuesta.getHttpStatusCode() == 200) {
            UsuarioDto dto = respuesta.getData();
            session.setAttribute("usuarioId", dto.getId());
            session.setAttribute("usuarioLogueado", true);

            if (dto.getRolId() != null && dto.getRolId() == 1) {
                return "redirect:/admin/lista";
            } else {
                return "redirect:/perfil";
            }
        } else {
            model.addAttribute("error", respuesta.getMessage());
            return "login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}
