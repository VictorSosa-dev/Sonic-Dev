package mx.uam.ayd.proyecto.presentacion.PedidoCliente;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mx.uam.ayd.proyecto.negocio.ServicioProducto;
import mx.uam.ayd.proyecto.negocio.modelo.Empleado;
import mx.uam.ayd.proyecto.negocio.modelo.Producto;
import mx.uam.ayd.proyecto.presentacion.cierreVenta.ControlCierreVenta;
import mx.uam.ayd.proyecto.presentacion.inicioSesion.ControlInicioSesion;
import mx.uam.ayd.proyecto.presentacion.venta.ControlVenta;
@Component
public class ControlPedidoCliente {

	@Autowired
	private VentanaPedidoCliente ventana;
	
	@Autowired
	private ControlVenta controlVenta;

	@Autowired
	private ServicioProducto servicioProducto;
	
	@Autowired
	private ControlCierreVenta controlCierreVenta;
	
	@Autowired
	private ControlInicioSesion controlInicioSesion;

	/**
	 * Inicia el flujo de control de la ventana crear pedido cliente
	 * 
	 */
	public void inicia(Empleado empleado) {
		ventana.muestra(this, empleado);
	}

	public List<Producto> obtenerProductosConReceta() {
		return servicioProducto.obtenerProductosConReceta();
		
		
	}

}
