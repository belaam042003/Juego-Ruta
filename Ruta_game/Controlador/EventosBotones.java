package Controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

/**
 * Esta clase implementa la interfaz ActionListener para manejar eventos de botones.
 */
public class EventosBotones implements ActionListener {

    // Referencia a un objeto que implementa la interfaz EventoAccion
    private EventoAccion accionListener;

    /**
     * Constructor de la clase EventosBotones.
     * @param accionListener Objeto que implementa la interfaz EventoAccion y se encarga de ejecutar acciones.
     */
    public EventosBotones(EventoAccion accionListener) {
        this.accionListener = accionListener;
    }

    /**
     * Método que se ejecuta cuando se produce un evento de acción (click) en un botón.
     * @param e Objeto ActionEvent que contiene información sobre el evento.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        // Obtener el botón que ha generado el evento
        JButton botonPresionado = (JButton) e.getSource();

        // Verificar qué botón se ha presionado y ejecutar la acción correspondiente
        if (botonPresionado.getText().equals("Jugar Carta")) {
            // Llamar al método realizarAccionB con el parámetro correspondiente para poner una carta
            accionListener.realizarAccionB(1);
        } else if (botonPresionado.getText().equals("Intercambiar Carta")) {
            // Llamar al método realizarAccionB con el parámetro correspondiente para descartar una carta
            accionListener.realizarAccionB(2);
        } else if (botonPresionado.getText().equals("Siguiente Turno")) {
            // Llamar al método realizarAccionB con el parámetro correspondiente para pasar el turno
            accionListener.realizarAccionB(6);
        }
    }
}
