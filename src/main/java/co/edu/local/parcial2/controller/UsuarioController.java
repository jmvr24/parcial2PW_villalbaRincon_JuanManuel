package co.edu.local.parcial2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import co.edu.local.parcial2.dto.UsuarioDTO;
import co.edu.local.parcial2.model.Usuarios;
import co.edu.local.parcial2.services.UsuarioService;

@Controller
public class UsuarioController {
	
	@GetMapping("/")
	public String motrarInicio() {
		return "index";
	}
	
	@GetMapping("/login")
	public String mostrarLogin() {
		return "login";
	}
	
	@Autowired
	private UsuarioService usuarioServicio;
	
	@ModelAttribute("usuario")
	public UsuarioDTO NuevoUsuario() {
		return new UsuarioDTO();
	}
	
	@GetMapping("/registro")
	public String mostrarRegistro(Model model) {
	    model.addAttribute("usuario", new Usuarios());
	    return "registro";
	}
	
	@PostMapping("/registro")
	public String registrarUsuario(@ModelAttribute("usuario") UsuarioDTO usuarioDTO) {
		if (usuarioServicio.validarUsername(usuarioDTO)) {
			usuarioServicio.save(usuarioDTO);
			return "redirect:/registro?exito";
		}else {
			return "redirect:/registro?error";
		}
	}
	
	
	@GetMapping("/rector/usuarios/registro")
	public String mostrarRegistrarAdminUsuario(Model modelo) {
		modelo.addAttribute("usuario", new UsuarioDTO());
		return "registroAdmin";
	}
	
	@PostMapping("/rector/usuarios/registro")
	public String registrarAdminUsuario(@ModelAttribute("usuario") UsuarioDTO usuarioDTO) {
		if (usuarioServicio.validarUsername(usuarioDTO)) {
			usuarioServicio.saveAdmin(usuarioDTO);
			return "redirect:/rector/usuarios/registro?exito";
		}else {
			return "redirect:/rector/usuarios/registro?error";
		}
	}

}
