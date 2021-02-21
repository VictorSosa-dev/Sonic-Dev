/**
 * Clase que se encarga de mostrar los clientes en la BD
 * al igual que aqui se encuentran los botones con los cuales 
 * podemos operar sobre los clientes:
 * Agregar, Editar, Eliminar y Realizar una venta.
 * 
 * @author Angel Pimentel
 */
package mx.uam.ayd.proyecto.presentacion.ventaMembresia;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import mx.uam.ayd.proyecto.negocio.modelo.Cliente;
import mx.uam.ayd.proyecto.negocio.modelo.Empleado;

import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@Slf4j
@SuppressWarnings("serial")
@Component
public class VentanaClientes extends JFrame {

	private JPanel contentPane;
	private ControlClientes controlClientes;
	private JTextField txtCargo;
	private JTextField txtNombreEmpleado;
	private JTextField txtId;
	private JScrollPane scrollPane;
	
	private Empleado empleado;
	DefaultTableModel modeloClientes = new DefaultTableModel();
	private JTable tablaClientes;
	
	/**
	 * Create the frame.
	 */
	public VentanaClientes() {
		setTitle("FARMAPASS-Clientes");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 540, 460);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel.setBounds(10, 11, 504, 45);
		contentPane.add(panel);
		panel.setLayout(null);
		
		txtCargo = new JTextField();
		txtCargo.setEnabled(true);
		txtCargo.setEditable(false);
		txtCargo.setText("");
		txtCargo.setBounds(10, 11, 107, 20);
		panel.add(txtCargo);
		txtCargo.setColumns(10);
		
		txtNombreEmpleado = new JTextField();
		txtNombreEmpleado.setEditable(false);
		txtNombreEmpleado.setHorizontalAlignment(SwingConstants.CENTER);
		txtNombreEmpleado.setBounds(156, 11, 200, 20);
		panel.add(txtNombreEmpleado);
		txtNombreEmpleado.setColumns(10);
		
