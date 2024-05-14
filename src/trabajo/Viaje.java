package trabajo;

import java.util.TreeMap;

public class Viaje implements Comparable<Viaje> {
	private String lugar = "";
	
	private TreeMap<String, Integer> fecha = new TreeMap<>();
	
	private double precio;
	
	public Viaje(String lugar, TreeMap<String, Integer> fecha, double precio) {
		if(lugar != null && !lugar.equals("")) {
			this.lugar = lugar;
		}
		
		if(!fecha.isEmpty()) {
			this.fecha = fecha;
		}
		
		if(precio > 0) {
			this.precio = precio;
		}
	}
	
	public String getLugar() {
		return lugar;
	}
	
	public void setLugar(String lugar) {
		if(lugar != null && !lugar.equals("")) {
			this.lugar = lugar;
		}
	}
	
	public TreeMap<String, Integer> getFecha() {
		return fecha;
	}
	
	public void setFecha(TreeMap<String, Integer> fecha) {
		if(!fecha.isEmpty()) {
			this.fecha = fecha;
		}
	}
	
	public double getPrecio() {
		return precio;
	}
	
	public void setPrecio(double precio) {
		if(precio > 0) {
			this.precio = precio;
		}
	}
	
	@Override
	public String toString() {
		String res = "";
		
		res += "Lugar: " + lugar + "\n";
		res += "Fecha: " + fecha.get("dia") + "/" + fecha.get("mes") + "/" + fecha.get("anio") + "\n";
		res += "Precio: " + precio + "\n";
		
		return res;
	}
	
	@Override
	public boolean equals(Object obj) {
		boolean res = false;
		
		Viaje temp = (Viaje) obj;
		
		if(fecha.equals(temp.fecha)) {
			res = true;
		}
		
		return res;
	}
	
	/**
	 * Función que se encargará de comparar los objetos a la hora de ordenarlos.
	 *
	 */
	/* ATENCIÓN: Código pendiente de revisar y/o mejorar. Es muy posible que
	 * la forma de comparar las fechas no sea la más óptima, o que el atributo
	 * fecha deba ser de otro tipo en vez de un TreeMap. */
	@Override
	public int compareTo(Viaje o) {
		int res = 0;
		
		/* Pasamos las dos fechas a Arrays para comprobar cuál fecha es menor
		 * o mayor que otra. */
		Integer[] fechaActual = this.fecha.values().toArray(new Integer[3]);
		Integer[] fechaComparar = o.fecha.values().toArray(new Integer[3]);
		
		// Si el año del primer objeto es menor que el año del objeto a comparar...
		if(fechaActual[2] < fechaComparar[2]) {
			// Significa que el primer objeto va antes.
			res = -1;
			
		// Si el año del primer objeto es mayor que el año del objeto a comparar...
		} else if(fechaActual[2] > fechaComparar[2]) {
			// Irá después del primer objeto.
			res = 1;
			
		// Si por el contrario los años coinciden...
		} else {
			
			// Hacemos las mismas comprobaciones que hicimos con los años pero
			// ahora lo hacemos con los meses y con los días.
			if(fechaActual[1] < fechaComparar[1]) {
				res = -1;
			} else if(fechaActual[1] > fechaComparar[1]) {
				res = 1;
			} else {
				if(fechaActual[0] < fechaComparar[0]) {
					res = -1;
				} else if(fechaActual[0] > fechaComparar[0]) {
					res = 1;
				}
			}
		}
		
		return res;
	}
}
