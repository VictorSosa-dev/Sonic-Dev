package mx.uam.ayd.proyecto.presentacion.cobro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mx.uam.ayd.proyecto.presentacion.venta.ControlVenta;

@Component
public class ControlCobro {
	
	@Autowired
	private VentanaCobro ventanaCobro;
	@Autowired
	private ControlVenta controlVenta;
	
	public void inicia(float total) {
		
		ventanaCobro.muestra(this,total);

	}

	public void termina() {
		ventanaCobro.setVisible(false);
		
	}

	public void obtenerLista(float total) {
		controlVenta.obtenerLista(total);
		
	}

	public void muestraDialogo() {
		ventanaCobro.muestraDialogo();
	}

}
