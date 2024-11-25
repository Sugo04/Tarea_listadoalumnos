package ad.hmarort.tema3.Interfaz;

public interface UI {
    void run(String[] args);
    void procesarDatos();
    void guardarDatos(String formato, String nombreArchivo);
    void mostrarResultados();
}