package mx.uam.ayd.proyecto.presentacion.venta;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mx.uam.ayd.proyecto.negocio.ServicioDetalleVenta;
import mx.uam.ayd.proyecto.negocio.ServicioProducto;
import mx.uam.ayd.proyecto.negocio.ServicioVenta;
import mx.uam.ayd.proyecto.negocio.modelo.Producto;
import mx.uam.ayd.proyecto.negocio.modelo.Venta;
import mx.uam.ayd.proyecto.negocio.modelo.DetalleVenta;
import mx.uam.ayd.proyecto.presentacion.cobro.ControlCobro;
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
	private ControlCobro controlCobro;
	
	@Autowired
	private ServicioProducto servicioProducto;
	
	@Autowired
	private ServicioVenta servicioVenta;
	
	@Autowired
	private ServicioDetalleVenta servicioDetalleVenta;
	
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
	

	public Producto obtenerProducto(String nombre) {
		
		try {
		return	servicioProducto.buscarProducto(nombre);

		} catch(Exception ex) {
		return null;	
			
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

	public void actulizaInventario1(String nombre) {
		servicioProducto.actualizaMenos(nombre);
		
	}
	
	public void actulizaInventario2(String nombre) {
		servicioProducto.actualizaMas(nombre);
		
	}
	public void total(float precio) {
		ventanaVenta.textTotal(precio);
	}

	public void muentraCobro(float total) {
		controlCobro.inicia(total);
		
	}


	public void obtenerLista(float total) {
		
		List <Producto> listaProductos = new ArrayList <> ();
		listaProductos=ventanaVenta.recorrerTabla();
		Venta venta = new Venta();
		Calendar fecha = new GregorianCalendar();
		int ano = fecha.get(Calendar.YEAR);
        int mes = fecha.get(Calendar.MONTH);
        int dia = fecha.get(Calendar.DAY_OF_MONTH);
        String fechaF = ano +"/"+ mes+"/"+ dia;
		venta.setFecha(fechaF);
		venta.setTotal(total);
		servicioDetalleVenta.agregarDetalleVenta(venta,listaProductos);
		controlCobro.muestraDialogo();
	}

	
	
	
}
