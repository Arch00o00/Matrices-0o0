
package modelo;
public enum TipoBarco {
    PORTAAVIONES("Portaaviones", 5),
    ACORAZADO("Acorazado", 4),
    CRUCERO("Crucero", 3),
    DESTRUCTOR("Destructor", 2),
    SUBMARINO("Submarino", 1);
    
    private final String nombre;
    private final int longitud;
    
    TipoBarco(String nombre, int longitud) {
        this.nombre = nombre;
        this.longitud = longitud;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public int getLongitud() {
        return longitud;
    }
}
