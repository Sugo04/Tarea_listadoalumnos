package ad.hmarort.tema3;

import java.util.Scanner;
import ad.hmarort.tema3.Interfaz.UI;
import ad.hmarort.tema3.Interfaz.UIFactory;
import ad.hmarort.tema3.Interfaz.UIFactory.TipoUI;

public class Main {
    public static void main(String[] args) {
        try {
            UI ui = seleccionarModoUI();
            ui.run(args);
        } catch (Exception e) {
            System.err.println("Error inesperado: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static UI seleccionarModoUI() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Presione INTRO para modo automático o cualquier otra tecla + INTRO para modo manual");
        
        String input = scanner.nextLine();
        
        // Si solo presiona INTRO, la entrada estará vacía
        if (input.trim().isEmpty()) {
            System.out.println("Iniciando modo automático...");
            return UIFactory.crearUI(TipoUI.AUTO);
        } else {
            System.out.println("Iniciando modo manual...");
            return UIFactory.crearUI(TipoUI.MANUAL);
        }
    }
}