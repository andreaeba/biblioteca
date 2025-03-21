package com.egg.biblioteca.servicios;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.egg.biblioteca.entidades.Autor;
import com.egg.biblioteca.entidades.Editorial;
import com.egg.biblioteca.entidades.Libro;
import com.egg.biblioteca.excepciones.MyException;
import com.egg.biblioteca.repositorios.AutorRepositorio;
import com.egg.biblioteca.repositorios.EditorialRepositorio;
import com.egg.biblioteca.repositorios.LibroRepositorio;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class LibroServicio {

    @Autowired
    private LibroRepositorio libroRepositorio;

    @Autowired
    private AutorRepositorio autorRepositorio;

    @Autowired
    private EditorialRepositorio editorialRepositorio;

    @Transactional
    public void crearLibro(Long isbn, String titulo, int ejemplares, String idAutor, String idEditorial) throws MyException {

        validar(isbn, titulo, ejemplares, idAutor, idEditorial);

        Autor autor = autorRepositorio.findById(String.valueOf(idAutor)).get();

        Editorial editorial = editorialRepositorio.findById(String.valueOf(idEditorial)).get();

        Libro libro = new Libro();

        libro.setIsbn(isbn);
        libro.setTitulo(titulo);
        libro.setEjemplares(ejemplares);
        libro.setAlta(new Date());

        libro.setAutor(autor);
        libro.setEditorial(editorial);

        libroRepositorio.save(libro);
    }

    public List<Libro> ListarLibros() {
        List<Libro> libros = new ArrayList<>();
        libros = libroRepositorio.findAll();
        return libros;
    }

    @Transactional
    public void modificarLibro(Long isbn, String titulo, int ejemplares, String idAutor, String idEditorial) throws MyException{

        validar(isbn, titulo, ejemplares, idAutor, idEditorial);

        Optional<Libro> respuesta = libroRepositorio.findById(isbn);
        Optional<Autor> respuestaAutor = autorRepositorio.findById(String.valueOf(idAutor));
        Optional<Editorial> respuestaEditorial = editorialRepositorio.findById(String.valueOf(idEditorial));

        Autor autor = new Autor();

        Editorial editorial = new Editorial();

        if(respuestaAutor.isPresent()){
            autor = respuestaAutor.get();
        }

        if(respuestaEditorial.isPresent()){
            editorial = respuestaEditorial.get();
        }

        if (respuesta.isPresent()) {
            Libro libro = respuesta.get();

            libro.setTitulo(titulo);
            libro.setEjemplares(ejemplares);
            libro.setAutor(autor);
            libro.setEditorial(editorial);

            libroRepositorio.save(libro);
        }


    }

    public Libro getOne(Long isbn){
        return libroRepositorio.getOne(isbn);
    }

    private void validar(Long isbn, String titulo, int ejemplares, String idAutor, String idEditorial) throws MyException {
        if(isbn == null) {
            throw new MyException("El ISBN no puede ser nulo");
        }
        if (titulo == null || titulo.isEmpty()  ) {
            throw new MyException("El título no puede estar vacío o ser nulo");
        }
        if (ejemplares <= 0 ) {
            throw new MyException("Los ejemplares no pueden ser menores o igual a cero");
        }
        if (idAutor == null || idAutor.isEmpty()) {
            throw new MyException("El idAutor no puede estar vacío o ser nulo");
        }

        if (idEditorial == null || idEditorial.isEmpty()) {
            throw new MyException("El idAutor no puede estar vacío o ser nulo");
        }
    }

}
