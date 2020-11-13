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
	private String nombres;
	private String apellidoP;
	private String apellidoM;
	private String correo;
	private String celular;
	private String nivel;
	public Empleado() {}
	public Empleado(String nombres, String apellidoP, String apellidoM, String correo, String celular, String nivel) {
		this.nombres = nombres;
		this.apellidoP = apellidoP;
		this.apellidoM = apellidoM;
		this.correo = correo;
		this.celular = celular;
		this.nivel = nivel;
		
	}
	
}
