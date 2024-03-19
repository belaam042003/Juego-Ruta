package Controlador;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import Modelo.Juego;

/**
 * La clase EventoCartas se encarga de manejar los eventos de clic del ratón en las cartas del juego.
 * Utiliza la interfaz MouseListener para detectar estos eventos.
 */
public class EventoCartas implements MouseListener {

    // Identificador de la carta por su nombre
    private String nombreCarta;

    /**
     * Constructor de la clase EventoCartas.
     * @param nombreCarta El nombre de la carta asociada al evento.
     */
    public EventoCartas(String nombreCarta) {
        this.nombreCarta = nombreCarta;
    }

    /**
     * Método que se ejecuta cuando se hace clic en una carta.
     * Llama al método realizarAccionPonerCarta de la clase Juego con el nombre de la carta.
     * @param e El evento de clic del ratón.
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        Juego.realizarAccionPonerCarta(nombreCarta);
    }

    /**
     * Método de la interfaz MouseListener que se ejecuta cuando el cursor entra en el área de una carta.
     * No implementado en esta clase.
     * @param e El evento de entrada del ratón.
     */
    @Override
    public void mouseEntered(MouseEvent e) {
        // No se implementa en esta clase
    }

    /**
     * Método de la interfaz MouseListener que se ejecuta cuando el cursor sale del área de una carta.
     * No implementado en esta clase.
     * @param e El evento de salida del ratón.
     */
    @Override
    public void mouseExited(MouseEvent e) {
        // No se implementa en esta clase
    }

    /**
     * Método de la interfaz MouseListener que se ejecuta cuando se presiona un botón del ratón sobre una carta.
     * No implementado en esta clase.
     * @param e El evento de presión del ratón.
     */
    @Override
    public void mousePressed(MouseEvent e) {
        // No se implementa en esta clase
    }

    /**
     * Método de la interfaz MouseListener que se ejecuta cuando se suelta un botón del ratón sobre una carta.
     * No implementado en esta clase.
     * @param e El evento de liberación del ratón.
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        // No se implementa en esta clase
    }
}
