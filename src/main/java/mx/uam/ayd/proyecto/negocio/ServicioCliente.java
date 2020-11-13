package mx.uam.ayd.proyecto.negocio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.uam.ayd.proyecto.datos.ClienteRepository;
import mx.uam.ayd.proyecto.negocio.modelo.Cliente;

@Service
public class ServicioCliente {
	@Autowired
	private ClienteRepository clienteRepository;

	public void agregaCliente(Cliente cliente) {

	}
}
