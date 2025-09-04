
package modelo;

import java.util.ArrayList;
import java.util.List;

public class Juego {
    private Jugador jugador1;
    private Jugador jugador2;
    private Jugador jugadorActual;
    private EstadoJuego estado;
    private int turno;
    private Jugador ganador;
    private List<String> historialJugadas;
    
    public Juego() {
        this.estado = EstadoJuego.CONFIGURACION;
        this.turno = 1;
        this.ganador = null;
        this.historialJugadas = new ArrayList<>();
    }
    public void iniciarJuego(String nombreJugador1, String nombreJugador2) {
        this.jugador1 = new Jugador(nombreJugador1);
        this.jugador2 = new Jugador(nombreJugador2);
        this.jugadorActual = jugador1;
        this.estado = EstadoJuego.CONFIGURACION;
        
        historialJugadas.add("=== INICIO DEL JUEGO ===");
        historialJugadas.add("Jugador 1: " + nombreJugador1);
        historialJugadas.add("Jugador 2: " + nombreJugador2);
    }
    
    public void cambiarTurno() {
        jugadorActual = (jugadorActual == jugador1) ? jugador2 : jugador1;
        turno++;
    }
    
    public ResultadoDisparo realizarDisparo(int x, int y) {
        if (estado != EstadoJuego.EN_CURSO) {
            return ResultadoDisparo.POSICION_INVALIDA;
        }
        
        Jugador enemigo = (jugadorActual == jugador1) ? jugador2 : jugador1;
        ResultadoDisparo resultado = jugadorActual.realizarDisparo(x, y, enemigo);
        
        String coordenada = convertirCoordenada(x, y);
        String jugada = String.format("Turno %d - %s dispara a %s: %s", 
            turno, jugadorActual.getNombre(), coordenada, resultado);
        historialJugadas.add(jugada);
        
        if (verificarVictoria()) {
            finalizarJuego();
        }
        
        return resultado;
    }
    
    public boolean verificarVictoria() {
        if (jugador1.todosLosBarcosHundidos()) {
            ganador = jugador2;
            return true;
        } else if (jugador2.todosLosBarcosHundidos()) {
            ganador = jugador1;
            return true;
        }
        return false;
    }
    
    public void finalizarJuego() {
        estado = EstadoJuego.FINALIZADO;
        if (ganador != null) {
            historialJugadas.add("=== FIN DEL JUEGO ===");
            historialJugadas.add("¡GANADOR: " + ganador.getNombre() + "!");
            historialJugadas.add("Estadísticas finales:");
            historialJugadas.add(jugador1.getNombre() + " - Precisión: " + 
                String.format("%.1f%%", jugador1.obtenerPrecision()));
            historialJugadas.add(jugador2.getNombre() + " - Precisión: " + 
                String.format("%.1f%%", jugador2.obtenerPrecision()));
        }
    }
    
    public void comenzarPartida() {
        if (estado == EstadoJuego.CONFIGURACION) {
            estado = EstadoJuego.EN_CURSO;
            historialJugadas.add("=== COMIENZA LA GUERRA ===");
        }
    }
    
    public void pausarReanudar() {
        if (estado == EstadoJuego.EN_CURSO) {
            estado = EstadoJuego.PAUSADO;
        } else if (estado == EstadoJuego.PAUSADO) {
            estado = EstadoJuego.EN_CURSO;
        }
    }
    
    private String convertirCoordenada(int x, int y) {
        char letra = (char) ('A' + y);
        return letra + String.valueOf(x + 1);
    }
    public Jugador obtenerJugadorEnemigo() {
        return (jugadorActual == jugador1) ? jugador2 : jugador1;
    }
    
    public Jugador obtenerJugadorActual() { return jugadorActual; }
    public EstadoJuego obtenerEstado() { return estado; }
    public Jugador getJugador1() { return jugador1; }
    public Jugador getJugador2() { return jugador2; }
    public Jugador getGanador() { return ganador; }
    public int getTurno() { return turno; }
    public List<String> getHistorialJugadas() { return new ArrayList<>(historialJugadas); }
}
