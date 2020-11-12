package mx.uam.ayd.proyecto.presentacion.principal.empleado;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mx.uam.ayd.proyecto.presentacion.venta.ControlVenta;

@Component
public class ControlPrincipalEmpleados {

	@Autowired
	private ControlVenta controlVenta;
	
	@Autowired
	private VentanaPrincipalEmpleados ventana;
	
	
	/**
	 * Inicia el flujo de control de la ventana principal
	 * 
	 */
	public void inicia() {

		ventana.muestra(this);
	}
	
	/**
	 * Método que arranca la historia de usuario "agregar productos para la venta"
	 * 
	*/
	public void agregarProductos() {
		
		controlVenta.inicia();
	}
	
	
	
	
	
}
