package mx.uam.ayd.proyecto.presentacion.cobro;

import javax.swing.JFrame;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mx.uam.ayd.proyecto.presentacion.venta.ControlVenta;

/*
 * Esta clase lleva el flujo del cobro
 */
@Component
public class ControlCobro {

	@Autowired
	private VentanaCobro ventanaCobro;
	@Autowired
	private ControlVenta controlVenta;

	/**
	 * 
	 * Inicia historia de usuario: Forma de cobro
	 */
	public void inicia(float total) {
		ventanaCobro.muestra(this, total);
	}

	public void termina() {
		ventanaCobro.setVisible(false);
	}

	/**
	 * Método para obtener un lista de los productos de la venta.
	 * 
	 * @param total
	 */
	public void obtenerLista(float total) {
		controlVenta.obtenerLista(total);
	}

	public void muestraDialogo() {
		ventanaCobro.muestraDialogo();
	}

	public void limpiarTabla() {
		controlVenta.limpiarTabla();
		
	}

	/**
	 * Termina historia de usuario: Forma de Cobro
	 */

}
