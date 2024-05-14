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
}
