package mx.uam.ayd.proyecto.presentacion.reporteVenta;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mx.uam.ayd.proyecto.negocio.ServicioDetalleVenta;
import mx.uam.ayd.proyecto.negocio.ServicioProducto;
import mx.uam.ayd.proyecto.negocio.ServicioVenta;
import mx.uam.ayd.proyecto.negocio.modelo.DetalleVenta;
import mx.uam.ayd.proyecto.negocio.modelo.Empleado;
import mx.uam.ayd.proyecto.negocio.modelo.Producto;
import mx.uam.ayd.proyecto.negocio.modelo.Venta;

@Component
public class ControlGeneraReporteVenta {
	
	@Autowired
	ServicioProducto servicioProducto;
	
	@Autowired
	VentanaGeneraReporteVenta ventana;
	
	@Autowired
	ServicioVenta servicioVenta;
	
	private Calendar fecha = new GregorianCalendar();
	private int ano = fecha.get(Calendar.YEAR);
	private int mes = fecha.get(Calendar.MONTH);
	private int dia = fecha.get(Calendar.DAY_OF_MONTH);
	private String fechaF = ano + "/" + mes + "/" + dia;
	
	public void inicia(Empleado empleado, int idVenta) {
		List<Venta> ventas = servicioVenta.obtenerVentasPorFecha(fechaF);
		Venta ventaSel;
		List<Producto> productos = new ArrayList();
		for(Venta venta : ventas) {
			if(venta.getIdVenta() == (long) idVenta) {
				ventaSel = venta;
				for (DetalleVenta detalleVenta : ventaSel.getVentas()) {
					productos.add(servicioProducto.obtenerProductoPorVenta(detalleVenta).get(0));
				}
			}
		}
		//productos.addAll(servicioProducto.obtenerProductoPorVenta(dV));
		
		for(Producto prod : productos) {
			ventana.llenaDatosVenta(prod, idVenta, productos.size());
		}
		ventana.muestra(this, empleado);
	}
}
