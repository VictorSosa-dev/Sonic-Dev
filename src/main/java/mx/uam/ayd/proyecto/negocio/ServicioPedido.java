package mx.uam.ayd.proyecto.negocio;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import mx.uam.ayd.proyecto.datos.PedidoRepository;
import mx.uam.ayd.proyecto.negocio.modelo.Pedido;

@Slf4j
@Service
public class ServicioPedido {

	@Autowired
	private PedidoRepository pedidoRepository;

	public List<Pedido> recuperaPedidos() {

		List<Pedido> pedidos = new ArrayList<>();

		for (Pedido pedido : pedidoRepository.findAll()) {
			pedidos.add(pedido);
		}

		return pedidos;
	}
}
