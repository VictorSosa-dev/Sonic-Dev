package mx.uam.ayd.proyecto.presentacion.principal.empleado;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mx.uam.ayd.proyecto.negocio.modelo.Empleado;
import mx.uam.ayd.proyecto.presentacion.PedidoCliente.ControlPedidoCliente;
import mx.uam.ayd.proyecto.presentacion.cierreVenta.ControlCierreVenta;
import mx.uam.ayd.proyecto.presentacion.inicioSesion.ControlInicioSesion;
import mx.uam.ayd.proyecto.presentacion.venta.ControlVenta;
@Component
public class ControlPrincipalEmpleados {

	@Autowired
	private ControlVenta controlVenta;

	@Autowired
	private VentanaPrincipalEmpleados ventana;
	
	@Autowired
	private ControlCierreVenta controlCierreVenta;
	
	@Autowired
	private ControlInicioSesion controlInicioSesion;
	
	@Autowired
	private ControlPedidoCliente controlPedidoCliente;

	/**
	 * Inicia el flujo de control de la ventana principal
	 * 
	 */
	public void inicia(Empleado empleado) {
		ventana.muestra(this, empleado);
	}

	/**
	 * Método que arranca la historia de usuario "agregar productos para la venta"
	 * 
	 */
	public void agregarProductos() {

		controlVenta.inicia();
	}

	public void iniciaCierreVenta(Empleado empleado) {
		controlCierreVenta.inicia(empleado);
		
	}

	public void cerrarSesion() {
		controlInicioSesion.inicia();
		ventana.oculta();
		
	}

	public void agregaPedidoCliente(Empleado empleado) {
		controlPedidoCliente.inicia(empleado);
		ventana.oculta();
		
	}

}
