package ad.hmarort.tema3.Interfaz;

import java.util.*;

import ad.hmarort.tema3.Estudiantes.Alumno;
import ad.hmarort.tema3.Estudiantes.Almacenamiento.AlmacenamientoAlumnosBin;
import ad.hmarort.tema3.Estudiantes.Almacenamiento.AlmacenamientoAlumnosCSV;
import ad.hmarort.tema3.Estudiantes.Almacenamiento.AlmacenamientoAlumnosJSON;
import ad.hmarort.tema3.Estudiantes.DatosAlumno.Estudios;
import ad.hmarort.tema3.Estudiantes.DatosAlumno.Fecha;

public class UIAuto {
    private static AlmacenamientoAlumnosBin bin = new AlmacenamientoAlumnosBin();
    private static AlmacenamientoAlumnosCSV csv = new AlmacenamientoAlumnosCSV();
    private static AlmacenamientoAlumnosJSON json = new AlmacenamientoAlumnosJSON();
    /**
     * Realizamos una funcion que recibe  unos argumentos y genera de manera automatica
     * un archivo que corresponde al tipo de documento que recibe con el nombre que tambien recibe.
     * @param args
     */
    public static void runUIAuto(String[] args) {
        if (args.length < 2) {
            System.out.println("Debe especificar el formato (CSV, JSON, SERIAL) y el nombre del archivo.");
            return;
        }

        int formato = Integer.parseInt(args[0]);
        String nombreArchivo = args[1];

        List<Alumno> estudiantes = Arrays.asList(
            new Alumno("Javier", "Llorente Pérez", new Fecha("15/05/2004"), Estudios.BACHILLERATO),
            new Alumno("Héctor", "Martín Ortega", new Fecha("10/07/2002"), Estudios.FP),
            new Alumno("David", "Ureña Montalvo", new Fecha("22/03/2005"), Estudios.UNIVERSIDAD)
        );

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
        }
    }
}