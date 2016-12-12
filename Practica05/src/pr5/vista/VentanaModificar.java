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

public class VentanaModificar extends JDialog {
	private static final long serialVersionUID = 7539104626634536748L;
	
	private final JPanel contentPanel = new JPanel();
	private JTextField tfDescripcion;
	private JTextField tfCodigo;
	private JTextField tfFecha;
	private JLabel labelMascota;
	private Controlador controlador;
	
	public VentanaModificar(final PetShop ventana, final long codigo, boolean mascota) {
		setBounds(100, 100, 360, 253);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		labelMascota = new JLabel("MODIFICAR MASCOTA");
		labelMascota.setHorizontalAlignment(SwingConstants.CENTER);
		labelMascota.setFont(new Font("Tahoma", Font.BOLD, 14));
		labelMascota.setBounds(10, 11, 324, 14);
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
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(tfCodigo.getText().length() <= 0 || tfFecha.getText().length() <= 0 || tfDescripcion.getText().length() <= 0) {
						JOptionPane.showMessageDialog(contentPanel, "Debes rellenar todos los campos!", "Error!", JOptionPane.ERROR_MESSAGE);
						return;
					}
					
					if(JOptionPane.showConfirmDialog(contentPanel, "Deseas modificar este art�culo?") != 0) {
						return;
					}
					
					long codigo = Long.parseLong(tfCodigo.getText());
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
						if(controlador.modificarArticulo(mascota)) {
							ventana.buscarArticulos();
							JOptionPane.showMessageDialog(contentPanel, "Mascota modificado!");
						} else {
							JOptionPane.showMessageDialog(contentPanel, "Ha ocurrido un error al modificar la mascota!", "Error!", JOptionPane.ERROR_MESSAGE);
						}
					} else {
						Complemento complemento = new Complemento(codigo, tfDescripcion.getText(), existencias, fecha);
						if(controlador.modificarArticulo(complemento)) {
							ventana.buscarArticulos();
							JOptionPane.showMessageDialog(contentPanel, "Complemento modificado!");
						} else {
							JOptionPane.showMessageDialog(contentPanel, "Ha ocurrido un error al modificar el complemento!", "Error!", JOptionPane.ERROR_MESSAGE);
						}
					}
					
					ventana.setVisible(true);
					dispose();
				} catch(Exception ex) {
					JOptionPane.showMessageDialog(contentPanel, "Ha ocurrido un error al modificar el art�culo!", "Error!", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		button.setBounds(10, 148, 324, 23);
		contentPanel.add(button);
		
		JButton button_1 = new JButton("SALIR");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ventana.setVisible(true);
				dispose();
			}
		});
		button_1.setBounds(10, 182, 324, 23);
		contentPanel.add(button_1);
		
		controlador = Controlador.getInstance();
		
		if(!mascota) {
			labelFecha.setText("Fecha de Caducidad");
			labelMascota.setText("MODIFICAR COMPLEMENTO");
			
			Complemento comp = (Complemento) controlador.buscarArticulo(codigo);
			tfCodigo.setText(comp.getCodigo()+"");
			tfDescripcion.setText(comp.getDescripcion());
			tfFecha.setText(comp.getFechaCaducidad().toString());
			spExistencias.setValue(comp.getExistencias());
		} else {
			Mascota masc = (Mascota) controlador.buscarArticulo(codigo);
			tfCodigo.setText(masc.getCodigo()+"");
			tfDescripcion.setText(masc.getDescripcion());
			tfFecha.setText(masc.getFechaNacimiento().toString());
			spExistencias.setValue(masc.getExistencias());
		}		
		
		setVisible(true);
	}

}