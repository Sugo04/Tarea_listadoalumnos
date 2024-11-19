package ad.hmarort.tema3.Estudiantes.Almacenamiento;
import java.util.List;

import ad.hmarort.tema3.Estudiantes.Alumno;
/**
 * Interfaz que se implementa en AlmacenamientoAlumnos para separar diferentes tipos de tratamiento de datos
 */
public interface Almacenamiento {
    void guardarAlumnos(List<Alumno> lista, String archivo);
    List<Alumno> cargarAlumnos(String archivo);
}