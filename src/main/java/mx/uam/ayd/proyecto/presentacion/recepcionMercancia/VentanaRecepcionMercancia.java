package mx.uam.ayd.proyecto.presentacion.recepcionMercancia;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import org.springframework.stereotype.Component;

import mx.uam.ayd.proyecto.presentacion.principal.ControlPrincipal;
import java.awt.Color;
import javax.swing.JFormattedTextField;
import javax.swing.table.DefaultTableModel;
import java.awt.GridLayout;
import javax.swing.ListSelectionModel;

@SuppressWarnings("serial")
@Component
public class VentanaRecepcionMercancia extends JFrame {

	private JPanel contentPane;
	private JButton btnEnviar;
	private ControlRecepcionMercancia control;
	private JTable tabla_recepcionMercancia;
	
	public VentanaRecepcionMercancia() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblCierreVenta = new JLabel("RECEPCIÓN DE MERCANCÍA");
		lblCierreVenta.setHorizontalAlignment(SwingConstants.CENTER);
		lblCierreVenta.setBounds(0, 5, 434, 16);
		contentPane.add(lblCierreVenta);

		btnEnviar = new JButton("Siguiente");
		btnEnviar.setForeground(new Color(0, 0, 0));
		btnEnviar.setBounds(335, 227, 89, 23);
		contentPane.add(btnEnviar);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(236, 227, 89, 23);
		contentPane.add(btnCancelar);
		
		JLabel lblNewLabel = new JLabel("Fecha de recepción:");
		lblNewLabel.setBounds(20, 34, 98, 14);
		contentPane.add(lblNewLabel);
		
		JFormattedTextField formattedTextField = new JFormattedTextField();
		formattedTextField.setEditable(false);
		formattedTextField.setBounds(20, 53, 65, 20);
		contentPane.add(formattedTextField);
		
		JPanel panel = new JPanel();
		panel.setBounds(20, 84, 404, 132);
		contentPane.add(panel);
		panel.setLayout(new GridLayout(0, 1, 0, 0));
		
		tabla_recepcionMercancia = new JTable();
		tabla_recepcionMercancia.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Producto", "Productos\n a recibir", "Productos\n recibidos", "Tipo", "Recibido", "Precio"
			}
		));
		panel.add(tabla_recepcionMercancia);
	}

	public void muestra(ControlRecepcionMercancia control) {

		this.control = control;

		setVisible(true);
	}
}
