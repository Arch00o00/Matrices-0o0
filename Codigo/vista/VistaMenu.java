package vista;

import java.util.Scanner;
public class VistaMenu {
    private Scanner scanner;
    
    public VistaMenu() {
        this.scanner = new Scanner(System.in);
    }
    
    public void mostrarMenuPrincipal() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("🚢 BARCOSSS - GUERRA NAVAL 🚢");
        System.out.println("=".repeat(50));
        System.out.println("1.  Nuevo Juego");
        System.out.println("2. Instrucciones");
        System.out.println("3. Estadísticas");
        System.out.println("4.  Acerca de");
        System.out.println("5.  Salir");
        System.out.println("=".repeat(50));
    }
    
    public void mostrarMenuConfiguracion() {
        System.out.println("\n" + "=".repeat(40));
        System.out.println(" CONFIGURACIÓN DEL JUEGO");
        System.out.println("=".repeat(40));
        System.out.println("1.  Colocación automática de barcos");
        System.out.println("2. Colocación manual de barcos");
        System.out.println("3.  Volver al menú principal");
        System.out.println("=".repeat(40));
    }
    
    public void mostrarInstrucciones() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println(" INSTRUCCIONES - BATTLESHIP");
        System.out.println("=".repeat(60));
        System.out.println();
        System.out.println(" OBJETIVO:");
        System.out.println("   Hundir todos los barcos del enemigo antes que él hunda los tuyos.");
        System.out.println();
        System.out.println(" FLOTA (11 barcos en total):");
        System.out.println("   • 1 Portaaviones (5 celdas)");
        System.out.println("   • 1 Acorazado (4 celdas)");
        System.out.println("   • 2 Cruceros (3 celdas cada uno)");
        System.out.println("   • 3 Destructores (2 celdas cada uno)");
        System.out.println("   • 4 Submarinos (1 celda cada uno)");
        System.out.println();
        System.out.println(" CÓMO JUGAR:");
        System.out.println("   1. Coloca tus barcos en el tablero");
        System.out.println("   2. Alterna turnos con tu oponente");
        System.out.println("   3. Dispara usando coordenadas (ej: A1, B5, J10)");
        System.out.println("   4. Observa los resultados:");
        System.out.println("      💧 Agua - No hay barco");
        System.out.println("      💥 Impacto - Golpeaste un barco");
        System.out.println("      🔥 Hundido - Destruiste un barco completo");
        System.out.println();
        System.out.println("🏆 VICTORIA:");
        System.out.println("   El primer jugador en hundir toda la flota enemiga gana.");
        System.out.println();
        System.out.println("=".repeat(60));
    }
    /** 
    public void mostrarCreditos() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("ℹ ACERCA DE BARCOSSS");
        System.out.println("=".repeat(50));
        System.out.println("Versión: 1.0");
        System.out.println(" Desarrollado en Java");
        System.out.println(" Arquitectura: MVC (Modelo-Vista-Controlador)");
        System.out.println(" Año: 2024");
        System.out.println();
        System.out.println("CARACTERÍSTICAS:");
        System.out.println("   • Matriz de objetos Celda[][]");
        System.out.println("   • Arquitectura MVC estrictamente separada");
        System.out.println("   • Juego completo de Battleship");
        System.out.println("   • Persistencia de historial de jugadas");
        System.out.println("   • Validaciones y manejo de errores");
        System.out.println("   • Interfaz de consola amigable");
        System.out.println();
        System.out.println("Basado en el clásico juego de estrategia naval");
        System.out.println("=".repeat(50));
    } */
    
    public int solicitarOpcionMenu() {
        System.out.print("\n Seleccione una opción: ");
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
    
    public boolean confirmarSalida() {
        System.out.print("\n ¿Está seguro que desea salir? (s/n): ");
        String respuesta = scanner.nextLine().trim().toLowerCase();
        return respuesta.startsWith("s");
    }
    
    public void mostrarMenuPausa() {
        System.out.println("\n" + "=".repeat(30));
        System.out.println("  JUEGO PAUSADO");
        System.out.println("=".repeat(30));
        System.out.println("1.  Continuar juego");
        System.out.println("2.  Ver estadísticas");
        System.out.println("3.  Ver historial");
        System.out.println("4.  Abandonar juego");
        System.out.println("=".repeat(30));
    }
    
    public void mostrarBienvenida() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("🌊 BIENVENIDO A LA GUERRA NAVAL 🌊");
        System.out.println("=".repeat(60));
        System.out.println("Prepárate para una épica batalla en alta mar...");
       /**  System.out.println("¿Tienes lo necesario para ser el almirante supremo?"); */
        System.out.println("=".repeat(60));
    }
    
    public void mostrarDespedida() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("⚓ ¡GRACIAS POR JUGAR BARCOSSS! ⚓");
        System.out.println("=".repeat(50));
        /**System.out.println("🌊 Que los vientos te sean favorables, almirante 🌊"); */
        System.out.println("=".repeat(50));
    }
}
