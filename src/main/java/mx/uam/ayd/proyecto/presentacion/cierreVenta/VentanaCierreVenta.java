package mx.uam.ayd.proyecto.presentacion.cierreVenta;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import org.springframework.stereotype.Component;

import mx.uam.ayd.proyecto.negocio.modelo.Empleado;

import javax.swing.JScrollPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JSeparator;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.EtchedBorder;
import javax.swing.JTextField;
import java.awt.BorderLayout;

@SuppressWarnings("serial")
@Component
public class VentanaCierreVenta extends JFrame {

	private JPanel contentPane;
	private JButton btnEnviar;
	private ControlCierreVenta control;

	DefaultTableModel modeloInventario = new DefaultTableModel();
	private JTable tabla_inventario;
	private JTextField txtNombreEmpleado;
	private Empleado empleado;
	private JTextField txtNivel;

	public VentanaCierreVenta() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 408, 351);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		modeloInventario.addColumn("Nombre");
		modeloInventario.addColumn("Compuesto");
		modeloInventario.addColumn("Precio");
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		
		JScrollPane scrollPaneCierreVenta = new JScrollPane();
		scrollPaneCierreVenta.setBounds(0, 0, 372, 135);
		panel.add(scrollPaneCierreVenta);
		
		tabla_inventario = new JTable(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Nombre", "Compuesto", "Precio"
			}
		));
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		
		txtNombreEmpleado = new JTextField();
		txtNombreEmpleado.setHorizontalAlignment(SwingConstants.CENTER);
		txtNombreEmpleado.setEditable(false);
		txtNombreEmpleado.setColumns(10);
		txtNombreEmpleado.setBounds(106, 6, 254, 22);
		panel_1.add(txtNombreEmpleado);
		
		JPanel panel_2 = new JPanel();
		
		JPanel panel_3 = new JPanel();
		
		JPanel panel_4 = new JPanel();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(panel_3, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(panel_2, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(panel_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 379, Short.MAX_VALUE)
						.addComponent(panel_4, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 372, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
					.addGap(8)
					.addComponent(panel_3, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 135, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_4, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		txtNivel = new JTextField();
		txtNivel.setEditable(false);
		txtNivel.setColumns(10);
		txtNivel.setBounds(10, 7, 86, 20);
		panel_1.add(txtNivel);
		panel_4.setLayout(null);
		
				JButton btnCancelar = new JButton("Cancelar");
				btnCancelar.setBounds(184, 11, 89, 23);
				panel_4.add(btnCancelar);
				
						btnEnviar = new JButton("Enviar");
						btnEnviar.setBounds(283, 11, 89, 23);
						panel_4.add(btnEnviar);
		panel_3.setLayout(null);
		
		
		
		
		JButton btnInventario = new JButton("Inventario");
		btnInventario.setBounds(0, 11, 83, 23);
		panel_3.add(btnInventario);
		
		JButton btnVentas = new JButton("Ventas");
		btnVentas.setBounds(93, 11, 65, 23);
		panel_3.add(btnVentas);
		
		JButton btnPedidos = new JButton("Pedidos");
		btnPedidos.setBounds(168, 11, 69, 23);
		panel_3.add(btnPedidos);
		btnInventario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				scrollPaneCierreVenta.setViewportView(tabla_inventario);
			}
		});
				panel_2.setLayout(null);
		
				JLabel lblCierreVenta = new JLabel("DETALLE DE CIERRE DE VENTA");
				lblCierreVenta.setBounds(0, 0, 379, 21);
				panel_2.add(lblCierreVenta);
				lblCierreVenta.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.setLayout(gl_contentPane);
	}

	public void muestra(ControlCierreVenta control, Empleado empleado) {
		this.control = control;
		this.empleado = empleado;
		this.txtNombreEmpleado
				.setText(empleado.getNombre() + " " + empleado.getApellidoP() + " " + empleado.getApellidoM());
		this.txtNivel.setText(empleado.getNivel());
		setVisible(true);
	}
}
