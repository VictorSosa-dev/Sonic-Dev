package mx.uam.ayd.proyecto.presentacion.principal.empleado;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.springframework.stereotype.Component;

import mx.uam.ayd.proyecto.presentacion.venta.ControlVenta;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;


@SuppressWarnings("serial")
@Component
public class VentanaPrincipalEmpleados extends JFrame {

	private JPanel contentPane;
	private ControlPrincipalEmpleados control;


	public VentanaPrincipalEmpleados() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JButton btnVenta = new JButton("Venta");
		btnVenta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				control.agregarProductos();
			}
		});
		
		JLabel lblNewLabel = new JLabel("Empleado");
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(238, Short.MAX_VALUE)
					.addComponent(btnVenta)
					.addGap(127))
				.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(340, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel)
					.addPreferredGap(ComponentPlacement.RELATED, 124, Short.MAX_VALUE)
					.addComponent(btnVenta)
					.addGap(81))
		);
		contentPane.setLayout(gl_contentPane);
	}
	
	public void muestra(ControlPrincipalEmpleados control) {
		
		this.control = control;
		
		setVisible(true);
	
}
}
