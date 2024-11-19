package ad.hmarort.tema3.Interfaz;

import java.util.*;

import ad.hmarort.tema3.Estudiantes.Alumno;
import ad.hmarort.tema3.Estudiantes.Almacenamiento.AlmacenamientoAlumnosBin;
import ad.hmarort.tema3.Estudiantes.Almacenamiento.AlmacenamientoAlumnosCSV;
import ad.hmarort.tema3.Estudiantes.Almacenamiento.AlmacenamientoAlumnosJSON;
import ad.hmarort.tema3.Estudiantes.DatosAlumno.Estudios;
import ad.hmarort.tema3.Estudiantes.DatosAlumno.Fecha;

public class UI {
    private static AlmacenamientoAlumnosBin bin = new AlmacenamientoAlumnosBin();
    private static AlmacenamientoAlumnosCSV csv = new AlmacenamientoAlumnosCSV();
    private static AlmacenamientoAlumnosJSON json = new AlmacenamientoAlumnosJSON();
    /**
     * Funcion que muestra la interfaz de entrada manual
     */
    public static void runUI() {
        Scanner scanner = new Scanner(System.in);
        List<Alumno> estudiantes = new ArrayList<>();

        System.out.println("Ingrese la cantidad de alumnos a registrar:");
        int numAlumnos = leerNumero(scanner);
        
        for (int i = 0; i < numAlumnos; i++) {
            System.out.println("Ingresando datos del alumno " + (i + 1) + ":");
            String nombre = leerDatoString(scanner, "Nombre");
            String apellidos = leerDatoString(scanner, "Apellidos");
            
            Fecha fechaNacimiento = null;
            while (fechaNacimiento == null) {
                try {
                    String fechaStr = leerDatoString(scanner, "Fecha de nacimiento (dd/MM/yyyy)");
                    fechaNacimiento = new Fecha(fechaStr);
                } catch (Exception e) {
                    System.out.println("Error: Formato de fecha incorrecto. Por favor, use el formato dd/MM/yyyy.");
                }
            }

            Estudios estudios = seleccionarEstudios(scanner);

            Alumno alumno = new Alumno(nombre, apellidos, fechaNacimiento, estudios);
            estudiantes.add(alumno);
        }

        // Guardar los estudiantes en el formato deseado
        System.out.println("Seleccione el formato de salida (1.CSV, 2.JSON, 3.SERIAL):");
        int formato = scanner.nextInt();
        
        System.out.println("Ingrese el nombre del archivo para guardar:");
        String nombreArchivo = scanner.next();

        List<Alumno> estudiantesCargados = null;
        try {
            switch (formato) {
                case 1:
                    csv.guardarAlumnos(estudiantes, nombreArchivo+".csv");
                    estudiantesCargados = csv.cargarAlumnos(nombreArchivo+".csv");
                    break;
                case 2:
                    json.guardarAlumnos(estudiantes, nombreArchivo+".json");
                    estudiantesCargados = json.cargarAlumnos(nombreArchivo+".json");
                    break;
                case 3:
                    bin.guardarAlumnos(estudiantes, nombreArchivo+".bin");
                    estudiantesCargados = bin.cargarAlumnos(nombreArchivo+".bin");
                    break;
                default:
                    System.out.println("Formato no válido.");
                    return;
            }
            System.out.println("Datos guardados correctamente en " + formato);

            if (estudiantes.equals(estudiantesCargados)) {
                System.out.println("Las listas de alumnos son iguales.");
            } else {
                System.out.println("Las listas de alumnos NO son iguales.");
            }
            
        } catch (Exception e) {
            System.out.println("Error al guardar o cargar el archivo: " + e.getMessage());
        } finally {
            scanner.close(); // Cerrar el scanner para evitar fugas de recursos
        }
    }

    /**
     * Comprobamos con esta funcion que la cadena sea válida al no esta vacia (me da igual que sea un solo espacio el nombre)
     * @param scanner
     * @param mensaje
     * @return
     */
    private static String leerDatoString(Scanner scanner, String mensaje) {
        String input = "";
        while (input.trim().isEmpty()) {
            System.out.print(mensaje + ": ");
            input = scanner.nextLine();
            if (input.trim().isEmpty()) {
                System.out.println("Error: El campo " + mensaje + " no puede estar vacío.");
            }
        }
        return input;
    }

    /**
     * Recibimos un scanner para comprobar que la entrada del número sea válida.
     * @param scanner
     * @return
     */
    private static int leerNumero(Scanner scanner) {
        int numero = -1;
        while (numero <= 0) {
            try {
                System.out.print("Número: ");
                numero = Integer.parseInt(scanner.nextLine());
                if (numero <= 0) {
                    System.out.println("Error: El número debe ser mayor que 0.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Debe ingresar un número válido.");
            }
        }
        return numero;
    }

    /**
     * Recibimos un scanner para poder ingresar el numero correspondiente al estudio correspondiente
     * @param scanner
     * @return
     */
    public static Estudios seleccionarEstudios(Scanner scanner) {
        System.out.println("Selecciona los estudios previos:");
        System.out.println("1. PRIMARIA");
        System.out.println("2. SECUNDARIA");
        System.out.println("3. BACHILLERATO");
        System.out.println("4. FP");
        System.out.println("5. UNIVERSIDAD");

        int opcion = -1;
        while (opcion < 1 || opcion > 5) {
            System.out.print("Elige una opción (1-5): ");
            opcion = scanner.nextInt();
            scanner.nextLine();
        }

        switch (opcion) {
            case 1:
                return Estudios.PRIMARIA;
            case 2:
                return Estudios.SECUNDARIA;
            case 3:
                return Estudios.BACHILLERATO;
            case 4:
                return Estudios.FP;
            case 5:
                return Estudios.UNIVERSIDAD;
            default:
                throw new IllegalArgumentException("Opción inválida");
        }
    }
}
