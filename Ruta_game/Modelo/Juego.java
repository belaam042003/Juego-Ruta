package Modelo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.SwingUtilities;

import Conexiones.Cliente;
import Controlador.*;
import Vista.Tablero;

/**
 * La clase Juego representa la lógica del juego. 
 * Contiene métodos para inicializar el juego, realizar acciones de juego, cambiar turnos, 
 * y mantener el estado actual del juego.
 */
public class Juego {
    public static int turnoActual = 0;
    public static List<Carta> mazo;
    private static boolean descartarCartaSeleccionada = false;
    public static List<Jugador> jugadores = new ArrayList<>();
    public static int opcion = 0;
    private static Tablero tablero;
    private static final Map<Integer, Runnable> acciones = new HashMap<>();

    private static Cliente cliente = new Cliente("localhost", 12345, tablero);

    /**
     * Método para configurar las opciones del juego y comenzar el juego.
     */
    public static void opcionesJuego() {
        int input = EventoJugadores.StringNumeroVisual("Ingrese Numero de Jugadores");
        int numJugadores = input;

        if (numJugadores > 0) {
            // Inicializar el mazo y las cartas del juego
            Juego.mazo = new ArrayList<>();
            Cartas_Factory fabricaCartas = new Cartas_Factory();
            Logica_Game.agregarCartasAlMazo(Juego.mazo, fabricaCartas, new Integer[] { 3, 3, 3, 4, 5 }, "Problema");
            Logica_Game.agregarCartasAlMazo(Juego.mazo, fabricaCartas, new Integer[] { 6, 6, 6, 6, 14 }, "Distancia");
            Logica_Game.agregarCartasAlMazo(Juego.mazo, fabricaCartas, new Integer[] { 1, 1, 1, 1 }, "Seguridad");
            Logica_Game.agregarCartasAlMazo(Juego.mazo, fabricaCartas, new Integer[] { 4, 12, 10, 10, 10 }, "Solucion");

            // Inicializar jugadores y repartir cartas iniciales
            inicializarJugadores(jugadores, numJugadores);
            mazoInical(Juego.mazo, jugadores, numJugadores);

            // Mostrar el tablero de juego en el hilo de interfaz de usuario
            SwingUtilities.invokeLater(() -> {
                tablero = new Tablero(jugadores, Juego::realizarAccion);
                tablero.setVisible(true);
                cliente.setTablero(tablero);
            });

            System.out.println("------------------------------------------------------------");
            System.out.println("Bienvenido al Ruta/1000 Millas");
            cliente.iniciar();
            EventoJugadores.StringVisual("Turno del jugador:"+jugadores.get(turnoActual).getId());
        }
    }

    /**
     * Método para inicializar los jugadores del juego.
     * @param jugadores La lista de jugadores del juego.
     * @param numJugadores El número total de jugadores.
     */
    private static void inicializarJugadores(List<Jugador> jugadores, int numJugadores) {
        for (int i = 1; i <= numJugadores; i++) {
            jugadores.add(new Jugador(i));
        }
    }

    /**
     * Método para realizar la acción de poner una carta en el juego.
     * @param nombreCarta El nombre de la carta a poner.
     */
    public static void realizarAccionPonerCarta(String nombreCarta) {
        int idAct = jugadores.get(turnoActual).getId();
        int posicionCarta = obtenerPosicionCartaEnMano(jugadores, idAct, nombreCarta);
        if (posicionCarta != -1) {
            boolean val = Logica_Game_P.ponerCarta(jugadores, idAct, posicionCarta);
            if(val){
                System.out.println(jugadores.get(0).getZona().size() + " Tamaño zona antes de enviar ");
                cliente.enviarJugadoresYAccion(jugadores, opcion);
            }
        } else {
            System.out.println("La carta no se encuentra en la cartas del jugador actual.");
        }
    }
    
