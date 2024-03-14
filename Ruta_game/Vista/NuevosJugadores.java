package Vista;
import javax.swing.*;

import Modelo.*;

import java.awt.*;
import java.util.List;

public class NuevosJugadores extends JPanel {

    public NuevosJugadores(List<Jugador> jugadores) {
        setLayout(new GridLayout(jugadores.size(), 1));
        actualizarInterfaz(jugadores);
    }

    public void actualizarInterfaz(List<Jugador> jugadores) {
        removeAll();

        for (Jugador jugador : jugadores) {
            NuevoJugador jugadorPanel = new NuevoJugador(jugador);
            add(jugadorPanel);
        }

        revalidate();
        repaint();
    }
}
