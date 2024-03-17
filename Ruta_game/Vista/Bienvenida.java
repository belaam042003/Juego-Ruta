package Vista;

import Main.Launcher;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * La clase Bienvenida representa la ventana de bienvenida del juego.
 * Contiene un mensaje de bienvenida, una imagen, instrucciones y un botón para iniciar el juego.
 */

 public class Bienvenida extends JFrame {
    private JButton btnIniciar; // Botón para iniciar el juego

    /**
     * Constructor de la clase Bienvenida.
     * Inicializa los componentes de la ventana de bienvenida.
     */
    public Bienvenida() {
        initComponents();
    }

    /**
     * Método privado para inicializar los componentes de la ventana de bienvenida.
     */
    private void initComponents() {
        // Configurar la ventana de inicio
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("¡Bienvenido a la Carrera Extrema!");

        // Crear un panel con diseño BoxLayout para organizar los componentes verticalmente
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.BLACK);
        panel.setSize(120, 400);

        // Agregar una etiqueta con la imagen de bienvenida en la parte superior
        ImageIcon imagenBienvenida = new ImageIcon("Imagenes_principales/ruta1.png");
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
        btnIniciar = new JButton("¡Comenzar!");
        btnIniciar.setBackground(Color.RED);
        btnIniciar.setForeground(Color.WHITE);
        btnIniciar.setFont(new Font("Arial", Font.BOLD, 20));
        btnIniciar.setFocusPainted(false);
        btnIniciar.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnIniciar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                instrucciones();
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
     * Método privado para mostrar la ventana de instrucciones.
     */
    private void instrucciones() {
        // Crea y muestra la ventana de instrucciones
        Instrucciones instrucciones = new Instrucciones();
        instrucciones.setVisible(true);
        
        // Cierra la ventana actual
        dispose();
    }

}
