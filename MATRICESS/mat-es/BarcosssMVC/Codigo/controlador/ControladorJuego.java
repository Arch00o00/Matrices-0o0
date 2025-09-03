
package controlador;

import modelo.*;
import vista.*;
import java.util.List;

public class ControladorJuego {
    private Juego juego;
    private VistaConsola vistaConsola;
    private VistaTablero vistaTablero;
    private VistaMenu vistaMenu;
    private VistaColocacionBarcos vistaColocacion;
    private ControladorEntrada controladorEntrada;
    private ControladorColocacion controladorColocacion;
    
    public ControladorJuego() {
        this.vistaConsola = new VistaConsola();
        this.vistaTablero = new VistaTablero();
        this.vistaMenu = new VistaMenu();
        this.vistaColocacion = new VistaColocacionBarcos();
        
        this.controladorEntrada = new ControladorEntrada(vistaConsola);
        this.controladorColocacion = new ControladorColocacion(vistaColocacion, vistaTablero);
        
        this.juego = new Juego();
    }
    public void iniciar() {
        vistaMenu.mostrarBienvenida();
        
        boolean continuar = true;
        while (continuar) {
            vistaMenu.mostrarMenuPrincipal();
            int opcion = controladorEntrada.solicitarOpcionMenuValida("Seleccione una opción", 1, 5);
            
            switch (opcion) {
                case 1:
                    iniciarNuevoJuego();
                    break;
                case 2:
                    vistaMenu.mostrarInstrucciones();
                    vistaConsola.pausar();
                    break;
                case 3:
                    mostrarEstadisticas();
                    break;
                case 4:
                    vistaMenu.mostrarCreditos();
                    vistaConsola.pausar();
                    break;
                case 5:
                    if (vistaMenu.confirmarSalida()) {
                        continuar = false;
                    }
                    break;
            }
        }
        
        vistaMenu.mostrarDespedida();
        vistaConsola.cerrar();
    }
    
    private void iniciarNuevoJuego() {
        configurarJuego();
        
        if (juego.obtenerEstado() == EstadoJuego.CONFIGURACION) {
            ejecutarJuego();
        }
    }
    
    private void configurarJuego() {
        vistaConsola.limpiarPantalla();
        vistaConsola.mostrarMensaje(" CONFIGURACIÓN DEL NUEVO JUEGO");
        vistaConsola.mostrarMensaje("=".repeat(40));
        String nombreJugador1 = controladorEntrada.solicitarNombreValido("Nombre del Jugador 1");
        String nombreJugador2 = controladorEntrada.solicitarNombreValido("Nombre del Jugador 2");
        
        juego.iniciarJuego(nombreJugador1, nombreJugador2);
        
        vistaConsola.mostrarMensaje("\nJugadores registrados:");
        vistaConsola.mostrarMensaje(" Jugador 1: " + nombreJugador1);
        vistaConsola.mostrarMensaje(" Jugador 2: " + nombreJugador2);
        
        colocarBarcosJugadores();
        
        if (juego.obtenerEstado() == EstadoJuego.CONFIGURACION) {
            juego.comenzarPartida();
            vistaConsola.mostrarMensaje("\n ¡CONFIGURACIÓN COMPLETADA!");
            vistaConsola.mostrarMensaje("¡QUE COMIENCE LA BATALLA!");
            vistaConsola.pausar();
        }
    }
    
    private void colocarBarcosJugadores() {
        // Jugador 1
        vistaConsola.limpiarPantalla();
        vistaConsola.mostrarMensaje(" COLOCACIÓN DE BARCOS - " + juego.getJugador1().getNombre().toUpperCase());
        controladorColocacion.colocarFlotaJugador(juego.getJugador1());
        vistaConsola.pausar();
        
        // Jugador 2
        vistaConsola.limpiarPantalla();
        vistaConsola.mostrarMensaje("COLOCACIÓN DE BARCOS - " + juego.getJugador2().getNombre().toUpperCase());
        controladorColocacion.colocarFlotaJugador(juego.getJugador2());
        vistaConsola.pausar();
    }
    
    private void ejecutarJuego() {
        while (juego.obtenerEstado() == EstadoJuego.EN_CURSO) {
            ejecutarTurno();
            
            if (!verificarFinJuego()) {
                juego.cambiarTurno();
            }
        }
        
        if (juego.obtenerEstado() == EstadoJuego.FINALIZADO) {
            mostrarResultados();
        }
    }
    
    private void ejecutarTurno() {
        Jugador jugadorActual = juego.obtenerJugadorActual();
        Jugador jugadorEnemigo = juego.obtenerJugadorEnemigo();
        
        vistaConsola.limpiarPantalla();
        vistaConsola.mostrarMensaje("TURNO DE: " + jugadorActual.getNombre().toUpperCase());
        vistaConsola.mostrarMensaje("Turno #" + juego.getTurno());
        
        vistaTablero.mostrarAmbosTableros(jugadorActual.getTableroPropio(), jugadorActual.getTableroEnemigo());
        
        vistaConsola.mostrarMensaje(String.format("\nDisparos: %d | Impactos: %d | Precisión: %.1f%%",
            jugadorActual.getDisparosRealizados(),
            jugadorActual.getImpactosLogrados(),
            jugadorActual.obtenerPrecision()));
        
        procesarDisparo();
    }
    