		txtId = new JTextField();
		txtId.setHorizontalAlignment(SwingConstants.CENTER);
		txtId.setEditable(false);
		txtId.setBounds(396, 11, 86, 20);
		panel.add(txtId);
		txtId.setColumns(10);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_1.setBounds(10, 61, 504, 35);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblClientes = new JLabel("Clientes");
		lblClientes.setHorizontalAlignment(SwingConstants.CENTER);
		lblClientes.setBounds(214, 11, 77, 14);
		panel_1.add(lblClientes);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(10, 107, 504, 190);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 504, 190);
		panel_2.add(scrollPane);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_3.setBounds(10, 365, 504, 45);
		contentPane.add(panel_3);
		panel_3.setLayout(null);
		
		JButton btnIniciarVenta = new JButton("Venta");
		btnIniciarVenta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int contSel = 0;
				int contNoSel = 0;
				String nombre = null;
				try {
					for(int i = 0; i< tablaClientes.getRowCount(); i++) {
						if(String.valueOf(tablaClientes.getValueAt(i, 6)) != "false" && String.valueOf(tablaClientes.getValueAt(i, 6)) != "null") {
							nombre = (String) tablaClientes.getValueAt(i, 1);
							contSel++;
						} else {
							contNoSel++;
						}
					}
					if(contSel == 1) {
						controlClientes.iniciaVentaMembresia(empleado, nombre);
						limpiaTabla();
						controlClientes.regresa(empleado);
					} else {
						if(nombre == null) {
							System.out.println(nombre);
							JOptionPane.showMessageDialog(null, "No has seleccionado a ningun cliente!", "Error", JOptionPane.WARNING_MESSAGE);
						} else {
							JOptionPane.showMessageDialog(null, "Solo puedes vender a un cliente a la vez!", "Error", JOptionPane.WARNING_MESSAGE);
						}
					}
				} catch(Exception e2) {
					System.out.println(e2);
					JOptionPane.showConfirmDialog(null, "Error al leer la tabla");
				}
			}
		});
		btnIniciarVenta.setBackground(Color.ORANGE);
		btnIniciarVenta.setBounds(23, 11, 89, 23);
		panel_3.add(btnIniciarVenta);
		
		JButton btnRegresar = new JButton("Regresar");
		btnRegresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlClientes.regresa(empleado);
				oculta();
				controlClientes.regresa(empleado);
			}
		});
		btnRegresar.setBounds(392, 11, 89, 23);
		panel_3.add(btnRegresar);
		
		tablaClientes = new JTable(modeloClientes) {
			public boolean isCellEditable(int rowIndex, int vColIndex) {
				if(vColIndex == 6) {
					return true;
				} else {
					return false;
				}
			}
		};
		
		modeloClientes.addColumn("ID");
		modeloClientes.addColumn("Nombre");
		modeloClientes.addColumn("Apellidos");
		modeloClientes.addColumn("Correo");
		modeloClientes.addColumn("Telefono");
		modeloClientes.addColumn("Usuario");
		modeloClientes.addColumn("Seleccionar");
		
		scrollPane.setViewportView(tablaClientes);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_5.setBounds(10, 308, 504, 45);
		contentPane.add(panel_5);
		panel_5.setLayout(null);
		
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.setBackground(Color.GREEN);
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlClientes.iniciaAgregarCliente(empleado);
				setVisible(false);
			}
		});
		btnAgregar.setBounds(24, 11, 89, 23);
		panel_5.add(btnAgregar);
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.setBackground(Color.YELLOW);
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int contSel = 0;
				int contNoSel = 0;
				String nombre = null;
				try {
					for(int i = 0; i< tablaClientes.getRowCount(); i++) {
						if(String.valueOf(tablaClientes.getValueAt(i, 6)) != "false" && String.valueOf(tablaClientes.getValueAt(i, 6)) != "null") {
							nombre = (String) tablaClientes.getValueAt(i, 1);
							contSel++;
						} else {
							contNoSel++;
						}
					}
					if(contSel == 1) {
						controlClientes.iniciaModificar(empleado, nombre);
						oculta();
					} else {
						if(nombre == null) {
							System.out.println(nombre);
							JOptionPane.showMessageDialog(null, "No has seleccionado a ningun cliente!", "Error", JOptionPane.WARNING_MESSAGE);
						} else {
							JOptionPane.showMessageDialog(null, "Solo puedes editar a un cliente a la vez!", "Error", JOptionPane.WARNING_MESSAGE);
						}
					}
				} catch(Exception e2) {
					System.out.println(e2);
					JOptionPane.showMessageDialog(null, "Error al leer la tabla.");
				}
			}
		});
		btnEditar.setBounds(201, 11, 89, 23);
		panel_5.add(btnEditar);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.setBackground(Color.RED);
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modeloClientes = (DefaultTableModel) tablaClientes.getModel();
				int a = tablaClientes.getSelectedRow();
				List<String> listaClientes = new ArrayList<>();
				int contSel = 0; 
				int contNoSel = 0; 
				try {
					for(int i = 0; i < tablaClientes.getRowCount(); i++) {
						if(String.valueOf(tablaClientes.getValueAt(i, 6)) != "false") {
							listaClientes.add((String) tablaClientes.getValueAt(i, 1));
							contSel++;//
						}
					}
					if(listaClientes.isEmpty()) {
						JOptionPane.showMessageDialog(null, "Debes de seleccionar a un cliente!", "Error", JOptionPane.WARNING_MESSAGE);
					} else {
						if(contSel == 1) {
							System.out.println(listaClientes.get(0));
							int confirmar = JOptionPane.showConfirmDialog(null, "Seguro que quieres eliminar a: " + listaClientes);
							if(JOptionPane.OK_OPTION == confirmar) {
								controlClientes.eliminarCliente(listaClientes);
								controlClientes.recuperaClientes();
							}
						} else {
							JOptionPane.showMessageDialog(null, "Solo puedes eliminar a un cliente a la vez!", "Error", JOptionPane.WARNING_MESSAGE);
						}
					}
				} catch(Exception e2) {
					
				}
			}
		});
		btnEliminar.setBounds(389, 11, 89, 23);
		panel_5.add(btnEliminar);
	}
	
	
	public void muestra(ControlClientes controlClientes, Empleado empleado) {
		this.controlClientes = controlClientes;
		this.empleado = empleado;
		
		txtNombreEmpleado.setText(empleado.getNombre() + " " + empleado.getApellido());
		txtCargo.setText("Cargo: " + empleado.getNivel());
		txtId.setText("ID: " + empleado.getIdEmpleado());
		
		limpiaTabla();
		controlClientes.recuperaClientes();
		addCheckBox(6, tablaClientes);
		
		scrollPane.setViewportView(tablaClientes);
		setVisible(true);
	}
	
	
	public void addCheckBox(int column, JTable table) {
		TableColumn tc = table.getColumnModel().getColumn(column);
		tc.setCellEditor(table.getDefaultEditor(Boolean.class));
		tc.setCellRenderer(table.getDefaultRenderer(Boolean.class));
	}
	
	
	public boolean isSelected(int row, int column, JTable table) {
		return table.getValueAt(row, column) != null;
	}
	
	
	public void limpiaTabla() {
		if(tablaClientes.getRowCount() > 0) {
			int filas = tablaClientes.getRowCount();
			try {
				for(int i = 0; filas > i; i++) {
					modeloClientes.removeRow(0);
				}
			} catch(Exception e) {
				log.warn("## ERROR AL LIMPIAR LA TABLA: " + e);
				JOptionPane.showMessageDialog(null, "No se pudo limpiar la tabla.", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	
	public void muestraDialogoConMensaje(String mensaje) {
		JOptionPane.showMessageDialog(this, mensaje);
	}
	
	
	public void oculta() {
		limpiaTabla();
		setVisible(false);
	}
	
	
	public void agregaClientes(Cliente cliente) {
		modeloClientes.addRow(new Object[] {
				cliente.getIdCliente(),
				cliente.getNombre(),
				cliente.getApellidos(),
				cliente.getCorreo(),
				cliente.getTelefono(),
				cliente.getUsuario(),
				false
		});
		
		tablaClientes.setModel(modeloClientes);
		scrollPane.setViewportView(tablaClientes);
	}
}
