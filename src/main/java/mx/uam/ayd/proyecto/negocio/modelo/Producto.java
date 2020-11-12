package mx.uam.ayd.proyecto.negocio.modelo;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import lombok.Data;

/**
 * Entidad de negocio Producto
 * @author VictorSosa
 *
 */

@Entity
@Data
public class Producto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idProducto;
	private String nombre;
	private String compuesto;
	private String receta;
	private String ubicacion;
	private float precio;
	private int piezas;
	public Producto () {}
	public Producto(String nombre, String compuesto, String receta, String ubicacion, float precio,
			int piezas) {
		this.nombre = nombre;
		this.compuesto = compuesto;
		this.receta = receta;
		this.ubicacion = ubicacion;
		this.precio = precio;
		this.piezas = piezas;
	}
	
	
}
