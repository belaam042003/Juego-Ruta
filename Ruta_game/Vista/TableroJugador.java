package Vista;
import javax.swing.*;
import javax.swing.border.TitledBorder;

import Modelo.Carta;
import Modelo.Jugador;

import java.awt.*;

public class TableroJugador extends JPanel {

    public TableroJugador(Jugador jugador) {
        setLayout(new GridLayout(2, 1)); 
        setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(Color.WHITE), // Borde blanco
            "Turno: " + jugador.getId() + " - Total: " + jugador.getTotal(), // Texto del borde
            TitledBorder.DEFAULT_JUSTIFICATION, 
            TitledBorder.DEFAULT_POSITION, 
            new Font("Arial", Font.BOLD, 20), // Fuente Arial, negrita, tamaño 20
            Color.WHITE)); // Color de texto blanco

        JPanel manoPanel = new JPanel(new FlowLayout());
        manoPanel.setBackground(Color.BLACK); 
        manoPanel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(Color.WHITE), // Borde blanco
            "Cartas que tienes:", 
            TitledBorder.DEFAULT_JUSTIFICATION, 
            TitledBorder.DEFAULT_POSITION, 
            new Font("Arial", Font.BOLD, 20), // Fuente Arial, negrita, tamaño 20
            Color.WHITE)); // Color de texto blanco
        for (Carta carta : jugador.getMano()) { 
            JPanel cartaPanel = new CartasView(carta); 
            manoPanel.add(cartaPanel); 
        }

        JPanel zonaPanel = new JPanel(new FlowLayout());
        zonaPanel.setBackground(Color.BLACK); 
        zonaPanel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(Color.WHITE), // Borde blanco
            "Zona", 
            TitledBorder.DEFAULT_JUSTIFICATION, 
            TitledBorder.DEFAULT_POSITION, 
            new Font("Arial", Font.BOLD, 20), // Fuente Arial, negrita, tamaño 20
            Color.WHITE)); // Color de texto blanco
        for (Carta carta : jugador.getZona()) { 
            JPanel cartaPanel = new CartasView(carta); 
            zonaPanel.add(cartaPanel); 
        }

        setBackground(Color.BLACK); 
        add(manoPanel); 
        add(zonaPanel); 
    }
}
