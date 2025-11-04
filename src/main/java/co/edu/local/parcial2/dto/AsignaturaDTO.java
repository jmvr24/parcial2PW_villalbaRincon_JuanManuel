package co.edu.local.parcial2.dto;


import co.edu.local.parcial2.model.Usuarios;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AsignaturaDTO {

	private Long asignId;
	private String asignNombre;
	private String descripcion;
	private int salon;
	private int hInicio;
	private int hFinal;
	private Usuarios docente;
	
	/**
	 * @param asignNombre
	 * @param descripcion
	 * @param salon
	 * @param hInicio
	 * @param hFinal
	 * @param docente
	 */
	public AsignaturaDTO(String asignNombre, String descripcion, int salon, int hInicio, int hFinal, Usuarios docente) {
		super();
		this.asignNombre = asignNombre;
		this.descripcion = descripcion;
		this.salon = salon;
		this.hInicio = hInicio;
		this.hFinal = hFinal;
		this.docente = docente;
	}

	/**
	 * @param hInicio
	 * @param hFinal
	 */
	public AsignaturaDTO(int hInicio, int hFinal) {
		super();
		this.hInicio = hInicio;
		this.hFinal = hFinal;
	}
	
	
	
}
