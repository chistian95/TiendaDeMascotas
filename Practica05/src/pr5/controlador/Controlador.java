package pr5.controlador;

import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;

import pr5.modelo.Articulo;
import pr5.modelo.Complemento;
import pr5.modelo.Fecha;
import pr5.modelo.Mascota;
import pr5.modelo.Tienda;
import pr5.vista.PetShop;
import pr5.vista.VentanaModificar;

public class Controlador {
	private static Controlador instancia;
	private Tienda tienda;
	
	private Controlador() {
		tienda = Tienda.getInstance();
	}
	
	public static Controlador getInstance() {
		if(instancia == null) {
			instancia = new Controlador();
		}
		return instancia;
	}
	
	public void introducirArticulo(JPanel contentPanel, PetShop shop, boolean isMascota, JTextField tfCodigo, JTextField tfDescripcion, JSpinner spExistencias, JTextField tfFecha) {
		try {
			if(tfCodigo.getText().length() <= 0 || tfFecha.getText().length() <= 0 || tfDescripcion.getText().length() <= 0) {
				JOptionPane.showMessageDialog(contentPanel, "Debes rellenar todos los campos!", "Error!", JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			long codigo = Long.parseLong(tfCodigo.getText());					
			if(tienda.buscarArticulo(codigo) != null) {
				JOptionPane.showMessageDialog(contentPanel, "Este artículo ya existe!", "Error!", JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			int existencias = Integer.parseInt(spExistencias.getValue().toString());
			if(existencias < 0) {
				JOptionPane.showMessageDialog(contentPanel, "Las existencias no pueden ser negativas!", "Error!", JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			Fecha fecha = null;
			if(Fecha.comprobar(tfFecha.getText())) {
				fecha = Fecha.crearFecha(tfFecha.getText());
			} else {
				JOptionPane.showMessageDialog(contentPanel, "La fecha no es correcta!", "Error!", JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			if(isMascota) {
				Mascota mascota = new Mascota(codigo, tfDescripcion.getText(), existencias, fecha);
				if(tienda.introducirArticulo(mascota)) {
					shop.buscarArticulos();
					JOptionPane.showMessageDialog(contentPanel, "Mascota creada!");
				} else {
					JOptionPane.showMessageDialog(contentPanel, "Ha ocurrido un error al crear la mascota!", "Error!", JOptionPane.ERROR_MESSAGE);
				}
			} else {
				Complemento complemento = new Complemento(codigo, tfDescripcion.getText(), existencias, fecha);
				if(tienda.introducirArticulo(complemento)) {
					shop.buscarArticulos();
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
	
	public void elminarArticulo(JPanel contentPane, PetShop shop, JList<String> listaMascotas, JList<String> listaComplementos) {
		if(listaMascotas.getSelectedIndex() == -1 && listaComplementos.getSelectedIndex() == -1) {
			JOptionPane.showMessageDialog(contentPane, "Debes seleccionar un artículo!", "Error!", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		if(JOptionPane.showConfirmDialog(contentPane, "Deseas borrar este artículo?") != 0) {
			return;
		}
		
		String res = "";
		if(listaMascotas.getSelectedIndex() != -1) {
			res = listaMascotas.getSelectedValue();
			
		} else {
			res = listaComplementos.getSelectedValue();				
		}	
		
		long codigo = Long.parseLong(res.split(", ")[0]);
		if(tienda.eliminarArticulo(codigo)) {
			shop.buscarArticulos();
			JOptionPane.showMessageDialog(contentPane, "Artículo eliminado!");
		} else {
			JOptionPane.showMessageDialog(contentPane, "Error al borrar artículo!", "Error!", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void guardarArticulos() {
		tienda.guardarArticulos();
	}
	
	public void buscarArticulo(JList<String> listaMascotas, JList<String> listaComplementos, JTextField tfCodigo) {
		DefaultListModel<String> modelMascotas = (DefaultListModel<String>) listaMascotas.getModel();
		DefaultListModel<String> modelComplementos = (DefaultListModel<String>) listaComplementos.getModel();
		modelMascotas.removeAllElements();
		modelComplementos.removeAllElements();
		if(tfCodigo.getText().length() > 0) {
			try {
				long codigo = Long.parseLong(tfCodigo.getText());
				Articulo articulo = tienda.buscarArticulo(codigo);
				if(articulo != null) {	
					if(articulo instanceof Complemento) {
						modelComplementos.addElement(articulo.toString());
					} else if(articulo instanceof Mascota) {
						modelMascotas.addElement(articulo.toString());
					}
				}
				tfCodigo.setText("");
			} catch(Exception e) {
				
			}		
			return;
		}
		for(Articulo art : tienda.getArticulos()) {
			if(art instanceof Complemento) {
				modelComplementos.addElement(art.toString());
			} else if(art instanceof Mascota) {
				modelMascotas.addElement(art.toString());
			}
		}
	}
	
	public void modificarArticulo(JPanel contentPanel, boolean isMascota, PetShop shop, VentanaModificar ventana, JTextField tfCodigo, JTextField tfFecha, JTextField tfDescripcion, JSpinner spExistencias) {
		try {
			if(tfCodigo.getText().length() <= 0 || tfFecha.getText().length() <= 0 || tfDescripcion.getText().length() <= 0) {
				JOptionPane.showMessageDialog(contentPanel, "Debes rellenar todos los campos!", "Error!", JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			if(JOptionPane.showConfirmDialog(contentPanel, "Deseas modificar este artículo?") != 0) {
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
			
			if(isMascota) {
				Mascota mascota = new Mascota(codigo, tfDescripcion.getText(), existencias, fecha);
				if(tienda.modificarArticulo(mascota)) {
					shop.buscarArticulos();
					JOptionPane.showMessageDialog(contentPanel, "Mascota modificado!");
				} else {
					JOptionPane.showMessageDialog(contentPanel, "Ha ocurrido un error al modificar la mascota!", "Error!", JOptionPane.ERROR_MESSAGE);
				}
			} else {
				Complemento complemento = new Complemento(codigo, tfDescripcion.getText(), existencias, fecha);
				if(tienda.modificarArticulo(complemento)) {
					shop.buscarArticulos();
					JOptionPane.showMessageDialog(contentPanel, "Complemento modificado!");
				} else {
					JOptionPane.showMessageDialog(contentPanel, "Ha ocurrido un error al modificar el complemento!", "Error!", JOptionPane.ERROR_MESSAGE);
				}
			}
			
			shop.setVisible(true);
			ventana.dispose();
		} catch(Exception ex) {
			JOptionPane.showMessageDialog(contentPanel, "Ha ocurrido un error al modificar el artículo!", "Error!", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void abrirVentanaModificar(JPanel contentPane, PetShop shop, JList<String> listaMascotas, JList<String> listaComplementos) {
		if(listaMascotas.getSelectedIndex() == -1 && listaComplementos.getSelectedIndex() == -1) {
			JOptionPane.showMessageDialog(contentPane, "Debes seleccionar un artículo!", "Error!", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		String res = "";
		if(listaMascotas.getSelectedIndex() != -1) {
			res = listaMascotas.getSelectedValue();
		} else {
			res = listaComplementos.getSelectedValue();
		}
		long codigo = Long.parseLong(res.split(", ")[0]);
		
		Articulo art = tienda.buscarArticulo(codigo);
		if(art == null) {
			JOptionPane.showMessageDialog(contentPane, "Error al modificar el artículo!", "Error!", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		if(art instanceof Mascota) {
			shop.abrirVentanaModificar(codigo, true);
		} else if(art instanceof Complemento) {
			shop.abrirVentanaModificar(codigo, false);
		} else {
			JOptionPane.showMessageDialog(contentPane, "Error al modificar el artículo!", "Error!", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void cargarDatos(boolean isMascota, long codigo, JLabel labelFecha, JLabel labelMascota, JTextField tfCodigo, JTextField tfDescripcion, JTextField tfFecha, JSpinner spExistencias) {
		if(!isMascota) {
			labelFecha.setText("Fecha de Caducidad");
			labelMascota.setText("MODIFICAR COMPLEMENTO");
			
			Complemento comp = (Complemento) tienda.buscarArticulo(codigo);
			tfCodigo.setText(comp.getCodigo()+"");
			tfDescripcion.setText(comp.getDescripcion());
			tfFecha.setText(comp.getFechaCaducidad().toString());
			spExistencias.setValue(comp.getExistencias());
		} else {
			Mascota masc = (Mascota) tienda.buscarArticulo(codigo);
			tfCodigo.setText(masc.getCodigo()+"");
			tfDescripcion.setText(masc.getDescripcion());
			tfFecha.setText(masc.getFechaNacimiento().toString());
			spExistencias.setValue(masc.getExistencias());
		}
	}
}
