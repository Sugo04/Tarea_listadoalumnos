package ad.hmarort.tema3.Interfaz;

public class UIFactory {
    /**
     * Tipos de interfaz de usuario disponibles
     */
    public enum TipoUI {
        AUTO,   // Para procesamiento automático con datos predefinidos
        MANUAL  // Para ingreso manual de datos
    }

    /**
     * Crea una instancia de UI según el tipo especificado
     * 
     * @param tipo El tipo de UI a crear (AUTO o MANUAL)
     * @return Una instancia de UI del tipo especificado
     * @throws IllegalArgumentException si el tipo no es válido
     */
    public static UI crearUI(TipoUI tipo) {
        return switch (tipo) {
            case AUTO -> new UIAutoImpl();
            case MANUAL -> new UIManualImpl();
            default -> throw new IllegalArgumentException("Tipo de UI no válido: " + tipo);
        };
    }
}