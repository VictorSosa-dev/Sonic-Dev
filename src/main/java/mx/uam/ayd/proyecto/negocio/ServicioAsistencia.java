package mx.uam.ayd.proyecto.negocio;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import mx.uam.ayd.proyecto.datos.AsistenciaRepository;
import mx.uam.ayd.proyecto.negocio.modelo.Asistencia;
import mx.uam.ayd.proyecto.negocio.modelo.Empleado;
import mx.uam.ayd.proyecto.negocio.modelo.Producto;
import mx.uam.ayd.proyecto.negocio.modelo.Usuario;

@Slf4j
@Service
public class ServicioAsistencia {

	@Autowired
	private AsistenciaRepository asistenciaRepository;

//	@Autowired
//	private Asistencia asistencia;

	public void registroAsistencia(String horaInicial, String horaFinal, String fecha, Empleado empleado) {
		// this.asistencia=asistencia.getNombre();

		Asistencia asistencia = new Asistencia();
		asistencia.setHoraInicial(horaInicial);
		asistencia.setHoraFinal(horaFinal);
		asistencia.setFecha(fecha);
		asistencia.setEmpleado(empleado);
		// asistencia.setNombre(nombre);
		asistenciaRepository.save(asistencia);
	}

	public void actualizar(String horaInicial, String horaFinal) {
		int piezas;
		Asistencia asistencia = asistenciaRepository.findByHoraInicial(horaInicial);
		asistencia.setHoraFinal(horaFinal);

		asistenciaRepository.save(asistencia);

//		piezas = producto.getPiezas();
//		producto.setPiezas(piezas - 1);
//		producto.getNombre();
//		producto.getPiezas();
//		producto.getCompuesto();
//		producto.getReceta();
//		producto.getUbicacion();
//		producto.getPrecio();

//		productoRepository.save(producto);

	}

//	public void registroAsistencia(String Horaini, String Horafin, String fecha) {
//		//this.asistencia=asistencia.getNombre();
//	   Asistencia asistencia= new Asistencia();
//	   // asistencia.setNombre(nombre);
//	    asistencia.setHoraInicial(Horaini);
//	    asistencia.setHoraFinal(Horafin);
//	    asistencia.setFecha(fecha);
//
//		asistenciaRepository.save(asistencia);
//	}

	public List<Asistencia> recuperarasistencia() {
		List<Asistencia> asistencias = new ArrayList<>();

		for (Asistencia asistencia : asistenciaRepository.findAll()) {
			asistencias.add(asistencia);
		}

		return asistencias;
	}

	public List<Asistencia> obtenerAsistenciasPorEmpleado(Empleado empleado) {
		return asistenciaRepository.findByEmpleado(empleado);
	}

	public void actualizar(Asistencia asistenciaAEditar) {
		 asistenciaRepository.save(asistenciaAEditar);
	}

//	public List <Asistencia> eliminarasistencias() {
//		List <Asistencia> asistencias = new ArrayList<>();
//		
//		for(Asistencia asistencia:asistenciaRepository.findAll()) {
//			asistencias.add(asistencia);
//		}
//				
//		return asistencias;
//	}

}
