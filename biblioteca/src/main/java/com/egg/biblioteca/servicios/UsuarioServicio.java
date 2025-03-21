package com.egg.biblioteca.servicios;


import com.egg.biblioteca.repositorios.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServicio {

        @Autowired
        private UsuarioRepositorio usuarioRepositorio;



}
