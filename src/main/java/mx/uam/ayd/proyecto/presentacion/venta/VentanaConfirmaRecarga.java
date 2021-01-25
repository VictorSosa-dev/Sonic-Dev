package mx.uam.ayd.proyecto.presentacion.venta;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import org.springframework.stereotype.Component;

import mx.uam.ayd.proyecto.negocio.modelo.Empleado;
/**
 * 
 * @author Luis Cristofer Alvarado Gabriel
 * Se lleva el flujo de la ventana para confirmar la recarga.
 *
 */
@SuppressWarnings("serial")
@Component
public class VentanaConfirmaRecarga extends JFrame {

	private JPanel contentPane;
	
	private JTextField txtNumEmpleado;
	private JTextField numTelefono;
	private JTextField txtNombreEmpleado;
	private JTextField txtNivel;
	private Empleado empleado;
	private ControlConfirmaRecarga control;
	private String numTel;
	private int montoS;
	private String compS;
	private JTextField Compania;
	private JTextField Monto;
	/**
	 * Create the frame.
	 */
	public VentanaConfirmaRecarga() {
		setResizable(false);
		setTitle("FARMAPASS - CONFIRMAR RECARGA");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 785, 312);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel labelTelefono = new JLabel("Teléfono");
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
		
		JButton btnNewButton = new JButton("Confirmar");
		btnNewButton.setBackground(Color.GREEN);
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton.setBounds(616, 222, 140, 35);
		contentPane.add(btnNewButton);
		
		
		/**
		 * Verifica que los campos esten llenos correctamente
		 * Si son correctos, llama al servicio de venta
		 */
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				numTel = numTelefono.getText();
				montoS = Integer.parseInt(Monto.getText());
				compS = Compania.getText();
				control.ingresaDatos(numTel, montoS, compS);
				setVisible(false);
				JOptionPane.showMessageDialog(null, "Recarga realizada con éxito", "Recarga exitosa", JOptionPane.INFORMATION_MESSAGE);
				
			}
		});
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBackground(Color.RED);
		btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnCancelar.setBounds(445, 222, 140, 35);
		contentPane.add(btnCancelar);
		
		/**
		 * Accion del botÃ³n Cancelar
		 * Regresa a la pestaÃ±a de recarga para introducir datos
		 */
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				control.iniciaRecarga(empleado);
				
			}
		});
			
		
		numTelefono = new JTextField();
		numTelefono.setEditable(false);
		numTelefono.setFont(new Font("Tahoma", Font.PLAIN, 14));
		numTelefono.setBounds(170, 105, 163, 28);
		contentPane.add(numTelefono);
		numTelefono.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Verifique que los datos son correctos, confirma para agregar a la lista.");
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
		
		Compania = new JTextField();
		Compania.setEditable(false);
		Compania.setFont(new Font("Tahoma", Font.PLAIN, 14));
		Compania.setColumns(10);
		Compania.setBounds(170, 166, 163, 28);
		contentPane.add(Compania);
		
		Monto = new JTextField();
		Monto.setEditable(false);
		Monto.setFont(new Font("Tahoma", Font.PLAIN, 14));
		Monto.setColumns(10);
		Monto.setBounds(510, 100, 163, 28);
		contentPane.add(Monto);
	}
	
	/**
	 * MÃ©todo que muestra los datos obtenidos
	 * anteriormente de la recarga
	 * @param control
	 * @param empleado
	 * @param telefono
	 * @param mon
	 * @param comp
	 */
	public void muestra(ControlConfirmaRecarga control, Empleado empleado, String telefono, int mon, String comp) {
		String id = String.valueOf(empleado.getIdEmpleado());
		this.control = control;
		this.empleado = empleado;
		this.txtNombreEmpleado
				.setText("Nombre: " + empleado.getNombre() + " " + empleado.getApellido());
		this.txtNivel.setText("Cargo: " + empleado.getNivel());
		this.txtNumEmpleado.setText("ID: " +id);
		this.Compania.setText(comp);;
		this.Monto.setText(Integer.toString(mon));
		this.numTelefono.setText(telefono);
		setVisible(true);

	}

	/**
	 * MÃ©todo que oculta la ventana de 
	 * confirmar recarga
	 */
	public void oculta() {
		// TODO Auto-generated method stub
		this.numTelefono.setText("");
		this.Compania.setText("");
		this.Monto.setText("");
		setVisible(false);
		
	}	
	
	
}
