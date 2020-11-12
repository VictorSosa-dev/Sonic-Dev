package mx.uam.ayd.proyecto.presentacion.venta;

import java.awt.BorderLayout;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;

import javax.swing.JTable;
import javax.swing.JScrollBar;
import javax.swing.JButton;
import javax.swing.JTextPane;
import javax.swing.JSlider;
import java.awt.Panel;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import mx.uam.ayd.proyecto.negocio.modelo.Grupo;
import mx.uam.ayd.proyecto.negocio.modelo.Producto;
import mx.uam.ayd.proyecto.presentacion.agregarUsuario.ControlAgregarUsuario;
import mx.uam.ayd.proyecto.presentacion.principal.ControlPrincipal;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;

import net.miginfocom.swing.MigLayout;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import org.springframework.stereotype.Component;

import javax.swing.JScrollPane;
import javax.swing.JCheckBox;
import javax.swing.DropMode;

@SuppressWarnings("serial")
@Component
public class VentanaVenta extends JFrame {

	private JPanel contentPane;
	private JTextField txtIngresaProducto;
	private JTextField textTotal;
	private ControlVenta control;
	private JTable table;
	float total = 0;
	
	DefaultTableModel modelo = new DefaultTableModel();
	
	
	public VentanaVenta() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 553, 342);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Venta");
		lblNewLabel.setFont(new Font("Segoe UI Black", Font.PLAIN, 15));
		lblNewLabel.setBounds(246, 11, 63, 14);
		contentPane.add(lblNewLabel);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.setEnabled(false);
		btnBuscar.setBounds(208, 27, 89, 23);
		contentPane.add(btnBuscar);
		
		txtIngresaProducto = new JTextField();
		txtIngresaProducto.setText("");
		txtIngresaProducto.setBounds(21, 28, 171, 20);
		contentPane.add(txtIngresaProducto);
		txtIngresaProducto.setColumns(10);
		
		JLabel lblTotal = new JLabel("Total");
		lblTotal.setBounds(366, 232, 39, 14);
		contentPane.add(lblTotal);
		
		textTotal = new JTextField();
		textTotal.setBounds(415, 229, 96, 20);
		contentPane.add(textTotal);
		textTotal.setColumns(10);
		
		JButton btnCobrar = new JButton("Cobrar");
		btnCobrar.setBounds(425, 260, 89, 23);
		contentPane.add(btnCobrar);
		
		JButton btnQuitarDeLista = new JButton("Quitar de lista");
		btnQuitarDeLista.setBounds(174, 228, 103, 23);
		contentPane.add(btnQuitarDeLista);
		
		JButton btnRecarga = new JButton("Recarga");
		btnRecarga.setBounds(10, 228, 89, 23);
		contentPane.add(btnRecarga);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(21, 63, 495, 150);
		contentPane.add(scrollPane);
		
		table = new JTable(modelo);
		
		//String encabezados[] = {"Nombre", "Compuesto", "Precio del Producto"};
		modelo.addColumn("Nombre");
		modelo.addColumn("Compuesto");
		modelo.addColumn("Precio del Prodcuto");

		/*
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Nombre", "Compuesto", "Precio del Producto"
			}
		));
		*/
		
		scrollPane.setViewportView(table);
		
		
		
		//listener
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				control.buscarProducto(txtIngresaProducto.getText());
				
			}
		});
		
		txtIngresaProducto.addKeyListener(new KeyAdapter() {
		public void keyReleased( KeyEvent e ) {  
			btnBuscar.setEnabled(txtIngresaProducto.getText().length() != 0);
			
		}
		
		});
		}
		
		
		public void muestra(ControlVenta control) {
		
		this.control = control;
		
		setVisible(true);

	}

		public void llenaTabla(Producto producto) {
			String a [] = new String[3];
			a[0]=producto.getNombre();
			a[1]=producto.getCompuesto();
			a[2]=String.valueOf(producto.getPrecio());
			modelo.addRow(a);
			table.setModel(modelo);
		}
		
		
		
		public void muestraDialogoConMensaje(String mensaje ) {
			JOptionPane.showMessageDialog(this , mensaje);
		}


		public void textTotal(float precio) {
			
			total += precio; 
			textTotal.setText(String.valueOf(total));
			
		}
}
