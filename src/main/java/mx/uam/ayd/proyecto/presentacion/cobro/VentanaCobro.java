package mx.uam.ayd.proyecto.presentacion.cobro;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import org.springframework.stereotype.Component;
import javax.swing.DropMode;

@SuppressWarnings("serial")
@Component
public class VentanaCobro extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldTotal;
	private JTextField textFieldCambio;
	private ControlCobro controlCobro;
	private float total;
	private JTextField textFieldNumero;
	private JTextField textFieldRecibi;
	

	
	public VentanaCobro() {
		setTitle("Cobro");
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JCheckBox chckbxEfectivo = new JCheckBox("Efectivo");
		chckbxEfectivo.setBounds(21, 22, 99, 29);
		chckbxEfectivo.setSelected(true);
		chckbxEfectivo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JCheckBox chckbxTarjeta = new JCheckBox("Tarjeta");
		chckbxTarjeta.setBounds(21, 69, 99, 27);
		chckbxTarjeta.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JPanel panel = new JPanel();
		panel.setBounds(122, 16, 303, 190);
		
		JButton btnFinalizar = new JButton("Finalizar");
		btnFinalizar.setForeground(new Color(255, 255, 255));
		btnFinalizar.setBackground(new Color(50, 205, 50));
		btnFinalizar.setBounds(257, 224, 126, 23);
		btnFinalizar.setEnabled(false);
		
		JButton btnRegresar = new JButton("Regresar");
		btnRegresar.setForeground(new Color(255, 255, 255));
		btnRegresar.setBackground(new Color(255, 0, 0));
		btnRegresar.setBounds(49, 224, 99, 23);
		
		JButton btnAprobado = new JButton("Aprobar");
		btnAprobado.setForeground(Color.WHITE);
		btnAprobado.setBackground(Color.CYAN);
		btnAprobado.setBounds(92, 164, 113, 23);
		btnAprobado.setVisible(false);
		JProgressBar progressBar = new JProgressBar();
		progressBar.setForeground(Color.GREEN);
		progressBar.setBackground(Color.WHITE);
		progressBar.setBounds(115, 110, 146, 9);
		progressBar.setVisible(false);
		
		JLabel lblTotal = new JLabel("Total:");
		lblTotal.setBounds(30, 27, 49, 20);
		lblTotal.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JLabel lblRecibi = new JLabel("Recibí:");
		lblRecibi.setBounds(30, 61, 60, 20);
		lblRecibi.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JLabel lblCambio = new JLabel("Cambio:");
		lblCambio.setBounds(30, 136, 75, 20);
		lblCambio.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JLabel lblEstado = new JLabel("Estado:");
		lblEstado.setBounds(30, 99, 75, 20);
		lblEstado.setVisible(false);
		lblEstado.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JLabel lblNoTarjeta = new JLabel("No. Tarjeta");
		lblNoTarjeta.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNoTarjeta.setBounds(30, 58, 83, 20);
		lblNoTarjeta.setVisible(false);
		
		textFieldTotal = new JTextField();
		textFieldTotal.setBounds(123, 27, 138, 20);
		textFieldTotal.setEditable(false);
		textFieldTotal.setColumns(10);
		
		textFieldCambio = new JTextField();
		textFieldCambio.setBounds(123, 138, 130, 20);
		textFieldCambio.setVisible(true);
		textFieldCambio.setEditable(false);
		textFieldCambio.setColumns(10);
		textFieldNumero = new JTextField();
		textFieldNumero.setEditable(false);
		textFieldNumero.setVisible(false);
		textFieldNumero.setColumns(10);
		textFieldNumero.setBounds(123, 63, 138, 20);
		contentPane.setLayout(null);
		contentPane.add(chckbxTarjeta);
		contentPane.add(chckbxEfectivo);
		contentPane.add(panel);
		contentPane.add(btnRegresar);
		contentPane.add(btnFinalizar);
		panel.setLayout(null);
		panel.add(lblTotal);
		panel.add(lblCambio);
		panel.add(lblRecibi);
		panel.add(textFieldCambio);
		panel.add(textFieldTotal);
		panel.add(lblEstado);
		panel.add(progressBar);
		panel.add(btnAprobado);
		panel.add(lblNoTarjeta);
		panel.add(textFieldNumero);
		
		textFieldRecibi = new JTextField();
		textFieldRecibi.setBounds(123, 63, 96, 20);
		panel.add(textFieldRecibi);
		textFieldRecibi.setColumns(10);
		
		
		
		
		//Listener de la opcion efectivo
		textFieldRecibi.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				if(textFieldRecibi.getText().length() == 0){
					textFieldCambio.setText(" ");
					btnFinalizar.setEnabled(false);
				}else {
					if(textFieldRecibi.getText().length() != 0){
						cambio();
						float a = Float.parseFloat(textFieldCambio.getText());
						if(a>=0)
							btnFinalizar.setEnabled(true);
					}
				}	
			}
			
			/**
			 * Llena campo cambio
			 */

			private void cambio() {
				float recibi = Float.parseFloat(textFieldRecibi.getText());
				float cambio = recibi-total;
				textFieldCambio.setText(String.valueOf(cambio));
			}
			
			@Override
			public void keyTyped(KeyEvent e) {
				char caracter = e.getKeyChar();
				if ( Character.isDigit(caracter)) {
				}else {
					e.consume();
				}
			}

		});
		
		//Listener de la opción tarjeta
		
		chckbxEfectivo.addItemListener(new ItemListener (){
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(chckbxEfectivo.isSelected() == true) {
					chckbxTarjeta.setSelected(false);
					lblEstado.setVisible(false);
					progressBar.setVisible(false);
					btnAprobado.setVisible(false);	
					lblNoTarjeta.setVisible(false);
					textFieldNumero.setVisible(false);
					lblRecibi.setVisible(true);
					lblCambio.setVisible(true);
					textFieldRecibi.setVisible(true);
					textFieldCambio.setVisible(true);
					textFieldRecibi.setText("");
					textFieldCambio.setText("");
					btnFinalizar.setEnabled(false);
				}
				
			}
		});
		
		
		
		chckbxTarjeta.addItemListener(new ItemListener (){
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(chckbxTarjeta.isSelected() == true) {
					chckbxEfectivo.setSelected(false);
					lblRecibi.setVisible(false);
					lblCambio.setVisible(false);
					textFieldCambio.setVisible(false);
					textFieldRecibi.setVisible(false);
					textFieldNumero.setVisible(true);
					textFieldNumero.setEditable(false);
					lblNoTarjeta.setVisible(true);
					lblEstado.setVisible(true);
					progressBar.setVisible(true);
					btnAprobado.setVisible(true);
					progressBar.setValue(0);
					if(textFieldNumero.getText().length() == 0) {
						btnFinalizar.setEnabled(false);
					}else {
						btnFinalizar.setEnabled(true);
					}
				}
			}
		});
		
		btnAprobado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Thread hilo=new Thread(){
					public void run(){
						for(int i=1;i<=100;i++) {
							try {
								sleep(10);
								progressBar.setValue(i);								
							} catch (InterruptedException ex) {
								JOptionPane.showMessageDialog(null, "No se acepto la tarjeta");
							}
						}
					}
				};
				hilo.start();
				textFieldNumero.setText(numeroTarjeta());
				btnAprobado.setEnabled(false);
				btnAprobado.setForeground(Color.WHITE);
				btnAprobado.setBackground(Color.RED);
				if(textFieldNumero.getText().length() != 0) {
					btnFinalizar.setEnabled(true);
				}
			}
		});
		
		btnRegresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textFieldRecibi.setText("");
				textFieldCambio.setText("");
				btnFinalizar.setEnabled(false);
				controlCobro.termina();
			}
		});
		
		btnFinalizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(chckbxEfectivo.isSelected() == true) {
					float a = Float.parseFloat(textFieldCambio.getText());
					if(a<0) {
						JOptionPane.showMessageDialog(null, "La venta no se puede realizar porque la cantidad Recida es incorrecta");
					}else {
						controlCobro.obtenerLista(total);
						textFieldRecibi.setText("");
						textFieldCambio.setText("");
						controlCobro.limpiarTabla();
						controlCobro.termina();
					}
				}else {
					textFieldNumero.setText("");
					chckbxEfectivo.setSelected(true);
					chckbxTarjeta.setSelected(false);
					btnAprobado.setBackground(Color.CYAN);
					btnAprobado.setForeground(Color.WHITE);
					progressBar.setValue(0);
					btnAprobado.setEnabled(true);
					controlCobro.obtenerLista(total);
					controlCobro.limpiarTabla();
					controlCobro.termina();

				}
			}
		});
		
	}
	

	protected String numeroTarjeta() {
		long number1 = (long) Math.floor(Math.random() * 90_000_000L) + 10_000_000L;
		long number2 = (long) Math.floor(Math.random() * 90_000_000L) + 10_000_000L;
		String cadena=null;
		while(number1 == number2) {
			number1 = (long) Math.floor(Math.random() * 90_000_000L) + 10_000_000L;
		}
		cadena = String.valueOf(number1).concat(String.valueOf(number2));
		return cadena;
	}


	//Métodos que ocupa la ventana
	public void muestra(ControlCobro controlCobro, float total) {
		textFieldTotal.setText(String.valueOf(total));
		this.controlCobro = controlCobro;
		this.total=total;
		setVisible(true);
	}

	public void muestraDialogo() {
		JOptionPane.showMessageDialog(null,"La venta se realizó con exito");
		
	}
}
