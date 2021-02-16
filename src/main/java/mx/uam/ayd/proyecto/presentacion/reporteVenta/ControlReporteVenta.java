package mx.uam.ayd.proyecto.presentacion.reporteVenta;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mx.uam.ayd.proyecto.negocio.ServicioProducto;
import mx.uam.ayd.proyecto.negocio.ServicioVenta;
import mx.uam.ayd.proyecto.negocio.modelo.DetalleVenta;
import mx.uam.ayd.proyecto.negocio.modelo.Empleado;
import mx.uam.ayd.proyecto.negocio.modelo.Producto;
import mx.uam.ayd.proyecto.negocio.modelo.Venta;
import mx.uam.ayd.proyecto.presentacion.monitoreo.ControlMonitoreo;
import mx.uam.ayd.proyecto.presentacion.venta.ControlVenta;
/**
 * Clase que lleva el flujo de control de la lista de 
 * detalle de ventas.
 * @author Luis Cristofer Alvarado Gabriel
 * @since 13/02/2021 
 */
@Component
public class ControlReporteVenta {

	@Autowired
	VentanaReporteVenta ventana;
	@Autowired
	ControlVenta controlVenta;
	@Autowired
	ServicioVenta servicioVenta;
	@Autowired
	ControlMonitoreo controlMonitoreo;
	@Autowired
	ServicioProducto servicioProducto;
	@Autowired
	ControlGeneraReporteVenta controlGeneraReporteVenta;
	
	private Calendar fecha = new GregorianCalendar();
	private int ano = fecha.get(Calendar.YEAR);
	private int mes = fecha.get(Calendar.MONTH);
	private int dia = fecha.get(Calendar.DAY_OF_MONTH);
	private String fechaF = ano + "/" + mes + "/" + dia;
	
	/**
	 * Obtiene las ventas que se realizaron el dia actual
	 * llena la tabla e inicia la ventana
	 * @param emp
	 */
	public void inicia(Empleado emp) {
		List<Venta> ventas = servicioVenta.obtenerVentasPorFecha(fechaF);
		for(Venta venta : ventas) {
			List<Producto> productos = new ArrayList<Producto>();
			for (DetalleVenta detalleVenta : venta.getVentas()) {
				productos.add(servicioProducto.obtenerProductoPorVenta(detalleVenta).get(0));
			}
			ventana.llenaTablaVentas(venta, productos.size());
		}
		ventana.muestra(this, emp);
	}
	
	public void inciaGeneraReporteVenta(Empleado empleado, int idVenta) {
		controlGeneraReporteVenta.inicia(empleado, idVenta);
	}
	
}
