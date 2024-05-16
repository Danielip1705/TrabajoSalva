package trabajo;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.TreeMap;
public class Principal {
	//objeto scanner
	public static Scanner sc=new Scanner(System.in);
	
	
	public static void main(String[] args) {
		
		//var tipo viaje para poder anadirle valores (PON EL CONSTRUCTOR VACIO)
		Viaje v=null;
		
		//var para lectura del archivo/escritura del archivo
		BufferedReader br = null;
		
		//
		BufferedWriter bw=null;
		
		
		String[] arrayPorPuntos = null;
		
		String[] arrayFecha=null;
		
		//
		String linea="";
		

		//string para escribir
		String lugar="";
		
		
		double precio=0;
		
		//mapa para la fecha
		TreeMap<String, Integer> fecha = new TreeMap<>();
		
		int dia=0;
		int mes=0;
		int anyo=0;
		
		boolean comprobacion=false;
		
		int opcion=0;
		
		
		//try/catch para el texto
		try {
			
			//se asigna el texto a leer
			br=new BufferedReader(new FileReader("src\\trabajo\\datosTurismo"));
			
			
			//leemos la primera linea para comprobar
			try {
				linea=br.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//while para la lectura de la linea hasta que sea null
			while(linea!=null) {
				
				
				//hacemos split de la linea del texto
				arrayPorPuntos=linea.split("::");
				
				//hacemos split para guardar la fecha
				arrayFecha=arrayPorPuntos[1].split("/");
				
				//pasamos el valor a double
				precio=Double.parseDouble(arrayPorPuntos[2]);
				
				dia=Integer.parseInt(arrayFecha[0]);
				mes=Integer.parseInt(arrayFecha[1]);
				anyo=Integer.parseInt(arrayFecha[2]);
				
				
				//anadimos el valor de dia mes anyo
				fecha.put("dia", dia);
				fecha.put("mes", mes);
				fecha.put("anio", anyo);
				
				
				//se anade al viaje
				v=new Viaje(arrayPorPuntos[0], fecha, precio);
				
				//ponemos el dato del viaje en el arraylist hasta llegar al final del texto
				CrudViaje.listaViajes.add(v);
				
				//comprobado que son las fechas en la posicion 1
//				System.out.println(arrayPorPuntos[1]);
				
				//try catch para comprobar el readline
				try {
					//leer la siguiente linea
					linea=br.readLine();
					
					
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}//end while linea!=null
			
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//end try catch FileNotFoundException e
		
		
		
		
		
		
		
		
		//do while de opcion
		do {
			//llamamos la funcion
			menu();
			
			//elegir opcion
			opcion=sc.nextInt();
			
			//switch case para la opcion del programa
			switch(opcion) {
			
			//1 Ver un listado de todos los viajes disponibles, mostrando el lugar, la fecha y el precio de cada uno.
			case 1:{
				CrudViaje.mostrarViajes();
				
				break;
			}
			
			//2 Añadir un nuevo viaje especificando el lugar, la fecha (en formato DD/MM/AAAA) y el precio.
			case 2:{

				//introducimos los valores de cada variable
				
				System.out.println("Introduce un lugar: ");
				lugar=sc.next();

				System.out.println("Introduce un dia: ");
				dia=sc.nextInt();
				
				System.out.println("Introduce un mes: ");
				mes=sc.nextInt();
				
				System.out.println("Introduce un anyo: ");
				anyo=sc.nextInt();
				
				System.out.println("Introduce un precio: ");
				precio=sc.nextDouble();
				

				//anadimos el valor de dia mes anyo
				fecha.put("dia", dia);
				fecha.put("mes", mes);
				fecha.put("anio", anyo);
				

                // Creamos un nuevo viaje
                v = new Viaje(lugar, fecha, precio);
                
                
                
                // Añadimos el viaje a la lista
                if(CrudViaje.anniadirViaje(v)) {

                    
                    System.out.println("Viaje anadido con éxito.");
    				
                }
                else {

    				System.out.println("El viaje no se ha podido anadir");
                }
				
				break;
			}
			
			//3 Modificar el precio o la fecha de un viaje existente, seleccionando el viaje por su lugar.
			case 3:{

                System.out.println("Introduce el lugar del viaje que deseas modificar: ");
                lugar = sc.next();
                
                //restablecemos el valor a null de la variable de los viajes
                v = null;
                
                //comprobamos si el viaje existe
                
                
                //recorremos mediante foreach la lista de viajes hasta encontrar la que tiene el lugar (el codigo puede ser mejorado, no es la manera mas optima)
                for (Viaje viaje : CrudViaje.listaViajes) {
                    if (viaje.getLugar().equalsIgnoreCase(lugar)) {
                        v = viaje;
                        break;
                    }
                }
                
                

				


                // Creamos un nuevo viaje
                v = new Viaje(lugar, fecha, precio);
                
                for(Viaje viaje: CrudViaje.listaViajes) {
                	
                	if (viaje.equals(v)) {
                		
                		System.out.println("Que dato quieres modificar?");
                		System.out.println("1: Fecha. 2: Precio");
        				//introducimos los valores de cada variable
                		switch(opcion) {
                		case 1:{

            				System.out.println("Introduce un dia: ");
            				dia=sc.nextInt();
            				
            				System.out.println("Introduce un mes: ");
            				mes=sc.nextInt();
            				
            				System.out.println("Introduce un anyo: ");
            				anyo=sc.nextInt();
            				

            				//anadimos el valor de dia mes anyo
            				fecha.put("dia", dia);
            				fecha.put("mes", mes);
            				fecha.put("anio", anyo);

                    		
                            viaje.setFecha(fecha);
            				
                            //funcion de modificar viaje
                            
                			break;
                		}
                		case 2:{

            				
            				System.out.println("Introduce un precio: ");
            				precio=sc.nextDouble();
                            viaje.setPrecio(precio);
                			
                            //funcion de modificar viaje
                            
                			break;
                		}
                		
                		}
                        

                    }
                }
                
                	
				
				break;
			}
			
			//4 Eliminar un viaje existente, seleccionándolo por su lugar.
			case 4:{
				

                System.out.println("Introduce el lugar del viaje que deseas modificar: ");
                lugar = sc.next();
                
                v=new Viaje(lugar, fecha, precio);
                
				for (Viaje viaje: CrudViaje.listaViajes) {
					//si el viaje es igual que v
					if(viaje.equals(v)) {
						//funcion de eliminar mediante el lugar escrito
					}
					
				}
				
				
				break;
			}
			
			//5 Guardar los cambios realizados en un archivo de texto.
			case 5:{
				
				try {
                    bw = new BufferedWriter(new FileWriter("src\\trabajo\\datosTurismo"));
                    for (Viaje viaje : CrudViaje.listaViajes) {
                        fecha = viaje.getFecha();
                        bw.write(viaje.getLugar() + "::" +
                                 fecha.get("dia") + "/" + fecha.get("mes") + "/" + fecha.get("anio") + "::" +
                                 viaje.getPrecio());
                        bw.newLine();
                    }
                    System.out.println("Cambios guardados con éxito.");
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        if (bw != null) bw.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
				
				break;
			}
			//6 Salir del programa.
			case 6:{
				
				System.out.println("Saliendo...");

				break;
			}
			
			// Opcion fuera de 1 a 6
			default:{
				
				System.out.println("Opcion no valida, elige otra opcion.");

				break;
			}
			
			}//end switchcase
			
			
			
			
			
			
		}while(opcion!=6);//end dowhile opcion
		
		System.out.println();
		
		//cierre de scanner
		sc.close();
		
		
		
	}//end main
	
	/**
	 * metodo para el menu que imprime un menu para el programa
	 * no recibe ni devuelve nada
	 */
	public static void menu() {
		//imprime el menu
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

