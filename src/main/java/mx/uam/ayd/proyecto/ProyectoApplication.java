package mx.uam.ayd.proyecto;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import mx.uam.ayd.proyecto.datos.GrupoRepository;
import mx.uam.ayd.proyecto.datos.ProductoRepository;
import mx.uam.ayd.proyecto.negocio.modelo.Grupo;
import mx.uam.ayd.proyecto.negocio.modelo.Producto;
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
	ControlPrincipalEmpleados controlPrincipalEmpleados;
	
	@Autowired
	ProductoRepository productoRepository;
	
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
		
		controlPrincipalEmpleados.inicia();
	}
	
	
	/**
	 * 
	 * Inicializa la BD con datos
	 * 
	 * 
	 */
	public void inicializaBD() {
		
		Producto producto = new Producto("Ajolotius","Miel de abeja",
				"No","Estante 2, segundo anaquel",50,10);
		productoRepository.save(producto);
		
		Producto producto1 = new Producto("Diclofenaco","Diclofenaco Sodico",
				"No","Estante 2, tercer anaquel",45,10);
		productoRepository.save(producto1);
		
		Producto producto2 = new Producto("XL3xtra","Parecetamol-fenilefina-clorfenamina",
				"No","Estante 2, primer anaquel",48,10);
		productoRepository.save(producto2);
		
		Producto producto3 = new Producto("Cloranfenicol","Cloranfenicol",
				"No","Estante 1, segundo anaquel",22,10);
		productoRepository.save(producto3);
		
		Producto producto4 = new Producto("Diurmessel","furosemina",
				"Si","Estante 1, cuarto anaquel",35,10);
		productoRepository.save(producto4);

		Producto producto5 = new Producto("Dualgos","Paracetamol-Ubuprofeno",
				"No","Estante 2, segundo anaquel",29,10);
		productoRepository.save(producto5);
				
		Grupo grupoAdmin = new Grupo();
		grupoAdmin.setNombre("Administradores");
		grupoRepository.save(grupoAdmin);
		
		Grupo grupoOps = new Grupo();
		grupoOps.setNombre("Operadores");
		grupoRepository.save(grupoOps);
				
	}
}
