package trabajo;

import java.util.ArrayList;

/**
 * Clase donde se realizaran los metodos necesarios para realizar el crud C
 * create (añadir viaje) R read (Mostrar listado) U update (modificar precio y
 * fecha existente) D delete (eliminar viaje)
 */
public class CrudViaje {

	static ArrayList<Viaje> listaViajes = new ArrayList<Viaje>();

	/*
	 * Posibles funciones (Creo) añadirViaje(Viaje vij) listado() delete(Viaje vij)
	 * guardarEnArchivo()
	 */
	public static void mostrarViajes() {

		for (Viaje vij : listaViajes) {
			System.out.println(vij);
		}
	}

	public static boolean anniadirViaje(Viaje viaje) {
		boolean hecho = false;

		if (viaje != null && !listaViajes.contains(viaje)) {
			listaViajes.add(viaje);
			hecho = true;
		}

		return hecho;
	}

	/**
	 * Funcion para modificar el precio y fecha de un viaje por su lugar
	 * @param lugar Cadena que contiene el lugar del viaje a modificar
	 * @param nuevoPrecio Precio nuevo a modificar
	 * @param nuevaFecha Fecha nueva a modificar
	 * @return True o false dependiendo de que exista el viaje
	 */
	
	
	public static boolean modificarViajePorLugar(String lugar, Double nuevoPrecio, String nuevaFecha) {
		boolean modificado = false;
		for (Viaje v : listaViajes) {
			if (v.getLugar().equalsIgnoreCase(lugar)) {
				if (nuevoPrecio != null) {
					v.setPrecio(nuevoPrecio);
				}
				if (nuevaFecha != null && Viaje.validarFecha(nuevaFecha)) {
					v.setFecha(nuevaFecha);
				}
				modificado=true;
			}
		}
		return modificado;
	}

}
