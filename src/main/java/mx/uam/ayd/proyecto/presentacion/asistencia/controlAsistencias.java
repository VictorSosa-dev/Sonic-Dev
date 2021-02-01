package mx.uam.ayd.proyecto.presentacion.asistencia;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mx.uam.ayd.proyecto.negocio.ServicioAsistencia;
import mx.uam.ayd.proyecto.negocio.ServicioEmpleado;
import mx.uam.ayd.proyecto.negocio.modelo.Asistencia;
import mx.uam.ayd.proyecto.negocio.modelo.Empleado;
import mx.uam.ayd.proyecto.presentacion.controlEmpleados.ControlEmpleados;
import mx.uam.ayd.proyecto.presentacion.inicioSesion.ControlInicioSesion;
import mx.uam.ayd.proyecto.presentacion.monitoreo.ControlMonitoreo;
/**
 * 
 * @author Luis Cristofer Alvarado Gabriel
 *
 */

@Component
public class controlAsistencias {
	@Autowired
	private ControlMonitoreo controlMonitoreo;
	@Autowired
	private ControlInicioSesion controlInicioSesion;
	@Autowired
	private ventanaAsistencias ventana;
	@Autowired
	private ServicioAsistencia servicioAsistencia;
	@Autowired
	private ServicioEmpleado servicioEmpleado;
	
	public void inicia(Empleado empleado) {
		//this.iniciaAsistencias();
		ventana.muestra(this, empleado);
	}
	
	/*public void iniciaAsistencias(Empleado empleado) {
		
		List <Asistencia> asistencias = servicioAsistencia.recuperarasistencia();
		for(Asistencia asistencia:asistencias) {
			ventana.llenaTabla(empleado, asistencia, this.servicioEmpleado);
		}
	}*/
	public void asistencia(Empleado empleado) {
		ventana.agregaAsistencia(empleado);
	}
	
	public void iniciaTablaEmpleados(ServicioEmpleado servicioEmpleado) {
		ventana.llenaTablaEmpleados(servicioEmpleado);
	}
	
}
