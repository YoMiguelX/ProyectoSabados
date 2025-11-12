package com.example.demo.Controller;

import com.example.demo.Dto.UsuarioDto;
import com.example.demo.Dto.Response.ApiResponse;
import com.example.demo.Interface.IUsuarioService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class JugadorController {

    @Autowired
    private IUsuarioService usuarioService;

    @GetMapping("/jugador")
    public String verPerfilJugador(HttpSession session, Model model) {
        if (session.getAttribute("usuarioId") == null) {
            return "redirect:/login";
        }

        Integer usuarioId = (Integer) session.getAttribute("usuarioId");
        ApiResponse<UsuarioDto> respuesta = usuarioService.findById(usuarioId);

        if (respuesta.getHttpStatusCode() == 200) {
            model.addAttribute("usuario", respuesta.getData());
            return "perfil_jugador";
        } else {
            model.addAttribute("error", respuesta.getMessage());
            return "redirect:/login";
        }
    }

    @GetMapping("/perfil/salir")
    public String cerrarSesion(HttpSession session) {
        if (session != null) {
            session.invalidate();
        }
        return "redirect:/login?logout=true";
    }
}
