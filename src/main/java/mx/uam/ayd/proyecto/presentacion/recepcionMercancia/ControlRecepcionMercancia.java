package mx.uam.ayd.proyecto.presentacion.recepcionMercancia;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Esta clase se encarga de llevar el flujo de control de la ventana de Recepción de Mercancía
 * 
 * @author KarinaVergara 
 *
 */
@Component
public class ControlRecepcionMercancia {
	@Autowired
	private VentanaRecepcionMercancia ventana;
	
	/**
	 * Inicia el flujo de control de la ventana de cierre de venta
	 * 
	 */
	public void inicia() {
		ventana.muestra(this);
	}
}
