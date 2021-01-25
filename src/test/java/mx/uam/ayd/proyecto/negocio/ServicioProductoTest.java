package mx.uam.ayd.proyecto.negocio;

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

@ExtendWith(MockitoExtension.class)
class ServicioProductoTest {
	
	@Mock
	private ProductoRepository productoRepository;

	// Esta anotación hace que se inyecten todos los Mocks al módulo que quiero
	// probar para que no haya nullPointerException por que las dependencias
	// no están satisfechas en tiempo de pruebas
	@InjectMocks
	private ServicioProducto servicio;
	
	private Producto producto1 = new Producto();
	private Producto producto2 = new Producto();
	
	@BeforeEach
	void setUp() throws Exception {
		
		producto1.setNombre("AJOLOTIUS");
		producto1.setCompuesto("Miel de abeja");
		producto1.setReceta("No");
		producto1.setUbicacion("Estante 2");
		producto1.setPiezas(2);
		producto1.setPrecio(50);
		
		producto2.setNombre("DICLOFENACO");
		producto2.setCompuesto("Diclofenaco sodico");
		producto2.setReceta("No");
		producto2.setUbicacion("Estante 2");
		producto2.setPiezas(10);
		producto2.setPrecio(45);
	}

	@AfterEach
	void tearDown() throws Exception {
	}
	
	@Test
	void testBuscarProductos() {
		
		Producto producto = new Producto();
		Producto p1 = new Producto();
		p1.setNombre("aspirina");
		p1.setCompuesto("ff");
		p1.setReceta("Si");
		p1.setUbicacion("aqui");
		p1.setPrecio(20);
		p1.setPiezas(5);
		
		when(productoRepository.findByNombre("aspirina")).thenReturn(p1);
		// Prueba 1: El método regresa el producto 
		//si lo encuantra
		Producto p = new Producto(); 
		p=servicio.buscarProducto("aspirina");
		
		assertEquals(p1, p);
		
		//Prueba 2: El método regresa null si no encuantra el producto
		p=servicio.buscarProducto("dualgos");
		
		assertNull(p);
		//Prueba 3: El método se le pase un null
		Assertions.assertThrows(IllegalArgumentException.class, () -> {

			servicio.buscarProducto(null);
			

		});

	}

	

	@Test
	void testActulizaInventarioMenos() {
		//Prueba 1: Se actualiza la cantidad de piezas de un producto
		LinkedList<Producto> lista = new LinkedList<>();
		Producto producto = new Producto();
		Producto p1 = new Producto();
		p1.setNombre("aspirina");
		p1.setCompuesto("ff");
		p1.setReceta("Si");
		p1.setUbicacion("aqui");
		p1.setPrecio(20);
		p1.setPiezas(5);
		
		lista.add(p1);
		
		servicio.actualizaInventarioMenos(lista);
		
		int p = p1.getPiezas();
		
		assertEquals(4,p);
		
		//Prueba 2: No se actualizó el inventario
		
		servicio.actualizaInventarioMenos(lista);
		
		int o = p1.getPiezas();
		
		assertNotEquals(8,o);
	}
	
	@Test
	void testRecuperarProductosEscasez() {
		//Prueba 1: 
		List<Producto> productos = servicio.obtenerProductos();
		assertTrue(productos.isEmpty());
		//Prueba 2:
		LinkedList <Producto> lista = new LinkedList <> ();
		lista.add(producto1);
		lista.add(producto2);
		when(productoRepository.findAll()).thenReturn(lista);
		
		productoRepository.save(producto1);
		productoRepository.save(producto2);
		
		productos = servicio.recuperarProductosEscazes();
		
		assertEquals(1, productos.size());
	}
	
	@Test
	void testActualizaInventarioNuevo() {
		//Prueba 1: Se actualiza la cantidad de piezas de un prodcuto
		int nuevasPiezas = 12;	
		Producto p1 = new Producto();
		p1.setNombre("aspirina");
		p1.setCompuesto("ff");
		p1.setReceta("Si");
		p1.setUbicacion("aqui");
		p1.setPrecio(20);
		p1.setPiezas(5);
		
		servicio.actualizaInventarioNuevo(p1, nuevasPiezas);
		
		int p = p1.getPiezas();
		assertEquals(12, p);
		
		//Prueba 2: Se recibio un valor nulo en
		//en el parametro producto
		
		Assertions.assertThrows(IllegalArgumentException.class, () -> {

			servicio.actualizaInventarioNuevo(null, nuevasPiezas);

		});
	
	}


	
}
