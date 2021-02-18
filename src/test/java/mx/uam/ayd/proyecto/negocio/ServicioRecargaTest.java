package mx.uam.ayd.proyecto.negocio;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import mx.uam.ayd.proyecto.datos.RecargaRepository;
import mx.uam.ayd.proyecto.negocio.modelo.Recarga;

@ExtendWith(MockitoExtension.class)
public class ServicioRecargaTest {

	@Mock
	private RecargaRepository recargaRepository;
	
	
	@InjectMocks
	private ServicioRecarga servicioRecarga;
	
	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}
	
	/**
	 * Teste del Metodo de realizar recarga
	 */
	
	@Test
	void testAgregarRecarga() {
		
		//Prueba: comprobar que la recarga se puede generar
		Recarga recarga1 = servicioRecarga.ingresaDatosRecarga("5588227711", 100, "Telcel");
		
		//assertTrue(recarga1 != null);
	}
}
