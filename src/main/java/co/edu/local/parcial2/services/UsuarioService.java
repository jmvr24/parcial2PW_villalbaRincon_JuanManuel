package co.edu.local.parcial2.services;

import co.edu.local.parcial2.dto.UsuarioDTO;
import co.edu.local.parcial2.model.Usuarios;

public interface UsuarioService {
    void save(UsuarioDTO usuarioDTO);
    void saveAdmin(UsuarioDTO usuarioDTO);
    boolean validarUsername(UsuarioDTO usuarioDTO);
    Usuarios findByNombre(String nombre);
}
