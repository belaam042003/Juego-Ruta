package Main;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import Modelo.Juego;
import Vista.Bienvenida;

public class Launcher {
    public static void main(String[] args) {
        // Crear y mostrar la ventana de inicio
        SwingUtilities.invokeLater(() -> {
            Bienvenida ventanaInicio = new Bienvenida();
            ventanaInicio.setVisible(true);
        });
    }
    // Método para iniciar el juego con el número de jugadores especificado
    public static void iniciarJuego() {
        // Obtener el número de jugadores desde1 el campo de texto
        
        try {   
            Juego.opcionesJuego();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Por favor, ingrese un número válido de jugadores.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
}
