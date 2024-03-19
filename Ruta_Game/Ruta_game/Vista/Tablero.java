package Vista;

import javax.swing.*;
import Controlador.*;
import Modelo.*;
import java.awt.*;
import java.util.List;

/**
 * La clase Tablero representa la ventana principal del juego, que contiene la
 * información
 * de los jugadores y los botones de acción.
 */
public class Tablero extends JFrame {

    private List<Jugador> jugadores; // Lista de jugadores en el juego
    public NuevosJugadores jugadores_n; // Panel que muestra la información de los jugadores
    public JButton btnPonerCarta; // Botón para poner una carta
    public JButton btnDescartarCarta; // Botón para descartar una carta
    public JButton btnPasarTurno; // Botón para pasar el turno
    private EventoAccion accionListener; // Listener para los eventos de los botones
    private EventosBotones botonController;

    /**
     * Constructor de la clase Tablero.
     * 
     * @param jugadores      Lista de jugadores en el juego.
     * @param accionListener Listener para los eventos de los botones.
     */
    public Tablero(List<Jugador> jugadores, EventoAccion accionListener) {
        this.jugadores = jugadores;
        this.accionListener = accionListener;
        initComponents(); // Inicializa los componentes de la ventana
        //initTimer(); // Inicializa el temporizador para actualizar la interfaz periódicamente
    }

    /**
     * Método para inicializar los componentes de la ventana.
     */
    private void initComponents() {
        botonController = new EventosBotones(accionListener); // Controlador para los eventos de los botones
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Cierra la aplicación cuando se cierra la ventana
        setTitle("Juego de Mil Millas"); // Establece el título de la ventana

        jugadores_n = new NuevosJugadores(jugadores); // Crea un panel para mostrar la información de los jugadores
        add(jugadores_n); // Agrega el panel de jugadores a la ventana

        // Crear botones
        //btnPonerCarta = new JButton("Jugar Carta");
        //btnPonerCarta.setBackground(Color.RED);
        //btnPonerCarta.setForeground(Color.WHITE);
        //btnPonerCarta.setFont(new Font("Arial", Font.BOLD, 20));
        //btnPonerCarta.setFocusPainted(false);
        //btnPonerCarta.addActionListener(botonController);

        btnDescartarCarta = new JButton("Intercambiar Carta");
        btnDescartarCarta.setBackground(Color.RED);
        btnDescartarCarta.setForeground(Color.WHITE);
        btnDescartarCarta.setFont(new Font("Arial", Font.BOLD, 20));
        btnDescartarCarta.setFocusPainted(false);
        btnDescartarCarta.addActionListener(botonController);

        btnPasarTurno = new JButton("Siguiente Turno");
        btnPasarTurno.setBackground(Color.RED);
        btnPasarTurno.setForeground(Color.WHITE);
        btnPasarTurno.setFont(new Font("Arial", Font.BOLD, 20));
        btnPasarTurno.setFocusPainted(false);
        btnPasarTurno.addActionListener(botonController);

        // Crear un panel para contener los botones y organizarlos horizontalmente
        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20)); // Organización horizontal con espaciado
        //panelBotones.add(btnPonerCarta);
        panelBotones.add(btnDescartarCarta);
        panelBotones.add(btnPasarTurno);

        // Agregar el panel de botones a la ventana en la parte inferior
        add(panelBotones, BorderLayout.SOUTH);

        // Establecer el color de fondo de la ventana
        getContentPane().setBackground(Color.PINK);

        pack(); // Ajusta automáticamente el tamaño de la ventana
        setSize(1200, 500);
        setLocationRelativeTo(null); // Centra la ventana en la pantalla
        setVisible(true); // Hace visible la ventana
    }

    /**
     * Método para inicializar el temporizador que actualiza la interfaz
     * periódicamente.
     */
    
    private void initTimer() {
        Timer timer = new Timer(1000, e -> {
            // Cada segundo, actualiza la interfaz con la información de los jugadores
            jugadores_n.actualizarInterfaz(jugadores);
        });
        timer.start(); // Inicia el temporizador
    }

    public EventosBotones getBotonController() {
        return botonController;
    }

}
