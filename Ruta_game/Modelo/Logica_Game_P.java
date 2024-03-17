package Modelo;

import java.util.Collections;
import java.util.List;
import Controlador.*;

/**
 * Esta clase contiene la lógica del juego.
 */
public class Logica_Game_P {

    /**
     * Este método saca una carta del mazo y la añade a la mano de un jugador específico.
     *
     * @param mazo Lista de cartas que representa el mazo del juego.
     * @param jugadores Lista de jugadores en el juego.
     * @param idJugador Identificador del jugador al que se le sacará la carta.
     */
    public static void sacarCarta(List<Carta> mazo,List<Jugador> jugadores, int idJugador) {
        Jugador jugadorSeleccionado = null;
        for (Jugador jugador : jugadores) {
            if (jugador.getId() == idJugador) {
                jugadorSeleccionado = jugador;
                break;
            }
        }
        if (!mazo.isEmpty()) {
            Collections.shuffle(mazo); // Mezclar el mazo
            Carta carta = mazo.remove(0); // Sacar la primera carta
            System.out.print("Has sacado: ");
            carta.mostrarCartas();
            jugadorSeleccionado.getMano().add(carta);
        } else {
            System.out.println("El mazo está vacío. No hay más cartas para sacar.");
        }
    }

    /**
     * Este método permite a un jugador seleccionar una carta de su mano y colocarla en la zona del juego, siempre que cumpla con ciertas reglas.
     *
     * @param jugadores Lista de jugadores en el juego.
     * @param idJugador Identificador del jugador que realiza la acción.
     * @param idCarta Identificador de la carta que el jugador desea poner en la zona.
     */
    public static void ponerCarta(List<Jugador> jugadores, int idJugador,int idCarta) {
        Jugador jugadorSeleccionado = null;

        // Buscar al jugador por su ID
        for (Jugador jugador : jugadores) {
            if (jugador.getId() == idJugador) {
                jugadorSeleccionado = jugador;
                break;
            }
        }

        if (jugadorSeleccionado != null) {
            // Mostrar la mano del jugador
            System.out.println("Mano del jugador " + jugadorSeleccionado.getId() + ":");
            List<Carta> mano = jugadorSeleccionado.getMano();
            for (int i = 0; i < mano.size(); i++) {
                System.out.println((i ) + ". ");
                mano.get(i).mostrarCartas();
            }

            // Solicitar al jugador que elija una carta para poner en la zona
            System.out.println("Ingresa el ID de la carta que quieres poner en la zona:");
            //int idCarta = EventoJugadores.StringNumeroVisual("Ingresa el id de la carta:");

            if (idCarta >= 0 && idCarta < mano.size()) {
                // Mover la carta de la mano del jugador a la zona
                Carta cartaSeleccionada = mano.get(idCarta );
                
                boolean verificacion = Reglas.VerificarRegla(cartaSeleccionada, jugadores, jugadorSeleccionado);
                if(verificacion){
                    
                    jugadorSeleccionado.getZona().add(cartaSeleccionada);
                    mano.remove(idCarta);
                    System.out.println("Carta puesta en la zona exitosamente.");
                    sacarCarta(Juego.mazo, jugadores, jugadorSeleccionado.getId());

                }else{
                    System.out.println("ERROR de Reglas");
                }

                
            } else {
                System.out.println("ID de carta no válido. Inténtalo de nuevo.");
            }
        } else {
            System.out.println("Jugador no encontrado con el ID proporcionado.");
        }
    }

    /**
     * Este método permite a un jugador descartar una carta de su mano.
     *
     * @param jugadores Lista de jugadores en el juego.
     * @param idJugador Identificador del jugador que realiza la acción.
     */
    public static void descartarCarta(List<Jugador> jugadores, int idJugador) {
        Jugador jugadorSeleccionado = null;
        

        // Buscar al jugador por su ID
        for (Jugador jugador : jugadores) {
            if (jugador.getId() == idJugador) {
                jugadorSeleccionado = jugador;
                break;
            }
        }

        if (jugadorSeleccionado != null) {
            // Mostrar la mano del jugador
            System.out.println("Mano del jugador " + jugadorSeleccionado.getId() + ":");
            List<Carta> mano = jugadorSeleccionado.getMano();
            for (int i = 0; i < mano.size(); i++) {
                System.out.println((i ) + ". ");
                mano.get(i).mostrarCartas();
            }

            
            System.out.println("Ingresa el ID de la carta que quieres descartar:");
            int idCarta = EventoJugadores.StringNumeroVisual("Ingresa el id de la carta descarte:");

            if (idCarta >= 0 && idCarta < mano.size()) {
                
                System.out.println("Se descarto la carta");
                mano.remove(idCarta);
                
                sacarCarta(Juego.mazo, jugadores, jugadorSeleccionado.getId());


                
            } else {
                System.out.println("ID de carta no válido. Inténtalo de nuevo.");
            }
        } else {
            System.out.println("Jugador no encontrado con el ID proporcionado.");
        }
    }

    /**
     * Este método muestra la mano de un jugador específico.
     *
     * @param jugadores Lista de jugadores en el juego.
     * @param idJugador Identificador del jugador cuya mano se va a mostrar.
     */
    public static void verManoJugador(List<Jugador> jugadores, int idJugador) {
        Jugador jugadorSeleccionado = null;

        // Buscar al jugador por su ID
        for (Jugador jugador : jugadores) {
            if (jugador.getId() == idJugador) {
                jugadorSeleccionado = jugador;
                break;
            }
        }

        if (jugadorSeleccionado != null) {
            System.out.println("Mano del jugador " + jugadorSeleccionado.getId() + ":");
            for (Carta carta : jugadorSeleccionado.getMano()) {
                carta.mostrarCartas();
            }
        } else {
            System.out.println("Jugador no encontrado con el ID proporcionado.");
        }
    }

    /**
     * Este método muestra la zona de juego de un jugador específico.
     *
     * @param jugadores Lista de jugadores en el juego.
     * @param idJugador Identificador del jugador cuya zona de juego se va a mostrar.
     */
    public static void verZona(List<Jugador> jugadores, int idJugador) {
        Jugador jugadorSeleccionado = null;

        // Buscar al jugador por su ID
        for (Jugador jugador : jugadores) {
            if (jugador.getId() == idJugador) {
                jugadorSeleccionado = jugador;
                break;
            }
        }
        System.out.println("Total: "+jugadorSeleccionado.getTotal());
        if (jugadorSeleccionado != null) {
            // Mostrar la zona del jugador
            System.out.println("Zona del jugador " + jugadorSeleccionado.getId() + ":");
            List<Carta> zona = jugadorSeleccionado.getZona();
            for (int i = 0; i < zona.size(); i++) {
                System.out.println((i + 1) + ". ");
                zona.get(i).mostrarCartas();
            }
        }
    }
}
