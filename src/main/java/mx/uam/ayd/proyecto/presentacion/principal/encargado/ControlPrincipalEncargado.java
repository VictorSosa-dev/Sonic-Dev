package mx.uam.ayd.proyecto.presentacion.principal.encargado;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mx.uam.ayd.proyecto.negocio.modelo.Empleado;
import mx.uam.ayd.proyecto.presentacion.busqueda.ControlBusqueda;
import mx.uam.ayd.proyecto.presentacion.cierreVenta.ControlCierreVenta;
import mx.uam.ayd.proyecto.presentacion.informeInventario.ControlInformeInventario;
import mx.uam.ayd.proyecto.presentacion.inicioSesion.ControlInicioSesion;
import mx.uam.ayd.proyecto.presentacion.monitoreo.ControlMonitoreo;
import mx.uam.ayd.proyecto.presentacion.venta.ControlVenta;

@Component
public class ControlPrincipalEncargado {

	@Autowired
	private ControlVenta controlVenta;

	@Autowired
	private VentanaPrincipalEncargado ventana;
	
	@Autowired
	private ControlCierreVenta controlCierreVenta;
	
	@Autowired
	private ControlInicioSesion controlInicioSesion;
	
	@Autowired
	private ControlMonitoreo controlMonitoreo;
	
	@Autowired
	private ControlBusqueda controlBusqueda;
	
	@Autowired
	private ControlCierreVenta controlPedidoCliente;
	
	@Autowired
	private ControlCierreVenta controlRecepcionMercancia;
	
	@Autowired
	private ControlInformeInventario controlInformeInventario;

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
		ventana.oculta();
		
	}

	public void cerrarSesion(Empleado empleado) {
		controlMonitoreo.registrarCerrar(empleado);
		controlInicioSesion.inicia();
		ventana.oculta();
		
	}

	public void monitoreo() {
		controlMonitoreo.inicia();
		
	}

	public void busqueda() {
		controlBusqueda.inicia();
		
	}

	public void agregaPedidoCliente(Empleado empleado) {
		controlPedidoCliente.inicia(empleado);
		ventana.oculta();
		
	}

	public void iniciaRecepcionMercancia(Empleado empleado) {
		controlRecepcionMercancia.inicia(empleado);
		ventana.oculta();
		
	}
	
	/**
	 * Inicia la ventana para realizar un pedido al proveedor
	 * HU-04
	 * @param empleado nombre del empleado encargado de hacer el pedido
	 */
	public void informeInventario(Empleado empleado) {
		controlInformeInventario.inicia(empleado);
		ventana.oculta();
	}

}
