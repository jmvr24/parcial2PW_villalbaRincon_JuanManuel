package co.edu.local.parcial2.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTO {
	
	private Long userId;
	private String nombre;
	private String password;
	private Long rol;
	
	/**
	 * @param nombre
	 * @param password
	 * @param rol
	 */
	public UsuarioDTO(String nombre, String password, Long rol) {
		super();
		this.nombre = nombre;
		this.password = password;
		this.rol = rol;
	}
	
	/**
	 * @param nombre
	 * @param password
	 */
	public UsuarioDTO(String nombre, String password) {
		super();
		this.nombre = nombre;
		this.password = password;
	}
	
	
}
