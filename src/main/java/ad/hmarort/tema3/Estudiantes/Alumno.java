package ad.hmarort.tema3.Estudiantes;

import java.io.Serializable;
import java.util.Objects;
import ad.hmarort.tema3.Estudiantes.DatosAlumno.*;

public class Alumno implements Serializable {
    private String idMatricula;
    private String nombre;
    private String apellidos;
    private Fecha fechaNacimiento;
    private Estudios estudiosPrevios;

    /**
     * Constructor por defecto
     */
    public Alumno() {
        this.nombre = "";
        this.apellidos = "";
        this.idMatricula = ""; // O algún valor por defecto
    }

    /**
     * Constructor de la clase Alumno
     * @param nombre
     * @param apellidos
     * @param fechaNacimiento
     * @param estudiosPrevios
     */
    public Alumno(String nombre, String apellidos, Fecha fechaNacimiento, Estudios estudiosPrevios) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.fechaNacimiento = fechaNacimiento;
        this.estudiosPrevios = estudiosPrevios;
        this.idMatricula = IDAlumno.generarID(nombre, apellidos, fechaNacimiento.toString());
    }

    /**
     * getterId
     * @return
     */
    public String getIdMatricula() {
        return idMatricula;
    }
    /**
     * setterId
     * @param idMatricula
     */
    public void setIdMatricula(String idMatricula) {
        this.idMatricula = idMatricula;
    }
    /**
     * 
     * @return
     */
    public String getNombre() {
        return nombre;
    }
    /**
     * 
     * @param nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    /**
     * 
     * @return
     */
    public String getApellidos() {
        return apellidos;
    }
    /**
     * 
     * @param apellidos
     */
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }
    /**
     * 
     * @return
     */
    public Fecha getFechaNacimiento() {
        return fechaNacimiento;
    }
    /**
     * 
     * @param fechaNacimiento
     */
    public void setFechaNacimiento(Fecha fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }
    /**
     * 
     * @return
     */
    public Estudios getEstudiosPrevios() {
        return estudiosPrevios;
    }
    /**
     * 
     * @param estudiosPrevios
     */
    public void setEstudiosPrevios(Estudios estudiosPrevios) {
        this.estudiosPrevios = estudiosPrevios;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || !(obj instanceof Alumno)) return false;
        Alumno otroAlumno = (Alumno) obj;
        return Objects.equals(idMatricula, otroAlumno.idMatricula);
    }

    @Override
    public String toString() {
        return "Alumno{" +
                "idMatricula='" + idMatricula + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", fechaNacimiento=" + fechaNacimiento +
                ", estudiosPrevios=" + estudiosPrevios +
                '}';
    }
}