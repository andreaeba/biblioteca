package com.egg.biblioteca.servicios;


import com.egg.biblioteca.entidades.Usuario;
import com.egg.biblioteca.enumeraciones.Rol;
import com.egg.biblioteca.excepciones.MyException;
import com.egg.biblioteca.repositorios.UsuarioRepositorio;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioServicio implements UserDetailsService {

        @Autowired
        private UsuarioRepositorio usuarioRepositorio;

        public void registrar(String nombre, String email, String password, String password2) throws MyException {

                validar(nombre, email, password, password2);

                Usuario usuario = new Usuario();

                usuario.setNombre(nombre);
                usuario.setEmail(email);
                usuario.setPassword(new BCryptPasswordEncoder().encode(password));
                usuario.setRol(Rol.USER);

                usuarioRepositorio.save(usuario);
        }

        private void validar(String nombre, String email, String password, String password2) throws MyException{

                if (nombre == null || nombre.isEmpty()  ) {
                        throw new MyException("El nombre no puede estar vacío o ser nulo");
                }

                if (email == null || email.isEmpty()) {
                        throw new MyException("El email no puede estar vacío o ser nulo");
                }

                if (password == null || password.isEmpty() || password.length() <= 5) {
                        throw new MyException("El password no puede estar vacío o ser nulo, y debe contener más de 5 dígitos");
                }
                if(!password.equals(password2)){
                        throw new MyException("Las contraseñas ingresadas deben ser iguales");
                }
        }

        @Override
        public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

                Usuario usuario = usuarioRepositorio.buscarPorEmail(email);

                if(usuario != null){

                        List<GrantedAuthority> permisos = new ArrayList<>();
                        GrantedAuthority p = new SimpleGrantedAuthority("ROLE_"+ usuario.getRol().toString());
                        permisos.add(p);
                        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
                        HttpSession session = attr.getRequest().getSession(true);
                        session.setAttribute("usuariosession", usuario);

                        return new User(usuario.getEmail(), usuario.getPassword(), permisos);
                } else {
                        return null;
                }

        }
}
