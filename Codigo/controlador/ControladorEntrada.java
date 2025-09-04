
package controlador;

import modelo.*;
import vista.VistaConsola;

public class ControladorEntrada {
    private VistaConsola vistaConsola;
    
    public ControladorEntrada(VistaConsola vistaConsola) {
        this.vistaConsola = vistaConsola;
    }
    
    public int[] validarCoordenadas(String entrada) {
        if (entrada == null || entrada.trim().isEmpty()) {
            return null;
        }
        
        entrada = entrada.trim().toUpperCase();
        
        if (entrada.length() < 2) {
            return null;
        }
        
        try {
            char letra = entrada.charAt(0);
            String numero = entrada.substring(1);
            if (letra < 'A' || letra > 'J') {
                return null;
            }
            
            int x = Integer.parseInt(numero) - 1;
            int y = letra - 'A';
            
            if (x < 0 || x > 9 || y < 0 || y > 9) {
                return null;
            }
            
            return new int[]{x, y};
            
        } catch (NumberFormatException e) {
            return null;
        }
    }
    
    public int validarOpcionMenu(String entrada, int min, int max) {
        if (entrada == null || entrada.trim().isEmpty()) {
            return -1;
        }
        
        try {
            int opcion = Integer.parseInt(entrada.trim());
            
            if (opcion >= min && opcion <= max) {
                return opcion;
            } else {
                return -1;
            }
            
        } catch (NumberFormatException e) {
            return -1;
        }
    }
    
    public String validarNombreJugador(String entrada) {
        if (entrada == null || entrada.trim().isEmpty()) {
            return null;
        }
        
        String nombre = entrada.trim();
        
        if (nombre.length() < 2 || nombre.length() > 20) {
            return null;
        }
        
        if (!nombre.matches("[a-zA-Z0-9\\s]+")) {
            return null;
        }
        
        return nombre;
    }
    
    public Orientacion validarOrientacion(String entrada) {
        if (entrada == null || entrada.trim().isEmpty()) {
            return null;
        }
        
        String orientacion = entrada.trim().toUpperCase();
        
        switch (orientacion) {
            case "H":
            case "HORIZONTAL":
                return Orientacion.HORIZONTAL;
            case "V":
            case "VERTICAL":
                return Orientacion.VERTICAL;
            default:
                return null;
        }
    }
    
    public void manejarEntradaInvalida(String tipoError) {
        switch (tipoError) {
            case "coordenadas":
                vistaConsola.mostrarError("Coordenadas inválidas. Use formato A1, B5, etc. (A-J, 1-10)");
                break;
            case "menu":
                vistaConsola.mostrarError("Opción inválida. Seleccione un número de las opciones mostradas.");
                break;
            case "nombre":
                vistaConsola.mostrarError("Nombre inválido. Use 2-20 caracteres (letras, números y espacios).");
                break;
            case "orientacion":
                vistaConsola.mostrarError("Orientación inválida. Use H (horizontal) o V (vertical).");
                break;
            default:
                vistaConsola.mostrarError("Entrada inválida. Intente nuevamente.");
        }
    }
    
    public int[] solicitarCoordenadasValidas(String mensaje) {
        while (true) {
            String entrada = vistaConsola.solicitarEntrada(mensaje);
            int[] coords = validarCoordenadas(entrada);
            
            if (coords != null) {
                return coords;
            }
            
            manejarEntradaInvalida("coordenadas");
        }
    }
    public int solicitarOpcionMenuValida(String mensaje, int min, int max) {
        while (true) {
            String entrada = vistaConsola.solicitarEntrada(mensaje);
            int opcion = validarOpcionMenu(entrada, min, max);
            
            if (opcion != -1) {
                return opcion;
            }
            
            manejarEntradaInvalida("menu");
        }
    }
    
    public String solicitarNombreValido(String mensaje) {
        while (true) {
            String entrada = vistaConsola.solicitarEntrada(mensaje);
            String nombre = validarNombreJugador(entrada);
            
            if (nombre != null) {
                return nombre;
            }
            
            manejarEntradaInvalida("nombre");
        }
    }
    
    public Orientacion solicitarOrientacionValida(String mensaje) {
        while (true) {
            String entrada = vistaConsola.solicitarEntrada(mensaje);
            Orientacion orientacion = validarOrientacion(entrada);
            
            if (orientacion != null) {
                return orientacion;
            }
            
            manejarEntradaInvalida("orientacion");
        }
    }

    public boolean validarConfirmacion(String entrada) {
        if (entrada == null || entrada.trim().isEmpty()) {
            return false;
        }
        
        String respuesta = entrada.trim().toLowerCase();
        return respuesta.equals("s") || respuesta.equals("si") || 
               respuesta.equals("y") || respuesta.equals("yes");
    }
}
