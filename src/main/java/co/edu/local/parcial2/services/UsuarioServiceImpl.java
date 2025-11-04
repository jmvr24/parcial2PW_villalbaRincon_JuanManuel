package co.edu.local.parcial2.services;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import co.edu.local.parcial2.dto.UsuarioDTO;
import co.edu.local.parcial2.model.Rol;
import co.edu.local.parcial2.model.Usuarios;
import co.edu.local.parcial2.repository.RolRepository;
import co.edu.local.parcial2.repository.UsuarioRepository;

public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	UsuarioRepository usuarioRepository;

	@Autowired
	RolRepository rolRepository;

	@Autowired
	PasswordEncoder passwordEncoder;

	
	private Collection<? extends GrantedAuthority> mapearAutoridadRoles(Collection<Rol> roles){
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getRolName())).collect(Collectors.toList());
	}

	@Override
	public Usuarios save(UsuarioDTO usuarioDTO) {
		if (usuarioRepository.findAll().size() == 0) {
		}
		Usuarios usuario = new Usuarios(usuarioDTO.getNombre(), passwordEncoder.encode(usuarioDTO.getPassword()),
				Arrays.asList(rolRepository.findByRolName("ROLE_RECTOR")));

		return usuarioRepository.save(usuario);
	}

	@Override
	public Usuarios saveAdmin(UsuarioDTO usuarioDTO) {
		Rol rol = rolRepository.findById(usuarioDTO.getRol())
				.orElseThrow(() -> new RuntimeException("Rol no encontrado"));

		Usuarios usuario = new Usuarios(usuarioDTO.getNombre(), passwordEncoder.encode(usuarioDTO.getPassword()),
				Arrays.asList(rol));
		return usuarioRepository.save(usuario);
	}

	@Override
	public boolean validarUsername(UsuarioDTO usuarioDTO) {
		Usuarios usuario = usuarioRepository.findByNombre(usuarioDTO.getNombre());

		if (usuario == null)
			return true;

		return false;
	}

	@Override
	public List<Usuarios> listarUsuarios() {
		return usuarioRepository.findAll();
	}

	@Override
	public List<Usuarios> listarDocentes() {
		return usuarioRepository.findAll().stream()
				.filter(usuario -> usuario.getRol().stream().anyMatch(rol -> rol.getRolName().equals("ROLE_DOCENTE")))
				.toList();
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuarios usuario = usuarioRepository.findByNombre(username);
		System.out.println(usuario);
		if (usuario == null) {
			throw new UsernameNotFoundException("Usuario o Password Inválidos");
		}

		return new User(usuario.getNombre(), usuario.getPassword(), mapearAutoridadRoles(usuario.getRol()));
	}

}
