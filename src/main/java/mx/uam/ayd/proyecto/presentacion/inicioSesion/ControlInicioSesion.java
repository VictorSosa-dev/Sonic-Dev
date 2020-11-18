
package mx.uam.ayd.proyecto.presentacion.inicioSesion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mx.uam.ayd.proyecto.negocio.ServicioEmpleado;
import mx.uam.ayd.proyecto.negocio.modelo.Empleado;
import mx.uam.ayd.proyecto.presentacion.agregarUsuario.ControlAgregarUsuario;
import mx.uam.ayd.proyecto.presentacion.listarUsuarios.ControlListarUsuarios;
import mx.uam.ayd.proyecto.presentacion.monitoreo.ControlMonitoreo;
import mx.uam.ayd.proyecto.presentacion.principal.empleado.ControlPrincipalEmpleados;
import mx.uam.ayd.proyecto.presentacion.principal.encargado.ControlPrincipalEncargado;

/**
 * Esta clase lleva el flujo de control de la ventana principal
 * 
 * @author humbertocervantes
 *
 */
@Component
public class ControlInicioSesion {

	@Autowired
	private ControlAgregarUsuario controlAgregarUsuario;
	
	@Autowired
	private ControlListarUsuarios controlListarUsuarios;
	
	@Autowired
	private ControlPrincipalEmpleados controlPrincipalEmpleados;
	
	@Autowired
	private VentanaInicioSesion ventana;
	
	@Autowired
	private ServicioEmpleado servicioEmpleado;
	
	@Autowired
	private ControlPrincipalEncargado controlPrincipalEncargado;
	
	@Autowired
	private ControlMonitoreo controlmonitoreo;

	
	
	/**
	 * Inicia el flujo de control de la ventana principal
	 * 
	 */
	public void inicia() {

		ventana.muestra(this);
	}

	/**
	 * Método que arranca la historia de usuario "agregar usuario"
	 * 
	 */
	public void agregarUsuario() {
		
		controlAgregarUsuario.inicia();
		
	}
	
	/**
	 * Método que arranca la historia de usuario "listar usuarios"
	 * 
	 */
	public void listarUsuarios() {
		controlListarUsuarios.inicia();
	}

	public void validaUsuario(String usuario, String password) {
		try {
			Empleado empleado = servicioEmpleado.validaUsuario(usuario, password);
			if(empleado.getNivel().equals("empleado")) {
				controlmonitoreo.registrarInicio(empleado);
				controlPrincipalEmpleados.inicia(empleado);
				ventana.oculta();
			} if(empleado.getNivel().equals("encargado")) {
				controlmonitoreo.registrarInicio(empleado);
				controlPrincipalEncargado.inicia(empleado);
				ventana.oculta();
			}
		} catch (Exception e) {
			ventana.muestraErrorPassword(e);
		}
		
	}
}
