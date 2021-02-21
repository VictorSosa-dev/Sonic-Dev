/**
 * Clase que se encarga de comunicarse con las ventanas
 * para que muestren su contenido. Tambien se comunica con los servicios 
 * para poder recuperar algunos datos.
 */
package mx.uam.ayd.proyecto.presentacion.ventaMembresia;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mx.uam.ayd.proyecto.negocio.ServicioCliente;
import mx.uam.ayd.proyecto.negocio.modelo.Cliente;
import mx.uam.ayd.proyecto.negocio.modelo.Empleado;
import mx.uam.ayd.proyecto.presentacion.agregarCliente.ControlAgregarCliente;
import mx.uam.ayd.proyecto.presentacion.principal.empleado.ControlPrincipalEmpleados;
import mx.uam.ayd.proyecto.presentacion.principal.encargado.ControlPrincipalEncargado;
import mx.uam.ayd.proyecto.presentacion.venta.ControlVenta;

@Component
public class ControlClientes {
	@Autowired
	private VentanaClientes ventanaClientes;
	
	@Autowired
	private ControlPrincipalEmpleados controlPrincipalEmpleados;
	
	@Autowired
	private ControlPrincipalEncargado controlPrincipalEncargado;
	
	@Autowired
	private ServicioCliente servicioCliente;
	
	@Autowired
	private ControlAgregarCliente controlAgregarCliente;
	
	@Autowired
	private ControlVenta controlVenta;
	
	@Autowired
	private VentanaModificarCliente ventanaModificarCliente;
	
	
	public void iniciaVentana(Empleado empleado) {
		ventanaClientes.muestra(this, empleado);
	}
	
	public void regresa(Empleado empleado) {
		if(empleado.getNivel().equals("empleado")) {
			controlPrincipalEmpleados.inicia(empleado);
			ventanaClientes.setVisible(false);
		} if(empleado.getNivel().equals("encargado")) {
				controlPrincipalEncargado.inicia(empleado);
				ventanaClientes.setVisible(false);
		}
	}
	
	public void iniciaAgregarCliente(Empleado empleado) {
		controlAgregarCliente.inicia(empleado);
	}
	
	public void iniciaVentaMembresia(Empleado empleado, String nombre) {
		Cliente cliente = servicioCliente.buscaCliente(nombre);
		controlVenta.iniciaVentaMembresia(empleado, cliente);
	}
	
	/**
	 * se recupera a todos los clientes de la BD
	 * 
	 */
	public void recuperaClientes() {
		List<Cliente> clientes = servicioCliente.recuperaClientes();
		ventanaClientes.limpiaTabla();
		
		if(clientes.isEmpty()) {
			ventanaClientes.muestraDialogoConMensaje("No hay clientes por mostrar!");
		} else {
			for (Cliente cliente : clientes) {
				ventanaClientes.agregaClientes(cliente);
			}
		}
	}
	
	
	public void eliminarCliente(List<String> nombre) {
		try {
			if(servicioCliente.eliminarCliente(nombre))
				ventanaClientes.muestraDialogoConMensaje("Usuario eliminado exitosamente");
		} catch(Exception e) {
			ventanaClientes.muestraDialogoConMensaje("Error al eliminar el usuario: " + e.getMessage());
		}
	}
	
	
	public void iniciaModificar(Empleado empleado, String nombre) {
		List<Cliente> clientes = servicioCliente.recuperaClientes();
		Cliente cliente = servicioCliente.buscaCliente(nombre);
		
		ventanaModificarCliente.muestra(this, empleado, clientes, cliente);
	}
	
	
	public Cliente validarUsuario(String usuario) {
		Cliente cliente = servicioCliente.validaUsuario(usuario);
		if(cliente == null) {
			return null;
		} else {
			return cliente;
		}
	}
	
	
	public void actualizaCliente(String nombre, String apellidos, String correo,
			String telefono, String usuario) {
		try {
			servicioCliente.actualizaCliente(nombre, apellidos, correo, telefono, usuario);
		} catch(Exception e) {
			ventanaModificarCliente.muestraDialogoConMensaje("Error al modificar al cliente: " + e.getMessage());
		}
	}
}