    private void procesarDisparo() {
        while (true) {
            vistaConsola.mostrarMensaje("\n OPCIONES:");
            vistaConsola.mostrarMensaje("1.  Realizar disparo");
            vistaConsola.mostrarMensaje("2. ⏸ Pausar juego");
            vistaConsola.mostrarMensaje("3. Ver estadísticas");
            
            int opcion = controladorEntrada.solicitarOpcionMenuValida("Seleccione una opción", 1, 3);
            
            switch (opcion) {
                case 1:
                    if (realizarDisparo()) {
                        return; 
                    }
                    break;
                case 2:
                    manejarPausa();
                    if (juego.obtenerEstado() != EstadoJuego.EN_CURSO) {
                        return; 
                    }
                    break;
                case 3:
                    mostrarEstadisticasDetalladas();
                    break;
            }
        }
    }
    private boolean realizarDisparo() {
        int[] coordenadas = controladorEntrada.solicitarCoordenadasValidas("Ingrese coordenadas de disparo (ej: A1, B5)");
        int x = coordenadas[0];
        int y = coordenadas[1];
        
        ResultadoDisparo resultado = juego.realizarDisparo(x, y);
        Mostrar resultado
        vistaConsola.mostrarResultadoDisparo(resultado);
       
        if (resultado == ResultadoDisparo.YA_DISPARADA || resultado == ResultadoDisparo.POSICION_INVALIDA) {
            return false; 
        }
        
        if (resultado == ResultadoDisparo.IMPACTO || resultado == ResultadoDisparo.HUNDIDO) {
            Jugador enemigo = juego.obtenerJugadorEnemigo();
            Celda celda = enemigo.getTableroPropio().obtenerCelda(x, y);
            if (celda != null && celda.obtenerBarco() != null) {
                vistaTablero.mostrarInfoBarco(celda.obtenerBarco());
            }
        }
        
        vistaConsola.pausar();
        return true; 
    }
    
    private boolean verificarFinJuego() {
        if (juego.verificarVictoria()) {
            juego.finalizarJuego();
            return true;
        }
        return false;
    }
    
    private void mostrarResultados() {
        vistaConsola.limpiarPantalla();
        vistaConsola.mostrarMensaje(" ¡JUEGO TERMINADO! 🏆");
        vistaConsola.mostrarMensaje("=".repeat(50));
        
        Jugador ganador = juego.getGanador();
        if (ganador != null) {
            vistaConsola.mostrarMensaje(" ¡GANADOR: " + ganador.getNombre().toUpperCase() + "! ");
            vistaConsola.mostrarMensaje("=".repeat(50));
            
            vistaConsola.mostrarEstadisticas(juego.getJugador1());
            vistaConsola.mostrarEstadisticas(juego.getJugador2());
            
            vistaConsola.mostrarMensaje("\n TABLEROS FINALES:");
            vistaConsola.mostrarMensaje("\n TABLERO DE " + juego.getJugador1().getNombre().toUpperCase());
            vistaTablero.mostrarTableroPropio(juego.getJugador1().getTableroPropio());
            
            vistaConsola.mostrarMensaje("\n TABLERO DE " + juego.getJugador2().getNombre().toUpperCase());
            vistaTablero.mostrarTableroPropio(juego.getJugador2().getTableroPropio());
            
            if (vistaConsola.confirmar("¿Desea ver el historial completo de jugadas?")) {
                mostrarHistorialCompleto();
            }
        }
        
        vistaConsola.pausar();
    }
    
    private void manejarPausa() {
        juego.pausarReanudar();
        
        while (juego.obtenerEstado() == EstadoJuego.PAUSADO) {
            vistaMenu.mostrarMenuPausa();
            int opcion = controladorEntrada.solicitarOpcionMenuValida("Seleccione una opción", 1, 4);
            
            switch (opcion) {
                case 1: 
                    juego.pausarReanudar();
                    vistaConsola.mostrarMensaje("▶  Juego reanudado");
                    break;
                case 2: 
                    mostrarEstadisticasDetalladas();
                    break;
                case 3: 
                    mostrarHistorialCompleto();
                    break;
                case 4: 
                    if (vistaConsola.confirmar("¿Está seguro que desea abandonar el juego?")) {
                        juego.finalizarJuego();
                        vistaConsola.mostrarMensaje("Juego abandonado");
                        return;
                    }
                    break;
            }
        }
    }
    
    private void mostrarEstadisticas() {
        vistaConsola.mostrarMensaje("\nESTADÍSTICAS GENERALES");
        vistaConsola.mostrarMensaje("=".repeat(30));
        vistaConsola.mostrarMensaje("Esta función mostraría estadísticas globales");
        vistaConsola.mostrarMensaje("como partidas jugadas, victorias, etc.");
        vistaConsola.pausar();
    }
    
    private void mostrarEstadisticasDetalladas() {
        vistaConsola.mostrarMensaje("\n ESTADÍSTICAS DETALLADAS DEL JUEGO ACTUAL");
        vistaConsola.mostrarMensaje("=".repeat(50));
        vistaConsola.mostrarMensaje("Turno actual: " + juego.getTurno());
        vistaConsola.mostrarMensaje("Estado: " + juego.obtenerEstado());
        
        vistaConsola.mostrarEstadisticas(juego.getJugador1());
        vistaConsola.mostrarEstadisticas(juego.getJugador2());
        
        vistaConsola.pausar();
    }
    
    private void mostrarHistorialCompleto() {
        List<String> historial = juego.getHistorialJugadas();
        
        vistaConsola.mostrarMensaje("\n HISTORIAL COMPLETO DE JUGADAS");
        vistaConsola.mostrarMensaje("=".repeat(50));
        
        for (String jugada : historial) {
            vistaConsola.mostrarMensaje(jugada);
        }
        
        vistaConsola.pausar();
    }
}
