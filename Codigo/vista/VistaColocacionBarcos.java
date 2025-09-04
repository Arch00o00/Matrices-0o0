
package vista;

import modelo.*;
import java.util.List;
import java.util.Scanner;
public class VistaColocacionBarcos {
    private Scanner scanner;
    
    public VistaColocacionBarcos() {
        this.scanner = new Scanner(System.in);
    }
    
    public void mostrarFlotaDisponible(List<Barco> flota) {
        System.out.println("\n FLOTA A COLOCAR:");
        System.out.println("=".repeat(40));
        
        int contador = 1;
        for (Barco barco : flota) {
            System.out.printf("%d. %s (%d celdas)\n", 
                contador++, barco.getNombre(), barco.obtenerLongitud());
        }
        System.out.println("=".repeat(40));
    }
    
    public int[] solicitarPosicionBarco(Barco barco) {
        System.out.println("\n COLOCANDO: " + barco.toString());
        System.out.println("Ingrese la posición inicial (ej: A1, B5):");
        
        while (true) {
            System.out.print("Posición: ");
            String entrada = scanner.nextLine().trim();
            int[] coords = parsearCoordenadas(entrada);
            
            if (coords != null) {
                return coords;
            }
            
            System.out.println(" Formato inválido. Use formato como A1, B5, etc.");
        }
    }
    
    public Orientacion solicitarOrientacion() {
        while (true) {
            System.out.print("Orientación (H=Horizontal, V=Vertical): ");
            String entrada = scanner.nextLine().trim().toUpperCase();
            
            if (entrada.equals("H") || entrada.equals("HORIZONTAL")) {
                return Orientacion.HORIZONTAL;
            } else if (entrada.equals("V") || entrada.equals("VERTICAL")) {
                return Orientacion.VERTICAL;
            }
            
            System.out.println("❌ Ingrese H para horizontal o V para vertical.");
        }
    }
    
    public void mostrarErrorColocacion(String error) {
        System.out.println("❌ ERROR DE COLOCACIÓN: " + error);
        System.out.println("Intente con otra posición u orientación.");
    }
    
    public boolean confirmarColocacion(Barco barco, int x, int y, Orientacion orientacion) {
        String coordenada = convertirCoordenada(x, y);
        String orientStr = (orientacion == Orientacion.HORIZONTAL) ? "Horizontal" : "Vertical";
        
        System.out.printf("\n CONFIRMACIÓN:\n");
        System.out.printf("Barco: %s\n", barco.toString());
        System.out.printf("Posición inicial: %s\n", coordenada);
        System.out.printf("Orientación: %s\n", orientStr);
        
        System.out.print("¿Confirmar colocación? (s/n): ");
        String respuesta = scanner.nextLine().trim().toLowerCase();
        return respuesta.startsWith("s");
    }
    
    public void mostrarInstruccionesColocacion() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println(" INSTRUCCIONES DE COLOCACIÓN");
        System.out.println("=".repeat(50));
        System.out.println("• Ingrese coordenadas como A1, B5, J10, etc.");
        System.out.println("• Elija orientación: H (horizontal) o V (vertical)");
        System.out.println("• Los barcos no pueden solaparse");
        System.out.println("• Los barcos deben caber completamente en el tablero");
        System.out.println("• Use coordenadas de la esquina superior-izquierda del barco");
        System.out.println("=".repeat(50));
    }
    
    public void mostrarMenuColocacion() {
        System.out.println("\n" + "=".repeat(40));
        System.out.println(" MODO DE COLOCACIÓN");
        System.out.println("=".repeat(40));
        System.out.println("1.  Colocación manual (recomendado)");
        System.out.println("2.  Colocación automática (rápido)");
        System.out.println("3.  Volver");
        System.out.println("=".repeat(40));
    }

    public int solicitarOpcionColocacion() {
        System.out.print("👉 Seleccione modo de colocación: ");
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
    public void mostrarProgresoAutomatico(String mensaje) {
        System.out.println("🎲 " + mensaje);
    }
   
    public void mostrarResumenColocacion(List<Barco> flota) {
        System.out.println("\n ¡COLOCACIÓN COMPLETADA!");
        System.out.println("=".repeat(30));
        System.out.println("Barcos colocados:");
        
        for (Barco barco : flota) {
            System.out.println("• " + barco.toString());
        }
        
        System.out.println("=".repeat(30));
        System.out.println("¡Listo para la batalla! ⚔️");
    }
    
    private int[] parsearCoordenadas(String entrada) {
        if (entrada.length() < 2) return null;
        
        try {
            char letra = Character.toUpperCase(entrada.charAt(0));
            String numero = entrada.substring(1);
            
            if (letra < 'A' || letra > 'J') return null;
            
            int x = Integer.parseInt(numero) - 1;
            int y = letra - 'A';
            
            if (x < 0 || x > 9 || y < 0 || y > 9) return null;
            
            return new int[]{x, y};
        } catch (NumberFormatException e) {
            return null;
        }
    }
    
    private String convertirCoordenada(int x, int y) {
        char letra = (char) ('A' + y);
        return letra + String.valueOf(x + 1);
    }
}
