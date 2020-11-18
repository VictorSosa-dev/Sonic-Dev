package mx.uam.ayd.proyecto.presentacion.cierreVenta;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mx.uam.ayd.proyecto.negocio.ServicioCliente;
import mx.uam.ayd.proyecto.negocio.ServicioPedidoCliente;
import mx.uam.ayd.proyecto.negocio.ServicioProducto;
import mx.uam.ayd.proyecto.negocio.ServicioVenta;
import mx.uam.ayd.proyecto.negocio.modelo.Cliente;
import mx.uam.ayd.proyecto.negocio.modelo.DetallePedidoCliente;
import mx.uam.ayd.proyecto.negocio.modelo.DetalleVenta;
import mx.uam.ayd.proyecto.negocio.modelo.Empleado;
import mx.uam.ayd.proyecto.negocio.modelo.PedidoCliente;
import mx.uam.ayd.proyecto.negocio.modelo.Producto;
import mx.uam.ayd.proyecto.negocio.modelo.Venta;
import mx.uam.ayd.proyecto.presentacion.principal.empleado.ControlPrincipalEmpleados;
import mx.uam.ayd.proyecto.presentacion.principal.encargado.ControlPrincipalEncargado;

/**
 * Esta clase se encarga de llevar el flujo de control de la ventana de Cierre
 * de Venta
 * 
 * @author KarinaVergara
 *
 */
@Component
public class ControlCierreVenta {
	@Autowired
	private VentanaCierreVenta ventana;

	@Autowired
	private ServicioVenta servicioVenta;

	@Autowired
	private ServicioProducto servicioProducto;
	
	@Autowired
	private ServicioPedidoCliente servicioPedidoCliente;
	
	@Autowired
	private ServicioCliente servicioCliente;
	
	@Autowired
	private ControlPrincipalEmpleados controlPrincipalEmpleado;
	
	@Autowired
	private ControlPrincipalEncargado controlPrincipalEncargado;

	/**
	 * Inicia el flujo de control de la ventana de cierre de venta
	 * 
	 * @param empleado contiene los datos del empleado que ha iniciado sesion
	 * 
	 */
	public void inicia(Empleado empleado) {
		ventana.muestra(this, empleado);
	}

	public void obtenerVentasDia(String fechaF) {
		List<Venta> ventasDia = servicioVenta.obtenerVentasPorFecha(fechaF);

		for (Venta venta : ventasDia) {
			List<Producto> productos = new ArrayList<Producto>();
			for (DetalleVenta detalleVenta : venta.getVentas()) {
				productos.add(servicioProducto.obtenerProductoPorVenta(detalleVenta).get(0));
			}
			ventana.agregaVentas(venta, productos.size());
		}
	}

	public void obtenerProductos() {
		List<Producto> productos = servicioProducto.obtenerProductos();
		for (Producto producto : productos) {
			ventana.agregaProductos(producto);
		}
	}

	public void obtenerPedidosClienteDelDia(String fechaF) {
		System.out.println("######### FECHA PARAMETRO: " + fechaF);
		List<PedidoCliente> pedidosClienteDia = servicioPedidoCliente.obtenerPedidosPorFechaCreacion(fechaF);
		//System.out.println("#### PEDIDOS CLIENTE X DIA: " + pedidosClienteDia);
		for (PedidoCliente pedidoCliente : pedidosClienteDia) {
			List<Cliente> cliente = servicioCliente.obtenerClientePorPedido(pedidoCliente);
			List<Producto> productos = new ArrayList<Producto>();
			for (DetallePedidoCliente detallePedidoCliente : pedidoCliente.getDetallesPedidoCliente()) {
				productos.add(detallePedidoCliente.getProducto());
			}
			//System.out.println("#### CLIENTE: " + cliente);
			//System.out.println("#### PRODUCTOS: " + productos);
			ventana.agregarPedido(pedidoCliente, productos, cliente);
		}
	}

	public void cancelaCierreVenta(Empleado empleado) {
		if (empleado.getNivel().equals("empleado")) {
			controlPrincipalEmpleado.inicia(empleado);
			ventana.oculta();
		} else if (empleado.getNivel().equals("encargado")) {
			controlPrincipalEncargado.inicia(empleado);
			ventana.oculta();
		}
		
	}
}
