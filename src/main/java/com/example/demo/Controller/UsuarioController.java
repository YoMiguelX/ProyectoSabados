package com.example.demo.Controller;

import com.example.demo.Dto.RegistroUsuarioDto;
import com.example.demo.Dto.Response.ApiResponse;
import com.example.demo.Dto.UsuarioDto;
import com.example.demo.Interface.IUsuarioService;
import com.example.demo.Model.Usuario;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    public String mostrarLogin(Model model) {
        return "login"; // nombre del archivo plantilla login.html
    }

    @PostMapping("/login")
    public String validarLogin(@RequestParam String correo,
                               @RequestParam String contrasena,
                               HttpSession session) {

        ApiResponse<UsuarioDto> response = usuarioService.login(correo, contrasena);

        if (response.getData() == null) {
            return "redirect:/login?error=true";
        }

        UsuarioDto usuario = response.getData();

        session.setAttribute("usuarioLog", usuario);
        session.setAttribute("rol", usuario.getRolId());

        if (usuario.getRolId() == 1) {
            return "redirect:/admin/lista";
        } else {
            return "redirect:/perfil";
        }
    }


    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}
