package mx.uam.ayd.proyecto.presentacion.informeInventario;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
//import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
//import javax.swing.table.TableColumn;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import mx.uam.ayd.proyecto.negocio.modelo.Empleado;
import mx.uam.ayd.proyecto.negocio.modelo.Producto;
import mx.uam.ayd.proyecto.presentacion.principal.encargado.ControlPrincipalEncargado;
@Slf4j
@SuppressWarnings("serial")
@Component
public class VentanaRealizarUnPedido extends JFrame {

	private JPanel contentPane;
	private JTextField textCargo;
	private JTextField textEmpleado;
	private JTextField textNumEmpleado;
	private ControlRealizarUnPedido controlRealizarUnPedido;
	private ControlPrincipalEncargado controlPrincipalEncargado;
	Empleado empleado;
	private Producto producto;
	
	//tabla inventario
	DefaultTableModel modeloInventario = new DefaultTableModel();
	private JTable tabla_Inventario= new JTable(modeloInventario) {
		public boolean isCellEditable(int rowIndex, int vColIndex) {
			if(vColIndex == 1) {
				return true;
			}else {
				return false;
			}
		}
	};
	
	/**
	 * Create the frame.
	 */
	public VentanaRealizarUnPedido() {
		
		setTitle("FARMAPASS");
		setBounds(100, 100, 450, 420);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 11, 414, 41);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		textCargo = new JTextField();
		textCargo.setEditable(false);
		textCargo.setBounds(10, 11, 86, 20);
		panel_1.add(textCargo);
		textCargo.setColumns(10);
		
		textEmpleado = new JTextField();
		textEmpleado.setEditable(false);
		textEmpleado.setBounds(106, 11, 250, 20);
		panel_1.add(textEmpleado);
		textEmpleado.setColumns(10);
		
		textNumEmpleado = new JTextField();
		textNumEmpleado.setBounds(366, 11, 34, 20);
		panel_1.add(textNumEmpleado);
		textNumEmpleado.setEditable(false);
		textNumEmpleado.setColumns(10);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(10, 63, 414, 32);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblRealizarPedido = new JLabel("Realizar Pedido");
		lblRealizarPedido.setHorizontalAlignment(SwingConstants.CENTER);
		lblRealizarPedido.setBounds(149, 11, 102, 14);
		panel_2.add(lblRealizarPedido);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(10, 107, 414, 211);
		contentPane.add(panel_3);
		panel_3.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 414, 200);
		panel_3.add(scrollPane);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBounds(10, 329, 414, 41);
		contentPane.add(panel_4);
		panel_4.setLayout(null);
		
		//añadiendo
		modeloInventario.addColumn("Producto");
		modeloInventario.addColumn("Cantidad");
		scrollPane.setViewportView(tabla_Inventario);
		
		JButton btnRealizarPedido = new JButton("Realizar pedido");
		btnRealizarPedido.setBounds(141, 11, 116, 23);
		panel_4.add(btnRealizarPedido);
		btnRealizarPedido.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modeloInventario = (DefaultTableModel)tabla_Inventario.getModel();
				int nuevasPiezas = 0; //param por pasar con el nuevo #de pzas
				Object totalPiezas = null; //#de pzas
				String a, nombre;
				float precioT = 0;
				for(int i = 0; i < tabla_Inventario.getRowCount(); i++){
					totalPiezas = tabla_Inventario.getValueAt(i,1); //obtiene el total de piezas
					a = String.valueOf(totalPiezas);
					if( a != "0" ||  a != " "){
						nuevasPiezas = Integer.valueOf(a);
						nombre = (String) tabla_Inventario.getValueAt(i,0);
						System.out.println(nombre + " " + nuevasPiezas);
						precioT = controlRealizarUnPedido.calculaPrecioTotal(nombre, nuevasPiezas);
						controlRealizarUnPedido.mandaPedido(empleado, nombre, nuevasPiezas, precioT);
						//oculta();
						
					}else{
						nombre = (String) tabla_Inventario.getValueAt(i,0);
						System.out.println(nuevasPiezas + ", " + nombre);
						//controlRealizarUnPedido.mandaPedido(nombre, nuevasPiezas);
					}
				}
			}
		});
		
		JButton btnCancelar = new JButton("Regresar");
		btnCancelar.setBounds(290, 11, 89, 23);
		panel_4.add(btnCancelar);
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlRealizarUnPedido.cancelar(empleado);
			}
		});
	}

	public void muestra(ControlRealizarUnPedido controlRealizarUnPedido, Empleado empleado, List<Producto> productos) {
		this.controlRealizarUnPedido = controlRealizarUnPedido;
		this.empleado = empleado;
		this.textEmpleado.setText(empleado.getNombre() + " " + empleado.getApellidoP() + " " + empleado.getApellidoM());
		this.textCargo.setText(empleado.getNivel());
		this.textNumEmpleado.setText(Long.toString(empleado.getIdEmpleado()));
		limpiaTabla();
		for (Producto producto : productos) {
			llenaTabla(producto);
		}
		setVisible(true);
	}
	
	public void llenaTabla(Producto producto) {
		Object a[] = new Object[4];
		a[0] = producto.getNombre();
		a[1] = producto.getPiezas();
		modeloInventario.addRow(a);
		tabla_Inventario.setModel(modeloInventario);
		this.producto = producto;
	}
	
	private void limpiaTabla() {
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
	//prueba
	public List<Producto> recorrerTabla() {
		List<Producto> lista = new ArrayList<>();
		Producto producto;
		try {
			for(int i = 0; i < tabla_Inventario.getRowCount(); i++) {
				producto = controlRealizarUnPedido.obtenerProducto((String) tabla_Inventario.getValueAt(i, 0));
				lista.add(producto);
			}
		}catch(Exception e){
			JOptionPane.showConfirmDialog(null, "Error al leer la tabla");
		}
		return lista;
	}
}