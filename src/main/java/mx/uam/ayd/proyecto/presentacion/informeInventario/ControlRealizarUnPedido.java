package mx.uam.ayd.proyecto.presentacion.informeInventario;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mx.uam.ayd.proyecto.negocio.modelo.Empleado;
import mx.uam.ayd.proyecto.negocio.modelo.Producto;
import mx.uam.ayd.proyecto.presentacion.principal.encargado.ControlPrincipalEncargado;

@Component
public class ControlRealizarUnPedido {
	
	@Autowired
	private VentanaRealizarUnPedido ventanaRealizarUnPedido;
	
	@Autowired
	private ControlPrincipalEncargado controlPrincipalEncargado;
	
	
	
	public void inicia(Empleado empleado, List<Producto> productos) {
		ventanaRealizarUnPedido.muestra(this, empleado, productos);
	}
	
	public void mandaPedido(String nombre, int piezas) {
		
	}
	
	public void cancelar(Empleado empleado) {
		if (empleado.getNivel().equals("encargado")) {
			controlPrincipalEncargado.inicia(empleado);
			ventanaRealizarUnPedido.oculta();
		}
	}
	
}
