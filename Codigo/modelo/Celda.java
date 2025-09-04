
package modelo;

public class Celda {
    private int x;
    private int y;
    private EstadoCelda estado;
    private Barco barco;
    private boolean disparada;
    
    public Celda(int x, int y) {
        this.x = x;
        this.y = y;
        this.estado = EstadoCelda.AGUA;
        this.barco = null;
        this.disparada = false;
    }
    
    public void asignarBarco(Barco barco) {
        this.barco = barco;
        this.estado = EstadoCelda.BARCO;
    }
    
    public ResultadoDisparo recibirDisparo() {
        if (disparada) {
            return ResultadoDisparo.YA_DISPARADA;
        }
        
        disparada = true;
        
        if (barco == null) {
            estado = EstadoCelda.AGUA_DISPARADA;
            return ResultadoDisparo.AGUA;
        } else {
            estado = EstadoCelda.IMPACTO;
            barco.recibirImpacto();
            
            if (barco.estaHundido()) {
                for (Celda posicion : barco.obtenerPosiciones()) {
                    posicion.estado = EstadoCelda.HUNDIDO;
                }
                return ResultadoDisparo.HUNDIDO;
            } else {
                return ResultadoDisparo.IMPACTO;
            }
        }
    }
    
    public boolean estaOcupada() {
        return barco != null;
    }
    
    public boolean estaDisparada() {
        return disparada;
    }
    
    public int getX() { return x; }
    public int getY() { return y; }
    public EstadoCelda obtenerEstado() { return estado; }
    public Barco obtenerBarco() { return barco; }
}
