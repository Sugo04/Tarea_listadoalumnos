package ad.hmarort.tema3.Interfaz;

import java.util.*;
import ad.hmarort.tema3.Estudiantes.Alumno;
import ad.hmarort.tema3.Estudiantes.Almacenamiento.AlmacenamientoFactory;
import ad.hmarort.tema3.Estudiantes.Almacenamiento.Almacenamiento;
import ad.hmarort.tema3.Estudiantes.DatosAlumno.Estudios;
import ad.hmarort.tema3.Estudiantes.DatosAlumno.Fecha;

public class UIAutoImpl implements UI {
    private List<Alumno> estudiantes;
    private String[] args;
    private AlmacenamientoFactory almacenamientoFactory;

    public UIAutoImpl() {
        this.estudiantes = new ArrayList<>();
    }

    @Override
    public void run(String[] args) {
        this.args = args;
        if (args.length < 2) {
            System.out.println("Error: Se requiere formato (1-4) y nombre de archivo");
            return;
        }
        
        procesarDatos();
        guardarDatos(obtenerFormato(args[0]), args[1]);
        mostrarResultados();
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
            case "1" -> "CSV";
            case "2" -> "JSON";
            case "3" -> "XML";
            case "4" -> "BIN";
            default -> throw new IllegalArgumentException("Formato no válido: " + opcion);
        };
    }
}