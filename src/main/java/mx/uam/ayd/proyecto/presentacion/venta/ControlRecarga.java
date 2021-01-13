package mx.uam.ayd.proyecto.presentacion.venta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mx.uam.ayd.proyecto.presentacion.recarga.VentanaConfirmacion;
import mx.uam.ayd.proyecto.presentacion.recarga.VentanaRecarga;

@Component
public class ControlRecarga {
	
@Autowired
private VentanaRecarga ventanaRecarga;

@Autowired
private VentanaConfirmacion ventanaConfirmacion;

@Autowired
private ControlVenta controlVenta;

public void inicia() {
	ventanaRecarga.setVisible(true);
}


}
