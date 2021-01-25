package mx.uam.ayd.proyecto.negocio.modelo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import org.hibernate.annotations.SQLDelete;

import lombok.Data;

@Entity
@Data

/**
 * Entidad de empleado
 * @author AKarina
 *
 */
public class Empleado {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idEmpleado;
	private String nombre;
	private String apellido;
	private int edad;
	private String correo;
	private String telefono;
	private String nivel;
	private String usuario;
	private String password;
	private String direccion;
	
	@OneToMany(targetEntity = PedidoCliente.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "idEmpleado")
	private final List <PedidoCliente> pedidosCliente = new ArrayList <> ();
	
	
	public Empleado() {}
	public Empleado(String nombre, String apellido, int edad, String direccion, String correo, String telefono, String nivel,
			String usuario, String password) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.edad = edad;
		this.direccion = direccion;
		this.correo = correo;
		this.telefono = telefono;
		this.nivel = nivel;
		this.usuario = usuario;
		this.password = password;
		
	}
	
	/**
	 * Agregar un pedido cliente y lo relaciona con el empleado
	 * 
	 * @param pedidoCliente el pedido cliente a relacionar
	 * @return regresa true si el pedido cliente se agrego correctamente y false si no
	 * @throws IllegalArgumentException si el pedido cliente es nulo
	 */
	
	public boolean addPedidoCliente(PedidoCliente pedidoCliente) {
		if (pedidoCliente == null) {
			throw new IllegalArgumentException("El detalle de venta no puede ser null");
		}
		if (pedidosCliente.contains(pedidoCliente)) {
			return false;
		}

		return pedidosCliente.add(pedidoCliente);
	}
}