    static {
        // Configuración de acciones de juego
        acciones.put(1, () -> {
            int idJugadorPonerCarta = jugadores.get(turnoActual).getId();
            int idCarta = EventoJugadores.StringNumeroVisual("Ingresa el numero de la carta:");
            Logica_Game_P.ponerCarta(jugadores, idJugadorPonerCarta, idCarta);
        });

        acciones.put(2, () -> {
            if (!descartarCartaSeleccionada) {
                int idJugadorDescartarCarta = jugadores.get(turnoActual).getId();
                Logica_Game_P.descartarCarta(jugadores, idJugadorDescartarCarta);
                descartarCartaSeleccionada = true;
            } else {
                cliente.enviarJugadoresYAccion(jugadores, opcion);
                System.out.println("Ya descataste una carta, te toca esperar el otro turno.");
            }
        });

        acciones.put(3, () -> Logica_Game.verMazo(mazo));

        acciones.put(4, () -> {
            System.out.println("Ingresa el ID del jugador para ver su mazo:");
            int idJugador = jugadores.get(turnoActual).getId();
            Logica_Game_P.verManoJugador(jugadores, idJugador);
        });

        acciones.put(5, () -> {
            System.out.println("Ingresa el ID del jugador para ver su zona:");
            int idJugadorVerZona = jugadores.get(turnoActual).getId();
            Logica_Game_P.verZona(jugadores, idJugadorVerZona);
        });

        acciones.put(6, () -> {
            System.out.println("Pasando Turno");
            cambiarTurno(jugadores);
            cliente.enviarJugadoresYAccion(jugadores, opcion);
        });

        acciones.put(7, () -> {
            System.out.println("¡Hasta luego!");
            System.exit(0);
        });
    }

    // Método para realizar la acción correspondiente según la opción seleccionada
    private static void realizarAccion(int opcion) {
        System.out.println("Turno del Jugador " + jugadores.get(turnoActual).getId());
        System.out.println("Selecciona una opción:");

        // Obtener la acción correspondiente de acuerdo a la opción seleccionada
        Runnable accion = acciones.get(opcion);

        if (accion != null) {
            accion.run();
            cliente.enviarJugadoresYAccion(jugadores, opcion);
        } else {
            System.out.println("Opción no válida. Por favor, selecciona de nuevo.");
        }
    }

    /**
     * Método para cambiar al siguiente turno de juego.
     * @param jugadores La lista de jugadores del juego.
     */
    private static void cambiarTurno(List<Jugador> jugadores) {
        turnoActual = (turnoActual + 1) % jugadores.size();
        descartarCartaSeleccionada = false; // Reiniciar la variable al cambiar de turno
    }

    /**
     * Método para repartir cartas iniciales a los jugadores.
     * @param mazo El mazo de cartas del juego.
     * @param jugadores La lista de jugadores del juego.
     * @param numJugadores El número total de jugadores.
     */
    public static void mazoInical(List<Carta> mazo, List<Jugador> jugadores, int numJugadores) {
        for (int i = 0; i < numJugadores; i++) {
            System.out.println("MAZO DEL JUGADOR " + (i + 1) + ": ");
            for (int j = 0; j < 6; j++) {
                Logica_Game_P.sacarCarta(mazo, jugadores, i + 1);
            }
        }
    }

    /**
     * Método para obtener la posición de una carta en la mano de un jugador.
     * @param jugadores La lista de jugadores del juego.
     * @param idJugador El ID del jugador.
     * @param nombreCarta El nombre de la carta.
     * @return La posición de la carta en la mano del jugador, o -1 si la carta no se encuentra.
     */
    public static int obtenerPosicionCartaEnMano(List<Jugador> jugadores, int idJugador, String nombreCarta) {
        for (Jugador jugador : jugadores) {
            if (jugador.getId() == idJugador) {
                List<Carta> mano = jugador.getMano();
                for (int i = 0; i < mano.size(); i++) {
                    if (mano.get(i).getNombre().equals(nombreCarta)) {
                        return i;
                    }
                }
            }
        }
        return -1; // Si la carta no se encuentra en la mano
    }

    /**
     * Método para configurar el cliente de conexión.
     * @param cliente El cliente de conexión.
     */
    public static void setCliente(Cliente cliente) {
        Juego.cliente = cliente;
    }

    /**
     * Método para actualizar la lista de jugadores con información recibida del servidor.
     * @param nuevaListaJugadores La nueva lista de jugadores recibida del servidor.
     */
    public static void actualizarJugadores(List<Jugador> nuevaListaJugadores) {
        // Actualizar la lista de jugadores con la nueva información recibida del servidor
        jugadores.clear();
        jugadores.addAll(nuevaListaJugadores);
        System.out.println("Nueva Lista llegando"); 
        // EventoJugadores.StringVisual("Turno del jugador: " + jugadores.get(turnoActual).getId());
    }

    // Métodos ficticios para obtener la lista de jugadores y el turno actual
    private static List<Jugador> obtenerListaJugadores() {
        // Implementa lógica para obtener la lista de jugadores
        return jugadores;
    }

    private static int obtenerTurnoActual() {
        // Implementa lógica para obtener el turno actual
        return turnoActual;
    }
}
