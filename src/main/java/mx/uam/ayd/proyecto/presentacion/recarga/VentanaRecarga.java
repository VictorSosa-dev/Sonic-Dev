package mx.uam.ayd.proyecto.presentacion.recarga;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import org.springframework.stereotype.Component;

import javax.swing.DefaultComboBoxModel;

@SuppressWarnings("serial")
@Component
public class VentanaRecarga extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldNumero;

	
	public VentanaRecarga() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 593, 419);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JComboBox comboBoxCompania = new JComboBox();
		comboBoxCompania.setModel(new DefaultComboBoxModel(new String[] {"Movistar", "Telcel", "AT&T"}));
		comboBoxCompania.setToolTipText("Seleccione compania");
		comboBoxCompania.setFont(new Font("Tahoma", Font.PLAIN, 18));
		comboBoxCompania.setBounds(285, 220, 148, 48);
		comboBoxCompania.addItem("Movistar");
		comboBoxCompania.addItem("Telcel");
		comboBoxCompania.addItem("Unefón");
		contentPane.add(comboBoxCompania);
		
		JComboBox comboBoxMonto = new JComboBox();
		comboBoxMonto.setModel(new DefaultComboBoxModel(new String[] {"10", "20", "30", "40", "50", "100", "150", "200", "500"}));
		comboBoxMonto.setToolTipText("Seleccione monto");
		comboBoxMonto.setFont(new Font("Tahoma", Font.PLAIN, 18));
		comboBoxMonto.setBounds(285, 138, 148, 48);
		comboBoxMonto.addItem("...");
		comboBoxMonto.addItem("20");
		comboBoxMonto.addItem("50");
		comboBoxMonto.addItem("100");
		comboBoxMonto.addItem("150");
		contentPane.add(comboBoxMonto);
		
		JLabel lblMonto = new JLabel("Monto");
		lblMonto.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblMonto.setBounds(119, 144, 66, 30);
		contentPane.add(lblMonto);
		
		JLabel lblCompania = new JLabel("Compañía");
		lblCompania.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblCompania.setBounds(119, 231, 95, 21);
		contentPane.add(lblCompania);
		
		JLabel lblNumero = new JLabel("Número");
		lblNumero.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNumero.setBounds(119, 68, 66, 30);
		contentPane.add(lblNumero);
		
		textFieldNumero = new JTextField();
		textFieldNumero.setToolTipText("Ingrese número");
		textFieldNumero.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textFieldNumero.setBounds(285, 68, 148, 41);
		contentPane.add(textFieldNumero);
		textFieldNumero.setColumns(1);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.setBounds(442, 316, 95, 30);
		contentPane.add(btnAceptar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(300, 316, 95, 30);
		contentPane.add(btnCancelar);
		
		JLabel lblNewLabel_1 = new JLabel("RECARGA");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(154, 10, 284, 30);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Farmapass");
		lblNewLabel_1_1.setBounds(10, 10, 65, 13);
		contentPane.add(lblNewLabel_1_1);
		
		JButton btnRegresar = new JButton("Regresar");
		btnRegresar.setBounds(52, 320, 89, 23);
		contentPane.add(btnRegresar);
	}
}
