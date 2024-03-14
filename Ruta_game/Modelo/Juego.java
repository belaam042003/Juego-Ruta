package Modelo;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.SwingUtilities;
import Controlador.*;


public class Juego {
    public static int turnoActual = 0;
    public static List<Carta> mazo;
    private static boolean descartarCartaSeleccionada = false;
    public static List<Jugador> jugadores = new ArrayList<>();
    public static int opcion = 0;
   // private static Juego jueguito;
    private static final Map<Integer, Runnable> acciones = new HashMap<>();

    //private static Cliente cliente =  new Cliente("localhost", 12345);
    
    
    public static void opcionesJuego() {
        int input = EventoJugadores.StringNumeroVisual("Ingrese Numero de Jugadores");
        int numJugadores = input;

        if (numJugadores > 0) {
            // Crear una instancia de JuegoRuta y ejecutar su método main
            Juego.mazo = new ArrayList<>();
            Cartas_Factory fabricaCartas = new Cartas_Factory();
            Logica_Game.agregarCartasAlMazo(Juego.mazo, fabricaCartas, new Integer[] { 3, 3, 3, 4, 5 }, "Problema");
            Logica_Game.agregarCartasAlMazo(Juego.mazo, fabricaCartas, new Integer[] { 6, 6, 6, 6, 14 }, "Distancia");
            Logica_Game.agregarCartasAlMazo(Juego.mazo, fabricaCartas, new Integer[] { 1, 1, 1, 1 }, "Seguridad");
            Logica_Game.agregarCartasAlMazo(Juego.mazo, fabricaCartas, new Integer[] { 4, 12, 10, 10, 10 }, "Solucion");
            inicializarJugadores(jugadores, numJugadores);
            mazoInical(Juego.mazo, jugadores, numJugadores);

            SwingUtilities.invokeLater(() -> {
                //jueguito = new Juego(jugadores, Juego::realizarAccion);
                //jueguito.setVisible(true);
            });

            System.out.println("------------------------------------------------------------");
            System.out.println("Bienvenido al Ruta/1000 Millas");
            //cliente.iniciar();
            
        }
    }

    private static void inicializarJugadores(List<Jugador> jugadores, int numJugadores) {
        for (int i = 1; i <= numJugadores; i++) {
            jugadores.add(new Jugador(i));
        }
    }

    public static void realizarAccionPonerCarta(String nombreCarta) {
        int idAct = jugadores.get(turnoActual).getId();
        int posicionCarta = obtenerPosicionCartaEnMano(jugadores, idAct, nombreCarta);
    
        if (posicionCarta != -1) {
            Logica_Game_P.ponerCarta(jugadores, idAct, posicionCarta);
        } else {
            System.out.println("La carta no se encuentra en la mano del jugador actual.");
        }
    }
    
    static {
        acciones.put(1, () -> {
            int idJugadorPonerCarta = jugadores.get(turnoActual).getId();
            int idCarta = EventoJugadores.StringNumeroVisual("Ingresa el id de la carta:");
            Logica_Game_P.ponerCarta(jugadores, idJugadorPonerCarta, idCarta);
        });

        acciones.put(2, () -> {
            if (!descartarCartaSeleccionada) {
                int idJugadorDescartarCarta = jugadores.get(turnoActual).getId();
                Logica_Game_P.descartarCarta(jugadores, idJugadorDescartarCarta);
                descartarCartaSeleccionada = true;
            } else {
                System.out.println("Ya has descartado una carta en este turno.");
            }
        });

        acciones.put(3, () -> Logica_Game.verMazo(mazo));

        acciones.put(4, () -> {
            System.out.println("Ingresa el ID del jugador para ver su mano:");
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
        } else {
            System.out.println("Opción no válida. Por favor, selecciona de nuevo.");
        }
    }

    private static void cambiarTurno(List<Jugador> jugadores) {
        turnoActual = (turnoActual + 1) % jugadores.size();
        descartarCartaSeleccionada = false; // Reiniciar la variable al cambiar de turno

        // Envía la lista de jugadores al cliente
        //if (cliente != null) {
            //cliente.enviarJugadoresYTurno(jugadores,turnoActual);
       // }
    }
    

    public static void mazoInical(List<Carta> mazo, List<Jugador> jugadores, int numJugadores) {
        for (int i = 0; i < numJugadores; i++) {
            System.out.println("MAZO DEL JUGADOR " + (i + 1) + ": ");
            for (int j = 0; j < 6; j++) {
                Logica_Game_P.sacarCarta(mazo, jugadores, i + 1);
            }
        }
    }
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
    ///public static void setCliente(Cliente cliente) {
       // Juego.cliente = cliente;
    //}

    public static void actualizarJugadores(List<Jugador> objetoRecibido) {
        // Actualizar la lista de jugadores con la nueva información recibida del servidor
        jugadores.clear();
        jugadores.addAll(objetoRecibido);
        System.out.println("Nueva Lista llegando");
    }
    

    // Método ficticio para obtener la lista de jugadores
    private static List<Jugador> obtenerListaJugadores() {
        // Implementa lógica para obtener la lista de jugadores
        return jugadores;
    }

    // Método ficticio para obtener el turno actual
    private static int obtenerTurnoActual() {
        // Implementa lógica para obtener el turno actual
        return turnoActual;
    }
    
}
