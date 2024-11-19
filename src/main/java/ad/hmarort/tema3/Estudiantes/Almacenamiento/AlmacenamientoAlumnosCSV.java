package ad.hmarort.tema3.Estudiantes.Almacenamiento;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import ad.hmarort.tema3.Estudiantes.Alumno;
import ad.hmarort.tema3.Estudiantes.DatosAlumno.Estudios;
import ad.hmarort.tema3.Estudiantes.DatosAlumno.Fecha;

public class AlmacenamientoAlumnosCSV extends AlmacenamientoAlumnos implements Almacenamiento {
    /**
     * Guarda la lista de alumnos en un archivo CSV.
     */
    @Override
    public void guardarAlumnos(List<Alumno> lista, String archivo) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo))) {
            // Escribir encabezados (opcional)
            writer.write("nombre,apellidos,fechaNacimiento,estudios");
            writer.newLine();

            // Escribir los datos de cada alumno en formato CSV
            for (Alumno alumno : lista) {
                writer.write(alumno.getNombre() + "," + alumno.getApellidos() + ","
                        + alumno.getFechaNacimiento() + "," + alumno.getEstudiosPrevios());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Carga la lista de alumnos desde un archivo CSV.
     */
    @Override
    public List<Alumno> cargarAlumnos(String archivo) {
        List<Alumno> lista = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
            String linea;
            boolean primeraLinea = true;

            // Leer archivo línea por línea
            while ((linea = reader.readLine()) != null) {
                // Omitir la primera línea si es el encabezado
                if (primeraLinea) {
                    primeraLinea = false;
                    continue;
                }

                String[] datos = linea.split(",");
                if (datos.length == 4) {
                    // Creamos un nuevo Alumno con datos del archivo CSV
                    String nombre = datos[0];
                    String apellidos = datos[1];
                    String fechaNacimiento = datos[2];
                    Estudios estudios = Estudios.valueOf(datos[3].toUpperCase());

                    Alumno alumno = new Alumno(nombre, apellidos, new Fecha(fechaNacimiento), estudios);
                    lista.add(alumno);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lista;
    }
}