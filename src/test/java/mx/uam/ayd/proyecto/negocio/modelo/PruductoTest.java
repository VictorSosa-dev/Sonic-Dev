package mx.uam.ayd.proyecto.negocio.modelo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PruductoTest {
	
	
	private Producto producto;
	
	@BeforeEach
	void setUp() throws Exception {
		// Este método se ejecuta antes de la ejecución
		// de cada metodo de prueba, es útil para
		// establecer las precondiciones
		
		producto = new Producto();
		producto.setNombre("Dualgos");
		
	}

	@AfterEach
	void tearDown() throws Exception {
		// Este método se ejecuta después de la ejecución
		// de cada método de prueba, es útil para
		// dejar todo como estaba antes de la prueba
	}
	@Test
	void test() {
		fail("Not yet implemented");
	}

}
