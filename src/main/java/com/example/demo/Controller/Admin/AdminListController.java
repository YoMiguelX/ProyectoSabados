package com.example.demo.Controller.Admin;
import com.example.demo.Dto.FiltroDTO;
import com.example.demo.Dto.LoginDto;
import com.example.demo.Dto.RegistroUsuarioDto;
import com.example.demo.Dto.Response.ApiResponse;
import com.example.demo.Dto.UsuarioDto;
import com.example.demo.Interface.IUsuarioService;
import com.example.demo.Model.Usuario;
import com.example.demo.Services.ExcelExportService;
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

import java.io.IOException;
import java.util.List;


@Controller
@RequestMapping("/admin")
public class AdminListController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private ExcelExportService excelExportService;




    @GetMapping("/lista")
    public String listarUsuarios(FiltroDTO filtros, Model model) {
        List<Usuario> administradores = usuarioService.filtrar(1,
                filtros.getNombre(), filtros.getApellido(), filtros.getCorreo(), filtros.getTelefono());

        List<Usuario> usuarios = usuarioService.filtrar(2,
                filtros.getNombre(), filtros.getApellido(), filtros.getCorreo(), filtros.getTelefono());

        model.addAttribute("administradores", administradores);
        model.addAttribute("usuarios", usuarios);
        model.addAttribute("filtros", filtros);

        return "admin/lista";
    }

    @GetMapping("/exportar")
    public void exportarExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=usuarios.xlsx");
        excelExportService.exportarUsuariosAExcel((List<Usuario>) usuarioService.findAll(), response);
    }
}
