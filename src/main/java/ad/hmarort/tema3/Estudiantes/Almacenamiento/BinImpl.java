package ad.hmarort.tema3.Estudiantes.Almacenamiento;
import java.io.*;
import java.util.*;

import ad.hmarort.tema3.Estudiantes.Alumno;

public class BinImpl extends AlmacenamientoAlumnos implements Almacenamiento {
    /**
     * Guarda la lista de alumnos en un archivo binario con la clase ObjectOutputStream.
     */
    @Override
    public void guardarAlumnos(List<Alumno> lista, String archivo) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivo))) {
            oos.writeObject(lista);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Carga la lista de alumnos desde un archivo binario con la clase ObjectInputStream
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<Alumno> cargarAlumnos(String archivo) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo))) {
            return (List<Alumno>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
}