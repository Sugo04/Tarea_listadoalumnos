package ad.hmarort.tema3.Estudiantes.Almacenamiento;

import java.io.*;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import ad.hmarort.tema3.Estudiantes.Alumno;

public class JSONImpl extends AlmacenamientoAlumnos implements Almacenamiento {
     private ObjectMapper objectMapper;
    /**
     * Inicializador del almacenamientoJSON
     */
    public JSONImpl() {
        // Inicializa ObjectMapper y registra el módulo para manejar LocalDate
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT); // Para formato legible en el JSON
    }

    @Override
    public void guardarAlumnos(List<Alumno> lista, String archivo) {
        try {
            objectMapper.writeValue(new File(archivo), lista);
            System.out.println("Datos guardados correctamente en " + archivo);
        } catch (IOException e) {
            System.out.println("Error al guardar los alumnos en el archivo JSON: " + e.getMessage());
        }
    }

    @Override
    public List<Alumno> cargarAlumnos(String archivo) {
        List<Alumno> listaAlumnos = null;
        try {
            listaAlumnos = objectMapper.readValue(new File(archivo), objectMapper.getTypeFactory().constructCollectionType(List.class, Alumno.class));
            System.out.println("Datos cargados correctamente desde " + archivo);
        } catch (IOException e) {
            System.out.println("Error al cargar los alumnos desde el archivo JSON: " + e.getMessage());
        }
        return listaAlumnos;
    }
}