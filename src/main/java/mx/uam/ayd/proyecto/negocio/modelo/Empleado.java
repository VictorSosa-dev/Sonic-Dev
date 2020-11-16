package mx.uam.ayd.proyecto.negocio.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data


public class Empleado {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idEmpleado;
	private String nombre;
	private String apellidoP;
	private String apellidoM;
	private String correo;
	private String celular;
	private String nivel;
	private String usuario;
	private String password;
	public Empleado() {}
	public Empleado(String nombre, String apellidoP, String apellidoM, String correo, String celular, String nivel,
			String usuario, String password) {
		this.nombre = nombre;
		this.apellidoP = apellidoP;
		this.apellidoM = apellidoM;
		this.correo = correo;
		this.celular = celular;
		this.nivel = nivel;
		this.usuario = usuario;
		this.password = password;
	}
	
	
}
