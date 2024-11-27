package ad.hmarort.tema3.Estudiantes.Almacenamiento;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator;

import ad.hmarort.tema3.Estudiantes.Alumno;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class XMLImpl extends AlmacenamientoAlumnos implements Almacenamiento {

    private final ObjectMapper xmlMapper;

    // Tipo genérico para listas de Alumno
    private static final TypeReference<List<Alumno>> TIPO_LISTA_ALUMNO = new TypeReference<>() {};

    public XMLImpl() {
        // Configuración del XmlMapper
        this.xmlMapper = new XmlMapper()
                .configure(ToXmlGenerator.Feature.WRITE_XML_DECLARATION, true)
                .enable(SerializationFeature.INDENT_OUTPUT); // Salida bonita
    }

    @Override
    public void guardarAlumnos(List<Alumno> lista, String archivo) {
        Path ruta = Path.of(archivo);

        try (
                OutputStream st1 = Files.newOutputStream(ruta);
                OutputStreamWriter writer = new OutputStreamWriter(st1, StandardCharsets.UTF_8);) {
                xmlMapper.writeValue(writer, lista);
        } catch (IOException err) {
            err.printStackTrace();
            System.exit(1);
        }
    }

    @Override
    public List<Alumno> cargarAlumnos(String archivo) {
        Path ruta = Path.of(archivo);

        try (InputStream in = Files.newInputStream(ruta)) {
            // Deserializa el XML en una lista de alumnos
            return xmlMapper.readValue(in, TIPO_LISTA_ALUMNO);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al cargar los alumnos desde el archivo XML: " + archivo, e);
        }
    }
}
