/**
 * Clase que muestra los campos a llenar para agregar un cliente
 * a la BD y asi poder tener beneficios.
 * @author Angel Pimentel
 */
package mx.uam.ayd.proyecto.presentacion.agregarCliente;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;

import org.springframework.stereotype.Component;

import mx.uam.ayd.proyecto.negocio.modelo.Cliente;
import mx.uam.ayd.proyecto.negocio.modelo.Empleado;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
@Component
public class VentanaAgregarCliente extends JFrame {

	private JPanel contentPane;
	private JTextField txtCargo;
	private JTextField txtNombreEmpleado;
	private JTextField txtIdEmpleado;
	private JTextField txtNombreCliente;
	private JTextField txtApellidoCliente;
	private JTextField txtCorreo;
	private JTextField txtTelefono;
	private JTextField txtUsuario;
	
	private ControlAgregarCliente controlAgregarCliente;
	private Empleado empleado;
	private Cliente cliente;
	
	/**
	 * Create the frame.
	 */
	public VentanaAgregarCliente() {
		setTitle("FARMAPASS-Registro clientes");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 450, 370);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel.setBounds(10, 11, 414, 45);
		contentPane.add(panel);
		panel.setLayout(null);
		
		txtCargo = new JTextField();
		txtCargo.setEditable(false);
		txtCargo.setBounds(10, 11, 111, 20);
		panel.add(txtCargo);
		txtCargo.setColumns(10);
		
		txtNombreEmpleado = new JTextField();
		txtNombreEmpleado.setHorizontalAlignment(SwingConstants.CENTER);
		txtNombreEmpleado.setEnabled(true);
		txtNombreEmpleado.setEditable(false);
		txtNombreEmpleado.setText("");
		txtNombreEmpleado.setBounds(131, 11, 210, 20);
		panel.add(txtNombreEmpleado);
		txtNombreEmpleado.setColumns(10);
		
		txtIdEmpleado = new JTextField();
		txtIdEmpleado.setHorizontalAlignment(SwingConstants.CENTER);
		txtIdEmpleado.setEditable(false);
		txtIdEmpleado.setBounds(351, 11, 53, 20);
		panel.add(txtIdEmpleado);
		txtIdEmpleado.setColumns(10);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_1.setBounds(10, 62, 414, 188);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Datos del nuevo cliente");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(136, 11, 150, 14);
		panel_1.add(lblNewLabel);
		
		txtNombreCliente = new JTextField();
		txtNombreCliente.setBounds(161, 36, 125, 20);
		panel_1.add(txtNombreCliente);
		txtNombreCliente.setColumns(10);
		
		txtApellidoCliente = new JTextField();
		txtApellidoCliente.setBounds(161, 67, 125, 20);
		panel_1.add(txtApellidoCliente);
		txtApellidoCliente.setColumns(10);
		
		txtCorreo = new JTextField();
		txtCorreo.setBounds(161, 98, 125, 20);
		panel_1.add(txtCorreo);
		txtCorreo.setColumns(10);
		
		txtTelefono = new JTextField();
		txtTelefono.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char caracter = e.getKeyChar();
				if(((caracter < '0') || (caracter > '9')) && (caracter != '\b')) {
					e.consume();
				}
			}
		});
		txtTelefono.setBounds(161, 129, 125, 20);
		panel_1.add(txtTelefono);
		txtTelefono.setColumns(10);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(82, 39, 69, 14);
		panel_1.add(lblNombre);
		
		JLabel lblApellidos = new JLabel("Apellidos:");
		lblApellidos.setBounds(82, 70, 69, 14);
		panel_1.add(lblApellidos);
		
		JLabel lblCorreo = new JLabel("Correo:");
		lblCorreo.setBounds(82, 101, 69, 14);
		panel_1.add(lblCorreo);
		
		JLabel lblTelefono = new JLabel("Telefono:");
		lblTelefono.setBounds(82, 132, 69, 14);
		panel_1.add(lblTelefono);
		
		txtUsuario = new JTextField();
		txtUsuario.setBounds(161, 160, 125, 20);
		panel_1.add(txtUsuario);
		txtUsuario.setColumns(10);
		
		JLabel lblUsuario = new JLabel("Usuario:");
		lblUsuario.setBounds(82, 163, 69, 14);
		panel_1.add(lblUsuario);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_2.setBounds(10, 261, 414, 45);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txtNombreCliente.getText().equals("") || txtApellidoCliente.getText().equals("")
						|| txtCorreo.getText().equals("") || txtTelefono.getText().equals("") || txtUsuario.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "No pueden haber campos sin llenar!", "Error", JOptionPane.ERROR_MESSAGE);
				}else {
					cliente = controlAgregarCliente.validarUsuario(txtUsuario.getText());
					if(cliente != null) {
						muestraDialogoConMensaje("Este usuario ya existe");
					}else {
						controlAgregarCliente.agregarCliente(txtNombreCliente.getText(), txtApellidoCliente.getText(),
								txtCorreo.getText(), txtTelefono.getText(), txtUsuario.getText());
						controlAgregarCliente.iniciaEmpleadoPrincipal(empleado);
					}
				}
			}
		});
		btnAgregar.setBackground(Color.GREEN);
		btnAgregar.setForeground(Color.DARK_GRAY);
		btnAgregar.setBounds(40, 11, 89, 23);
		panel_2.add(btnAgregar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlAgregarCliente.cancelar(empleado);
				setVisible(false);
			}
		});
		btnCancelar.setBackground(Color.RED);
		btnCancelar.setForeground(Color.DARK_GRAY);
		btnCancelar.setBounds(279, 11, 89, 23);
		panel_2.add(btnCancelar);
	}
	
	public void muestra(ControlAgregarCliente controlAgregarCliente, Empleado empleado) {
		
		this.controlAgregarCliente = controlAgregarCliente;
		this.empleado = empleado;
		this.txtCargo.setText("Cargo: " + empleado.getNivel());
		this.txtNombreEmpleado.setText(empleado.getNombre() + " " + empleado.getApellido());
		this.txtIdEmpleado.setText("ID: " + empleado.getIdEmpleado());
		
		txtNombreCliente.setText("");
		txtApellidoCliente.setText("");
		txtCorreo.setText("");
		txtTelefono.setText("");
		txtUsuario.setText("");
		
		setVisible(true);
	}
	
	public void muestraDialogoConMensaje(String mensaje) {
		JOptionPane.showMessageDialog(this , mensaje);
	}
}
