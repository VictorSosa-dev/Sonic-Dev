package mx.uam.ayd.proyecto.presentacion.informeInventario;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.hibernate.internal.build.AllowSysOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mx.uam.ayd.proyecto.negocio.ServicioDetallePedidoProveedor;
import mx.uam.ayd.proyecto.negocio.ServicioPedidoProveedor;
import mx.uam.ayd.proyecto.negocio.ServicioProducto;
import mx.uam.ayd.proyecto.negocio.modelo.Empleado;
import mx.uam.ayd.proyecto.negocio.modelo.PedidoProveedor;
import mx.uam.ayd.proyecto.negocio.modelo.Producto;
import mx.uam.ayd.proyecto.presentacion.principal.encargado.ControlPrincipalEncargado;

@Component
public class ControlRealizarUnPedido {
	
	@Autowired
	private VentanaRealizarUnPedido ventanaRealizarUnPedido;
	
	@Autowired
	private ControlPrincipalEncargado controlPrincipalEncargado;
	
	@Autowired
	private ServicioPedidoProveedor servicioPedidoProveedor;
	
	@Autowired
	private ServicioDetallePedidoProveedor servicioDetallePedidoProveedor;
	
	@Autowired
	private ServicioProducto servicioProducto;
	
	private List<Producto> listaProductos = new ArrayList<>();
	private Producto producto;
	
	public void inicia(Empleado empleado, List<Producto> productos) {
		ventanaRealizarUnPedido.muestra(this, empleado, productos);
	}
	
	public void mandaPedido(Empleado empleado, String nombre, int piezas, float precioT) {
		try {
			
			producto = servicioProducto.buscarProducto(nombre);
			Calendar fecha = new GregorianCalendar();
			int anio = fecha.get(Calendar.YEAR);
			int mes = fecha.get(Calendar.MONTH);
			int dia = fecha.get(Calendar.DAY_OF_MONTH);
			String fechaF = anio + "/" + mes + "/" + dia; 
			PedidoProveedor pedido = new PedidoProveedor(fechaF, piezas, precioT);
			servicioPedidoProveedor.guardar(pedido);
			//servicioDetallePedidoProveedor.agregarDetallePedidoProveedor(pedido, producto, piezas);
			//controlPrincipalEncargado.inicia(empleado);
		}catch(Exception e) {
			ventanaRealizarUnPedido.muestraDialogoConMensaje("No se pudo mandar el pedido");
			System.out.println(e);
		}
	}
	
	public float calculaPrecioTotal(String nombre, int piezas) {
		producto = servicioProducto.buscarProducto(nombre);
		float precioTotal = 0;
		if(producto != null) {
			precioTotal = producto.getPrecio()*piezas;
		}
		
		return precioTotal;
	}
	
	public void cancelar(Empleado empleado) {
		if (empleado.getNivel().equals("encargado")) {
			controlPrincipalEncargado.inicia(empleado);
			ventanaRealizarUnPedido.oculta();
		}
	}
	//prueba
	public Producto obtenerProducto(String nombre) {
		try {
			return servicioProducto.buscarProducto(nombre);
		}catch(Exception e) {
			return null;
		}
	}
	
}
