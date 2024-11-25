package ad.hmarort.tema3.Estudiantes.Almacenamiento;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import ad.hmarort.tema3.Estudiantes.Alumno;
/**
 * Interfaz que se implementa en AlmacenamientoAlumnos para separar diferentes tipos de tratamiento de datos
 */
public interface Almacenamiento {
    default void guardarAlumnos(List<Alumno> lista, String archivo) throws IOException{
        try (
            OutputStream st = archivo == null?System.out:Files.newOutputStream(Path.of(archivo));
        ) {
            escribirFormato(st, lista);
        }
    }
    public void escribirFormato(OutputStream st, List<Alumno> lista) throws IOException;
}