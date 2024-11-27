package ad.hmarort.tema3.Estudiantes.Almacenamiento;

import java.util.ArrayList;
import java.util.List;
/**
 * Clase de almacenamiento de alumnos abstracta
 */
import ad.hmarort.tema3.Estudiantes.Alumno;

public abstract class AlmacenamientoAlumnos {
    private List<Alumno> listaEstudiantes;

    /**
     * constructor por defecto de las clases Almacenamiento alumnos
     */
    public AlmacenamientoAlumnos() {
        this.listaEstudiantes = new ArrayList<>();
    }

    /**
     * Agregamos un alumno a la lista de alumnos
     * @param alumno
     */
    public void agregarEstudiante(Alumno alumno) {
        listaEstudiantes.add(alumno);
    }

    /**
     * Devuelve la lista de alumnos
     */
    public List<Alumno> obtenerEstudiantes() {
        return listaEstudiantes;
    }
}
