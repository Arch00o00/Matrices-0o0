
package modelo;

import java.util.ArrayList;
import java.util.List;

public class Jugador {
    private String nombre;
    private Tablero tableroPropio;
    private Tablero tableroEnemigo;
    private List<Barco> flota;
    private int disparosRealizados;
    private int impactosLogrados;
    
    public Jugador(String nombre) {
        this.nombre = nombre;
        this.tableroPropio = new Tablero(10);
        this.tableroEnemigo = new Tablero(10);
        this.flota = new ArrayList<>();
        this.disparosRealizados = 0;
        this.impactosLogrados = 0;
        
        inicializarFlota();
    }
    
    private void inicializarFlota() {
        flota.add(new Barco(TipoBarco.PORTAAVIONES));
        flota.add(new Barco(TipoBarco.ACORAZADO));
        flota.add(new Barco(TipoBarco.CRUCERO));
        flota.add(new Barco(TipoBarco.CRUCERO));
        flota.add(new Barco(TipoBarco.DESTRUCTOR));
        flota.add(new Barco(TipoBarco.DESTRUCTOR));
        flota.add(new Barco(TipoBarco.DESTRUCTOR));
        flota.add(new Barco(TipoBarco.SUBMARINO));
        flota.add(new Barco(TipoBarco.SUBMARINO));
        flota.add(new Barco(TipoBarco.SUBMARINO));
        flota.add(new Barco(TipoBarco.SUBMARINO));
    }
    
    public boolean colocarBarco(Barco barco, int x, int y, Orientacion orientacion) {
        return tableroPropio.colocarBarco(barco, x, y, orientacion);
    }
    
    public ResultadoDisparo recibirDisparo(int x, int y) {
        return tableroPropio.recibirDisparo(x, y);
    }
    
    public ResultadoDisparo realizarDisparo(int x, int y, Jugador enemigo) {
        disparosRealizados++;
        ResultadoDisparo resultado = enemigo.recibirDisparo(x, y);
        
        if (resultado == ResultadoDisparo.IMPACTO || resultado == ResultadoDisparo.HUNDIDO) {
            impactosLogrados++;
        }
        
        Celda celdaEnemiga = tableroEnemigo.obtenerCelda(x, y);
        if (celdaEnemiga != null && !celdaEnemiga.estaDisparada()) {
            tableroEnemigo.recibirDisparo(x, y);
        }
        
        return resultado;
    }
    
    public boolean todosLosBarcosHundidos() {
        return tableroPropio.todosLosBarcosHundidos();
    }
    
    public double obtenerPrecision() {
        if (disparosRealizados == 0) return 0.0;
        return (double) impactosLogrados / disparosRealizados * 100;
    }
    
    public String getNombre() { return nombre; }
    public Tablero getTableroPropio() { return tableroPropio; }
    public Tablero getTableroEnemigo() { return tableroEnemigo; }
    public List<Barco> obtenerFlota() { return new ArrayList<>(flota); }
    public int getDisparosRealizados() { return disparosRealizados; }
    public int getImpactosLogrados() { return impactosLogrados; }
    
    @Override
    public String toString() {
        return nombre;
    }
}
