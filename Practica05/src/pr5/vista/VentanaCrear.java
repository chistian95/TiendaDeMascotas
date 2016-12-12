package pr5.vista;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import pr5.controlador.Controlador;
import pr5.modelo.Complemento;
import pr5.modelo.Fecha;
import pr5.modelo.Mascota;

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
		setBounds(100, 100, 390, 256);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		labelMascota = new JLabel("CREAR MASCOTA");
		labelMascota.setFont(new Font("Tahoma", Font.BOLD, 14));
		labelMascota.setHorizontalAlignment(SwingConstants.CENTER);
		labelMascota.setBounds(10, 11, 354, 14);
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
		btnCrearMascota.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					if(tfCodigo.getText().length() <= 0 || tfFecha.getText().length() <= 0 || tfDescripcion.getText().length() <= 0) {
						JOptionPane.showMessageDialog(contentPanel, "Debes rellenar todos los campos!", "Error!", JOptionPane.ERROR_MESSAGE);
						return;
					}
					
					long codigo = Long.parseLong(tfCodigo.getText());					
					if(controlador.buscarArticulo(codigo) != null) {
						JOptionPane.showMessageDialog(contentPanel, "Este artículo ya existe!", "Error!", JOptionPane.ERROR_MESSAGE);
						return;
					}
					
					int existencias = Integer.parseInt(spExistencias.getValue().toString());
					
					Fecha fecha = null;
					if(Fecha.comprobar(tfFecha.getText())) {
						fecha = Fecha.crearFecha(tfFecha.getText());
					} else {
						JOptionPane.showMessageDialog(contentPanel, "La fecha no es correcta!", "Error!", JOptionPane.ERROR_MESSAGE);
						return;
					}
					
					if(mascota) {
						Mascota mascota = new Mascota(codigo, tfDescripcion.getText(), existencias, fecha);
						if(controlador.introducirArticulo(mascota)) {
							tienda.buscarArticulos();
							JOptionPane.showMessageDialog(contentPanel, "Mascota creada!");
						} else {
							JOptionPane.showMessageDialog(contentPanel, "Ha ocurrido un error al crear la mascota!", "Error!", JOptionPane.ERROR_MESSAGE);
						}
					} else {
						Complemento complemento = new Complemento(codigo, tfDescripcion.getText(), existencias, fecha);
						if(controlador.introducirArticulo(complemento)) {
							tienda.buscarArticulos();
							JOptionPane.showMessageDialog(contentPanel, "Complemento creado!");
						} else {
							JOptionPane.showMessageDialog(contentPanel, "Ha ocurrido un error al crear el complemento!", "Error!", JOptionPane.ERROR_MESSAGE);
						}
					}
					
					tfCodigo.setText("");
					tfFecha.setText("");
					tfDescripcion.setText("");
					spExistencias.setValue(0);
				} catch(Exception e) {
					JOptionPane.showMessageDialog(contentPanel, "Ha ocurrido un error al crear el artículo!", "Error!", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnCrearMascota.setBounds(10, 148, 354, 23);
		contentPanel.add(btnCrearMascota);
		
		btnSalir = new JButton("SALIR");
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
