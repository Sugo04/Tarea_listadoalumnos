package ad.hmarort.tema3.Interfaz;

import java.util.*;
import ad.hmarort.tema3.Estudiantes.Alumno;
import ad.hmarort.tema3.Estudiantes.Almacenamiento.AlmacenamientoFactory;
import ad.hmarort.tema3.Estudiantes.Almacenamiento.Almacenamiento;
import ad.hmarort.tema3.Estudiantes.DatosAlumno.Estudios;
import ad.hmarort.tema3.Estudiantes.DatosAlumno.Fecha;

public class UIManualImpl implements UI {
    private List<Alumno> estudiantes;
    private Scanner scanner;
    private AlmacenamientoFactory almacenamientoFactory;

    public UIManualImpl() {
        this.estudiantes = new ArrayList<>();
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void run(String[] args) {
        try {
            procesarDatos();
            String formato = seleccionarFormato();
            String nombreArchivo = solicitarNombreArchivo();
            guardarDatos(formato, nombreArchivo);
            mostrarResultados();
        } finally {
            scanner.close();
        }
    }

    @Override
    public void procesarDatos() {
        System.out.println("Ingrese la cantidad de alumnos a registrar:");
        int numAlumnos = leerNumero(scanner);
        
        for (int i = 0; i < numAlumnos; i++) {
            System.out.println("\nIngresando datos del alumno " + (i + 1) + ":");
            Alumno alumno = crearAlumno();
            estudiantes.add(alumno);
        }
    }

    @Override
    public void guardarDatos(String formato, String nombreArchivo) {
        try {
            almacenamientoFactory = new AlmacenamientoFactory(formato);
            Almacenamiento almacenamiento = almacenamientoFactory.crearSalida();
            almacenamiento.guardarAlumnos(estudiantes, nombreArchivo);
            System.out.println("Datos guardados correctamente en " + nombreArchivo);
        } catch (Exception e) {
            System.out.println("Error al guardar los datos: " + e.getMessage());
        }
    }

    @Override
    public void mostrarResultados() {
        System.out.println("\nResumen de alumnos registrados:");
        for (int i = 0; i < estudiantes.size(); i++) {
            System.out.println("\nAlumno " + (i + 1) + ":");
            System.out.println(estudiantes.get(i));
        }
    }

    private Alumno crearAlumno() {
        String nombre = leerDatoString("Nombre");
        String apellidos = leerDatoString("Apellidos");
        Fecha fechaNacimiento = leerFecha();
        Estudios estudios = seleccionarEstudios();

        return new Alumno(nombre, apellidos, fechaNacimiento, estudios);
    }

    // Métodos auxiliares existentes...
    private String leerDatoString(String mensaje) {
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

    private int leerNumero(Scanner scanner) {
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

    private String seleccionarFormato() {
        System.out.println("\nSeleccione el formato de salida:");
        System.out.println("1. CSV");
        System.out.println("2. JSON");
        System.out.println("3. XML");
        System.out.println("4. BINARIO");
        
        int opcion = -1;
        while (opcion < 1 || opcion > 4) {
            opcion = leerNumero(scanner);
        }
        
        switch (opcion) {
            case 1: return "CSV";
            case 2: return "JSON";
            case 3: return "XML";
            case 4: return "BIN";
            default: return "CSV";
        }
    }

    private String solicitarNombreArchivo() {
        System.out.println("\nIngrese el nombre del archivo para guardar:");
        return scanner.nextLine();
    }

    private Fecha leerFecha() {
        Fecha fecha = null;
        while (fecha == null) {
            try {
                String fechaStr = leerDatoString("Fecha de nacimiento (dd/MM/yyyy)");
                fecha = new Fecha(fechaStr);
            } catch (Exception e) {
                System.out.println("Error: Formato de fecha incorrecto. Use dd/MM/yyyy");
            }
        }
        return fecha;
    }

    private Estudios seleccionarEstudios() {
        System.out.println("\nSeleccione los estudios previos:");
        System.out.println("1. PRIMARIA");
        System.out.println("2. SECUNDARIA");
        System.out.println("3. BACHILLERATO");
        System.out.println("4. FP");
        System.out.println("5. UNIVERSIDAD");

        int opcion = -1;
        while (opcion < 1 || opcion > 5) {
            opcion = leerNumero(scanner);
        }

        return switch (opcion) {
            case 1 -> Estudios.PRIMARIA;
            case 2 -> Estudios.SECUNDARIA;
            case 3 -> Estudios.BACHILLERATO;
            case 4 -> Estudios.FP;
            case 5 -> Estudios.UNIVERSIDAD;
            default -> throw new IllegalArgumentException("Opción inválida");
        };
    }
}