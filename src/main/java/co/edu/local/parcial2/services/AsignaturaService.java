package co.edu.local.parcial2.services;

import java.util.List;

import co.edu.local.parcial2.dto.AsignaturaDTO;
import co.edu.local.parcial2.model.Asignatura;

public interface AsignaturaService {
	public List<Asignatura> listarAsignaturas();
	public boolean validarAsignatura(AsignaturaDTO asignaturaDTO);
	public Asignatura save(AsignaturaDTO asignaturaDTO);
	public void eliminarAsignatura(Long id);
	public void actualizarUser(AsignaturaDTO asignaturaDTO);
	public void actualizarDocente(AsignaturaDTO asignaturaDTO);
	public boolean validarActualizar(AsignaturaDTO asignaturaDTO);

}
