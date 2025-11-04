package co.edu.local.parcial2.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import co.edu.local.parcial2.dto.AsignaturaDTO;
import co.edu.local.parcial2.model.Asignatura;
import co.edu.local.parcial2.model.Usuarios;
import co.edu.local.parcial2.repository.AsignaturaRepository;
import co.edu.local.parcial2.repository.UsuarioRepository;

public class AsignaturaServiceImpl implements AsignaturaService {

	@Autowired
	AsignaturaRepository asignaturaRepository;

	@Autowired
	UsuarioRepository usuarioRepository;


	@Override
	public boolean validarAsignatura(AsignaturaDTO asignaturaDTO) {
		Asignatura asignatura = asignaturaRepository.findByAsignNombre(asignaturaDTO.getAsignNombre());

		if (asignatura == null) {
			return true;
		}
		return false;
	}

	@Override
	public Asignatura save(AsignaturaDTO asignaturaDTO) {
		Asignatura asignatura = new Asignatura(asignaturaDTO.getAsignNombre(), asignaturaDTO.getDescripcion(),
				asignaturaDTO.getSalon(), asignaturaDTO.getHInicio(), asignaturaDTO.getHFinal(),
				asignaturaDTO.getDocente());
		return asignaturaRepository.save(asignatura);
	}
	
	@Override
	public List<Asignatura> listarAsignaturas() {
		return asignaturaRepository.findAll();
	}

	@Override
	public void actualizarUser(AsignaturaDTO asignaturaDTO) {
		Usuarios usuario = usuarioRepository.findByNombre(asignaturaDTO.getDocente().getNombre());
		Asignatura asignatura = asignaturaRepository.findById(asignaturaDTO.getAsignId())
				.orElseThrow(() -> new RuntimeException("Asignatura no encontrada"));
		asignatura.setAsignNombre(asignaturaDTO.getAsignNombre());
		asignatura.setDescripcion(asignaturaDTO.getDescripcion());
		asignatura.setSalon(asignaturaDTO.getSalon());
		asignatura.setHInicio(asignaturaDTO.getHInicio());
		asignatura.setHFinal(asignaturaDTO.getHFinal());
		asignatura.setDocente(asignaturaDTO.getDocente());
		asignaturaRepository.save(asignatura);
	}

	@Override
	public void actualizarDocente(AsignaturaDTO asignaturaDTO) {
		Asignatura asignatura = asignaturaRepository.findById(asignaturaDTO.getAsignId())
				.orElseThrow(() -> new RuntimeException("Asignatura no encontrada"));
		asignatura.setHInicio(asignaturaDTO.getHInicio());
		asignatura.setHFinal(asignaturaDTO.getHFinal());
		asignaturaRepository.save(asignatura);
	}

	@Override
	public void eliminarAsignatura(Long id) {
		asignaturaRepository.deleteById(id);
	}

	@Override
	public boolean validarActualizar(AsignaturaDTO asignaturaDTO) {
		Asignatura asignatura = asignaturaRepository.findById(asignaturaDTO.getAsignId())
				.orElseThrow(() -> new RuntimeException("Asignatura no encontrada"));

		if (asignatura.getAsignNombre().equals(asignaturaDTO.getAsignNombre()))
			return true;

		return false;
	}

}
