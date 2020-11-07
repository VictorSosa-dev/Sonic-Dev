package mx.uam.ayd.proyecto.presentacion.agregarUsuario;

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
import java.awt.TextField;
import javax.swing.JTable;
import javax.swing.JScrollBar;
import javax.swing.JButton;
import javax.swing.JTextPane;
import javax.swing.JSlider;
import java.awt.Panel;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import javax.swing.BoxLayout;
import net.miginfocom.swing.MigLayout;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import javax.swing.JTextField;

public class VentanaVenta extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField txtIngresaProducto;
	private JTextField textField;

	
	public VentanaVenta() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		table = new JTable();
		table.setFillsViewportHeight(true);
		table.setColumnSelectionAllowed(true);
		table.setCellSelectionEnabled(true);
		table.setBounds(29, 64, 368, 118);
		contentPane.add(table);
		
		JLabel lblNewLabel = new JLabel("Venta");
		lblNewLabel.setBounds(188, 11, 39, 14);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Buscar");
		btnNewButton.setBounds(308, 27, 89, 23);
		contentPane.add(btnNewButton);
		
		txtIngresaProducto = new JTextField();
		txtIngresaProducto.setText("Ingresa Producto");
		txtIngresaProducto.setBounds(37, 30, 171, 20);
		contentPane.add(txtIngresaProducto);
		txtIngresaProducto.setColumns(10);
		
		JLabel lblTotal = new JLabel("Total");
		lblTotal.setBounds(273, 196, 39, 14);
		contentPane.add(lblTotal);
		
		textField = new JTextField();
		textField.setBounds(330, 193, 96, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton_1 = new JButton("Cobrar");
		btnNewButton_1.setBounds(340, 229, 89, 23);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Quitar de lista");
		btnNewButton_2.setBounds(139, 196, 103, 23);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Recarga");
		btnNewButton_3.setBounds(10, 196, 89, 23);
		contentPane.add(btnNewButton_3);
	}
}
