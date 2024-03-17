package Vista;

import javax.swing.BoxLayout;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Main.Launcher;


public class Instrucciones extends JFrame {
    private JButton btnIniciar; // Botón para iniciar el juego

    /**
     * Constructor de la clase Instrucciones.
     * Inicializa los componentes de la ventana de bienvenida.
     */
    public Instrucciones() {
        initComponents();
    }

    /**
     * Método privado para inicializar los componentes de la ventana de bienvenida.
     */
    private void initComponents() {
        // Configurar la ventana de inicio
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Recomendaciones antes de correr");

        // Crear un panel con diseño BoxLayout para organizar los componentes verticalmente
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.BLACK);
        panel.setSize(120, 400);

        // Agregar una etiqueta con la imagen de bienvenida en la parte superior
        ImageIcon imagenBienvenida = new ImageIcon("Imagenes_principales/instrucciones.png");
        JLabel labelImagen = new JLabel(imagenBienvenida);
        labelImagen.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(labelImagen);

        // Agregar un panel para las instrucciones con un diseño personalizado
        JPanel instructionsPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.dispose();
            }
        };
        //instructionsPanel.setPreferredSize(new Dimension(200, 120));
        instructionsPanel.setBackground(Color.BLACK);
        instructionsPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(instructionsPanel);

        // Agregar un botón de inicio con un diseño personalizado
        btnIniciar = new JButton("¡A correr!");
        btnIniciar.setBackground(Color.RED);
        btnIniciar.setForeground(Color.WHITE);
        btnIniciar.setFont(new Font("Arial", Font.BOLD, 20));
        btnIniciar.setFocusPainted(false);
        btnIniciar.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnIniciar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Launcher.iniciarJuego();
            }
        });
        panel.add(Box.createVerticalStrut(5)); // Espacio vertical
        panel.add(btnIniciar);

        // Ajustar el tamaño de la ventana automáticamente
        add(panel);
        pack();

        // Centrar la ventana en la pantalla
        setLocationRelativeTo(null);
    }

    /**
     * Método para mostrar un cuadro de diálogo con un mensaje y obtener un número del usuario.
     *
     * @param mensaje El mensaje que se mostrará al usuario.
     * @return El número ingresado por el usuario.
     */
    public static int mostrarStringYObtenerNumero(String mensaje) {
        String input = JOptionPane.showInputDialog(null, mensaje);
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Por favor, ingrese un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
            return mostrarStringYObtenerNumero(mensaje);
        }
    }
}
