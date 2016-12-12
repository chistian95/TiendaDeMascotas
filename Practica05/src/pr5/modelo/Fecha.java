package pr5.modelo;

import java.io.Serializable;

public class Fecha implements Serializable {
	private static final long serialVersionUID = 2465819693114069246L;
	
	private int a�o;
	private int mes;
	private int dia;
	
	public Fecha() {
		this(1, 1, 1);
	}
	
	public Fecha(int dia, int mes, int a�o) {
		this.a�o = a�o;
		this.mes = mes;
		this.dia = dia;
	}
	
	public int compararFechas(Fecha fecha) {		
		if(a�o < fecha.a�o) {
			return -1;
		}
		if(a�o > fecha.a�o) {
			return 1;
		}
		
		if(mes < fecha.mes) {
			return -1;
		}
		if(mes > fecha.mes) {
			return 1;
		}
		
		if(dia < fecha.dia) {
			return -1;
		}
		if(dia > fecha.dia) {
			return 1;
		}
		
		return 0;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + a�o;
		result = prime * result + dia;
		result = prime * result + mes;
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
		Fecha other = (Fecha) obj;
		if (a�o != other.a�o)
			return false;
		if (dia != other.dia)
			return false;
		if (mes != other.mes)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return dia+"/"+mes+"/"+a�o;
	}

	public int getA�o() {
		return a�o;
	}

	public void setA�o(int a�o) {
		this.a�o = a�o;
	}

	public int getMes() {
		return mes;
	}
	
	public String getMesNombre() {
		switch(mes) {
		case 1:
			return "Enero";
		case 2:
			return "Febrero";
		case 3:
			return "Marzo";
		case 4:
			return "Abril";
		case 5:
			return "Mayo";
		case 6:
			return "Junio";
		case 7:
			return "Julio";
		case 8:
			return "Agosto";
		case 9:
			return "Septiembre";
		case 10:
			return "Octubre";
		case 11:
			return "Noviembre";
		case 12:
			return "Diciembre";
		default:
			return "Error";
		}
	}

	public void setMes(int mes) {
		this.mes = mes;
	}

	public int getDia() {
		return dia;
	}

	public void setDia(int dia) {
		this.dia = dia;
	}
	
	public static Fecha crearFecha(String fecha) {
		String[] romper = fecha.split("/");
		if(romper.length != 3) {
			return null;
		}
		
		int dia = 0, mes = 0, a�o = 0;
		try {
			dia = Integer.parseInt(romper[0]);
			mes = Integer.parseInt(romper[1]);
			a�o = Integer.parseInt(romper[2]);
		} catch(Exception e) {
			return null;
		}
		
		if(dia < 1 || dia > 30) {
			return null;
		}
		if(mes < 1 || mes > 12) {
			return null;
		}
		if(a�o < 0) {
			return null;
		}
		
		return new Fecha(dia, mes, a�o);
	}
	
	public static boolean comprobar(String fecha) {
		String[] romper = fecha.split("/");
		if(romper.length != 3) {
			return false;
		}
		
		int dia = 0, mes = 0, a�o = 0;
		try {
			dia = Integer.parseInt(romper[0]);
			mes = Integer.parseInt(romper[1]);
			a�o = Integer.parseInt(romper[2]);
		} catch(Exception e) {
			return false;
		}
		
		if(dia < 1 || dia > 30) {
			return false;
		}
		if(mes < 1 || mes > 12) {
			return false;
		}
		if(a�o < 0) {
			return false;
		}
		
		return true;
	}
}
