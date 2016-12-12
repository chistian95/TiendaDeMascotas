package pr5.modelo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class Tienda {
	private static Tienda instancia;
	private List<Articulo> listaArticulos;
	
	private Tienda() {
		listaArticulos = new ArrayList<Articulo>();
		cargarArticulos();
	}
	
	public void cargarArticulos() {
		try {
			listaArticulos = new ArrayList<Articulo>();
			
			File archivo = new File("listaArticulos.obj");
			if(!archivo.exists()) {
				return;
			}
			
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo));
			Articulo articulo;
			while((articulo = (Articulo) ois.readObject()) != null) {
				listaArticulos.add(articulo);
			}
			
			ois.close();
		} catch (Exception e) {
		}
	}
	
	public void guardarArticulos() {
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("listaArticulos.obj"));
			for(Articulo art : listaArticulos) {
				oos.writeObject(art);
			}
			
			oos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public boolean introducirArticulo(Articulo articulo) {
		if(listaArticulos.contains(articulo)) {
			return false;
		}
		listaArticulos.add(articulo);
		return true;
	}
	
	public String mostrarProductos() {
		String msg = "";
		for(Articulo art : listaArticulos) {
			msg += art.toString() + "\n";
		}
		return msg;
	}
	
	public Articulo buscarArticulo(long codigo) {
		for(Articulo art : listaArticulos) {
			if(art.getCodigo() == codigo) {
				return art;
			}
		}
		return null;
	}
	
	public boolean modificarArticulo(Articulo articulo) {
		for(Articulo art : listaArticulos) {
			if(art.getCodigo() == articulo.getCodigo()) {
				art.modificar(articulo);
				return true;
			}
		}
		return false;
	}
	
	public boolean eliminarArticulo(long codigo) {
		Articulo articulo = buscarArticulo(codigo);
		if(articulo == null) {
			return false;
		}
		
		for(Articulo art : listaArticulos) {
			if(art.getCodigo() == articulo.getCodigo()) {
				listaArticulos.remove(art);
				return true;
			}
		}
		return false;
	}
	
	public List<Articulo> getArticulos() {
		return listaArticulos;
	}
	
	public static Tienda getInstance() {
		if (instancia == null) {
			instancia = new Tienda(); 
		}
		return instancia;
	}
}
