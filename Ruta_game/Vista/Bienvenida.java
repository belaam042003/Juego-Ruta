package Vista;
import javax.swing.*;

import Main.Launcher;
import Modelo.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

public class Bienvenida extends JFrame {
    private JButton btnIniciar;

    public Bienvenida() {
        initComponents();
    }

    private void initComponents() {
        // Inicializar componentes
        btnIniciar = new JButton("Iniciar Juego");

        // Agregar ActionListener al botón de inicio
        btnIniciar.addActionListener(e -> Launcher.iniciarJuego());

        // Configurar el diseño de la ventana de inicio
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("¡Bienvenido al juego en donde tú eres el conductor de tu victoria!");


        // Crear un panel con GridBagLayout para controlar el diseño
        JPanel panel = new JPanel(new GridBagLayout());

        // Establecer una imagen de fondo para el panel
        ImageIcon background = new ImageIcon("Imagenes/ruta.png"); // Ajusta la ruta a tu imagen de fondo
        JLabel backgroundLabel = new JLabel(background);
        backgroundLabel.setLayout(new BorderLayout());
        panel.add(backgroundLabel, new GridBagConstraints());

        // Agregar componentes al panel
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10); // Añadir espacio entre los componentes
        panel.add(new JLabel(""), gbc);

        gbc.gridy = 1;

        gbc.gridy = 2;
        panel.add(btnIniciar, gbc);
        // Establecer color de fondo
        panel.setBackground(Color.LIGHT_GRAY);
        // Agregar el panel a la ventana
        add(panel);
        pack();
        
        setSize(new Dimension(600, 700));
        setLocationRelativeTo(null);
    }

    public static  int mostrarStringYObtenerNumero(String mensaje) {
        String input = JOptionPane.showInputDialog(null, mensaje);
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Por favor, ingrese un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
            // Puedes manejar este error de otra manera según tus necesidades
            return mostrarStringYObtenerNumero(mensaje);
        }
    }    
}