package mx.uam.ayd.proyecto.negocio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.uam.ayd.proyecto.datos.DetallePedidoProveedorRepository;
import mx.uam.ayd.proyecto.datos.PedidoClienteRepository;
import mx.uam.ayd.proyecto.datos.PedidoProveedorRepository;
import mx.uam.ayd.proyecto.negocio.modelo.DetallePedidoCliente;
import mx.uam.ayd.proyecto.negocio.modelo.DetallePedidoProveedor;
import mx.uam.ayd.proyecto.negocio.modelo.PedidoProveedor;
import mx.uam.ayd.proyecto.negocio.modelo.Producto;

@Service
public class ServicioDetallePedidoProveedor {
	@Autowired
	private DetallePedidoProveedorRepository detallePedidoProveedorRepository;
	
	@Autowired
	private ServicioPedidoProveedor servicioPedidoProveedor;
	
	@Autowired
	private PedidoProveedorRepository pedidoProveedorRepository;
	
	@Autowired
	private ServicioProducto servicioProducto;
	
	

	public void agregarDetallePedidoProveedor(PedidoProveedor pedidoProveedor, Producto producto, int piezas) {
		DetallePedidoProveedor detallePedidoProveedor = new DetallePedidoProveedor();
		detallePedidoProveedorRepository.save(detallePedidoProveedor);
		detallePedidoProveedor.setProducto(producto);
		detallePedidoProveedor.setNumeroPiezas(piezas);
		detallePedidoProveedorRepository.save(detallePedidoProveedor);
		pedidoProveedor.addDetallePedidoProveedor(detallePedidoProveedor);
		servicioPedidoProveedor.guardar(pedidoProveedor);
		servicioProducto.guardar(producto);
		producto.addDetallePedidoProveedor(detallePedidoProveedor);
		servicioProducto.guardar(producto);
		detallePedidoProveedorRepository.save(detallePedidoProveedor);
	}
	
	/**
	 * El metodo registra el pedido y su detalle
	 * de los pedidos que son los productos que se pidieron
	 * @param pedido
	 * @param productos
	 * @return
	 */
	public boolean addDetalleProveedor(PedidoProveedor pedido, List<Producto> productos) {
		if(pedido == null && productos.size()==0) {
			throw new NullPointerException("Se recibio un pedido vacio");
		}
		
		pedidoProveedorRepository.save(pedido);
		for (Producto producto : productos) {
			DetallePedidoProveedor detalle = new DetallePedidoProveedor();
			detallePedidoProveedorRepository.save(detalle);
			pedido.addDetallePedidoProveedor(detalle);
			pedidoProveedorRepository.save(pedido);
		}
		return true;
	}
}
