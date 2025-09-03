
package modelo;

import java.util.ArrayList;
import java.util.List;
public class Barco {
    private String nombre;
    private int longitud;
    private List<Celda> posiciones;
    private Orientacion orientacion;
    private boolean hundido;
    private int impactos;
    public Barco(String nombre, int longitud) {
        this.nombre = nombre;
        this.longitud = longitud;
        this.posiciones = new ArrayList<>();
        this.hundido = false;
        this.impactos = 0;
    }
    
    public Barco(TipoBarco tipo) {
        this(tipo.getNombre(), tipo.getLongitud());
    }
    
    public void colocar(List<Celda> posiciones, Orientacion orientacion) {
        this.posiciones = new ArrayList<>(posiciones);
        this.orientacion = orientacion;
        
        for (Celda celda : posiciones) {
            celda.asignarBarco(this);
        }
    }
    
    
    public void recibirImpacto() {
        impactos++;
        if (impactos >= longitud) {
            hundido = true;
        }
    }
    
    public boolean estaHundido() {
        return hundido;
    }
    
    public String getNombre() { return nombre; }
    public int obtenerLongitud() { return longitud; }
    public List<Celda> obtenerPosiciones() { return new ArrayList<>(posiciones); }
    public Orientacion getOrientacion() { return orientacion; }
    public int getImpactos() { return impactos; }
    
    @Override
    public String toString() {
        return nombre + " (" + longitud + " celdas)";
    }
}
