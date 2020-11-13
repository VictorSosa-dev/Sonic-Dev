package mx.uam.ayd.proyecto.presentacion.venta;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import org.springframework.stereotype.Component;

import mx.uam.ayd.proyecto.negocio.modelo.Producto;

@SuppressWarnings("serial")
@Component
public class VentanaVenta extends JFrame {

	private JPanel contentPane;
	private JTextField txtIngresaProducto;
	private JTextField textTotal;
	private ControlVenta controlVenta;
	private JTable table;
	float total = 0;
	Producto producto;

	DefaultTableModel modelo = new DefaultTableModel();

	public VentanaVenta() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 619, 342);
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
		lblTotal.setBounds(424, 232, 39, 14);
		contentPane.add(lblTotal);

		textTotal = new JTextField();
		textTotal.setEditable(false);
		textTotal.setBounds(473, 229, 96, 20);
		contentPane.add(textTotal);
		textTotal.setColumns(10);

		JButton btnCobrar = new JButton("Cobrar");
		btnCobrar.setBounds(480, 271, 89, 23);
		contentPane.add(btnCobrar);

		JButton btnQuitarDeLista = new JButton("Quitar de lista");
		btnQuitarDeLista.setBounds(154, 228, 143, 23);
		contentPane.add(btnQuitarDeLista);

		JButton btnRecarga = new JButton("Recarga");
		btnRecarga.setBounds(10, 228, 89, 23);
		contentPane.add(btnRecarga);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(21, 63, 574, 150);
		contentPane.add(scrollPane);

		table = new JTable(modelo);

		modelo.addColumn("Nombre");
		modelo.addColumn("Compuesto");
		modelo.addColumn("Precio del Prodcuto");
		modelo.addColumn("Seleccionar");

		scrollPane.setViewportView(table);

		// listener
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlVenta.buscarProducto(txtIngresaProducto.getText());

			}
		});

		txtIngresaProducto.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				btnBuscar.setEnabled(txtIngresaProducto.getText().length() != 0);

			}

		});

		btnQuitarDeLista.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int x = table.getSelectedRow();
				int y = table.getSelectedColumn();
				Object a = table.getValueAt(x, y - 1);
				float r = Float.parseFloat(a.toString());
				total -= r;
				textTotal.setText(String.valueOf(total));
				modelo.removeRow(x);
				controlVenta.actulizaInventario2(producto.getNombre());

			}
		});

		btnCobrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// List <DetalleVenta> detalleVenta = new ArrayList <> ();
				// venta = recorrerTabla();
				// controlVenta.agregarVenta(venta);
				controlVenta.muentraCobro(Float.parseFloat(textTotal.getText()));

			}
		});

	}

	// Muestra la ventana
	public void muestra(ControlVenta controlVenta) {

		this.controlVenta = controlVenta;

		setVisible(true);

	}

	// Metodos para la Ventana
	public void llenaTabla(Producto producto) {
		String a[] = new String[4];
		a[0] = producto.getNombre();
		a[1] = producto.getCompuesto();
		a[2] = String.valueOf(producto.getPrecio());
		a[3] = "Selecciona para quitar ";
		modelo.addRow(a);
		table.setModel(modelo);
		this.producto = producto;

	}

	public void muestraDialogoConMensaje(String mensaje) {
		JOptionPane.showMessageDialog(this, mensaje);
	}

	public void textTotal(float precio) {

		total += precio;
		textTotal.setText(String.valueOf(total));

	}

	/**
	 * Método que recorre la tabla
	 * 
	 * @return venta de tipo lista
	 */
	public List<Producto> recorrerTabla() {
		List<Producto> lista = new ArrayList<>();
		Producto producto;

		for (int i = 0; i < table.getRowCount(); i++) {

			producto = controlVenta.obtenerProducto((String) table.getValueAt(i, 0));

			lista.add(producto);
		}

		return lista;

	}

}
