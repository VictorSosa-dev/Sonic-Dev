/**
 * Clase encatgada de mostrar los campos para 
 * modificar algunos atributos de un cliente
 * @author Angel Pimentel
 */
package mx.uam.ayd.proyecto.presentacion.ventaMembresia;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

import org.springframework.stereotype.Component;

import mx.uam.ayd.proyecto.negocio.modelo.Cliente;
import mx.uam.ayd.proyecto.negocio.modelo.Empleado;

import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

@SuppressWarnings("serial")
@Component
public class VentanaModificarCliente extends JFrame {

	private JPanel contentPane;
	private JTextField txtCargo;
	private JTextField txtNombreEmpleado;
	private JTextField txtId;
	private JTextField txtIdCliente;
	private JTextField txtNombreCliente;
	private JTextField txtApellidosCliente;
	private JTextField txtCorreo;
	private JTextField txtTelefono;
	private JTextField txtUsuario;
	
	private ControlClientes controlClientes;
	private Empleado empleado;
	private Cliente cliente;
	/**
	 * Create the frame.
	 */
	public VentanaModificarCliente() {
		setTitle("FARMAPASS-Modificar Cliente");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 580, 430);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel.setBounds(10, 11, 544, 45);
		contentPane.add(panel);
		panel.setLayout(null);
		
		txtCargo = new JTextField();
		txtCargo.setEditable(false);
		txtCargo.setBounds(10, 11, 134, 20);
		panel.add(txtCargo);
		txtCargo.setColumns(10);
		
		txtNombreEmpleado = new JTextField();
		txtNombreEmpleado.setEditable(false);
		txtNombreEmpleado.setHorizontalAlignment(SwingConstants.CENTER);
		txtNombreEmpleado.setBounds(154, 11, 300, 20);
		panel.add(txtNombreEmpleado);
		txtNombreEmpleado.setColumns(10);
		
		txtId = new JTextField();
		txtId.setHorizontalAlignment(SwingConstants.CENTER);
		txtId.setEditable(false);
		txtId.setBounds(467, 11, 67, 20);
		panel.add(txtId);
		txtId.setColumns(10);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_1.setBounds(10, 67, 544, 257);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		txtIdCliente = new JTextField();
		txtIdCliente.setHorizontalAlignment(SwingConstants.CENTER);
		txtIdCliente.setEditable(false);
		txtIdCliente.setBounds(114, 73, 86, 20);
		panel_1.add(txtIdCliente);
		txtIdCliente.setColumns(10);
		
		txtNombreCliente = new JTextField();
		txtNombreCliente.setEditable(false);
		txtNombreCliente.setBounds(114, 118, 140, 20);
		panel_1.add(txtNombreCliente);
		txtNombreCliente.setColumns(10);
		
		txtApellidosCliente = new JTextField();
		txtApellidosCliente.setEditable(false);
		txtApellidosCliente.setBounds(114, 162, 140, 20);
		panel_1.add(txtApellidosCliente);
		txtApellidosCliente.setColumns(10);
		
		txtCorreo = new JTextField();
		txtCorreo.setBounds(373, 73, 140, 20);
		panel_1.add(txtCorreo);
		txtCorreo.setColumns(10);
		
		txtTelefono = new JTextField();
		txtTelefono.setBounds(373, 118, 140, 20);
		txtTelefono.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char caracter = e.getKeyChar();
				if(((caracter < '0') || (caracter > '9')) && (caracter != '\b')) {
					e.consume();
				}
			}
		});
		panel_1.add(txtTelefono);
		txtTelefono.setColumns(10);
		
		txtUsuario = new JTextField();
		txtUsuario.setBounds(373, 162, 140, 20);
		panel_1.add(txtUsuario);
		txtUsuario.setColumns(10);
		
		JLabel lblIdCliente = new JLabel("ID:");
		lblIdCliente.setBounds(36, 76, 68, 14);
		panel_1.add(lblIdCliente);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(36, 121, 68, 14);
		panel_1.add(lblNombre);
		
		JLabel lblApellidos = new JLabel("Apellidos:");
		lblApellidos.setBounds(36, 165, 68, 14);
		panel_1.add(lblApellidos);
		
		JLabel lblCorreo = new JLabel("Correo:");
		lblCorreo.setBounds(295, 76, 68, 14);
		panel_1.add(lblCorreo);
		
		JLabel lblTelefono = new JLabel("Telefono:");
		lblTelefono.setEnabled(true);
		lblTelefono.setBounds(295, 121, 68, 14);
		panel_1.add(lblTelefono);
		
		JLabel lblUsuario = new JLabel("Usuario:");
		lblUsuario.setBounds(295, 165, 68, 14);
		panel_1.add(lblUsuario);
		
		JLabel lblModificar = new JLabel("Cliente a modificar");
		lblModificar.setHorizontalAlignment(SwingConstants.CENTER);
		lblModificar.setBounds(190, 22, 140, 14);
		panel_1.add(lblModificar);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_2.setBounds(10, 335, 544, 45);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.setBackground(Color.GREEN);
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txtNombreCliente.getText().equals("") || txtApellidosCliente.getText().equals("") || txtCorreo.getText().equals("")
						|| txtTelefono.getText().equals("") || txtUsuario.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "No pueden haber campos sin llenar!", "Error", JOptionPane.ERROR_MESSAGE);
				} else {
					cliente = controlClientes.validarUsuario(txtUsuario.getText());
					if(cliente != null) {
						JOptionPane.showMessageDialog(null, "Este usuario ya existe!", "Error", JOptionPane.ERROR_MESSAGE);
					} else {
						controlClientes.actualizaCliente(txtNombreCliente.getText(), txtApellidosCliente.getText()
								, txtCorreo.getText(), txtTelefono.getText(), txtUsuario.getText());
						setVisible(false);
						muestraDialogoConMensaje("Cliente modificado exitosamente!");
						controlClientes.regresa(empleado);
						controlClientes.recuperaClientes();
					}
				}
			}
		});
		btnGuardar.setBounds(120, 11, 89, 23);
		panel_2.add(btnGuardar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlClientes.regresa(empleado);
				setVisible(false);
			}
		});
		btnCancelar.setBackground(Color.RED);
		btnCancelar.setBounds(325, 11, 89, 23);
		panel_2.add(btnCancelar);
	}
	
	
	public void muestra(ControlClientes controlClientes, Empleado empleado, List<Cliente> clientes, Cliente cliente) {
		this.controlClientes = controlClientes;
		this.empleado = empleado;
		this.cliente = cliente;
		this.txtCargo.setText("Cargo: " + empleado.getNivel());
		this.txtNombreEmpleado.setText(empleado.getNombre() + " " + empleado.getApellido());
		this.txtId.setText("ID: " + String.valueOf(empleado.getIdEmpleado()));
		
		this.txtIdCliente.setText(String.valueOf(cliente.getIdCliente()));
		this.txtNombreCliente.setText(cliente.getNombre());
		this.txtApellidosCliente.setText(cliente.getApellidos());
		this.txtCorreo.setText(cliente.getCorreo());
		this.txtTelefono.setText(cliente.getTelefono());
		this.txtUsuario.setText(cliente.getUsuario());
		
		setVisible(true);;
	}
	
	
	public void muestraDialogoConMensaje(String mensaje) {
		JOptionPane.showMessageDialog(this, mensaje);
	}
	
}
