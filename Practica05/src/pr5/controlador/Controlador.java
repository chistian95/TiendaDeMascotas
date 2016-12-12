package pr5.controlador;

import java.util.List;

import pr5.modelo.Articulo;
import pr5.modelo.Tienda;

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
	
	public boolean introducirArticulo(Articulo articulo) {
		return tienda.introducirArticulo(articulo);
	}
	
	public boolean eliminarArticulo(long codigo) {
		return tienda.eliminarArticulo(codigo);
	}
	
	public void guardarArticulos() {
		tienda.guardarArticulos();
	}
	
	public Articulo buscarArticulo(long codigo) {
		return tienda.buscarArticulo(codigo);
	}
	
	public boolean modificarArticulo(Articulo articulo) {
		return tienda.modificarArticulo(articulo);
	}
	
	public List<Articulo> getArticulos() {
		return tienda.getArticulos();
	}
}
