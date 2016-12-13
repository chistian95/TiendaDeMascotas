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

public class VentanaModificar extends JDialog {
	private static final long serialVersionUID = 7539104626634536748L;
	
	private final JPanel contentPanel = new JPanel();
	private JTextField tfDescripcion;
	private JTextField tfCodigo;
	private JTextField tfFecha;
	private JLabel labelMascota;
	private Controlador controlador;
	
	public VentanaModificar(final PetShop ventana, final long codigo, boolean mascota) {
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		final VentanaModificar yo = this;
		
		setBounds(100, 100, 360, 253);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(SystemColor.info);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		labelMascota = new JLabel("MODIFICAR MASCOTA");
		labelMascota.setOpaque(true);
		labelMascota.setBackground(new Color(245, 222, 179));
		labelMascota.setHorizontalAlignment(SwingConstants.CENTER);
		labelMascota.setFont(new Font("Tahoma", Font.BOLD, 14));
		labelMascota.setBounds(10, 8, 324, 25);
		contentPanel.add(labelMascota);
		
		JLabel label_1 = new JLabel("C\u00F3digo");
		label_1.setBounds(10, 36, 130, 14);
		contentPanel.add(label_1);
		
		JLabel label_2 = new JLabel("Descripci\u00F3n");
		label_2.setBounds(204, 36, 130, 14);
		contentPanel.add(label_2);
		
		tfDescripcion = new JTextField();
		tfDescripcion.setColumns(10);
		tfDescripcion.setBounds(204, 61, 130, 20);
		contentPanel.add(tfDescripcion);
		
		tfCodigo = new JTextField();
		tfCodigo.setEnabled(false);
		tfCodigo.setEditable(false);
		tfCodigo.setColumns(10);
		tfCodigo.setBounds(10, 61, 130, 20);
		contentPanel.add(tfCodigo);
		
		JLabel label_3 = new JLabel("Existencias");
		label_3.setBounds(10, 92, 130, 14);
		contentPanel.add(label_3);
		
		JLabel labelFecha = new JLabel("Fecha de Nacimiento");
		labelFecha.setBounds(204, 92, 130, 14);
		contentPanel.add(labelFecha);
		
		tfFecha = new JTextField();
		tfFecha.setColumns(10);
		tfFecha.setBounds(204, 117, 130, 20);
		contentPanel.add(tfFecha);
		
		JSpinner spExistencias = new JSpinner();
		spExistencias.setBounds(10, 117, 130, 20);
		contentPanel.add(spExistencias);
		
		JButton button = new JButton("ACEPTAR");
		button.setForeground(SystemColor.inactiveCaptionBorder);
		button.setBackground(SystemColor.textHighlight);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlador.modificarArticulo(contentPanel, mascota, ventana, yo, tfCodigo, tfFecha, tfDescripcion, spExistencias);
			}
		});
		button.setBounds(10, 148, 324, 23);
		contentPanel.add(button);
		
		JButton button_1 = new JButton("SALIR");
		button_1.setForeground(SystemColor.inactiveCaptionBorder);
		button_1.setBackground(SystemColor.textHighlight);
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ventana.setVisible(true);
				dispose();
			}
		});
		button_1.setBounds(10, 182, 324, 23);
		contentPanel.add(button_1);
		
		controlador = Controlador.getInstance();		
		controlador.cargarDatos(mascota, codigo, labelFecha, labelMascota, tfCodigo, tfDescripcion, tfFecha, spExistencias);
		
		setVisible(true);
	}

}
