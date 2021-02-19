package mx.uam.ayd.proyecto.presentacion.reporteVenta;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mx.uam.ayd.proyecto.negocio.ServicioReporteVenta;
import mx.uam.ayd.proyecto.negocio.modelo.Empleado;
import mx.uam.ayd.proyecto.negocio.modelo.ReporteVenta;

@Component
public class ControlMuestraReportesVenta {

	@Autowired
	VentanaMuestraReportesVenta ventana;
	
	@Autowired 
	ServicioReporteVenta servicioReporteVenta;
	
	public void inicia(Empleado empleado) {
		List<ReporteVenta> reportesVentas = servicioReporteVenta.recuperaReportes();
		ventana.muestra(this, empleado, reportesVentas);
	}
	
}
