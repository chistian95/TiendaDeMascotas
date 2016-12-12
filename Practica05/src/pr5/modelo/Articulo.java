package pr5.modelo;

import java.io.Serializable;

public class Articulo implements Serializable {
	private static final long serialVersionUID = -9080626289201770818L;
	
	private long codigo;
	private String descripcion;
	private int existencias;
	
	public Articulo(long codigo, String descripcion, int existencias) {
		super();
		this.codigo = codigo;
		this.descripcion = descripcion;
		this.existencias = existencias;
	}
	
	public void modificar(Articulo articulo) {
		this.descripcion = articulo.descripcion;
		this.existencias = articulo.existencias;
	}
	
	@Override
	public String toString() {
		return codigo + ", " + descripcion + ", " + existencias + " unidades";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (codigo ^ (codigo >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Articulo other = (Articulo) obj;
		if (codigo != other.codigo)
			return false;
		return true;
	}

	public long getCodigo() {
		return codigo;
	}
	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public int getExistencias() {
		return existencias;
	}
	public void setExistencias(int existencias) {
		this.existencias = existencias;
	}
	
	
}
