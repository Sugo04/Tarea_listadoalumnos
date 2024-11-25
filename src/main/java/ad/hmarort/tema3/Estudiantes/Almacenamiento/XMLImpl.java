package ad.hmarort.tema3.Estudiantes.Almacenamiento;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import ad.hmarort.tema3.Estudiantes.Alumno;

public class XMLImpl extends AlmacenamientoAlumnos implements Almacenamiento{

    @Override
    public void guardarAlumnos(List<Alumno> lista, String archivo) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'guardarAlumnos'");
    }
    
    @Override
    public void escribirFormato(OutputStream st, List<Alumno> lista) throws IOException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'escribirFormato'");
    }

}
