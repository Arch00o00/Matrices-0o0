package controlador;
import modelo.*;
import vista.VistaColocacionBarcos;
import vista.VistaTablero;
import java.util.List;
import java.util.Random;

public class ControladorColocacion {
    private VistaColocacionBarcos vistaColocacion;
    private VistaTablero vistaTablero;
    private Random random;
    
    public ControladorColocacion(VistaColocacionBarcos vistaColocacion, VistaTablero vistaTablero) {
        this.vistaColocacion = vistaColocacion;
        this.vistaTablero = vistaTablero;
        this.random = new Random();
    }
    
    public void colocarFlotaJugador(Jugador jugador) {
        vistaColocacion.mostrarInstruccionesColocacion();
        
        while (true) {
            vistaColocacion.mostrarMenuColocacion();
            int opcion = vistaColocacion.solicitarOpcionColocacion();
            
            switch (opcion) {
                case 1:
                    colocarFlotaManual(jugador);
                    return;
                case 2:
                    colocarFlotaAutomatica(jugador);
                    return;
                case 3:
                    return; 
                default:
                    System.out.println(" Opción inválida. Intente nuevamente.");
            }
        }
    }
    
    private void colocarFlotaManual(Jugador jugador) {
        List<Barco> flota = jugador.obtenerFlota();
        int barcosColocados = 0;
        
        System.out.println("\nCOLOCACIÓN MANUAL INICIADA");
        System.out.println("Jugador: " + jugador.getNombre());
        
        for (Barco barco : flota) {
            boolean colocado = false;
            
            while (!colocado) {
                vistaTablero.mostrarTableroPropio(jugador.getTableroPropio());
                vistaTablero.mostrarProgresoColocacion(barcosColocados, flota.size());
                
                colocado = colocarBarcoIndividual(jugador, barco);
                
                if (colocado) {
                    barcosColocados++;
                    System.out.println(" " + barco.getNombre() + " colocado exitosamente!");
                } else {
                    System.out.println(" No se pudo colocar el barco. Intente nuevamente.");
                }
            }
        }
        vistaTablero.mostrarTableroPropio(jugador.getTableroPropio());
        vistaColocacion.mostrarResumenColocacion(flota);
    }
    
    private void colocarFlotaAutomatica(Jugador jugador) {
        List<Barco> flota = jugador.obtenerFlota();
        int barcosColocados = 0;
        
        System.out.println("\n COLOCACIÓN AUTOMÁTICA INICIADA");
        System.out.println("Jugador: " + jugador.getNombre());
        
        for (Barco barco : flota) {
            boolean colocado = false;
            int intentos = 0;
            int maxIntentos = 100; 
            
            vistaColocacion.mostrarProgresoAutomatico("Colocando " + barco.getNombre() + "...");
            
            while (!colocado && intentos < maxIntentos) {
                int x = random.nextInt(10);
                int y = random.nextInt(10);
                Orientacion orientacion = random.nextBoolean() ? 
                    Orientacion.HORIZONTAL : Orientacion.VERTICAL;
                
                if (validarColocacion(jugador.getTableroPropio(), barco, x, y, orientacion)) {
                    if (jugador.colocarBarco(barco, x, y, orientacion)) {
                        colocado = true;
                        barcosColocados++;
                        System.out.println(" LISTO " + barco.getNombre() + " colocado automáticamente");
                    }
                }
                
                intentos++;
            }
            
            if (!colocado) {
                System.out.println("❌ Error: No se pudo colocar " + barco.getNombre() + " automáticamente");
                colocarBarcoIndividual(jugador, barco);
                barcosColocados++;
            }
        }
        
        vistaTablero.mostrarTableroPropio(jugador.getTableroPropio());
        vistaColocacion.mostrarResumenColocacion(flota);
    }
    
    public boolean colocarBarcoIndividual(Jugador jugador, Barco barco) {
        while (true) {
            int[] posicion = vistaColocacion.solicitarPosicionBarco(barco);
            int x = posicion[0];
            int y = posicion[1];
            
            Orientacion orientacion = vistaColocacion.solicitarOrientacion();
            
            if (!validarColocacion(jugador.getTableroPropio(), barco, x, y, orientacion)) {
                continue; 
            }
            
            if (vistaColocacion.confirmarColocacion(barco, x, y, orientacion)) {
                return jugador.colocarBarco(barco, x, y, orientacion);
            } else {
                System.out.println("Colocación cancelada. Intente nuevamente.");
            }
        }
    }
    public boolean validarColocacion(Tablero tablero, Barco barco, int x, int y, Orientacion orientacion) {
        if (!tablero.validarPosicion(x, y)) {
            vistaColocacion.mostrarErrorColocacion("Posición inicial fuera del tablero");
            return false;
        }
        
        int longitud = barco.obtenerLongitud();
        
        if (orientacion == Orientacion.HORIZONTAL) {
            if (y + longitud > 10) {
                vistaColocacion.mostrarErrorColocacion("El barco se sale del tablero horizontalmente");
                return false;
            }
        } else {
            if (x + longitud > 10) {
                vistaColocacion.mostrarErrorColocacion("El barco se sale del tablero verticalmente");
                return false;
            }
        }
        if (!tablero.verificarEspacioLibre(x, y, longitud, orientacion)) {
            vistaColocacion.mostrarErrorColocacion("Hay otro barco en esa posición");
            return false;
        }
        
        return true;
    }
    
    public void mostrarProgreso(int barcosColocados, int totalBarcos) {
        vistaTablero.mostrarProgresoColocacion(barcosColocados, totalBarcos);
    }
    
    public void reiniciarColocacion(Jugador jugador) {
        jugador = new Jugador(jugador.getNombre());
        System.out.println("Colocación reiniciada. Tablero limpio.");
    }
}
