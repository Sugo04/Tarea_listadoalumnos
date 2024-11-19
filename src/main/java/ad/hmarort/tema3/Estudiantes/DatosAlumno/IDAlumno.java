package ad.hmarort.tema3.Estudiantes.DatosAlumno;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;

public class IDAlumno implements Serializable{

    /**
     * Utilizamos un hash con algoritmo MD5 para realizar un identificador de los alumnos 
     * con sus datos recopilados
     * @param nombre
     * @param apellidos
     * @param fechaNacimiento
     * @return
     */
    public static String generarID(String nombre, String apellidos, String fechaNacimiento) {
        try {
            String datos = nombre + apellidos + fechaNacimiento;
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] hash = md.digest(datos.getBytes(StandardCharsets.UTF_8));

            // Convertir el hash en una cadena hexadecimal
            StringBuilder sb = new StringBuilder();
            for (byte b : hash) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
