package mx.uam.ayd.proyecto.datos;

import org.springframework.data.repository.CrudRepository;

import mx.uam.ayd.proyecto.negocio.modelo.Producto;

/**
 * 
 * @author VictorSosa
 *
 */

public interface ProductoRepository extends CrudRepository <Producto, Long> {
	
	
	//Producto findByNombreandCompuestoandRecetaandUbicacionandPrecioandPiezas(String nombre,
		//	String compuesto,String receta,String ubicacion,float precio,int piezas);

	Producto findByNombre(String nombre);
}
