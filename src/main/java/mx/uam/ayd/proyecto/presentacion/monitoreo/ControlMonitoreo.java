package mx.uam.ayd.proyecto.presentacion.monitoreo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
//import lombok.extern.slf4j.Slf4j;
import mx.uam.ayd.proyecto.negocio.ServicioAsistencia;
import mx.uam.ayd.proyecto.negocio.modelo.Asistencia;
//import mx.uam.ayd.proyecto.negocio.modelo.Empleado;
import mx.uam.ayd.proyecto.negocio.modelo.Producto;


@Component
public class ControlMonitoreo {
	
	@Autowired
	private ServicioAsistencia servicioasistencia;
	
	@Autowired
	private VentanaMonitoreo ventanamonitoreo;


		
	
	//Inicia la ventana de Monitoreo y carga las asistencias
	public void inicia() {
		this.iniciaAsistencias();
	     ventanamonitoreo.muestra(this);
	}//Fin del metodo inicia
	
	
	//Metodo que guarda la hora en se inicio sesion
	public void registrarInicio() {
		LocalDateTime ahora= LocalDateTime.now();
		int anio= ahora.getYear();
		int mes = ahora.getMonthValue();
		int dia= ahora.getDayOfMonth();
		int hora= ahora.getHour();
		int minuto= ahora.getMinute();
		int segundo=ahora.getSecond();
		
		String horainical= hora+":"+minuto+":"+segundo;
		String fech=dia+"/"+mes+"/"+anio;
	    servicioasistencia.registroAsistencia(horainical,null,fech);

	    //servicioasistencia.registroAsistencia(horainical, "", fech, empleado.getNombre());
		
	}//Fin del metodo registrarInicio
	
	
	public void registrarCerrar() {
		LocalDateTime ahora= LocalDateTime.now();
		int anio= ahora.getYear();
		int mes = ahora.getMonthValue();
		int dia= ahora.getDayOfMonth();
		int hora= ahora.getHour();
		int minuto= ahora.getMinute();
		int segundo=ahora.getSecond();
		
		String horafinal= hora+":"+minuto+":"+segundo;
		String fech=dia+"/"+mes+"/"+anio;
	    servicioasistencia.registroAsistencia(null, horafinal, fech);

	   // servicioasistencia.registroAsistencia("", horafinal, fech, empleado.getNombre());
		
		
		
	}

	//Metodo que me recupera las asistencias y me las guarda en una lista
	public void iniciaAsistencias() {
		List <Asistencia> asistencias = servicioasistencia.recuperarasistencia();
		//asistencias= asistencias.get(0);
		for(Asistencia asistencia:asistencias) {
			//log.info("Asistencia: "+asistencia);
			//System.out.println(asistencia.getHoraInicial());
			//if(asistencias.get(0) !=asistencia)
			ventanamonitoreo.llenaTabla(asistencia);
			//asistencias.clear();
		}
		
	}//Fin del metodo iniciaAsistencias
	


}//Fin de la clase ControlMonitoreo
