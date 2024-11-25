package ad.hmarort.tema3.Estudiantes.Almacenamiento;

import java.io.*;
import java.util.List;

import ad.hmarort.tema3.Estudiantes.Alumno;

public class CSVImpl extends AlmacenamientoAlumnos implements Almacenamiento {
    @Override
    public void guardarAlumnos(List<Alumno> lista, String archivo) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo))) {
            // Escribir encabezados
            writer.write("nombre,apellidos,fechaNacimiento,estudios");
            writer.newLine();

            // Escribir los datos de cada alumno en formato CSV
            for (Alumno alumno : lista) {
                writer.write(String.format("%s,%s,%s,%s",
                    alumno.getNombre(),
                    alumno.getApellidos(),
                    alumno.getFechaNacimiento(),
                    alumno.getEstudiosPrevios()));
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void escribirFormato(OutputStream st, List<Alumno> lista) throws IOException {
        OutputStreamWriter writer = new OutputStreamWriter(st);
        BufferedWriter bw = new BufferedWriter(writer);
        
        // Escribir encabezados
        bw.write("nombre,apellidos,fechaNacimiento,estudios");
        bw.newLine();

        // Escribir los datos de cada alumno
        for (Alumno alumno : lista) {
            bw.write(String.format("%s,%s,%s,%s",
                alumno.getNombre(),
                alumno.getApellidos(),
                alumno.getFechaNacimiento(),
                alumno.getEstudiosPrevios()));
            bw.newLine();
        }
        
        bw.flush();
    }
}