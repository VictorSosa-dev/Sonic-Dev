package mx.uam.ayd.proyecto.presentacion.venta;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mx.uam.ayd.proyecto.negocio.ServicioProducto;
import mx.uam.ayd.proyecto.negocio.modelo.Producto;
import mx.uam.ayd.proyecto.presentacion.cobro.VentanaCobro;

/*
 * Esta clase lleva el flujo de la ventana de venta
 */

@Component 
public class ControlVenta {
	
	@Autowired
	private VentanaVenta ventanaVenta;
	
	@Autowired
	private VentanaProducto ventanaProducto;
	
	@Autowired
	private VentanaCobro ventanaCobro;
	
	@Autowired
	private ServicioProducto servicioProducto;
	
	/**
	 * Inicia la historia de usuario
	 * 
	 */
	public void inicia() {
		
		ventanaVenta.muestra(this);
		
	}

	public void buscarProducto(String nombre) {
		
		try {
			ventanaProducto.muestra(this);
			ventanaProducto.llena(servicioProducto.buscarProducto(nombre));

		} catch(Exception ex) {
			cerrarVentana();
			ventanaVenta.muestraDialogoConMensaje("Error al buscar el Producto: "+ex.getMessage());
			
		}
	}

	public void termina() {
		ventanaProducto.setVisible(false);		
	}
	
	public void cerrarVentana() {
		ventanaProducto.setVisible(false);		
	}
	
	public void agregarTabla(Producto producto) {
		
		try{
			ventanaVenta.llenaTabla(producto);
			ventanaVenta.muestraDialogoConMensaje("Producto agregado exitosamente");
		} catch(Exception ex){
			ventanaVenta.muestraDialogoConMensaje("Error al buscar el Producto: "+ex.getMessage());
		
		}
		
		cerrarVentana();
	}

	public void actulizaInventario(String nombre) {
		servicioProducto.Actualiza(nombre);
		
	}

	public void total(float precio) {
		ventanaVenta.textTotal(precio);
	}
	
	
}
