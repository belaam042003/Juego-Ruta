package Main;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import Modelo.Juego;
import Vista.Bienvenida;

/**
 * La clase Launcher es la clase principal que inicia la aplicación.
 * Se encarga de crear y mostrar la ventana de inicio del juego.
 */
public class Launcher {
    
    /**
     * Método principal que inicia la aplicación.
     * @param args Los argumentos de la línea de comandos (no se utilizan en este caso).
     */
    public static void main(String[] args) {
        // Crear y mostrar la ventana de inicio en el hilo de interfaz de usuario
        SwingUtilities.invokeLater(() -> {
            Bienvenida ventanaInicio = new Bienvenida();
            ventanaInicio.setVisible(true);
        });

       
    
    }
    
    /**
     * Método para iniciar el juego con el número de jugadores especificado.
     */
    public static void iniciarJuego() {
        // Obtener el número de jugadores desde el campo de texto de la ventana de inicio
        try {   
            Juego.opcionesJuego(); // Llamar al método para configurar las opciones del juego
        } catch (NumberFormatException ex) {
            // Mostrar un mensaje de error si se ingresa un número inválido de jugadores
            JOptionPane.showMessageDialog(null, "Por favor, ingrese un número válido de jugadores.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
