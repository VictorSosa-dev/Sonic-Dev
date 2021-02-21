package mx.uam.ayd.proyecto.presentacion.principal.encargado;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mx.uam.ayd.proyecto.negocio.modelo.Empleado;
import mx.uam.ayd.proyecto.presentacion.actualizaInventario.ControlActualiza;
import mx.uam.ayd.proyecto.presentacion.asistencia.controlAsistencias;
import mx.uam.ayd.proyecto.presentacion.busqueda.ControlBusqueda;
import mx.uam.ayd.proyecto.presentacion.cierreVenta.ControlCierreVenta;
import mx.uam.ayd.proyecto.presentacion.controlEmpleados.ControlEmpleados;
import mx.uam.ayd.proyecto.presentacion.informeInventario.ControlInformeInventario;
import mx.uam.ayd.proyecto.presentacion.inicioSesion.ControlInicioSesion;
import mx.uam.ayd.proyecto.presentacion.inventario.ControlInventario;
import mx.uam.ayd.proyecto.presentacion.monitoreo.ControlMonitoreo;
import mx.uam.ayd.proyecto.presentacion.pedidoRealizado.ControlPedidoRealizado;
import mx.uam.ayd.proyecto.presentacion.reporteEmpleados.ControlReporteEmpleados;
import mx.uam.ayd.proyecto.presentacion.venta.ControlVenta;
import mx.uam.ayd.proyecto.presentacion.ventaMembresia.ControlClientes;

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
	
	@Autowired
	private ControlInventario controlInventario;
	
	@Autowired
	private ControlActualiza controlActualiza;

	@Autowired
	private ControlPedidoRealizado controlPedidoRealizado;
	
	@Autowired
	private ControlEmpleados controlEmpleados;
	
	@Autowired
	private ControlReporteEmpleados controlReporteEmpleados;
	
	@Autowired
	private controlAsistencias controlAsistencias;
	
	@Autowired
	private ControlClientes controlClientes;
	
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
	public void agregarProductos(Empleado empleado) {

		controlVenta.inicia(empleado);
	}

	public void agregarEmpleado(Empleado empleado) {
		controlEmpleados.inicia(empleado);
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

	public void iniciaInventario(Empleado empleado) {
		controlInventario.inicia(empleado);
		ventana.oculta();		
	}
	
	public void muestraVentanaActualiza(Empleado empleado) {
		controlActualiza.inicia(empleado);
	}
	
	/**
	 * Inicia la ventana que muestra los productos a los cuales
	 * se le realizo un pedido al proveedor
	 * @param empleado nombre del empleado encargado de realizar el pedido
	 */
	public void pedidosRealizados(Empleado empleado) {
		controlPedidoRealizado.inicia(empleado);
		//ventana.oculta();
	}
	
	/**
	 * Inicia la ventana relacionada a la HU-14
	 * @param empleado nombre del empleado encargado de realizar el reporte
	 */
	public void generarReporte(Empleado empleado) {
		controlReporteEmpleados.inicia(empleado);
	}
	

	public void asistenciasEmp(Empleado empleado) {
		// TODO Auto-generated method stub
		//controlAsistencias.iniciaAsistencias(empleado);
		controlAsistencias.inicia(empleado);
	}
	
	public void iniciaClientes(Empleado empleado) {
		controlClientes.iniciaVentana(empleado);
		ventana.oculta();
	}
	
}
