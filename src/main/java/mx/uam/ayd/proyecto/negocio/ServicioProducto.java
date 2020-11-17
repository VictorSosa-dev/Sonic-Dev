package mx.uam.ayd.proyecto.negocio;

import java.util.List;

import javax.swing.JOptionPane;

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
		
		if (producto == null) {
			throw new IllegalArgumentException("No se encontro el producto");
		} else{
			log.info("Producto encontrado:" +nombre);
			return producto;
		}
		
	}
	
	/**
	 * Método que decrementa la cantidad de piezas de un producto.
	 * 
	 * @param lista de productos de la venta
	 */
	public void actualizaInventarioMenos(List<Producto> listaProductos) {
		try {
			for (Producto producto : listaProductos) {
				producto.setPiezas(producto.getPiezas()-1);
				productoRepository.save(producto);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "No se actualizaron las piezas");
		}
		
	}

	
}
