package ad.hmarort.tema3.Estudiantes.DatosAlumno;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Fecha implements Serializable {
    private LocalDate fecha;

    /**
     * constructor por defecto
     */
    public Fecha() {
        this.fecha = LocalDate.now();
    }

    /**
     * Damos y obligamos a que la fecha tenga el formato adecuado
     * @param fecha
     */
    public Fecha(String fecha) {
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        this.fecha = LocalDate.parse(fecha, formato);
    }

    /**
     * Devolvemos la fecha formateada
     * @return
     */
    public LocalDate getFecha() {
        return fecha;
    }

    @Override
    public String toString() {
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return fecha.format(formato);
    }
}