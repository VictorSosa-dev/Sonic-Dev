package mx.uam.ayd.proyecto.presentacion.venta;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.math.BigInteger;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import org.springframework.stereotype.Component;

import mx.uam.ayd.proyecto.negocio.modelo.Empleado;
import javax.swing.JList;
import javax.swing.DefaultComboBoxModel;

/**
 * 
 * @author Luis Cristofer Alvarado Gabriel
 * Se lleva el flujo para la ventana de realizar una recarga
 *
 */

@SuppressWarnings("serial")
@Component
public class VentanaRecarga extends JFrame {

	private JPanel contentPane;
	
	private JTextField txtNumEmpleado;
	private JTextField numTelefono;
	private JTextField txtNombreEmpleado;
	private JTextField txtNivel;
	private Empleado empleado;
	private ControlRecarga control;
	private String numTel;
	private int montoS;
	private String compS;
	private JComboBox Compania;
	private JComboBox Monto;
	private String auxComp;
	private String auxMonto;
	


	/**
	 * Create the frame.
	 */
	public VentanaRecarga() {
		setResizable(false);
		setTitle("FARMAPASS - RECARGA");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 785, 312);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel labelTelefono = new JLabel("Teléfono:");
		labelTelefono.setFont(new Font("Tahoma", Font.BOLD, 20));
		labelTelefono.setBounds(53, 100, 94, 28);
		contentPane.add(labelTelefono);
		
		JLabel labelMonto = new JLabel("Monto:");
		labelMonto.setFont(new Font("Tahoma", Font.BOLD, 20));
		labelMonto.setBounds(406, 100, 94, 28);
		contentPane.add(labelMonto);
		
		JLabel labelComp = new JLabel("Compañía:");
		labelComp.setFont(new Font("Tahoma", Font.BOLD, 20));
		labelComp.setBounds(52, 166, 108, 28);
		contentPane.add(labelComp);
		
		JButton btnNewButton = new JButton("Continuar");
		btnNewButton.setBackground(Color.GREEN);
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton.setBounds(616, 222, 120, 30);
		contentPane.add(btnNewButton);
		
		/**
		 * Verifica que los campos esten llenos correctamente
		 * Si son correctos, pasa a al control de confirmar recarga
		 */
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (validaCamposVacios()) {
					control.ingresaDatos(empleado, numTel, montoS, compS);
					setVisible(false);
				} else {
					JOptionPane.showMessageDialog(null, "Verfique que los campos estan correctamente llenos!",
							"Error de entrada de datos!", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setForeground(Color.WHITE);
		btnCancelar.setBackground(Color.RED);
		btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnCancelar.setBounds(445, 222, 120, 30);
		contentPane.add(btnCancelar);
		
		/**
		 * Accion del botÃ³n Cancelar
		 * regresa a la ventana de venta
		 */
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				control.iniciaVenta(empleado);
			}
		});
			
		
		numTelefono = new JTextField();
		numTelefono.setFont(new Font("Tahoma", Font.PLAIN, 14));
		numTelefono.setBounds(170, 105, 163, 28);
		contentPane.add(numTelefono);
		numTelefono.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Ingrese los datos para realizar la recarga ");
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(0, 49, 766, 28);
		contentPane.add(lblNewLabel);
		
		txtNivel = new JTextField();
		txtNivel.setEditable(false);
		txtNivel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtNivel.setColumns(10);
		txtNivel.setBounds(10, 10, 215, 28);
		contentPane.add(txtNivel);
		
		txtNombreEmpleado = new JTextField();
		txtNombreEmpleado.setHorizontalAlignment(SwingConstants.LEFT);
		txtNombreEmpleado.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtNombreEmpleado.setEditable(false);
		txtNombreEmpleado.setColumns(10);
		txtNombreEmpleado.setBounds(239, 10, 251, 28);
		contentPane.add(txtNombreEmpleado);
		
		txtNumEmpleado = new JTextField();
		txtNumEmpleado.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtNumEmpleado.setEditable(false);
		txtNumEmpleado.setColumns(10);
		txtNumEmpleado.setBounds(500, 10, 256, 28);
		contentPane.add(txtNumEmpleado);
		
		Compania = new JComboBox();
		Compania.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				auxComp = String.valueOf(Compania.getSelectedItem());
			}
		});
		Compania.addItem("Telcel");
		Compania.addItem("Iusacell");
		Compania.addItem("Movistar");
		Compania.addItem("AT&T");
		auxComp = String.valueOf(Compania.getSelectedItem());

		Compania.setFont(new Font("Tahoma", Font.PLAIN, 14));
		Compania.setBounds(170, 166, 163, 28);
		contentPane.add(Compania);
		
		Monto = new JComboBox();
		Monto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				auxMonto = String.valueOf(Monto.getSelectedItem());
			}
		});
		Monto.setModel(new DefaultComboBoxModel(new String[] {"10", "20", "50", "100", "150"}));
		auxMonto = String.valueOf(Monto.getSelectedItem());
		Monto.setFont(new Font("Tahoma", Font.PLAIN, 14));
		Monto.setBounds(510, 100, 163, 28);
		contentPane.add(Monto);
	}
	
	/** Muestra la ventana de la recarga
	 * @param el control que lleva el flujo
	 * @param el empleado que ha iniciado sesiÃ³n
	 **/
	public void muestra(ControlRecarga control, Empleado empleado) {
		String id = String.valueOf(empleado.getIdEmpleado());
		this.control = control;
		this.empleado = empleado;
		this.txtNombreEmpleado
				.setText("Nombre: " + empleado.getNombre() + " " + empleado.getApellidoP() + " " + empleado.getApellidoM());
		this.txtNivel.setText("Cargo: " + empleado.getNivel());
		this.txtNumEmpleado.setText("ID: " +id);
		setVisible(true);
		

	}
	
	/**
	 * Valida que los 
	 * campos para realizar la recarga
	 * no estÃ©n vacÃ­os
	 **/
	private boolean validaCamposVacios() {
		if(numTelefono.getText().length() != 10 || numTelefono.getText().isEmpty() 
				|| String.valueOf(auxMonto) == "" || String.valueOf(auxComp) == "") {
			return false;
		}else {
			this.numTel = numTelefono.getText();
			this.montoS = Integer.parseInt(auxMonto);
			this.compS = auxComp;
			return true;
		}
	}
	
	/**oculta
	 * la ventana de la recarga
	 **/
	public void oculta() {
		setVisible(false);
	}
}


