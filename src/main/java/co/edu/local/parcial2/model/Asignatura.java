package co.edu.local.parcial2.model;

import io.micrometer.common.lang.NonNull;
import jakarta.annotation.Nonnull;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "asignaturas")
public class Asignatura {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long asignId;
	
	@NonNull
	@Column(name = "asig_nombre")
	private String asignNombre;
	
	@NonNull
	@Column(name = "asig_descripcion")
	private String descripcion;
	
	@NonNull
	@Column(name="asig_salon")
	private int salon;
	
	@NonNull
	@Column(name = "asig_h_inicio")
	private int hInicio;
	
	@NonNull
	@Column(name = "asig_h_final")
	private int hFinal;
	
	@Nonnull
	@ManyToOne(cascade = {CascadeType.DETACH, CascadeType.REFRESH},
    fetch = FetchType.EAGER)
	@JoinColumn(name = "docente_id")
	private Usuarios docente;

	/**
	 * @param asignNombre
	 * @param descripcion
	 * @param salon
	 * @param hInicio
	 * @param hFinal
	 * @param docente
	 */
	public Asignatura(String asignNombre, String descripcion, int salon, int hInicio, int hFinal, Usuarios docente) {
		super();
		this.asignNombre = asignNombre;
		this.descripcion = descripcion;
		this.salon = salon;
		this.hInicio = hInicio;
		this.hFinal = hFinal;
		this.docente = docente;
	}
	
	

}
