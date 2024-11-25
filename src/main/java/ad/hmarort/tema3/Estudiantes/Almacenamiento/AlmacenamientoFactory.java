package ad.hmarort.tema3.Estudiantes.Almacenamiento;

public class AlmacenamientoFactory {
    private Almacenamiento bd;

    public AlmacenamientoFactory(String formato) throws IllegalArgumentException {
        switch (formato.toUpperCase()) {
            case "CSV":
                bd = new CSVImpl();
                break;
            case "JSON":
                bd = new JSONImpl();
                break;
            case "XML":
                bd = new XMLImpl();
                break;
            case "BIN":
            case "SERIAL":
                bd = new BinImpl();
                break;
            default:
                throw new IllegalArgumentException("Formato no soportado: " + formato);
        }
    }

    // Método para crear la instancia de salida (guardar datos)
    public Almacenamiento crearSalida() {
        return bd;
    }

    // Método para crear la instancia de entrada (cargar datos)
    public Almacenamiento crearEntrada() {
        return bd;
    }
}