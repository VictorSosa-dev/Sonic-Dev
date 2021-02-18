package mx.uam.ayd.proyecto.presentacion.reporteVenta;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mx.uam.ayd.proyecto.negocio.ServicioDetalleVenta;
import mx.uam.ayd.proyecto.negocio.ServicioProducto;
import mx.uam.ayd.proyecto.negocio.ServicioReporteVenta;
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
	
	@Autowired
	ServicioReporteVenta servicioReporteVenta;
	
	private Calendar fecha = new GregorianCalendar();
	private int ano = fecha.get(Calendar.YEAR);
	private int mes = fecha.get(Calendar.MONTH);
	private int dia = fecha.get(Calendar.DAY_OF_MONTH);
	private String fechaF = ano + "/" + mes + "/" + dia;
	
	public void inicia(Empleado empleado, long idVenta) {
		List<Venta> ventas = servicioVenta.obtenerVentasPorFecha(fechaF);
		Venta ventaSel = new Venta();
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
		ventana.muestra(this, empleado, ventaSel);
	}
	
	public void generaReporteVenta(Venta venta, long idVenta, String comentario, Empleado empleado) {
		String empQueReporta = empleado.getNombre() + " " + empleado.getApellido();
		LocalDate hoy = LocalDate.now();
		int anio= hoy.getYear();
		int mes = hoy.getMonthValue();
		int dia= hoy.getDayOfMonth();
		String fecha = dia+"/"+mes+"/"+anio;
		servicioReporteVenta.registroReporteVenta(venta, fecha, idVenta, comentario, empQueReporta);
	}
}
