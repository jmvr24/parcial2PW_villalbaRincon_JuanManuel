package co.edu.local.parcial2.services;

import co.edu.local.parcial2.dto.UsuarioDTO;
import co.edu.local.parcial2.model.Rol;
import co.edu.local.parcial2.model.Usuarios;
import co.edu.local.parcial2.repository.RolRepository;
import co.edu.local.parcial2.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioServiceImpl implements UsuarioService, UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RolRepository rolRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void save(UsuarioDTO usuarioDTO) {
        Usuarios usuario = new Usuarios();
        usuario.setNombre(usuarioDTO.getNombre());
        usuario.setPassword(passwordEncoder.encode(usuarioDTO.getPassword()));
        usuario.setRol(List.of(rolRepository.findByRolName("ESTUDIANTE")));
        usuarioRepository.save(usuario);
    }

    @Override
    public void saveAdmin(UsuarioDTO usuarioDTO) {
        Usuarios usuario = new Usuarios();
        usuario.setNombre(usuarioDTO.getNombre());
        usuario.setPassword(passwordEncoder.encode(usuarioDTO.getPassword()));
        usuario.setRol(List.of(rolRepository.findByRolName("RECTOR")));
        usuarioRepository.save(usuario);
    }

    @Override
    public boolean validarUsername(UsuarioDTO usuarioDTO) {
        return usuarioRepository.findByNombre(usuarioDTO.getNombre()) == null;
    }

    @Override
    public Usuarios findByNombre(String nombre) {
        return usuarioRepository.findByNombre(nombre);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuarios usuario = usuarioRepository.findByNombre(username);
        if (usuario == null) {
            throw new UsernameNotFoundException("Usuario no encontrado: " + username);
        }
        return new User(
                usuario.getNombre(),
                usuario.getPassword(),
                mapearAutoridadRoles(usuario.getRol())
        );
    }

    private List<SimpleGrantedAuthority> mapearAutoridadRoles(Collection<Rol> roles) {
        return roles.stream()
                .map(rol -> new SimpleGrantedAuthority("ROLE_" + rol.getRolName()))
                .collect(Collectors.toList());
    }
}
