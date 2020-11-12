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


	public void Actualiza(String nombre) {
		int piezas;
		Producto producto1 = productoRepository.findByNombre(nombre);
		piezas = producto1.getPiezas();
		producto1.setPiezas(piezas-1);
		producto1.getNombre();
		producto1.getPiezas();
		producto1.getCompuesto();
		producto1.getReceta();
		producto1.getUbicacion();
		producto1.getPrecio();
		
		productoRepository.save(producto1);
		System.out.print(piezas);
		System.out.print(producto1);
	}


	
}
