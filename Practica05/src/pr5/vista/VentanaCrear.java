package pr5.vista;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import pr5.controlador.Controlador;
import java.awt.Color;
import java.awt.SystemColor;

public class VentanaCrear extends JDialog {
	private static final long serialVersionUID = -4129377478680923191L;
	
	private final JPanel contentPanel = new JPanel();
	private JTextField tfCodigo;
	private JTextField tfDescripcion;
	private JTextField tfFecha;
	private Controlador controlador;
	private JLabel lblFechaDeNacimiento;
	private JLabel labelMascota;
	private JButton btnSalir;

	public VentanaCrear(final PetShop tienda, boolean mascota) {
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 390, 256);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(SystemColor.info);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		labelMascota = new JLabel("CREAR MASCOTA");
		labelMascota.setOpaque(true);
		labelMascota.setBackground(new Color(245, 222, 179));
		labelMascota.setFont(new Font("Tahoma", Font.BOLD, 14));
		labelMascota.setHorizontalAlignment(SwingConstants.CENTER);
		labelMascota.setBounds(10, 6, 354, 25);
		contentPanel.add(labelMascota);
		
		tfCodigo = new JTextField();
		tfCodigo.setBounds(10, 61, 130, 20);
		contentPanel.add(tfCodigo);
		tfCodigo.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("C\u00F3digo");
		lblNewLabel.setBounds(10, 36, 130, 14);
		contentPanel.add(lblNewLabel);
		
		tfDescripcion = new JTextField();
		tfDescripcion.setColumns(10);
		tfDescripcion.setBounds(224, 61, 140, 20);
		contentPanel.add(tfDescripcion);
		
		JLabel lblDescripcin = new JLabel("Descripci\u00F3n");
		lblDescripcin.setBounds(224, 36, 140, 14);
		contentPanel.add(lblDescripcin);
		
		JLabel lblExistencias = new JLabel("Existencias");
		lblExistencias.setBounds(10, 92, 130, 14);
		contentPanel.add(lblExistencias);
		
		lblFechaDeNacimiento = new JLabel("Fecha de Nacimiento");
		lblFechaDeNacimiento.setBounds(224, 92, 140, 14);
		contentPanel.add(lblFechaDeNacimiento);
		
		tfFecha = new JTextField();
		tfFecha.setColumns(10);
		tfFecha.setBounds(224, 117, 140, 20);
		contentPanel.add(tfFecha);
		
		JSpinner spExistencias = new JSpinner();
		spExistencias.setBounds(10, 117, 130, 20);
		contentPanel.add(spExistencias);
		
		JButton btnCrearMascota = new JButton("ACEPTAR");
		btnCrearMascota.setForeground(SystemColor.inactiveCaptionBorder);
		btnCrearMascota.setBackground(SystemColor.textHighlight);
		btnCrearMascota.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controlador.introducirArticulo(contentPanel, tienda, mascota, tfCodigo, tfDescripcion, spExistencias, tfFecha);
			}
		});
		btnCrearMascota.setBounds(10, 148, 354, 23);
		contentPanel.add(btnCrearMascota);
		
		btnSalir = new JButton("SALIR");
		btnSalir.setForeground(SystemColor.inactiveCaptionBorder);
		btnSalir.setBackground(SystemColor.textHighlight);
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tienda.setVisible(true);
				dispose();
			}
		});
		btnSalir.setBounds(10, 182, 354, 23);
		contentPanel.add(btnSalir);
		
		if(!mascota) {
			lblFechaDeNacimiento.setText("Fecha de Caducidad");
			labelMascota.setText("CREAR COMPLEMENTO");
		}
		
		controlador = Controlador.getInstance();
		setVisible(true);
	}
}
