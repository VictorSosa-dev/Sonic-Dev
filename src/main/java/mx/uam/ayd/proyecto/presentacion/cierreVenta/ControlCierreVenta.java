package mx.uam.ayd.proyecto.presentacion.cierreVenta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mx.uam.ayd.proyecto.negocio.modelo.Empleado;

/**
 * Esta clase se encarga de llevar el flujo de control de la ventana de Cierre de Venta
 * 
 * @author KarinaVergara 
 *
 */
@Component
public class ControlCierreVenta {
	@Autowired
	private VentanaCierreVenta ventana;
	
	/**
	 * Inicia el flujo de control de la ventana de cierre de venta
	 * @param empleado contiene los datos del empleado que ha iniciado sesion
	 * 
	 */
	public void inicia(Empleado empleado) {
		ventana.muestra(this, empleado);
	}
}
