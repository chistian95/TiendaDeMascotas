package pr5.modelo;

public class Mascota extends Articulo implements SerVivo {
	private static final long serialVersionUID = 2816088373604577539L;
	
	private Fecha fechaNacimiento;
	
	public Mascota(long codigo, String descripcion, int existencias, Fecha fechaNacimiento) {
		super(codigo, descripcion, existencias);
		this.fechaNacimiento = fechaNacimiento;
	}
	
	@Override
	public void modificar(Articulo articulo) {
		super.modificar(articulo);
		if(!(articulo instanceof Mascota)) {
			return;
		}
		Mascota mascota = (Mascota) articulo;
		this.fechaNacimiento = mascota.fechaNacimiento;
	}

	@Override
	public String toString() {
		return super.toString() + ", fecha de nacimiento: " + fechaNacimiento + ", celebramos su cumpleaños el " + cumpleaños();
	}

	@Override
	public String cumpleaños() {
		return fechaNacimiento.getDia() + " de " + fechaNacimiento.getMesNombre();
	}

	public Fecha getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Fecha fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}	
}
