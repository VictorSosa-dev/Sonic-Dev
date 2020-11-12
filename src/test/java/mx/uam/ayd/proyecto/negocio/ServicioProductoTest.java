package mx.uam.ayd.proyecto.negocio;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import mx.uam.ayd.proyecto.datos.ProductoRepository;
import mx.uam.ayd.proyecto.negocio.modelo.Producto;

@ExtendWith(MockitoExtension.class)
class ServicioProductoTest {
	
	@Mock
	private ProductoRepository productoRepository;
	
	// Esta anotación hace que se inyecten todos los Mocks al módulo que quiero
	// probar para que no haya nullPointerException por que las dependencias
	// no están satisfechas en tiempo de pruebas
	@InjectMocks
	ServicioProducto servicio;
	
	@Test
	void testBuscarProductos() {
		
		
		//Prueba 1 el producto que regresa no es nulo.
		Producto producto = servicio.buscarProducto("Dualgos");
		
		assertEquals("Dualgos",producto.getNombre());
	
	}

}
