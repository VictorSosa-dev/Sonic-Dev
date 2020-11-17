package mx.uam.ayd.proyecto.negocio;

import static org.assertj.core.api.Assertions.assertThatObject;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import mx.uam.ayd.proyecto.datos.ProductoRepository;
import mx.uam.ayd.proyecto.negocio.modelo.Producto;
import mx.uam.ayd.proyecto.negocio.modelo.Usuario;

@ExtendWith(MockitoExtension.class)
class ServicioProductoTest {

	@BeforeEach
	void setUp() throws Exception {

		
	}

	@AfterEach
	void tearDown() throws Exception {
	}
	
	@Mock
	private ProductoRepository productoRepository;
	
	// Esta anotación hace que se inyecten todos los Mocks al módulo que quiero
	// probar para que no haya nullPointerException por que las dependencias
	// no están satisfechas en tiempo de pruebas
	@InjectMocks
	private ServicioProducto servicio;
	
	@Test
	void testBuscarProductos() {
	
		//Prueba 1: El método regresa nul la busqueda null
		Assertions.assertThrows(IllegalArgumentException.class, () -> {

			servicio.buscarProducto("aspirina");
	
		});
		
	}	

}
