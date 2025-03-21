package com.egg.biblioteca.controladores;


import com.egg.biblioteca.entidades.Autor;
import com.egg.biblioteca.entidades.Editorial;
import com.egg.biblioteca.entidades.Libro;
import com.egg.biblioteca.excepciones.MyException;
import com.egg.biblioteca.servicios.AutorServicio;
import com.egg.biblioteca.servicios.EditorialServicio;
import com.egg.biblioteca.servicios.LibroServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Controller
@RequestMapping("/libro")
public class LibroControlador {

    @Autowired
    private LibroServicio libroServicio;

    @Autowired
    private AutorServicio autorServicio;

    @Autowired
    private EditorialServicio editorialServicio;

    @GetMapping("/registrar")
    private String registrar(ModelMap modelo) {
        List<Autor> autores = autorServicio.listarAutores();
        List<Editorial> editoriales = editorialServicio.listarEditoriales();

        modelo.addAttribute("autores", autores);
        modelo.addAttribute("editoriales", editoriales);
        return "libro_form.html";
    }

    @PostMapping("/registro")
    private String registro(@RequestParam(required = false) Long isbn, @RequestParam String titulo, @RequestParam(required = false) int ejemplares, @RequestParam String idAutor, @RequestParam String idEditorial, ModelMap modelo) {

        try{
            libroServicio.crearLibro(isbn, titulo, ejemplares, idAutor, idEditorial);
            modelo.put("exito", "El libro fue cargado exitosamente!");
        } catch (MyException ex) {
            modelo.put("error", ex.getMessage());
            return "libro_form.html";
        }
        return "index.html";
    }

    @GetMapping("/lista")
    public String listar(ModelMap modelo) {


        List<Libro> libros =  libroServicio.ListarLibros();
        modelo.addAttribute("libros", libros);
        return "libro_lista.html";
    }

    @GetMapping("/modificar/{isbn}")
    public String modificar(@PathVariable Long isbn, ModelMap modelo) {

        modelo.put("libro", libroServicio.getOne(isbn));

        return "libro_modificar.html";

    }


    @PostMapping("/modificar/{isbn}")
    public String modificar(@PathVariable Long isbn, String titulo, int ejemplares, String idAutor, String idEditorial, ModelMap modelo) {

        try{
            libroServicio.modificarLibro(isbn, titulo, ejemplares, idAutor, idEditorial);

            return "redirect:../lista";
        } catch (MyException ex){
            modelo.put("error", ex.getMessage());
        }
        return "libro_modificar.html";
    }



}
