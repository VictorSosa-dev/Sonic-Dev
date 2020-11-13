package mx.uam.ayd.proyecto.negocio.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data

public class Recarga {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int celular;
	private int monto;
	private String compañia;
	public Recarga() {}
	public Recarga(int celular, int monto, String compañia) {
		this.celular = celular;
		this.compañia = compañia;
		this.monto = monto;
	}
}
