package mx.uam.ayd.proyecto.negocio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import mx.uam.ayd.proyecto.datos.EmpleadoRepository;
import mx.uam.ayd.proyecto.negocio.modelo.Empleado;

@Slf4j
@Service
public class ServicioEmpleado {

	@Autowired
	private EmpleadoRepository empleadoRepository;

	public Empleado validaUsuario(String usuario, String password) {
		Empleado empleado = empleadoRepository.findByUsuario(usuario);
		if (validaPassword(password, empleado.getPassword())) {
			return empleado;
		} else {
			throw new IllegalArgumentException("La contraseña es incorrecta");
		}
	}

	private boolean validaPassword(String passwordIngresada, String password) {
		if (password.equals(passwordIngresada)) {
			return true;
		} else {
			return false;
		}
	}
	
	public Empleado guardarEmpleado(Empleado empleado) {
		return empleadoRepository.save(empleado);
	}

}
