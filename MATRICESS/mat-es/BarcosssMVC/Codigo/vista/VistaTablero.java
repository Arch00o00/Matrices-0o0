
package vista;

import modelo.*;

public class VistaTablero {
    
    public void mostrarTableroPropio(Tablero tablero) {
        System.out.println("\n TU TABLERO:");
        dibujarTablero(tablero.obtenerEstadoTablero(), true);
    }
    
    public void mostrarTableroEnemigo(Tablero tablero) {
        System.out.println("\n TABLERO ENEMIGO:");
        dibujarTablero(tablero.obtenerEstadoTablero(), false);
    }
    
    public void mostrarAmbosTableros(Tablero propio, Tablero enemigo) {
        System.out.println("\n TU TABLERO                    TABLERO ENEMIGO");
        
        Celda[][] tableroPropio = propio.obtenerEstadoTablero();
        Celda[][] tableroEnemigo = enemigo.obtenerEstadoTablero();
        
        System.out.print("   A B C D E F G H I J           ");
        System.out.println("   A B C D E F G H I J");
        
        for (int i = 0; i < 10; i++) {
            System.out.printf("%2d ", i + 1);
            for (int j = 0; j < 10; j++) {
                System.out.print(obtenerSimboloCelda(tableroPropio[i][j], true) + " ");
            }
            
            System.out.print("         ");
            
            System.out.printf("%2d ", i + 1);
            for (int j = 0; j < 10; j++) {
                System.out.print(obtenerSimboloCelda(tableroEnemigo[i][j], false) + " ");
            }
            System.out.println();
        }
        
        mostrarLeyenda();
    }
    public void dibujarTablero(Celda[][] tablero, boolean mostrarBarcos) {
        System.out.print("   ");
        for (char c = 'A'; c <= 'J'; c++) {
            System.out.print(c + " ");
        }
        System.out.println();
        for (int i = 0; i < 10; i++) {
            System.out.printf("%2d ", i + 1);
            for (int j = 0; j < 10; j++) {
                System.out.print(obtenerSimboloCelda(tablero[i][j], mostrarBarcos) + " ");
            }
            System.out.println();
        }
        
        mostrarLeyenda();
    }
    public char obtenerSimboloCelda(Celda celda, boolean mostrarBarcos) {
        EstadoCelda estado = celda.obtenerEstado();
        
        switch (estado) {
            case AGUA:
                return '~';
            case BARCO:
                return mostrarBarcos ? '■' : '~';
            case IMPACTO:
                return 'X';
            case HUNDIDO:
                return '#';
            case AGUA_DISPARADA:
                return '·';
            default:
                return '?';
        }
    }
    
    public void mostrarLeyenda() {
        System.out.println("\n LEYENDA:");
        System.out.println("~ = Agua          · = Agua disparada");
        System.out.println("■ = Barco         X = Impacto");
        System.out.println("# = Hundido");
    }
    
    public void mostrarProgresoColocacion(int barcosColocados, int totalBarcos) {
        System.out.println("\n📍 PROGRESO DE COLOCACIÓN:");
        System.out.printf("Barcos colocados: %d/%d\n", barcosColocados, totalBarcos);
        
        // Barra de progreso visual
        int progreso = (barcosColocados * 20) / totalBarcos;
        System.out.print("[");
        for (int i = 0; i < 20; i++) {
            System.out.print(i < progreso ? "█" : "░");
        }
        System.out.println("]");
    }
    
    public void mostrarInfoBarco(Barco barco) {
        System.out.println("\n🚢 " + barco.getNombre());
        System.out.println("Longitud: " + barco.obtenerLongitud() + " celdas");
        if (barco.estaHundido()) {
            System.out.println("Estado: ❌ HUNDIDO");
        } else {
            System.out.println("Estado: ✅ A flote");
            System.out.println("Impactos recibidos: " + barco.getImpactos() + "/" + barco.obtenerLongitud());
        }
    }
}
