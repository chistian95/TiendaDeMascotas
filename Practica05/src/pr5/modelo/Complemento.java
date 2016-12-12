package pr5.modelo;

public class Complemento extends Articulo {
	private static final long serialVersionUID = -3840650068054271865L;
	
	private Fecha fechaCaducidad;
	
	public Complemento(long codigo, String descripcion, int existencias, Fecha fechaCaducidad) {
		super(codigo, descripcion, existencias);
		this.fechaCaducidad = fechaCaducidad;
	}
	
	@Override
	public void modificar(Articulo articulo) {
		super.modificar(articulo);
		if(!(articulo instanceof Complemento)) {
			return;
		}
		Complemento complemento = (Complemento) articulo;
		this.fechaCaducidad = complemento.fechaCaducidad;
	}

	@Override
	public String toString() {
		return super.toString() + ", fecha de caducidad: " + fechaCaducidad;
	}

	public Fecha getFechaCaducidad() {
		return fechaCaducidad;
	}

	public void setFechaCaducidad(Fecha fechaCaducidad) {
		this.fechaCaducidad = fechaCaducidad;
	}
}
