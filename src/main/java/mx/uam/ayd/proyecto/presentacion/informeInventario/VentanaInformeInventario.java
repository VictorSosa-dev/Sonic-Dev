package mx.uam.ayd.proyecto.presentacion.informeInventario;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import mx.uam.ayd.proyecto.negocio.modelo.Empleado;
import mx.uam.ayd.proyecto.negocio.modelo.Producto;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@Slf4j
@SuppressWarnings("serial")
@Component
public class VentanaInformeInventario extends JFrame {

	private JPanel contentPane;
	private JTextField textCargo;
	private JTextField textEmpleado;
	private JTextField textNumEmpleado;
	
	//tabla inventario
	DefaultTableModel modeloInventario = new DefaultTableModel();
	private JTable tabla_Inventario;
	private ControlInformeInventario controlInformeInventario;
	private Empleado empleado;
	private Producto producto;
	//private List<Producto> productos = new ArrayList<>();
	/**
	 * Create the frame.
	 */
	public VentanaInformeInventario() {
		
		setTitle("FARMAPASS");
		
		setBounds(100, 100, 450, 316);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		
		JPanel panel_2 = new JPanel();
		
		JPanel panel_3 = new JPanel();
		
		JPanel panel_4 = new JPanel();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 424, GroupLayout.PREFERRED_SIZE)
					.addGap(10))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 424, Short.MAX_VALUE)
					.addContainerGap())
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(panel_4, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 424, Short.MAX_VALUE)
						.addComponent(panel_3, GroupLayout.DEFAULT_SIZE, 424, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_3, GroupLayout.PREFERRED_SIZE, 155, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_4, GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE))
		);
		panel_4.setLayout(null);
		
		JButton btnRealizarUnPedido = new JButton("Realizar un pedido");
		btnRealizarUnPedido.setBounds(146, 11, 127, 23);
		panel_4.add(btnRealizarUnPedido);
		btnRealizarUnPedido.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modeloInventario = (DefaultTableModel) tabla_Inventario.getModel();
				//int a = tabla_Inventario.getSelectedRow();
				List<String> listaProductos = new ArrayList<>();
				List<Producto> productos2 = new ArrayList<>();
				try {
					//System.out.println(tabla_Inventario.getRowCount());
					for(int i = 0; i < tabla_Inventario.getRowCount(); i++) {
						//System.out.println(tabla_Inventario.getValueAt(i, 3));
						if(String.valueOf(tabla_Inventario.getValueAt(i, 3)) != "false" && String.valueOf(tabla_Inventario.getValueAt(i, 3)) != "null") {
							listaProductos.add((String) tabla_Inventario.getValueAt(i, 0));
							//System.out.println(listaProductos);
							producto = controlInformeInventario.obtenerProducto((String) tabla_Inventario.getValueAt(i, 0));
							productos2.add(producto);
						}
					}
					if(listaProductos.isEmpty()) {
						muestraDialogoConMensaje("Debes de seleccionar un producto");
					}else {
						int confirmar = JOptionPane.showConfirmDialog(null, "¿Quieres realizar un pedido?");
						if(JOptionPane.OK_OPTION == confirmar) {
							//System.out.println(listaProductos);
							//System.out.println(productos2);
							controlInformeInventario.iniciaRealizarPedido(empleado, productos2);
						}
					}
				}catch(Exception ex) {
					
				}
			}
		});
		
		JButton btnCerrar = new JButton("Cerrar");
		btnCerrar.setBounds(314, 11, 89, 23);
		panel_4.add(btnCerrar);
		panel_3.setLayout(null);
		btnCerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//controlInformeInventario.cerrar();
				controlInformeInventario.cerrar(empleado);
			}
		});
		
		JScrollPane scrollPaneInformeInventario = new JScrollPane();
		scrollPaneInformeInventario.setBounds(0, 0, 424, 155);
		panel_3.add(scrollPaneInformeInventario);
		panel_2.setLayout(null);
		
		JLabel lblInformeInventario = new JLabel("Informe Inventario");
		lblInformeInventario.setHorizontalAlignment(SwingConstants.CENTER);
		lblInformeInventario.setBounds(0, 0, 424, 21);
		panel_2.add(lblInformeInventario);
		panel_1.setLayout(null);
		
		textCargo = new JTextField();
		textCargo.setEditable(false);
		textCargo.setBounds(10, 7, 86, 20);
		panel_1.add(textCargo);
		textCargo.setColumns(10);
		
		textEmpleado = new JTextField();
		textEmpleado.setEditable(false);
		textEmpleado.setBounds(106, 7, 253, 20);
		panel_1.add(textEmpleado);
		textEmpleado.setColumns(10);
		
		textNumEmpleado = new JTextField();
		textNumEmpleado.setEditable(false);
		textNumEmpleado.setBounds(369, 7, 51, 20);
		panel_1.add(textNumEmpleado);
		textNumEmpleado.setColumns(10);
		contentPane.setLayout(gl_contentPane);
		
		//tabla inventario
		tabla_Inventario = new JTable(modeloInventario) {
			public boolean isCellEditable(int rowIndex, int vColIndex) {
				if(vColIndex == 3) {
					return true;
				}else {
					return false;
				}
			}
		};
		//añadiendo
		modeloInventario.addColumn("Producto");
		modeloInventario.addColumn("Compuesto");
		modeloInventario.addColumn("Cantidad");
		modeloInventario.addColumn("Seleccionó");
		
		scrollPaneInformeInventario.setViewportView(tabla_Inventario);		
	}
	
	public void muestra(ControlInformeInventario controlInformeInventario, Empleado empleado, List<Producto> productos) {
		this.controlInformeInventario = controlInformeInventario;
		this.empleado = empleado;
		this.textEmpleado.setText(empleado.getNombre() + " " + empleado.getApellidoP() + " " + empleado.getApellidoM());
		this.textCargo.setText(empleado.getNivel());
		this.textNumEmpleado.setText(Long.toString(empleado.getIdEmpleado()));
		
		//modeloInventario.setRowCount(0);
		limpiaTabla();
		for(Producto producto:productos) {
			modeloInventario.addRow(new Object[] {producto.getNombre(), producto.getCompuesto(), producto.getPiezas()});
			tabla_Inventario.setModel(modeloInventario);
		}
		addCheckBox(3, tabla_Inventario);
		setVisible(true);
	}
	
	/**
	 * Metodo que agrega los checkbox a la tabla del inventario
	 * @param column
	 * @param table
	 */
	public void addCheckBox(int column, JTable table) {
		TableColumn tc = table.getColumnModel().getColumn(column);
		tc.setCellEditor(table.getDefaultEditor(Boolean.class));
		tc.setCellRenderer(table.getDefaultRenderer(Boolean.class));
	}
	
	/**
	 * Este metodo permite identificar si algun producto esta sellecionado o no
	 * @param row numero de la fila donde esta ubicado el checbox
	 * @param column columna donde esta ubicado el checkbox
	 * @param table tabla donde se agregara
	 * @return si esta seleccionado o no
	 */
	public boolean IsSelected(int row, int column, JTable table) {
		return table.getValueAt(row, column) != null;
	}
	
	private void limpiaTabla() {
		//txtTotalProductos.setText("");
		int filas = tabla_Inventario.getRowCount();
		try {
			for (int i = 0; filas > i; i++) {
				modeloInventario.removeRow(0);
			}
		} catch (Exception e) {
			log.warn("## ERROR AL LIMPIAR LA TABLA: " + e);
			JOptionPane.showMessageDialog(null, "No se pudo limpiar la tabla.", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void muestraDialogoConMensaje(String mensaje) {
		JOptionPane.showMessageDialog(this, mensaje);
	}
	
	public void oculta() {
		limpiaTabla();
		setVisible(false);
	}
	
}
