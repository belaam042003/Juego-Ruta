package Controlador;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import Modelo.Juego;

/**
/Se genera la clase EventoCartas con el atributo:
 *	 nombreCarta - Identificador de la carta con el nombre
 *   Aparte esta clase funciona con la interfaz de 
 *   Mouse Listener ya que de esta forma sera que queremos mover el tablero
*/

public class EventoCartas implements MouseListener {

    private String nombreCarta;

    // Constructor - recibe el nombre de la carta y lo guarda en el respectivo atributo
    public EventoCartas(String nombreCarta) {
        this.nombreCarta = nombreCarta;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // Llamar a Juego.realizarAccionPonerCarta con el nombre de la carta
        Juego.realizarAccionPonerCarta(nombreCarta);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub
    }
}
