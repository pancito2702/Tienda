package com.tienda.services;


import com.tienda.dao.UsuarioDao;
import com.tienda.domain.Rol;
import com.tienda.domain.Usuario;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class UsuarioService implements UserDetailsService {

 
    @Autowired
    private UsuarioDao usuarioDao;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //Buscar el usuario en la table de usuarios
       Usuario usuario = usuarioDao.findByUsername(username);
       
       //Se verifica que el usuario existe... sino se lanza una excepcion
       if (usuario == null) {
           throw new UsernameNotFoundException(username);
       }
       
       //Se cargan los roles del usuario
       ArrayList roles = new ArrayList<GrantedAuthority>();
       
  
       //Por medio de la asociacion se recuperan los roles de la tabla de roles       
       for (Rol rol: usuario.getRoles()) {
           roles.add(new SimpleGrantedAuthority(rol.getNombre()));
       }
       return new User(usuario.getUsername(), usuario.getPassword(), roles);
    }
    
}
