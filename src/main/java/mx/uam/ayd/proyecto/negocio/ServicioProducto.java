package mx.uam.ayd.proyecto.negocio;

import java.util.List;

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

	/**
	 * Método que busca el produco en la base de datos
	 * 
	 * @param nombre
	 * @return Regresa un producto si lo encontro y null si no está
	 * @throws IllegalArgumentException si se pasa un null.
	 */
	public Producto buscarProducto(String nombre) {

		Producto producto = productoRepository.findByNombre(nombre);

		System.out.print(producto);
		if (producto == null) {
			throw new IllegalArgumentException("No se encontro el producto");
		} 
		
		return producto;
		
	}

	/**
	 * Método que incrementa la cantidad de piezas de un producto.
	 * 
	 * @param nombre
	 */

	public void actualizaInventarioMas(String nombre) {
		int piezas;
		Producto producto1 = productoRepository.findByNombre(nombre);
		piezas = producto1.getPiezas();
		producto1.setPiezas(piezas + 1);
		producto1.getNombre();
		producto1.getPiezas();
		producto1.getCompuesto();
		producto1.getReceta();
		producto1.getUbicacion();
		producto1.getPrecio();

		productoRepository.save(producto1);
	}
	
	/**
	 * Método que decrementa la cantidad de piezas de un producto.
	 * 
	 * @param lista de productos de la venta
	 */
	public void actualizaInventarioMenos(List<Producto> listaProductos) {
		
		for (Producto producto : listaProductos) {
			producto.setPiezas(producto.getPiezas()-1);
			productoRepository.save(producto);
		}
	}

	
}
