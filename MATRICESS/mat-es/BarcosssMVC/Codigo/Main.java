public class Main {
    
    public static void main(String[] args) {
        try {
            controlador.ControladorJuego controladorPrincipal = new controlador.ControladorJuego();
            
            controladorPrincipal.iniciar();
            
        } catch (Exception e) {
            System.err.println("❌ Error fatal en la aplicación:");
            System.err.println(e.getMessage());
            e.printStackTrace();
            
            System.err.println("\n🔧 Verifique que:");
            System.err.println("• Todas las clases estén compiladas correctamente");
            System.err.println("• La estructura de paquetes sea correcta");
            System.err.println("• No haya conflictos de dependencias");
            
            System.exit(1);
        }
    }
}
