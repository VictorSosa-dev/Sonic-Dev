package mx.uam.ayd.proyecto.presentacion.PedidoCliente;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

import org.springframework.stereotype.Component;

import mx.uam.ayd.proyecto.negocio.modelo.Empleado;
import mx.uam.ayd.proyecto.negocio.modelo.Producto;
import javax.swing.JTable;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import java.awt.FlowLayout;

@SuppressWarnings("serial")
@Component
public class VentanaPedidoCliente extends JFrame {

	private JPanel contentPane;
	private ControlPedidoCliente control;
	private Empleado empleado;
	private JTextField txtNombreEmpleado;
	private JTextField txtNivel;
	private JTextField txtNombreCliente;
	private JTextField txtAPaterno;
	private JTextField txtAMaterno;
	private JTextField txtCorreo;
	private JTextField txtTelefono;

	private JComboBox<String> comboProductos = new JComboBox<String>();
	JLabel lblNewLabel = new JLabel("Producto:");

	JPanel panelProducto = new JPanel();
	private JTextField txtPiezas;

	public VentanaPedidoCliente() {
		setBounds(100, 100, 513, 420);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		panel.setBounds(5, 5, 483, 34);
		panel.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));

		JPanel panel_datosCliente = new JPanel();
		panel_datosCliente.setBounds(5, 45, 483, 132);

		JPanel panel_botones = new JPanel();
		panel_botones.setBounds(5, 318, 483, 52);
		panel_botones.setLayout(null);

		JButton btnCrear = new JButton("Crear pedido");
		btnCrear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// control.iniciaCierreVenta(empleado);
			}
		});
		btnCrear.setForeground(new Color(255, 255, 255));
		btnCrear.setBackground(Color.GREEN);
		btnCrear.setBounds(317, 18, 156, 23);
		panel_botones.add(btnCrear);
		panel_datosCliente.setLayout(null);
		contentPane.setLayout(null);
		panel.setLayout(null);

		txtNombreEmpleado = new JTextField();
		txtNombreEmpleado.setHorizontalAlignment(SwingConstants.CENTER);
		txtNombreEmpleado.setEditable(false);
		txtNombreEmpleado.setBounds(106, 6, 367, 22);
		panel.add(txtNombreEmpleado);
		txtNombreEmpleado.setColumns(10);

		txtNivel = new JTextField();
		txtNivel.setEditable(false);
		txtNivel.setBounds(10, 7, 86, 20);
		panel.add(txtNivel);
		txtNivel.setColumns(10);
		contentPane.add(panel);
		contentPane.add(panel_datosCliente);

		JLabel lblDatosCliente = new JLabel("DATOS DEL CLIENTE");
		lblDatosCliente.setHorizontalAlignment(SwingConstants.CENTER);
		lblDatosCliente.setBounds(0, 0, 483, 14);
		panel_datosCliente.add(lblDatosCliente);

		txtNombreCliente = new JTextField();
		txtNombreCliente.setBounds(93, 25, 140, 20);
		panel_datosCliente.add(txtNombreCliente);
		txtNombreCliente.setColumns(10);

		txtAPaterno = new JTextField();
		txtAPaterno.setBounds(103, 56, 130, 20);
		panel_datosCliente.add(txtAPaterno);
		txtAPaterno.setColumns(10);

		txtAMaterno = new JTextField();
		txtAMaterno.setColumns(10);
		txtAMaterno.setBounds(373, 56, 100, 20);
		panel_datosCliente.add(txtAMaterno);

		JLabel lblClienteNombre = new JLabel("Nombre(s):");
		lblClienteNombre.setHorizontalAlignment(SwingConstants.CENTER);
		lblClienteNombre.setBounds(0, 28, 83, 14);
		panel_datosCliente.add(lblClienteNombre);

		JLabel lblClienteAPaterno = new JLabel("Apellido Paterno:");
		lblClienteAPaterno.setHorizontalAlignment(SwingConstants.CENTER);
		lblClienteAPaterno.setBounds(0, 59, 93, 14);
		panel_datosCliente.add(lblClienteAPaterno);

		JLabel lblApellidoMaterno = new JLabel("Apellido Materno:");
		lblApellidoMaterno.setHorizontalAlignment(SwingConstants.CENTER);
		lblApellidoMaterno.setBounds(243, 59, 120, 14);
		panel_datosCliente.add(lblApellidoMaterno);

		txtCorreo = new JTextField();
		txtCorreo.setColumns(10);
		txtCorreo.setBounds(93, 87, 140, 20);
		panel_datosCliente.add(txtCorreo);

		txtTelefono = new JTextField();
		txtTelefono.setColumns(10);
		txtTelefono.setBounds(333, 87, 140, 20);
		panel_datosCliente.add(txtTelefono);

		JLabel lblCorreo = new JLabel("Correo:");
		lblCorreo.setHorizontalAlignment(SwingConstants.CENTER);
		lblCorreo.setBounds(0, 90, 83, 14);
		panel_datosCliente.add(lblCorreo);

		JLabel lblTelefono = new JLabel("Telefono:");
		lblTelefono.setHorizontalAlignment(SwingConstants.CENTER);
		lblTelefono.setBounds(243, 90, 83, 14);
		panel_datosCliente.add(lblTelefono);
		contentPane.add(panel_botones);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setForeground(Color.WHITE);
		btnCancelar.setBackground(new Color(255, 0, 0));
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				comboProductos.removeAllItems();
				control.cancelarPedidoCliente(empleado);
			}
		});
		btnCancelar.setBounds(183, 18, 124, 23);
		panel_botones.add(btnCancelar);

		panelProducto.setBounds(5, 188, 483, 65);
		contentPane.add(panelProducto);
		panelProducto.setLayout(null);

		comboProductos.setBounds(89, 25, 140, 20);
		lblNewLabel.setBounds(10, 28, 69, 14);
		panelProducto.add(lblNewLabel);
		panelProducto.add(comboProductos);
		
		JLabel lblPiezas = new JLabel("Piezas:");
		lblPiezas.setBounds(239, 28, 45, 14);
		panelProducto.add(lblPiezas);
		
		txtPiezas = new JTextField();
		txtPiezas.setBounds(294, 25, 45, 20);
		panelProducto.add(txtPiezas);
		txtPiezas.setColumns(10);
		
		JLabel lblProducto = new JLabel("PRODUCTO");
		lblProducto.setHorizontalAlignment(SwingConstants.CENTER);
		lblProducto.setBounds(0, 0, 483, 14);
		panelProducto.add(lblProducto);
		
		JButton btnAgregarProducto = new JButton("Agregar");
		btnAgregarProducto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				agregarProductosTabla();
			}
		});
		btnAgregarProducto.setBounds(384, 24, 89, 23);
		panelProducto.add(btnAgregarProducto);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(5, 264, 482, 43);
		contentPane.add(panel_1);
		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JScrollPane scrollPane = new JScrollPane();
		panel_1.add(scrollPane);

	}

	public void muestra(ControlPedidoCliente control, Empleado empleado) {
		this.control = control;
		this.empleado = empleado;
		this.txtNombreEmpleado
				.setText(empleado.getNombre() + " " + empleado.getApellidoP() + " " + empleado.getApellidoM());
		this.txtNivel.setText(empleado.getNivel() + ":");
		despliegaListaProductosConReceta();
		setVisible(true);

	}

	public void oculta() {
		setVisible(false);
	}
	
	private void despliegaListaProductosConReceta() {
		comboProductos.addItem("Selecciona un producto");
		List<Producto> productos = control.obtenerProductosConReceta();
		for (Producto producto : productos) {
			comboProductos.addItem(producto.getNombre());
		}
	}
	
	private void agregarProductosTabla() {
		comboProductos.getSelectedItem();
		
	}
}
