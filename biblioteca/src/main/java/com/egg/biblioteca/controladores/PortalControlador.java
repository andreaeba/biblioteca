package com.egg.biblioteca.controladores;


import com.egg.biblioteca.entidades.Usuario;
import com.egg.biblioteca.excepciones.MyException;
import com.egg.biblioteca.servicios.UsuarioServicio;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Controller
@RequestMapping("/")
public class PortalControlador {

    @Autowired
    private UsuarioServicio usuarioServicio;

    @GetMapping("/")
    public String index(){
     return "index.html";
    }

    @GetMapping("/registrar")
    public String registrar(){
        return "registro.html";
    }

    @PostMapping("/registro")
    public String registro(@RequestParam String nombre, @RequestParam String email, @RequestParam String password, String password2, ModelMap modelo, MultipartFile archivo){

        try{
            usuarioServicio.registrar(archivo, nombre, email, password, password2);
            modelo.put("exito", "El registro fue exitoso!");

            return "index.html";
        } catch (MyException ex){
            modelo.put("error", ex.getMessage());
            modelo.put("nombre", nombre);
            modelo.put("email", email);

            return "registro.html";
        }
    }

    @GetMapping("/login")
    public String login(@RequestParam(required = false) String error, ModelMap modelo ){
        if(error!=null){
            modelo.put("error", "Usuario o contraseña inválidos");
        }
        return "login.html";
    }

    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @GetMapping("/inicio")
    public String inicio(HttpSession session){

        Usuario logueado = (Usuario) session.getAttribute("usuariosession");

        if(logueado.getRol().toString().equals("ADMIN")){
            return "redirect:/admin/dashboard";

        }
        return "inicio.html";
    }

    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @GetMapping("/perfil")
    public String perfil(ModelMap modelo, HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuariosession");
        modelo.put("usuario", usuario);

        return "usuario_modificar.html";
    }

    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @PostMapping("/perfil/{id}")
    public String actualizar(MultipartFile archivo, @PathVariable UUID id, @RequestParam String nombre, @RequestParam String email,
                             @RequestParam String password, @RequestParam String password2, ModelMap modelo) {

        try{
            usuarioServicio.actualizar(archivo, String.valueOf(id), nombre, email, password, password2);
            modelo.put("exito", "El usuario fue actualizado correctamente.");
            return "inicio.html";
        } catch (MyException ex) {
            modelo.put("error", ex.getMessage());
            modelo.put("nombre", nombre);
            modelo.put("email", email);
            return "usuario_modificar.html";
        }
    }

    @GetMapping("/logout")
    public String logout() {
        return "login.html";
    }

}
