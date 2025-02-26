package com.egg.biblioteca.servicios;

import com.egg.biblioteca.entidades.Editorial;
import com.egg.biblioteca.repositorios.EditorialRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EditorialServicio {

    @Autowired
    private EditorialRepositorio editorialRepositorio;

    private void crearEditorial(String nombre){

        Editorial editorial = new Editorial();

        editorial.setNombre(nombre);

        editorialRepositorio.save(editorial);
    }


}
