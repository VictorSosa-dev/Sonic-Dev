package mx.uam.ayd.proyecto.negocio.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data


public class Asistencia {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idAsistencia;
	private String horaInicial;
	private String horaFinal;
	private String fecha;
	private String nombre;
//	private int hora;
//	private int minuto;
//	private int segundo;
	
	public Asistencia() {}
	public Asistencia(String horaInicial, String horaFinal,String fecha) {
		this.horaInicial= horaInicial;
		this.horaFinal= horaFinal;
		this.fecha=fecha;
	//this.nombre = nombre;
//		this.hora= hora;
//		this.minuto= minuto;
//		this.segundo= segundo;
	}
	
}