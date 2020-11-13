package mx.uam.ayd.proyecto.negocio.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

/**
 * Entidad de negocio Pedido
 * 
 * @author AJ
 *
 */
@Entity
@Data
public class Pedido {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idPedido;
	private String fecha;
	private int totalProductos;
	private float precio;

	public Pedido() {
	}

	public Pedido(int totalProductos, float precio) {
		this.fecha = null;
		this.totalProductos = totalProductos;
		this.precio = precio;
	}
}
