package mx.uam.ayd.proyecto.negocio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import mx.uam.ayd.proyecto.datos.ProductoRepository;
import mx.uam.ayd.proyecto.negocio.modelo.Producto;

@Slf4j
@Service
public class ServicioProducto {
	
	@Autowired
	private ProductoRepository productoRepository; 
	
	
	public Producto buscarProducto(String nombre){
		
		Producto producto = productoRepository.findByNombre(nombre); 
		
		System.out.print(producto);
		if(producto == null) {
			throw new IllegalArgumentException("Ese usuario ya existe");
		}
		else {
			return producto;
		}
	}


	public void actualizaMenos(String nombre) {
		int piezas;
		Producto producto = productoRepository.findByNombre(nombre);
		piezas = producto.getPiezas();
		producto.setPiezas(piezas-1);
		producto.getNombre();
		producto.getPiezas();
		producto.getCompuesto();
		producto.getReceta();
		producto.getUbicacion();
		producto.getPrecio();
		
		productoRepository.save(producto);
		
	}


	public void actualizaMas(String nombre) {
		int piezas;
		Producto producto = productoRepository.findByNombre(nombre);
		piezas = producto.getPiezas();
		producto.setPiezas(piezas+1);
		producto.getNombre();
		producto.getPiezas();
		producto.getCompuesto();
		producto.getReceta();
		producto.getUbicacion();
		producto.getPrecio();
		
		productoRepository.save(producto);		
	}


	
}
