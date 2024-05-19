package trabajo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Principal {
	
	// objeto scanner (hecho static para poder resumir codigo si es necesario)
	public static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {

		// var tipo viaje para poder anadirle valores (PON EL CONSTRUCTOR VACIO)
		Viaje v = null;

		// var para lectura del archivo/escritura del archivo
		BufferedReader br = null;
		
		//var para escribir en el archivo que quieras;
		BufferedWriter bw=null;

		String[] arrayPorPuntos = null;

		String[] arrayFecha = null;

		String fechaPrecioEleg = "";
		//
		String linea = "";

		// string para escribir
		String lugar = "";

		double precio = 0;

		// mapa para la fecha
		String fecha = "";
		
		int tamanoLista=0;

		int opcion = 0;

		// try/catch para el texto
		try {

			// se asigna el texto a leer
			br = new BufferedReader(new FileReader("src/trabajo/datosTurismo"));

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


				// anadimos el valor de dia mes anyo
				fecha = arrayFecha[0] + "/" + arrayFecha[1] + "/" + arrayFecha[2];

				// se anade al viaje
				v = new Viaje(arrayPorPuntos[0], fecha, precio);

				// ponemos el dato del viaje en el arraylist hasta llegar al final del texto
				CrudViaje.listaViajes.add(v);

				// leer la siguiente linea
				linea = br.readLine();

			} // end while linea!=null

		} catch (FileNotFoundException e) {
			//
			System.out.println("El archivo indicado no existe.");
			System.out.println(e);
			
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
				
				do {
					// introducimos los valores de cada variable
					System.out.println("Introduce un lugar: ");
					lugar = sc.nextLine();

					System.out.println("Introduce una fecha (DD/MM/AAAA): ");
					fecha = sc.nextLine();

					System.out.println("Introduce un precio: ");
					precio = sc.nextDouble();

					sc.nextLine();
					
					if (Viaje.validarFecha(fecha)) {
						// Creamos un nuevo viaje
						v = new Viaje(lugar, fecha, precio);
						
						//comprobamos si el viaje se ha podido anyadir
						if (CrudViaje.anniadirViaje(v)) {
							System.out.println("Viaje añadido con éxito");
						} else {
							System.out.println("No se ha podido añadir el viaje");
						}
					} else {
						System.out.println("Fecha no válida");
					}
					
				} while(Viaje.validarFecha(fecha) != true);
		
				
				
				System.out.println();
				break;
			}

			// 3 Modificar el precio o la fecha de un viaje existente, seleccionando el
			// viaje por su lugar.
			case 3: {
				
				//Indicamos el lugar
				System.out.println("Inserte el lugar a modificar");

				lugar = sc.nextLine();

				System.out.println("¿Que quieres modificar?");

				//Indicamos si quiere elegir la fecha o el precio
				System.out.println("[Fecha]\t[Precio]");

				fechaPrecioEleg = sc.nextLine();

				//Si elige la fecha, solo se modifica la fecha con la funcion ya creada
				if (fechaPrecioEleg.equalsIgnoreCase("fecha")) {
					System.out.println("Escriba la fecha a modificar");

					fecha = sc.nextLine();

					precio = 0;

					CrudViaje.modificarViajePorLugar(lugar, null, fecha);

					// y si a elegido el precio, se modifica dicho elemento
				} else if (fechaPrecioEleg.equalsIgnoreCase("precio")) {
					System.out.println("Escriba el precio a modificar");

					fecha = null;

					precio = sc.nextDouble();
					sc.nextLine();

					CrudViaje.modificarViajePorLugar(lugar, precio, null);
				} else {
					System.out.println("Opcion no valida");
				}
				break;
			}

			// 4 Eliminar un viaje existente, seleccionándolo por su lugar.
			case 4: {
				
				//Se indica el lugar para poder eliminar el viaje
				System.out.println("Inserte el lugar a modificar");
				
				lugar = sc.nextLine();
				
				//Se elimina el viaje que tiene el lugar indicado
				CrudViaje.eliminarViaje(lugar);
				
				
				break;
			}

			// 5 Guardar los cambios realizados en un archivo de texto.
			case 5: {
				
				//try catch para comprobar que el archivo existe
				 try {
					//le asignamos al BuffereedWriter la lectura para poder escribir
					bw = new BufferedWriter(new FileWriter("src/trabajo/datosTurismo"));
					
					//por cada dato dentro de la lista
					for (Viaje viaje : CrudViaje.listaViajes) {
						
						viaje.datosParaGuardar();
						
						//se escribe usando el metodo creado en Viaje
	                    bw.write(viaje.datosParaGuardar());
	                    bw.newLine();
	                    
	                }
					 
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally {
					
					try {
						bw.flush();
						bw.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
				
				
				tamanoLista=0;
				
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

		System.out.println("Gracias por su visita.");

		// cierre de scanner
		sc.close();

	}// end main

	/**
	 * metodo para el menu que imprime un menu para el programa 
	 * (no recibe ni devuelve nada)
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