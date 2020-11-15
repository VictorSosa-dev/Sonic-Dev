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

	/**
	 * Método que busca el produco en la base de datos
	 * 
	 * @param nombre
	 * @return Regresa un producto si lo encontro y null si no está
	 *
	 */
	public Producto buscarProducto(String nombre) {

		Producto producto = productoRepository.findByNombre(nombre);

		System.out.print(producto);
		if (producto == null) {
			throw new IllegalArgumentException("Ese usuario ya existe");
		} else {
			return producto;
		}
	}

	/**
	 * Método que decrementa la cantidad de piezas de un producto.
	 * 
	 * @param nombre
	 */

	public void actualizaMenos(String nombre) {
		int piezas;
		Producto producto = productoRepository.findByNombre(nombre);
		piezas = producto.getPiezas();
		producto.setPiezas(piezas - 1);
		producto.getNombre();
		producto.getPiezas();
		producto.getCompuesto();
		producto.getReceta();
		producto.getUbicacion();
		producto.getPrecio();

		productoRepository.save(producto);

	}

	/**
	 * Método que incrementa la cantidad de piezas de un producto.
	 * 
	 * @param nombre
	 */

	public void actualizaMas(String nombre) {
		int piezas;
		Producto producto = productoRepository.findByNombre(nombre);
		piezas = producto.getPiezas();
		producto.setPiezas(piezas + 1);
		producto.getNombre();
		producto.getPiezas();
		producto.getCompuesto();
		producto.getReceta();
		producto.getUbicacion();
		producto.getPrecio();

		productoRepository.save(producto);
	}

}
