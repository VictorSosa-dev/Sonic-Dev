package mx.uam.ayd.proyecto.datos;

import java.util.List;

import org.springframework.data.repository.CrudRepository;


import mx.uam.ayd.proyecto.negocio.modelo.Asistencia;
import mx.uam.ayd.proyecto.negocio.modelo.Empleado;

public interface AsistenciaRepository extends CrudRepository<Asistencia, Long> {
	
	public Asistencia findByHoraInicialAndHoraFinalAndFecha(String horaInicial,String horaFinal, String fecha);
	public Asistencia findByHoraInicial(String horaInicial);
	public List<Asistencia> findByEmpleado(Empleado empleado);

}
