package mx.uam.ayd.proyecto.presentacion.cobro;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.FileOutputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.Barcode39;
import com.itextpdf.text.pdf.PdfWriter;

import lombok.extern.slf4j.Slf4j;
import mx.uam.ayd.proyecto.negocio.modelo.Empleado;
import mx.uam.ayd.proyecto.negocio.modelo.Producto;
import mx.uam.ayd.proyecto.presentacion.controlEmpleados.VentanaControlEmpleados;
import mx.uam.ayd.proyecto.presentacion.venta.ControlRecarga;
import mx.uam.ayd.proyecto.presentacion.venta.ControlVenta;
import mx.uam.ayd.proyecto.presentacion.venta.VentanaVenta;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JSeparator;
import javax.swing.JProgressBar;
@Slf4j
@SuppressWarnings("serial")
@Component
public class VentanaCobro extends JFrame {
	
	@Autowired
	private ControlVenta controlVenta;
	
	@Autowired
	private VentanaVenta ventanaVenta;

	private JPanel contentPane;
	private JTextField textFieldTotal;
	private JTextField textFieldRecibi;
	private JTextField textFieldCambio;
	private ControlCobro controlCobro;
	private float total;
	private float recibi;
	private float cambio;
	public Empleado empleado;
	
	
	Producto producto;

	
	

	
	public VentanaCobro() {
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Cobro");
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JCheckBox chckbxEfectivo = new JCheckBox("Efectivo");
		chckbxEfectivo.setSelected(true);
		chckbxEfectivo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JCheckBox chckbxTarjeta = new JCheckBox("Tarjeta");
		chckbxTarjeta.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JPanel panel = new JPanel();
		
		JButton btnFinalizar = new JButton("Finalizar");
		btnFinalizar.setEnabled(false);
		
		JButton btnRegresar = new JButton("Regresar");
		
		JButton btnAprobado = new JButton("Aprobado");
		btnAprobado.setVisible(false);
		JProgressBar progressBar = new JProgressBar();
		progressBar.setVisible(false);
		
		GroupLayout gl_panel = new GroupLayout(panel);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(16)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(chckbxTarjeta, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE)
						.addComponent(chckbxEfectivo, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 303, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(44)
					.addComponent(btnRegresar)
					.addPreferredGap(ComponentPlacement.RELATED, 184, Short.MAX_VALUE)
					.addComponent(btnFinalizar)
					.addGap(52))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(17)
							.addComponent(chckbxEfectivo)
							.addGap(18)
							.addComponent(chckbxTarjeta))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, 190, GroupLayout.PREFERRED_SIZE)))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(btnRegresar)
						.addComponent(btnFinalizar, GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE))
					.addContainerGap())
		);
		
		JLabel lblTotal = new JLabel("Total:");
		lblTotal.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JLabel lblRecibi = new JLabel("Recibí:");
		lblRecibi.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JLabel lblCambio = new JLabel("Cambio:");
		lblCambio.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JLabel lblEstado = new JLabel("Estado:");
		lblEstado.setVisible(false);
		lblEstado.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		textFieldTotal = new JTextField();
		textFieldTotal.setEditable(false);
		textFieldTotal.setColumns(10);
		
		textFieldRecibi = new JTextField();
		textFieldRecibi.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char caracter = e.getKeyChar();
				if (((caracter < '0') || (caracter > '9')) && (caracter != '\b')) {
					e.consume();
				}
			}
		});
		textFieldRecibi.setColumns(10);
		
		textFieldCambio = new JTextField();
		textFieldCambio.setEditable(false);
		textFieldCambio.setColumns(10);
		
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(30)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel.createSequentialGroup()
									.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
										.addComponent(lblTotal, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblCambio, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblRecibi, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE))
									.addGap(18)
									.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
										.addComponent(textFieldCambio, GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
										.addComponent(textFieldRecibi)
										.addComponent(textFieldTotal)))
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(lblEstado, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(progressBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(92)
							.addComponent(btnAprobado)))
					.addContainerGap(50, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(27)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(textFieldTotal, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblTotal))
					.addGap(14)
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addComponent(textFieldRecibi, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblRecibi))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblEstado)
						.addComponent(progressBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(17)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCambio)
						.addComponent(textFieldCambio, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(btnAprobado)
					.addContainerGap())
		);
		panel.setLayout(gl_panel);
		contentPane.setLayout(gl_contentPane);
		
		//Listener de  la opción efectivo
		
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
					textFieldRecibi.setVisible(false);
					textFieldCambio.setVisible(false);
					lblEstado.setVisible(true);
					progressBar.setVisible(true);
					btnAprobado.setVisible(true);
					btnFinalizar.setEnabled(false);
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
				btnFinalizar.setEnabled(true);
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
				float a = Float.parseFloat(textFieldCambio.getText());
				if(a<0) {
					JOptionPane.showMessageDialog(null, "La venta no se puede realizar porque la cantidad Recida es incorrecta");
				}else {
					controlCobro.obtenerLista(total);
					controlCobro.termina();
					
				}
				log.info(">>>Recibi: $", textFieldRecibi.getText());
				log.info(">>>Cambio: $", textFieldCambio.getText());
				//generarTicketEfectivo();
				generarTicketTarjeta();
				ventanaVenta.limpia();

				
			}
			
			
		});
		
	}
	
	
	public void generarTicketEfectivo() {
		
		Rectangle pageSize = new Rectangle(300.0F, 550.0F);
		
		LocalDateTime ahora= LocalDateTime.now();
		int anio= ahora.getYear();
		int mes = ahora.getMonthValue();
		int dia= ahora.getDayOfMonth();
		int hora= ahora.getHour();
		int minuto= ahora.getMinute();
		int segundo=ahora.getSecond();
		
		String horainical= hora+":"+minuto+":"+segundo;
		String fecha=dia+"/"+mes+"/"+anio;
		
		String header = "FARMAPASS\n" +
				 				"AV.PERIFERICO 390\n" +
				 				"LA PURISIMA, IZTAPALAPA, CIUDAD DE MEXICO";
		String gracias = "¡GRACIAS POR SU COMPRA!\n";
		String footer = "\nLe Atendio: " + empleado.getNombre() + " " + empleado.getApellido();
		
		List<Producto> productos = ventanaVenta.recorrerTabla();
		
		String total = "Total              "+" $          " + textFieldTotal.getText();
		String efectivo = "Efectivo         " + " $          " + textFieldRecibi.getText();
		String cambio = "Cambio          " + " $          " + textFieldCambio.getText();
		
		Document documento = new Document(pageSize);
		
		
		try {
			
			String ruta = System.getProperty("user.home");
			PdfWriter pdf = PdfWriter.getInstance(documento, new FileOutputStream("Ticket.pdf"));
			
			documento.open();
			
			Paragraph parrafoTitulo = new Paragraph();
			Paragraph parrafoProductos = new Paragraph();

			Paragraph parrafoFecha = new Paragraph();
			Paragraph parrafoTotal = new Paragraph();
			Paragraph parrafoEfectivo = new Paragraph();
			Paragraph parrafoCambio = new Paragraph();

			Paragraph parrafoEmpleado = new Paragraph();
			Paragraph parrafoGracias = new Paragraph();


			parrafoTitulo.setAlignment(Paragraph.ALIGN_CENTER);
			parrafoProductos.setAlignment(Paragraph.ALIGN_LEFT);
			parrafoFecha.setAlignment(Paragraph.ALIGN_CENTER);
			parrafoTotal.setAlignment(Paragraph.ALIGN_LEFT);
			parrafoEfectivo.setAlignment(Paragraph.ALIGN_LEFT);
			parrafoCambio.setAlignment(Paragraph.ALIGN_LEFT);
			parrafoEmpleado.setAlignment(Paragraph.ALIGN_LEFT);
			parrafoGracias.setAlignment(Paragraph.ALIGN_CENTER);

			parrafoTitulo.setFont(FontFactory.getFont("Tahoma", 14, Font.BOLD, BaseColor.BLACK));
			parrafoProductos.setFont(FontFactory.getFont("Tahoma", 14, Font.BOLD, BaseColor.BLACK));
			parrafoFecha.setFont(FontFactory.getFont("Tahoma", 14, Font.BOLD, BaseColor.BLACK));
			parrafoTotal.setFont(FontFactory.getFont("Tahoma", 14, Font.BOLD, BaseColor.BLACK));
			parrafoEfectivo.setFont(FontFactory.getFont("Tahoma", 14, Font.BOLD, BaseColor.BLACK));
			parrafoCambio.setFont(FontFactory.getFont("Tahoma", 14, Font.BOLD, BaseColor.BLACK));
			parrafoEmpleado.setFont(FontFactory.getFont("Tahoma", 12, Font.BOLD, BaseColor.BLACK));
			parrafoGracias.setFont(FontFactory.getFont("Tahoma", 14, Font.BOLD, BaseColor.BLACK));
			
			parrafoTitulo.add(header);
			parrafoFecha.add(fecha + "    " + horainical);
			parrafoTotal.add(total);
			parrafoEfectivo.add(efectivo);
			parrafoCambio.add(cambio);
			parrafoEmpleado.add(footer);
			parrafoGracias.add(gracias);
			for (int i = 0; i < productos.size(); i++) {
				parrafoProductos.add("\n"+String.valueOf(productos.get(i).getNombre().toLowerCase()) + "          $          " + productos.get(i).getPrecio());
			}
		
			

			Barcode39 code = new Barcode39();
			code.setCode("1234567890");
			Image img = code.createImageWithBarcode(pdf.getDirectContent(), BaseColor.BLACK, BaseColor.BLACK);
			img.setAlignment(Chunk.ALIGN_UNDEFINED);
			documento.add(new Paragraph("================================"));
			
			documento.add(parrafoTitulo);
			documento.add(parrafoFecha);
			documento.add(new Paragraph("================================"));
			documento.add(parrafoProductos);
			documento.add(new Paragraph("   "));
			
			documento.add(parrafoTotal);
			documento.add(parrafoEfectivo);
			documento.add(parrafoCambio);
			documento.add(new Paragraph("   "));
			
			documento.add(new Paragraph("================================"));
			documento.add(parrafoGracias);
			documento.add(parrafoEmpleado);
			documento.add(new Paragraph(" "));
			documento.add(img);
			documento.add(new Paragraph("   "));
			documento.add(new Paragraph("\n\n================================"));

			documento.close();
			
		} catch (Exception e2) {
            JOptionPane.showMessageDialog(null, "Error al generar el ticket.");

		}
	}
	
	public void generarTicketTarjeta() {
		
		Rectangle pageSize = new Rectangle(300.0F, 550.0F);
		
		LocalDateTime ahora= LocalDateTime.now();
		int anio= ahora.getYear();
		int mes = ahora.getMonthValue();
		int dia= ahora.getDayOfMonth();
		int hora= ahora.getHour();
		int minuto= ahora.getMinute();
		int segundo=ahora.getSecond();
		List<Integer> numbers = new ArrayList<>(40);
		for (int i=1;i<=10;i++){
		   numbers.add(i);
		}
		

		String horainical= hora+":"+minuto+":"+segundo;
		String fecha=dia+"/"+mes+"/"+anio;
		
		String header = "FARMAPASS\n" +
				 				"AV.PERIFERICO 390\n" +
				 				"LA PURISIMA, IZTAPALAPA, CIUDAD DE MEXICO";
		String gracias = "¡GRACIAS POR SU COMPRA!\n";
		String footer = "\nLe Atendio: " + empleado.getNombre() + " " + empleado.getApellido();
		
		List<Producto> productos = ventanaVenta.recorrerTabla();
		
		String total = "Total              "+" $          " + textFieldTotal.getText();
		String tarjeta = "\nTarjeta:         " + "          " + textFieldRecibi.getText();
		String pagoAprobado = "\nPAGO APROBADO";
		Document documento = new Document(pageSize);
		
		
		try {
			
			String ruta = System.getProperty("user.home");
			PdfWriter pdf = PdfWriter.getInstance(documento, new FileOutputStream("Ticket.pdf"));
			
			documento.open();
			
			Paragraph parrafoTitulo = new Paragraph();
			Paragraph parrafoProductos = new Paragraph();

			Paragraph parrafoFecha = new Paragraph();
			Paragraph parrafoTotal = new Paragraph();
			Paragraph parrafoTarjeta = new Paragraph();
			Paragraph parrafoPagoAprobado = new Paragraph();

			Paragraph parrafoEmpleado = new Paragraph();
			Paragraph parrafoGracias = new Paragraph();


			parrafoTitulo.setAlignment(Paragraph.ALIGN_CENTER);
			parrafoProductos.setAlignment(Paragraph.ALIGN_LEFT);
			parrafoFecha.setAlignment(Paragraph.ALIGN_CENTER);
			parrafoTotal.setAlignment(Paragraph.ALIGN_LEFT);
			parrafoTarjeta.setAlignment(Paragraph.ALIGN_LEFT);
			parrafoPagoAprobado.setAlignment(Paragraph.ALIGN_CENTER);
			parrafoEmpleado.setAlignment(Paragraph.ALIGN_LEFT);
			parrafoGracias.setAlignment(Paragraph.ALIGN_CENTER);

			parrafoTitulo.setFont(FontFactory.getFont("Tahoma", 14, Font.BOLD, BaseColor.BLACK));
			parrafoProductos.setFont(FontFactory.getFont("Tahoma", 14, Font.BOLD, BaseColor.BLACK));
			parrafoFecha.setFont(FontFactory.getFont("Tahoma", 14, Font.BOLD, BaseColor.BLACK));
			parrafoTotal.setFont(FontFactory.getFont("Tahoma", 14, Font.BOLD, BaseColor.BLACK));
			parrafoTarjeta.setFont(FontFactory.getFont("Tahoma", 14, Font.BOLD, BaseColor.BLACK));
			parrafoPagoAprobado.setFont(FontFactory.getFont("Tahoma", 14, Font.BOLD, BaseColor.BLACK));
			parrafoEmpleado.setFont(FontFactory.getFont("Tahoma", 12, Font.BOLD, BaseColor.BLACK));
			parrafoGracias.setFont(FontFactory.getFont("Tahoma", 14, Font.BOLD, BaseColor.BLACK));
			
			parrafoTitulo.add(header);
			parrafoFecha.add(fecha + "    " + horainical);
			parrafoTotal.add(total);
			parrafoTarjeta.add(tarjeta);
			parrafoPagoAprobado.add(pagoAprobado);
			parrafoEmpleado.add(footer);
			parrafoGracias.add(gracias);
			for (int i = 0; i < productos.size(); i++) {
				parrafoProductos.add("\n"+String.valueOf(productos.get(i).getNombre().toLowerCase()) + "          $          " + productos.get(i).getPrecio());
			}
		
			

			Barcode39 code = new Barcode39();
			code.setCode(String.valueOf(numbers));
			Image img = code.createImageWithBarcode(pdf.getDirectContent(), BaseColor.BLACK, BaseColor.BLACK);
			img.setAlignment(Chunk.ALIGN_UNDEFINED);
			documento.add(new Paragraph("================================"));
			
			documento.add(parrafoTitulo);
			documento.add(parrafoFecha);
			documento.add(new Paragraph("================================"));
			documento.add(parrafoProductos);
			documento.add(new Paragraph("   "));
			
			documento.add(parrafoTotal);
			documento.add(parrafoTarjeta);
			documento.add(parrafoPagoAprobado);
			documento.add(new Paragraph("   "));
			
			documento.add(new Paragraph("================================"));
			documento.add(parrafoGracias);
			documento.add(parrafoEmpleado);
			documento.add(new Paragraph(" "));
			documento.add(img);
			documento.add(new Paragraph("   "));
			documento.add(new Paragraph("\n\n================================"));

			documento.close();
			
		} catch (Exception e2) {
            JOptionPane.showMessageDialog(null, "Error al generar el ticket.");

		}
	}
	
	
	
	//Métodos que ocupa la ventana
	public void muestra(ControlCobro controlCobro, float total, Empleado empleado) {
		textFieldTotal.setText(String.valueOf(total));
		this.empleado = empleado;
		
		this.controlCobro = controlCobro;
		this.total=total;
		textFieldRecibi.setText("");
		textFieldCambio.setText("");
		setVisible(true);
	}

	public void muestraDialogo() {
		JOptionPane.showMessageDialog(null,"La venta se realizó con exito");
		
	}
}
