package ad.hmarort.tema3.Estudiantes.Almacenamiento;

import java.io.*;
import java.util.List;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamWriter;

import ad.hmarort.tema3.Estudiantes.Alumno;

public class XMLImpl extends AlmacenamientoAlumnos implements Almacenamiento {

    @Override
    public void guardarAlumnos(List<Alumno> lista, String archivo) {
        try {
            XMLOutputFactory factory = XMLOutputFactory.newInstance();
            XMLStreamWriter writer = factory.createXMLStreamWriter(new FileOutputStream(archivo), "UTF-8");
            
            writer.writeStartDocument("UTF-8", "1.0");
            writer.writeStartElement("alumnos");
            
            for (Alumno alumno : lista) {
                writer.writeStartElement("alumno");
                
                writer.writeStartElement("nombre");
                writer.writeCharacters(alumno.getNombre());
                writer.writeEndElement();
                
                writer.writeStartElement("apellidos");
                writer.writeCharacters(alumno.getApellidos());
                writer.writeEndElement();
                
                writer.writeStartElement("fechaNacimiento");
                writer.writeCharacters(alumno.getFechaNacimiento().toString());
                writer.writeEndElement();
                
                writer.writeStartElement("estudios");
                writer.writeCharacters(alumno.getEstudiosPrevios().toString());
                writer.writeEndElement();
                
                writer.writeEndElement(); // fin alumno
            }
            
            writer.writeEndElement(); // fin alumnos
            writer.writeEndDocument();
            writer.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void escribirFormato(OutputStream st, List<Alumno> lista) throws IOException {
        try {
            XMLOutputFactory factory = XMLOutputFactory.newInstance();
            XMLStreamWriter writer = factory.createXMLStreamWriter(st);
            
            writer.writeStartDocument("UTF-8", "1.0");
            writer.writeStartElement("alumnos");
            
            for (Alumno alumno : lista) {
                writer.writeStartElement("alumno");
                writer.writeStartElement("nombre");
                writer.writeCharacters(alumno.getNombre());
                writer.writeEndElement();
                writer.writeStartElement("apellidos");
                writer.writeCharacters(alumno.getApellidos());
                writer.writeEndElement();
                writer.writeStartElement("fechaNacimiento");
                writer.writeCharacters(alumno.getFechaNacimiento().toString());
                writer.writeEndElement();
                writer.writeStartElement("estudios");
                writer.writeCharacters(alumno.getEstudiosPrevios().toString());
                writer.writeEndElement();
                writer.writeEndElement();
            }
            
            writer.writeEndElement();
            writer.writeEndDocument();
            writer.close();
            
        } catch (Exception e) {
            throw new IOException("Error al escribir XML: " + e.getMessage());
        }
    }
}