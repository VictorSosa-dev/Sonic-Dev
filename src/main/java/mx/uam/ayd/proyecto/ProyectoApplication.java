package mx.uam.ayd.proyecto;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import mx.uam.ayd.proyecto.datos.GrupoRepository;
import mx.uam.ayd.proyecto.datos.ProductoRepository;
import mx.uam.ayd.proyecto.datos.VentaRepository;
import mx.uam.ayd.proyecto.datos.ClienteRepository;
import mx.uam.ayd.proyecto.datos.DetalleVentaRepository;
import mx.uam.ayd.proyecto.datos.EmpleadoRepository;
import mx.uam.ayd.proyecto.negocio.modelo.Cliente;
import mx.uam.ayd.proyecto.negocio.modelo.Empleado;
import mx.uam.ayd.proyecto.negocio.modelo.Grupo;
import mx.uam.ayd.proyecto.negocio.modelo.Producto;
import mx.uam.ayd.proyecto.presentacion.asistencia.controlAsistencias;
import mx.uam.ayd.proyecto.presentacion.inicioSesion.ControlInicioSesion;
import mx.uam.ayd.proyecto.presentacion.inventario.ControlCargarArchivo;
import mx.uam.ayd.proyecto.presentacion.principal.ControlPrincipal;
import mx.uam.ayd.proyecto.presentacion.principal.empleado.ControlPrincipalEmpleados;

/**
 * 
 * Clase principal que arranca la aplicación
 * Rama de trabajo sobre la HU-05 Control de empleados
 * @author SosaPiña ingenieria 
 *
 */

@Slf4j
@SpringBootApplication
public class ProyectoApplication {

	@Autowired
	ControlInicioSesion controlInicioSesion;

	@Autowired
	ProductoRepository productoRepository;
	
	@Autowired
	EmpleadoRepository empleadoRepository;

	@Autowired
	DetalleVentaRepository detalleVentaRepository;

	@Autowired
	VentaRepository ventaRepository;

	@Autowired
	ControlPrincipal controlPrincipal;

	@Autowired
	GrupoRepository grupoRepository;
	
	@Autowired
	ControlCargarArchivo controlCargarArchivo;
	@Autowired
	private controlAsistencias controlAsistencia;
	
	@Autowired
	ClienteRepository clienteRepository;

	public static void main(String[] args) {

		SpringApplicationBuilder builder = new SpringApplicationBuilder(ProyectoApplication.class);

		builder.headless(false);

		builder.run(args);
	}

	@PostConstruct
	public void inicia() {
		
		inicializaBD();
		controlInicioSesion.inicia();
	}

	/**
	 * 
	 * Inicializa la BD con datos
	 * 
	 * 
	 */
	public void inicializaBD() {
		
		String ruta = "./ejemplo-carga.txt";
		try {
			controlCargarArchivo.cargaDefault(ruta);
		} catch (NumberFormatException | IOException e) {
			log.warn(">> EL ARCHIVO DEFAULT NO PUDO SER CARGADO");
		}

		Empleado pruebaEmpleado = new Empleado("Karina", "Vergara Guzman", 20, "Av. Patito","karina@gmail.com", "5587388643",
				"empleado", "anver", "123456789");
		Empleado pruebaEncargado = new Empleado("Ximena", "Pereda Rodriguez", 22, "Av. Ballena", "ximena@gmail.com", "5587388642",
				"encargado", "alma", "987654321");
		
		Empleado pruebaEmpleado2 = new Empleado("Alfonso", "Jimenez Guzman", 21, "Av. Patito #43","alfonso@gmail.com", "5587384356",
				"empleado", "alfonso", "Alfonso1234");
		
		Empleado pruebaEncargado2 = new Empleado("Raul", "Gomez Rodriguez", 22, "Av. Ballena#45", "raul@gmail.com", "5587434355",
				"encargado", "raul", "Raul12345");
		empleadoRepository.save(pruebaEmpleado);
		empleadoRepository.save(pruebaEmpleado2);
		empleadoRepository.save(pruebaEncargado);
		empleadoRepository.save(pruebaEncargado2);

		Grupo grupoAdmin = new Grupo();
		grupoAdmin.setNombre("Administradores");
		grupoRepository.save(grupoAdmin);

		Grupo grupoOps = new Grupo();
		grupoOps.setNombre("Operadores");
		grupoRepository.save(grupoOps);
		
		//Clientes de prueba
		Cliente cliente1 = new Cliente();
		Cliente cliente2 = new Cliente();
		Cliente cliente3 = new Cliente();
		Cliente cliente4 = new Cliente();
		
		cliente1.setNombre("Arantza");
		cliente1.setApellidos("Gonzalez Huerta");
		cliente1.setCorreo("ara@gmail.com");
		cliente1.setTelefono("5578344535");
		cliente1.setUsuario("ara");
		
		cliente2.setNombre("David");
		cliente2.setApellidos("Pineda Ramirez");
		cliente2.setCorreo("david@gmail.com");
		cliente2.setTelefono("5512365478");
		cliente2.setUsuario("dad-12");
		
		cliente3.setNombre("Aron");
		cliente3.setApellidos("Hernandez Hernandez");
		cliente3.setCorreo("aron@gmail.com");
		cliente3.setTelefono("5596325874");
		cliente3.setUsuario("aron-21");
		
		cliente4.setNombre("Juan");
		cliente4.setApellidos("Diaz Mendoza");
		cliente4.setCorreo("jdm@gmail.com");
		cliente4.setTelefono("5515984267");
		cliente4.setUsuario("diaz-24");
		
		clienteRepository.save(cliente1);
		clienteRepository.save(cliente2);
		clienteRepository.save(cliente3);
		clienteRepository.save(cliente4);
		
	}
}
