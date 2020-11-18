package mx.uam.ayd.proyecto.negocio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.uam.ayd.proyecto.datos.ClienteRepository;
import mx.uam.ayd.proyecto.negocio.modelo.Cliente;
import mx.uam.ayd.proyecto.negocio.modelo.PedidoCliente;

@Service
public class ServicioCliente {
	@Autowired
	private ClienteRepository clienteRepository;
	
	/**
	 * Guarda cambios en un cliente
	 * @param cliente entidad de cliente a guardar
	 */
	public void guardarCliente(Cliente cliente) {
		clienteRepository.save(cliente);
	}

	public List<Cliente> obtenerClientePorPedido(PedidoCliente pedidoCliente) {
		return clienteRepository.findByPedidosCliente(pedidoCliente);
	}
}
