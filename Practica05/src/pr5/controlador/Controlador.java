package pr5.controlador;

import java.util.List;

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
				JOptionPane.showMessageDialog(contentPanel, "Este art�culo ya existe!", "Error!", JOptionPane.ERROR_MESSAGE);
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
			JOptionPane.showMessageDialog(contentPanel, "Ha ocurrido un error al crear el art�culo!", "Error!", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void elminarArticulo(JPanel contentPane, PetShop shop, JList<String> listaMascotas, JList<String> listaComplementos) {
		if(listaMascotas.getSelectedIndex() == -1 && listaComplementos.getSelectedIndex() == -1) {
			JOptionPane.showMessageDialog(contentPane, "Debes seleccionar un art�culo!", "Error!", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		if(JOptionPane.showConfirmDialog(contentPane, "Deseas borrar este art�culo?") != 0) {
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
			JOptionPane.showMessageDialog(contentPane, "Art�culo eliminado!");
		} else {
			JOptionPane.showMessageDialog(contentPane, "Error al borrar art�culo!", "Error!", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void guardarArticulos() {
		tienda.guardarArticulos();
	}
	
	public Articulo buscarArticulo(long codigo) {
		return tienda.buscarArticulo(codigo);
	}
	
	public void modificarArticulo(JPanel contentPanel, boolean isMascota, PetShop shop, VentanaModificar ventana, JTextField tfCodigo, JTextField tfFecha, JTextField tfDescripcion, JSpinner spExistencias) {
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
			JOptionPane.showMessageDialog(contentPanel, "Ha ocurrido un error al modificar el art�culo!", "Error!", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public List<Articulo> getArticulos() {
		return tienda.getArticulos();
	}
}
