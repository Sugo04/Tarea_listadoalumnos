package ad.hmarort.tema3.Estudiantes.Almacenamiento;
import java.io.*;
import java.util.*;

import ad.hmarort.tema3.Estudiantes.Alumno;

public class BinImpl extends AlmacenamientoAlumnos implements Almacenamiento {

    @Override
    public void guardarAlumnos(List<Alumno> lista, String archivo) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivo))) {
            oos.writeObject(lista);
        } catch (IOException e) {
            throw new RuntimeException("Error al guardar alumnos en formato binario: " + e.getMessage());
        }
    }

    @Override
    public void escribirFormato(OutputStream st, List<Alumno> lista) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(st)) {
            oos.writeObject(lista);
            oos.flush();
        } catch (IOException e) {
            throw new IOException("Error al escribir en formato binario: " + e.getMessage());
        }
    }

    /**
     * Método auxiliar para cargar alumnos desde un archivo binario
     */
    @SuppressWarnings("unchecked")
    public List<Alumno> cargarAlumnos(String archivo) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo))) {
            return (List<Alumno>) ois.readObject();
        }
    }
}