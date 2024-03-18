package Vista;

import javax.swing.*;
import Modelo.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * La clase NuevosJugadores representa la vista de los jugadores en el juego.
 * Esta clase se utiliza para mostrar la información de varios jugadores en la interfaz gráfica.
 */
public class NuevosJugadores extends JPanel {

    /**
     * Constructor de la clase NuevosJugadores.
     * @param jugadores La lista de jugadores que se van a mostrar en la interfaz.
     */
    public NuevosJugadores(List<Jugador> jugadores) {
        setLayout(new GridLayout(jugadores.size(), 1)); // Establece un diseño de rejilla para organizar los paneles
        actualizarInterfaz(jugadores); // Actualiza la interfaz con la información de los jugadores
    }

    /**
     * Método para actualizar la interfaz con la información de los jugadores.
     * @param jugadores La lista de jugadores que se van a mostrar en la interfaz.
     */

    //private ArrayList <TableroJugador> misjugadores = new ArrayList<>(); 
    
    public void actualizarInterfaz(List<Jugador> jugadores) {
        removeAll(); // Elimina todos los componentes del panel

        for (Jugador jugador : jugadores) { // Itera sobre la lista de jugadores
            TableroJugador jugadorPanel = new TableroJugador(jugador); // Crea un panel para mostrar la información del jugador
            add(jugadorPanel); // Agrega el panel del jugador al panel principal
            jugadorPanel.repaint();
        }

        System.out.println(jugadores.get(0).getZona().size() + " Tamaño de zona ");
        revalidate(); // Vuelve a validar el diseño del panel
        repaint(); // Vuelve a pintar el panel
    }
}
