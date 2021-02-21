/**
 * Clase que se encarga de comunicarse con otros
 * controladores para mostrar las ventanas y los servicios
 * @author Angel
 */
package mx.uam.ayd.proyecto.presentacion.agregarCliente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mx.uam.ayd.proyecto.negocio.ServicioCliente;
import mx.uam.ayd.proyecto.negocio.modelo.Cliente;
import mx.uam.ayd.proyecto.negocio.modelo.Empleado;
import mx.uam.ayd.proyecto.presentacion.principal.empleado.ControlPrincipalEmpleados;
import mx.uam.ayd.proyecto.presentacion.principal.encargado.ControlPrincipalEncargado;
import mx.uam.ayd.proyecto.presentacion.ventaMembresia.ControlClientes;

@Component
public class ControlAgregarCliente {
	
	@Autowired
	private VentanaAgregarCliente ventanaAgregarCliente;
	
	@Autowired
	private ControlPrincipalEncargado controlPrincipalEncargado;
	
	@Autowired
	private ControlPrincipalEmpleados controlPrincipalEmpleados;
	
	@Autowired
	private ControlClientes controlClientes;
	
	@Autowired
	private ServicioCliente servicioCliente;
	
	
	public void inicia(Empleado empleado) {
		ventanaAgregarCliente.muestra(this, empleado);
	}
	
	
	public void cancelar(Empleado empleado) {
		controlClientes.iniciaVentana(empleado);
	}
	
	
	public void agregarCliente(String nombre, String apellidos, String correo, String telefono, String usuario) {
		try {
			Cliente cliente = new Cliente();
			
			cliente.setNombre(nombre);
			cliente.setApellidos(apellidos);
			cliente.setCorreo(correo);
			cliente.setTelefono(telefono);
			cliente.setUsuario(usuario);;
			
			servicioCliente.guardarCliente(cliente);
			ventanaAgregarCliente.muestraDialogoConMensaje("Cliente agregado exitosamente!");
		} catch(Exception e) {
			ventanaAgregarCliente.muestraDialogoConMensaje("Error al agregar cliente: " + e.getMessage());
		}
	}
	
	
	public void iniciaEmpleadoPrincipal(Empleado empleado) {
		if(empleado.getNivel().equals("empleado")) {
			controlPrincipalEmpleados.inicia(empleado);
			ventanaAgregarCliente.setVisible(false);
		} if(empleado.getNivel().equals("encargado")) {
				controlPrincipalEncargado.inicia(empleado);
				ventanaAgregarCliente.setVisible(false);
		}
	}
	
	
	public Cliente validarUsuario(String usuario) {
		Cliente cliente = servicioCliente.validaUsuario(usuario);
		if(cliente == null) {
			return null;
		} else {
			return cliente;
		}
	}
}
