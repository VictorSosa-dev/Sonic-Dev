package mx.uam.ayd.proyecto;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import mx.uam.ayd.proyecto.datos.GrupoRepository;
import mx.uam.ayd.proyecto.datos.ProductoRepository;
import mx.uam.ayd.proyecto.datos.VentaRepository;
import mx.uam.ayd.proyecto.datos.DetalleVentaRepository;
import mx.uam.ayd.proyecto.datos.EmpleadoRepository;
import mx.uam.ayd.proyecto.negocio.modelo.Empleado;
import mx.uam.ayd.proyecto.negocio.modelo.Grupo;
import mx.uam.ayd.proyecto.negocio.modelo.Producto;
import mx.uam.ayd.proyecto.presentacion.inicioSesion.ControlInicioSesion;
import mx.uam.ayd.proyecto.presentacion.principal.ControlPrincipal;
import mx.uam.ayd.proyecto.presentacion.principal.empleado.ControlPrincipalEmpleados;

/**
 * 
 * Clase principal que arranca la aplicación
 * 
 * @author SosaPiña
 *
 */
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

		Producto producto = new Producto("AJOLOTIUS", "Miel de abeja", "No", "Estante 2, segundo anaquel", 50, 10);
		productoRepository.save(producto);

		Producto producto1 = new Producto("DICLOFENACO", "Diclofenaco Sodico", "No", "Estante 2, tercer anaquel", 45,
				10);
		productoRepository.save(producto1);

		Producto producto2 = new Producto("XL3XTRA", "Parecetamol-fenilefina-clorfenamina", "No",
				"Estante 2, primer anaquel", 48, 10);
		productoRepository.save(producto2);

		Producto producto3 = new Producto("CLORANFENICOL", "Cloranfenicol", "No", "Estante 1, segundo anaquel", 22, 10);
		productoRepository.save(producto3);

		Producto producto4 = new Producto("DIURMESSEL", "furosemina", "Si", "Estante 1, cuarto anaquel", 35, 10);
		productoRepository.save(producto4);

		Producto producto5 = new Producto("DUALGOS", "Paracetamol-Ubuprofeno", "No", "Estante 2, segundo anaquel", 29,
				10);
		productoRepository.save(producto5);

		Empleado pruebaEmpleado = new Empleado("Karina", "Vergara", "Guzman", "karina@gmail.com", "5587388643",
				"empleado", "anver", "123456789");
		Empleado pruebaEncargado = new Empleado("Ximena", "Pereda", "Rodriguez", "ximena@gmail.com", "5587388642",
				"encargado", "alma", "987654321");
		empleadoRepository.save(pruebaEmpleado);
		empleadoRepository.save(pruebaEncargado);

		Grupo grupoAdmin = new Grupo();
		grupoAdmin.setNombre("Administradores");
		grupoRepository.save(grupoAdmin);

		Grupo grupoOps = new Grupo();
		grupoOps.setNombre("Operadores");
		grupoRepository.save(grupoOps);

	}
}
