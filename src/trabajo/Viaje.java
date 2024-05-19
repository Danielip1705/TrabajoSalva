package trabajo;

public class Viaje {

	private String lugar = "";

	private String fecha = "";

	private double precio;

	public Viaje(String lugar, String fecha, double precio) {

		if (lugar != null && !lugar.equals("")) {
			this.lugar = lugar;
		}

		if (fecha != null && !fecha.equals("") && validarFecha(fecha)) {
			this.fecha = fecha;
		}

		if (precio > 0) {
			this.precio = precio;
		}
	}

	public static boolean validarFecha(String fecha) {
		boolean res = false;

		try {
			int diaInt = Integer.valueOf(fecha.substring(0, 2));
			int mesInt = Integer.valueOf(fecha.substring(3, 5));

			if ((diaInt >= 1 && diaInt <= 31) && (mesInt >= 1 && mesInt <= 12)) {
				res = true;
			}
		} catch (NumberFormatException e) {

		}

		return res;
	}

	public String getLugar() {
		return lugar;
	}

	public void setLugar(String lugar) {
		if (lugar != null && !lugar.equals("")) {
			this.lugar = lugar;
		}
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		if (fecha != null && !fecha.equals("")) {
			this.fecha = fecha;
		}
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		if (precio > 0) {
			this.precio = precio;
		}
	}

	@Override
	public String toString() {

		String res = "";
		String[] temp = fecha.split("/");
		
		try {
			

			res += "Lugar: " + lugar + "\n";
			res += "Fecha: " + temp[0] + "/" + temp[1] + "/" + temp[2] + "\n";
			
			
		} catch (ArrayIndexOutOfBoundsException e) {
			res += "Fecha no válida." + "\n";
		} finally {
			res += "Precio: " + precio + "\n";
		}
		
		
		return res;
	}

	@Override
	public boolean equals(Object obj) {
		boolean res = false;

		Viaje temp = (Viaje) obj;

		if (lugar.equals(temp.lugar) && fecha.equals(temp.fecha)) {
			res = true;
		}

		return res;
	}

}
