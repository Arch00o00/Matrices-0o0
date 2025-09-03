
package vista;

import modelo.*;
import java.util.Scanner;
public class VistaConsola {
    private Scanner scanner;
    
    public VistaConsola() {
        this.scanner = new Scanner(System.in);
    }
   
    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }
    public void mostrarError(String error) {
        System.out.println("❌ ERROR: " + error);
    }
    public String solicitarEntrada(String prompt) {
        System.out.print(prompt + ": ");
        return scanner.nextLine().trim();
    }
    public int[] solicitarCoordenadas() {
        while (true) {
            String entrada = solicitarEntrada("Ingrese coordenadas (ej: A1, B5)");
            int[] coords = parsearCoordenadas(entrada);
            if (coords != null) {
                return coords;
            }
            mostrarError("Formato inválido. Use formato como A1, B5, etc.");
        }
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
    public void mostrarResultadoDisparo(ResultadoDisparo resultado) {
        switch (resultado) {
            case AGUA:
                mostrarMensaje("💧 ¡Agua! No hay nada ahí.");
                break;
            case IMPACTO:
                mostrarMensaje("💥 ¡IMPACTO! Has golpeado un barco.");
                break;
            case HUNDIDO:
                mostrarMensaje("🔥 ¡HUNDIDO! Has hundido un barco completo.");
                break;
            case YA_DISPARADA:
                mostrarMensaje("⚠️  Ya disparaste a esa posición.");
                break;
            case POSICION_INVALIDA:
                mostrarMensaje("❌ Posición inválida.");
                break;
        }
    }
    
    public void mostrarEstadisticas(Jugador jugador) {
        mostrarMensaje("\nESTADÍSTICAS DE " + jugador.getNombre().toUpperCase());
        mostrarMensaje("Disparos realizados: " + jugador.getDisparosRealizados());
        mostrarMensaje("Impactos logrados: " + jugador.getImpactosLogrados());
        mostrarMensaje("Precisión: " + String.format("%.1f%%", jugador.obtenerPrecision()));
    }
    
    public void limpiarPantalla() {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }
    
    public void pausar() {
        mostrarMensaje("\nPresione Enter para continuar...");
        scanner.nextLine();
    }
    public boolean confirmar(String mensaje) {
        String respuesta = solicitarEntrada(mensaje + " (s/n)");
        return respuesta.toLowerCase().startsWith("s");
    }
    
    public void cerrar() {
        scanner.close();
    }
}
