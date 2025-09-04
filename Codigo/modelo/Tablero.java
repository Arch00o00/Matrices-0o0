
package modelo;

import java.util.ArrayList;
import java.util.List;

public class Tablero {
    private Celda[][] tablero; 
    private int tamaño;
    private List<Barco> barcosColocados;
    
    public Tablero(int tamaño) {
        this.tamaño = tamaño;
        this.tablero = new Celda[tamaño][tamaño];
        this.barcosColocados = new ArrayList<>();
        
        for (int i = 0; i < tamaño; i++) {
            for (int j = 0; j < tamaño; j++) {
                tablero[i][j] = new Celda(i, j);
            }
        }
    }
    
    public boolean colocarBarco(Barco barco, int x, int y, Orientacion orientacion) {
        if (!validarPosicion(x, y)) {
            return false;
        }
        
        if (!verificarEspacioLibre(x, y, barco.obtenerLongitud(), orientacion)) {
            return false;
        }
        
        List<Celda> posiciones = new ArrayList<>();
        
        for (int i = 0; i < barco.obtenerLongitud(); i++) {
            int posX = x;
            int posY = y;
            
            if (orientacion == Orientacion.HORIZONTAL) {
                posY += i;
            } else {
                posX += i;
            }
            
            posiciones.add(tablero[posX][posY]);
        }
        
        barco.colocar(posiciones, orientacion);
        barcosColocados.add(barco);
        
        return true;
    }
    
    public ResultadoDisparo recibirDisparo(int x, int y) {
        if (!validarPosicion(x, y)) {
            return ResultadoDisparo.POSICION_INVALIDA;
        }
        
        return tablero[x][y].recibirDisparo();
    }
    
    public Celda obtenerCelda(int x, int y) {
        if (!validarPosicion(x, y)) {
            return null;
        }
        return tablero[x][y];
    }
    
    public boolean validarPosicion(int x, int y) {
        return x >= 0 && x < tamaño && y >= 0 && y < tamaño;
    }
    
    public boolean verificarEspacioLibre(int x, int y, int longitud, Orientacion orientacion) {
        for (int i = 0; i < longitud; i++) {
            int posX = x;
            int posY = y;
            
            if (orientacion == Orientacion.HORIZONTAL) {
                posY += i;
            } else {
                posX += i;
            }
            
            if (!validarPosicion(posX, posY) || tablero[posX][posY].estaOcupada()) {
                return false;
            }
        }
        
        return true;
    }
    
    public Celda[][] obtenerEstadoTablero() {
        return tablero;
    }
    
    public boolean todosLosBarcosHundidos() {
        for (Barco barco : barcosColocados) {
            if (!barco.estaHundido()) {
                return false;
            }
        }
        return !barcosColocados.isEmpty();
    }
    
    public int getTamaño() { return tamaño; }
    public List<Barco> getBarcosColocados() { return new ArrayList<>(barcosColocados); }
}
