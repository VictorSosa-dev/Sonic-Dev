package mx.uam.ayd.proyecto.datos;

import org.springframework.data.repository.CrudRepository;

import mx.uam.ayd.proyecto.negocio.modelo.Producto;

/**
 * 
 * @author VictorSosa
 *
 */

public interface ProductoRepository extends CrudRepository <Producto, Long> {
	
	Producto findByNombre(String nombre);
}
