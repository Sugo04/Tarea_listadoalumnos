package ad.hmarort.tema3;

import ad.hmarort.tema3.Interfaz.UI;
import ad.hmarort.tema3.Interfaz.UIFactory;

public class Main {
    public static void main(String[] args) {
        try {
            UI ui = UIFactory.crearUI(args);
            
            ui.run(args);
            
        } catch (IllegalArgumentException e) {
            System.err.println("Error: " + e.getMessage());
            mostrarAyuda();
        } catch (Exception e) {
            System.err.println("Error inesperado: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void mostrarAyuda() {
        System.out.println("\nUso del programa:");
        System.out.println("1. Modo Manual: ");
        System.out.println("   Sin argumentos");
        System.out.println("\n2. Modo Automático:");
        System.out.println("   Argumentos: <formato> <nombreArchivo>");
        System.out.println("\nDonde:");
        System.out.println("   <formato>: ");
        System.out.println("      1 - CSV");
        System.out.println("      2 - JSON");
        System.out.println("      3 - XML");
        System.out.println("      4 - BIN");
        System.out.println("   <nombreArchivo>: Nombre del archivo de salida (sin extensión)");
    }
}