package Vista;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import Modelo.Carta;
import Modelo.Jugador;
import java.awt.*;

public class TableroJugador extends JPanel {

    public TableroJugador(Jugador jugador) {
        setLayout(new BorderLayout()); 

        // Crear un JLabel para mostrar el texto del turno sin borde
        JLabel turnoLabel = new JLabel("Turno: " + jugador.getId() + " - Puntuación: " + jugador.getTotal());
        turnoLabel.setForeground(Color.BLACK); // Establecer el color del texto
        turnoLabel.setFont(new Font("Arial", Font.BOLD, 20)); // Establecer la fuente y tamaño del texto
        turnoLabel.setHorizontalAlignment(SwingConstants.CENTER); // Centrar el texto horizontalmente

        // Agregar el JLabel al panel
        add(turnoLabel, BorderLayout.NORTH);

        // Crear un panel para mostrar las cartas en la mano del jugador
        JPanel manoPanel = new JPanel(new FlowLayout(FlowLayout.LEADING, 10, 10));
        manoPanel.setBackground(Color.WHITE); 
        manoPanel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(Color.WHITE), 
            "Tus cartas:", 
            TitledBorder.DEFAULT_JUSTIFICATION, 
            TitledBorder.DEFAULT_POSITION, 
            new Font("Arial", Font.BOLD, 20), 
            Color.BLUE)); 
        for (Carta carta : jugador.getMano()) { 
            JPanel cartaPanel = new CartasView(carta); 
            manoPanel.add(cartaPanel); 
        }

        // Crear un panel para mostrar las cartas en la zona del jugador
        JPanel zonaPanel = new JPanel(new FlowLayout(FlowLayout.LEADING, 10, 10));
        zonaPanel.setBackground(Color.WHITE); 
        zonaPanel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(Color.WHITE), 
            "Zona", 
            TitledBorder.DEFAULT_JUSTIFICATION, 
            TitledBorder.DEFAULT_POSITION, 
            new Font("Arial", Font.BOLD, 20), 
            Color.BLUE)); 
        for (Carta carta : jugador.getZona()) { 
            JPanel cartaPanel = new CartasView(carta); 
            zonaPanel.add(cartaPanel); 
        }

        // Crear un panel para contener los paneles de cartas
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBackground(Color.BLACK);
        contentPanel.add(manoPanel, BorderLayout.WEST);
        contentPanel.add(zonaPanel, BorderLayout.CENTER);

        // Agregar el panel de cartas al panel principal
        add(contentPanel, BorderLayout.CENTER);
    }
}
