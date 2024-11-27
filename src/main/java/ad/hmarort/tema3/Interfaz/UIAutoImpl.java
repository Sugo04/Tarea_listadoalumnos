package ad.hmarort.tema3.Interfaz;

import java.util.*;
import ad.hmarort.tema3.Estudiantes.Alumno;
import ad.hmarort.tema3.Estudiantes.Almacenamiento.AlmacenamientoFactory;
import ad.hmarort.tema3.Estudiantes.Almacenamiento.Almacenamiento;
import ad.hmarort.tema3.Estudiantes.DatosAlumno.Estudios;
import ad.hmarort.tema3.Estudiantes.DatosAlumno.Fecha;

public class UIAutoImpl implements UI {
    private List<Alumno> estudiantes;
    private List<Alumno> estudiantesCargados;
    private String[] args;
    private AlmacenamientoFactory almacenamientoFactory;

    public UIAutoImpl() {
        this.estudiantes = new ArrayList<>();
        this.estudiantesCargados = new ArrayList<>();
    }

    @Override
    public void run(String[] args) {
        this.args = args;
        if (args.length < 2) {
            System.out.println("Error: Se requiere formato (1-4) y nombre de archivo");
            return;
        }
        
        procesarDatos();
        String formato = obtenerFormato(args[0]);
        guardarDatos(formato, args[1]);
        cargarDatos(formato, args[1]);
        mostrarResultados();
        compararListas();
    }

    @Override
    public void procesarDatos() {
        // Datos de ejemplo predefinidos
        estudiantes.addAll(Arrays.asList(
            new Alumno("Juan", "Pérez García", new Fecha("15/05/2000"), Estudios.BACHILLERATO),
            new Alumno("María", "López Sánchez", new Fecha("22/07/1999"), Estudios.FP),
            new Alumno("Carlos", "González Ruiz", new Fecha("10/03/2001"), Estudios.UNIVERSIDAD)
        ));
    }

    @Override
    public void guardarDatos(String formato, String nombreArchivo) {
        try {
            almacenamientoFactory = new AlmacenamientoFactory(formato);
            Almacenamiento almacenamiento = almacenamientoFactory.crearSalida();
            
            // Añadir extensión según formato
            String nombreCompleto = nombreArchivo + "." + formato.toLowerCase();
            almacenamiento.guardarAlumnos(estudiantes, nombreCompleto);
            
            System.out.println("Datos guardados correctamente en " + nombreCompleto);
        } catch (Exception e) {
            System.out.println("Error al guardar los datos: " + e.getMessage());
        }
    }

    @Override
    public void mostrarResultados() {
        System.out.println("\nResumen de alumnos guardados:");
        estudiantes.forEach(System.out::println);
    }

    private String obtenerFormato(String opcion) {
        return switch (opcion) {
            case "csv" -> "CSV";
            case "json" -> "JSON";
            case "xml" -> "XML";
            case "binario" -> "BIN";
            default -> throw new IllegalArgumentException("Formato no válido: " + opcion);
        };
    }

    // Cargar los datos desde el archivo guardado
    private void cargarDatos(String formato, String nombreArchivo) {
        try {
            almacenamientoFactory = new AlmacenamientoFactory(formato);
            Almacenamiento almacenamiento = almacenamientoFactory.crearEntrada();
            
            // Añadir extensión según formato
            String nombreCompleto = nombreArchivo + "." + formato.toLowerCase();
            estudiantesCargados = almacenamiento.cargarAlumnos(nombreCompleto);
            System.out.println("Datos cargados correctamente desde " + nombreCompleto);
        } catch (Exception e) {
            System.out.println("Error al cargar los datos: " + e.getMessage());
        }
    }

    // Comparar las listas de estudiantes
    private void compararListas() {
        if (estudiantes.equals(estudiantesCargados)) {
            System.out.println("Las listas de alumnos son iguales.");
        } else {
            System.out.println("Las listas de alumnos NO son iguales.");
        }
    }
}