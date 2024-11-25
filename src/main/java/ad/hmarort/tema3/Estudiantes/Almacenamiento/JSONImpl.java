package ad.hmarort.tema3.Estudiantes.Almacenamiento;

import java.io.*;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;

import ad.hmarort.tema3.Estudiantes.Alumno;

public class JSONImpl extends AlmacenamientoAlumnos implements Almacenamiento {
    private final ObjectMapper objectMapper;

    public JSONImpl() {
        objectMapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        objectMapper.registerModule(module);
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
    }

    @Override
    public void guardarAlumnos(List<Alumno> lista, String archivo) {
        try {
            objectMapper.writeValue(new File(archivo), lista);
        } catch (IOException e) {
            throw new RuntimeException("Error al guardar alumnos en formato JSON: " + e.getMessage());
        }
    }

    @Override
    public void escribirFormato(OutputStream st, List<Alumno> lista) throws IOException {
        try {
            objectMapper.writeValue(st, lista);
            st.flush();
        } catch (IOException e) {
            throw new IOException("Error al escribir en formato JSON: " + e.getMessage());
        }
    }

    /**
     * Método auxiliar para cargar alumnos desde un archivo JSON
     */
    public List<Alumno> cargarAlumnos(String archivo) throws IOException {
        try {
            return objectMapper.readValue(
                new File(archivo),
                objectMapper.getTypeFactory().constructCollectionType(List.class, Alumno.class)
            );
        } catch (IOException e) {
            throw new IOException("Error al cargar alumnos desde JSON: " + e.getMessage());
        }
    }
}