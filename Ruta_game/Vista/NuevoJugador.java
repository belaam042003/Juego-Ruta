package Vista;

import javax.swing.*;
import Modelo.*;
import java.awt.*;

public class NuevoJugador extends JPanel {

    public NuevoJugador(Jugador jugador) {
        setLayout(new GridLayout(2, 1));
        setBorder(BorderFactory.createTitledBorder("ID: " + jugador.getId() + " - Total: " + jugador.getTotal()));

        JPanel manoPanel = new JPanel(new FlowLayout());
        manoPanel.setBorder(BorderFactory.createTitledBorder("Mano"));
        for (Carta carta : jugador.getMano()) {
            JPanel cartaPanel = new CartasView(carta);
            manoPanel.add(cartaPanel);
        }

        JPanel zonaPanel = new JPanel(new FlowLayout());
        zonaPanel.setBorder(BorderFactory.createTitledBorder("Zona"));
        for (Carta carta : jugador.getZona()) {
            JPanel cartaPanel = new CartasView(carta);
            zonaPanel.add(cartaPanel);
        }

        add(manoPanel);
        add(zonaPanel);
    }
}
