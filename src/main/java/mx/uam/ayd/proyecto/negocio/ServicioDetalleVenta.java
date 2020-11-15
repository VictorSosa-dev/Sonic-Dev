package mx.uam.ayd.proyecto.negocio;

/**
 * @author VictorSosa
 */

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import mx.uam.ayd.proyecto.datos.DetalleVentaRepository;
import mx.uam.ayd.proyecto.datos.ProductoRepository;
import mx.uam.ayd.proyecto.datos.VentaRepository;
import mx.uam.ayd.proyecto.negocio.modelo.DetalleVenta;
import mx.uam.ayd.proyecto.negocio.modelo.Producto;
import mx.uam.ayd.proyecto.negocio.modelo.Venta;

@Slf4j
@Service
public class ServicioDetalleVenta {
	@Autowired
	private ProductoRepository productoRepository;
	@Autowired
	private VentaRepository ventaRepository;
	@Autowired
	private DetalleVentaRepository detalleDeVentaRepository;

	/**
	 * Método que registra los detalles la venta y la venta.
	 * 
	 * @param venta
	 * @param listaProductos
	 */

	public void agregarDetalleVenta(Venta venta, List<Producto> listaProductos) {

		System.out.println(venta.getTotal());
		ventaRepository.save(venta);
		for (Producto producto : listaProductos) {
			DetalleVenta detalle = new DetalleVenta();
			detalleDeVentaRepository.save(detalle);
			venta.addDetalleVenta(detalle);
			producto.addDetalleVenta(detalle);
			ventaRepository.save(venta);
			System.out.println(producto);
			productoRepository.save(producto);
		}

	}

}
