package com.example.demo.Controller.Admin;


import com.example.demo.Model.Rol;
import com.example.demo.Model.Usuario;
import com.example.demo.Services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin")
public class AdminCRUDController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @GetMapping("")
    public String redireccionarAdmin() {
        return "redirect:/admin/lista";
    }

    @GetMapping("/crear")
    public String crear(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "admin/crear_admin";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Usuario usuario, RedirectAttributes redirect) {
        Rol rolAdmin = new Rol(); // creas un nuevo rol
        rolAdmin.setIdRol(1);     // le asignas el ID que corresponde a administrador
        usuario.setRol(rolAdmin); // ahora sí funciona
        usuario.setContrasena(passwordEncoder.encode(usuario.getContrasena()));
        usuarioService.guardarUsuario(usuario);
        redirect.addFlashAttribute("success", "Administrador creado correctamente");
        return "redirect:/admin/crear";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Integer id, Model model) {
        model.addAttribute("usuario", usuarioService.findById(id));
        return "admin/editar";
    }

    @PostMapping("/editar/{id}")
    public String actualizar(@ModelAttribute Usuario usuario) {
        usuarioService.guardarUsuario(usuario);
        return "redirect:/admin/lista";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Integer id) {
        usuarioService.delete(id);
        return "redirect:/admin/lista";
    }
}
