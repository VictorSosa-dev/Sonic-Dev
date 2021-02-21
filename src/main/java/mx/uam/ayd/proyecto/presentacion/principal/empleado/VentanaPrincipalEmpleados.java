package mx.uam.ayd.proyecto.presentacion.principal.empleado;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

import org.springframework.stereotype.Component;

import mx.uam.ayd.proyecto.negocio.modelo.Empleado;
import mx.uam.ayd.proyecto.presentacion.reporteEmpleados.ControlNumReporte;

@SuppressWarnings("serial")
@Component
public class VentanaPrincipalEmpleados extends JFrame {

	private JPanel contentPane;
	private ControlPrincipalEmpleados control;
	private Empleado empleado;
	private JTextField txtNombreEmpleado;
	private JTextField txtNivel;
<<<<<<< HEAD
	private JTextField txtIdEmpleado;
=======
	
	private ControlNumReporte controlNumReporte;
>>>>>>> 3ea40363e3c4138c477a045d4880f570c6145659

	public VentanaPrincipalEmpleados() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 560, 337);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		panel.setBounds(5, 5, 529, 34);
		panel.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(5, 45, 529, 174);

		JPanel panel_2 = new JPanel();
		panel_2.setBounds(10, 238, 524, 52);
		panel_2.setLayout(null);

		JButton btnCierreVenta = new JButton("Inicia cierre de venta");
		btnCierreVenta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				control.iniciaCierreVenta(empleado);
			}
		});
		btnCierreVenta.setForeground(new Color(255, 255, 255));
		btnCierreVenta.setBackground(new Color(255, 0, 0));
		btnCierreVenta.setBounds(10, 11, 156, 23);
		panel_2.add(btnCierreVenta);
		panel_1.setLayout(null);

		JButton btnVenta = new JButton("Venta-Normal");
		btnVenta.setBounds(49, 45, 321, 23);
		panel_1.add(btnVenta);
		btnVenta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				control.agregarProductos(empleado);
			}
		});
		contentPane.setLayout(null);
		panel.setLayout(null);

		txtNombreEmpleado = new JTextField();
		txtNombreEmpleado.setHorizontalAlignment(SwingConstants.CENTER);
		txtNombreEmpleado.setEditable(false);
		txtNombreEmpleado.setBounds(153, 6, 270, 22);
		panel.add(txtNombreEmpleado);
		txtNombreEmpleado.setColumns(10);

		txtNivel = new JTextField();
		txtNivel.setEditable(false);
		txtNivel.setBounds(10, 7, 116, 20);
		panel.add(txtNivel);
		txtNivel.setColumns(10);
		contentPane.add(panel);
		
		txtIdEmpleado = new JTextField();
		txtIdEmpleado.setHorizontalAlignment(SwingConstants.CENTER);
		txtIdEmpleado.setEditable(false);
		txtIdEmpleado.setBounds(433, 7, 86, 20);
		panel.add(txtIdEmpleado);
		txtIdEmpleado.setColumns(10);
		contentPane.add(panel_1);
		
		JButton btnNuevoPedidoCliente = new JButton("Pedido cliente");
		btnNuevoPedidoCliente.setEnabled(false);
		btnNuevoPedidoCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				control.agregaPedidoCliente(empleado);
			}
		});
		btnNuevoPedidoCliente.setBounds(229, 123, 141, 23);
		panel_1.add(btnNuevoPedidoCliente);
		
		JButton btnNewButtonBusqueda = new JButton("Busqueda");
		btnNewButtonBusqueda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				control.busqueda();
			}
		});
		btnNewButtonBusqueda.setBounds(49, 12, 321, 23);
		panel_1.add(btnNewButtonBusqueda);
		
		JButton btnRecepcionMercancia = new JButton("Recepcion de Mercancia");
		btnRecepcionMercancia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				control.iniciaRecepcionMercancia(empleado);
			}
		});
		btnRecepcionMercancia.setBounds(49, 78, 321, 23);
		panel_1.add(btnRecepcionMercancia);
		contentPane.add(panel_2);
		
		JButton btnActualiza = new JButton("Actualiza Inventario");
		btnActualiza.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				control.muestraVentanaActualiza(empleado);
			}
		});
		btnActualiza.setBounds(49, 123, 170, 23);
		panel_1.add(btnActualiza);
		
		JButton btnVentaMembresia = new JButton("Clientes");
		btnVentaMembresia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				control.iniciaClientes(empleado);
			}
		});
		btnVentaMembresia.setBounds(380, 12, 139, 23);
		panel_1.add(btnVentaMembresia);
		contentPane.add(panel_2);
	}

	public void muestra(ControlPrincipalEmpleados control, Empleado empleado) {
		this.control = control;
		this.empleado = empleado;
		this.txtNombreEmpleado
				.setText(empleado.getNombre() + " " + empleado.getApellido());
<<<<<<< HEAD
		this.txtNivel.setText("Cargo: " + empleado.getNivel());
		this.txtIdEmpleado.setText("ID: " + empleado.getIdEmpleado());
=======
		this.txtNivel.setText(empleado.getNivel() + ":");
		
>>>>>>> 3ea40363e3c4138c477a045d4880f570c6145659
		setVisible(true);

	}
	

	public void oculta() {
		setVisible(false);
	}
}
