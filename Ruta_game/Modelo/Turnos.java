package Modelo;

import java.io.Serializable;
import java.util.List;

/**
 * La clase Turnos representa el estado del juego en un determinado momento.
 * Contiene la lista de jugadores y el número del turno actual.
 */
public class Turnos implements Serializable {
    private List<Jugador> jugadores; // Lista de jugadores en el juego
    private int turnoActual; // Número del turno actual

    /**
     * Constructor de la clase Turnos.
     *
     * @param jugadores Lista de jugadores en el juego.
     * @param turnoActual Número del turno actual.
     */
    public void EstadoJuego(List<Jugador> jugadores, int turnoActual) {
        this.jugadores = jugadores;
        this.turnoActual = turnoActual;
    }

    /**
     * Obtiene la lista de jugadores en el juego.
     *
     * @return La lista de jugadores.
     */
    public List<Jugador> getJugadores() {
        return jugadores;
    }

    /**
     * Obtiene el número del turno actual.
     *
     * @return El número del turno actual.
     */
    public int getTurnoActual() {
        return turnoActual;
    }
}
