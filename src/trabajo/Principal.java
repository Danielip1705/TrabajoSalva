package trabajo;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Principal {
	// objeto scanner
	public static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {

		// var tipo viaje para poder anadirle valores (PON EL CONSTRUCTOR VACIO)
		Viaje v = null;

		// var para lectura del archivo/escritura del archivo
		BufferedReader br = null;

		String[] arrayPorPuntos = null;

		String[] arrayFecha = null;

		//
		String linea = "";

		// string para escribir
		String lugar = "";

		double precio = 0;

		// mapa para la fecha
		String fecha = "";

		int dia = 0;
		int mes = 0;
		int anyo = 0;

		int opcion = 0;

		// try/catch para el texto
		try {

			// se asigna el texto a leer
			br = new BufferedReader(new FileReader("src\\trabajo\\datosTurismo.txt"));

			// leemos la primera linea para comprobar
			linea = br.readLine();

			// while para la lectura de la linea hasta que sea null
			while (linea != null) {

				// hacemos split de la linea del texto
				arrayPorPuntos = linea.split("::");

				// hacemos split para guardar la fecha
				arrayFecha = arrayPorPuntos[1].split("/");

				// pasamos el valor a double
				precio = Double.parseDouble(arrayPorPuntos[2]);

				dia = Integer.parseInt(arrayFecha[0]);
				mes = Integer.parseInt(arrayFecha[1]);
				anyo = Integer.parseInt(arrayFecha[2]);

				// anadimos el valor de dia mes anyo
				fecha = dia + "/" + mes + "/" + anyo;

				// se anade al viaje
				v = new Viaje(arrayPorPuntos[0], fecha, precio);

				// ponemos el dato del viaje en el arraylist hasta llegar al final del texto
				CrudViaje.listaViajes.add(v);

				// leer la siguiente linea
				linea = br.readLine();

			} // end while linea!=null

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null) {
					br.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		// do while de opcion
		do {
			// llamamos la funcion
			menu();

			// elegir opcion
			opcion = sc.nextInt();
			sc.nextLine(); // Consumir el salto de línea después del entero

			// switch case para la opcion del programa
			switch (opcion) {

			// 1 Ver un listado de todos los viajes disponibles, mostrando el lugar, la
			// fecha y el precio de cada uno.
			case 1: {
				CrudViaje.mostrarViajes();
				break;
			}

			// 2 Añadir un nuevo viaje especificando el lugar, la fecha (en formato
			// DD/MM/AAAA) y el precio.
			case 2: {
				// introducimos los valores de cada variable
				System.out.println("Introduce un lugar: ");
				lugar = sc.nextLine();

				System.out.println("Introduce una fecha (DD/MM/AAAA): ");
				fecha = sc.nextLine();

				System.out.println("Introduce un precio: ");
				precio = sc.nextDouble();

				if (Viaje.validarFecha(fecha)) {
					// Creamos un nuevo viaje
					v = new Viaje(lugar, fecha, precio);

					if (CrudViaje.anniadirViaje(v)) {
						System.out.println("Viaje añadido con éxito");
					} else {
						System.out.println("No se ha podido añadir el viaje");
					}
				} else {
					System.out.println("Fecha no válida");
				}

				System.out.println();
				break;
			}

			// 3 Modificar el precio o la fecha de un viaje existente, seleccionando el
			// viaje por su lugar.
			case 3: {

				break;
			}

			// 4 Eliminar un viaje existente, seleccionándolo por su lugar.
			case 4: {

				break;
			}

			// 5 Guardar los cambios realizados en un archivo de texto.
			case 5: {

				break;
			}
			// 6 Salir del programa.
			case 6: {

				System.out.println("Saliendo...");

				break;
			}

			// Opcion fuera de 1 a 6
			default: {

				System.out.println("Opcion no valida, elige otra opcion.");

				break;
			}

			}// end switchcase

		} while (opcion != 6);// end dowhile opcion

		System.out.println();

		// cierre de scanner
		sc.close();

	}// end main

	/**
	 * metodo para el menu que imprime un menu para el programa no recibe ni
	 * devuelve nada
	 */
	public static void menu() {
		// imprime el menu
		System.out.println("------------------------");
		System.out.println("Bienvenido a la gestion del viaje.");
		System.out.println("Elige una opcion:");
		System.out.println("1 Ver listado de los viajes disponibles.");
		System.out.println("2 Añadir un nuevo viaje.");
		System.out.println("3 Modificar el precio o la fecha de un viaje existente.");
		System.out.println("4 Eliminar un viaje existente.");
		System.out.println("5 Guardar los cambios realizados en un archivo de texto.");
		System.out.println("6 Salir del programa.");
		System.out.println("------------------------");

	}

}
